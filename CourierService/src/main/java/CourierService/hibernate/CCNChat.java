/*
 * A CCNx chat program.
 *
 * Copyright (C) 2008, 2009, 2010, 2011 Palo Alto Research Center, Inc.
 *
 * This work is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License version 2 as published by the
 * Free Software Foundation.
 * This work is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License
 * for more details. You should have received a copy of the GNU General Public
 * License along with this program; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
 * Boston, MA 02110-1301, USA.
 */

package CourierService.hibernate;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;


import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.ccnx.android.ccnlib.JsonMessage.Request;
import org.ccnx.android.ccnlib.RouteRequest;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.io.content.ContentEncodingException;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import Courier.CourierService.Models.Car;
import Courier.CourierService.Models.Manager;
import Courier.CourierService.Models.Route;
import Courier.CourierService.Models.RouteInformation;
import Courier.CourierService.Models.RouteList;
import Courier.CourierService.Models.User;
import Courier.CourierService.Services.RouteService;
import Courier.CourierService.Services.RouteServiceImpl;
import Courier.CourierService.Services.UserService;
import Courier.CourierService.Services.UserServiceImpl;
import CourierService.hibernate.CCNChatNet.CCNChatCallback;

/**
 * Based on a client/server chat example in Robert Sedgewick's Algorithms in
 * Java.
 * 
 * Refactored to be just the JFrame UI.
 */
public class CCNChat extends JFrame implements ActionListener, CCNChatCallback {
	private static final long serialVersionUID = -8779269133035264361L;

	// Chat window
	protected JTextArea _messagePane = new JTextArea(10, 32);
	private JTextField _typedText = new JTextField(32);

	private final CCNChatNet _chat;

	private String respond;

	private String respondString;

	private boolean isRespondProvided;

	public CCNChat(String namespace) throws MalformedContentNameStringException {

		_chat = new CCNChatNet(this, namespace);

		// close output stream - this will cause listen() to stop and exit
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				try {
					stop();
				} catch (IOException e1) {
					System.out.println("IOException shutting down listener: "
							+ e1);
					e1.printStackTrace();
				}
			}
		});

		// Make window
		_messagePane.setEditable(false);
		_messagePane.setBackground(Color.LIGHT_GRAY);
		_messagePane.setLineWrap(true);
		_typedText.addActionListener(this);

		Container content = getContentPane();
		content.add(new JScrollPane(_messagePane), BorderLayout.CENTER);
		content.add(_typedText, BorderLayout.SOUTH);

		// display the window, with focus on typing box
		setTitle("CCNChat 1.2: [" + namespace + "]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		_typedText.requestFocusInWindow();
		setVisible(true);
	}

	/**
	 * Process input to TextField after user hits enter. (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		try {
			String newText = _typedText.getText();
			if ((null != newText) && (newText.length() > 0)) {
				_chat.sendMessage(newText);
			}

		} catch (Exception e1) {
			System.err.println("Exception saving our input: "
					+ e1.getClass().getName() + ": " + e1.getMessage());
			e1.printStackTrace();
			recvMessage("Exception saving our input: "
					+ e1.getClass().getName() + ": " + e1.getMessage());
		}
		_typedText.setText("");
		_typedText.requestFocusInWindow();
	}

	/**
	 * Add a message to the output.
	 * 
	 * @param message
	 */
	public void recvMessage(String message) {
		_messagePane.insert(message, _messagePane.getText().length());
		_messagePane.setCaretPosition(_messagePane.getText().length());

		String messageTmp = message;
		respond = messageTmp;

		if (messageTmp.contains("request\":true")) {
			String answer = "";

			String tmp[] = messageTmp.split("]: ");
			respondString = tmp[1];
			System.out.println("JSON RESPONSE:" + respondString);
			isRespondProvided = true;

			if (messageTmp.contains(Request.LOGIN.toString())) {
				
				ObjectMapper readMap = new ObjectMapper();
				User userLogin = new User();
				try {
					userLogin = readMap.readValue(respondString,
							User.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

				UserService uSer = new UserServiceImpl();

				User user = uSer.login(userLogin.getLogin(), userLogin.getPassword());
				if(user==null){
					user = new User();
					user.setTag("LOGIN_ERROR");
				}
				user.setTag("LOGIN");
				user.setRespond(true);

				ObjectMapper mapper = new ObjectMapper();

				String x = "";
				// mapper.writeValue(System.out, user);
				try {
					x = mapper.writeValueAsString(user);
					System.out.println(x);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				answer = x;

			} else if (messageTmp.contains(Request.GET_DRIVERS.toString())) {

				UserService uSer = new UserServiceImpl();
				Manager manager = new Manager();
				manager = uSer.getAllDrivers();
				manager.setTag(Request.GET_DRIVERS.toString());
				manager.setRespond(true);
				

				ObjectMapper mapper = new ObjectMapper();

				String x = "";
				// mapper.writeValue(System.out, user);
				try {
					x = mapper.writeValueAsString(manager);
					System.out.println(x);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				answer = x;

			} else if (messageTmp.contains(Request.GET_ROUTES.toString())) {

				ObjectMapper readMap = new ObjectMapper();
				RouteList routeList = new RouteList();
				try {
					routeList = readMap.readValue(respondString,
							RouteList.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				RouteService rs = new RouteServiceImpl();
				routeList.setUserList(rs.getRoutesForDriver(routeList.getUserId()));
				routeList.setRespond(true);
				routeList.setRequest(false);
				
				try {
					answer = readMap.writeValueAsString(routeList);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				

			} else if (messageTmp.contains(Request.ADD_ROUTE.toString())) {

				ObjectMapper readMap = new ObjectMapper();
				RouteRequest routeInformation = new RouteRequest();
				try {
					routeInformation = readMap.readValue(respondString,
							RouteRequest.class);
				} catch (JsonParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JsonMappingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				/*UserService uSer = new UserServiceImpl();*/

				/*User user = uSer.login("DriverA", "123");
				Car car = new Car();
				car = user.getCars().iterator().next();
				routeInformation.setCar(car);
				routeInformation.setUser(user);*/

				RouteService rs = new RouteServiceImpl();
				rs.addRoute(routeInformation.getStart(),
						routeInformation.getKoniec(),
						routeInformation.getStartDate(), routeInformation
								.getEndDate(), routeInformation.getDistance(),
						routeInformation.getFuel(), routeInformation.getUserId());
				//rs.addRouteInstance(routeInformation);

				routeInformation.setRespond(true);
				try {
					answer = readMap.writeValueAsString(routeInformation);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			try {
				_chat.sendMessage(answer);
			} catch (ContentEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void usage() {
		System.err.println("usage: CCNChat <ccn URI>");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CCNChat client;
		try {
			client = new CCNChat("/ccnchat");
			client.start();
		} catch (MalformedContentNameStringException e) {
			System.err.println("Not a valid ccn URI: " + args[0] + ": "
					+ e.getMessage());
			e.printStackTrace();
		} catch (ConfigurationException e) {
			System.err.println("Configuration exception running ccnChat: "
					+ e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException handling chat messages: "
					+ e.getMessage());
			e.printStackTrace();
		}
	}

	// =========================================================
	// Internal methods

	/**
	 * Called by window thread when when window closes
	 */
	protected void stop() throws IOException {
		_chat.shutdown();
	}

	/**
	 * This blocks until _chat.shutdown() called
	 * 
	 * @throws IOException
	 * @throws MalformedContentNameStringException
	 * @throws ConfigurationException
	 */
	protected void start() throws ConfigurationException,
			MalformedContentNameStringException, IOException {
		_chat.listen();
	}
}
