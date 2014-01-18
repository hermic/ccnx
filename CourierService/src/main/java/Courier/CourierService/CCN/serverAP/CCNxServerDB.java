package Courier.CourierService.CCN.serverAP;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneLayout;

import org.ccnx.android.ccnlib.RouteRequest;
import org.ccnx.android.ccnlib.JsonMessage.Request;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.io.content.ContentEncodingException;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;

import Courier.CourierService.Models.Manager;
import Courier.CourierService.Models.RouteList;
import Courier.CourierService.Models.User;
import Courier.CourierService.Services.RouteService;
import Courier.CourierService.Services.RouteServiceImpl;
import Courier.CourierService.Services.UserService;
import Courier.CourierService.Services.UserServiceImpl;
import CourierService.hibernate.CCNChatNet;
import CourierService.hibernate.CCNChatNet.CCNChatCallback;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CCNxServerDB extends JFrame implements CCNChatCallback {
	private static final long serialVersionUID = -8779269133035264361L;

	// Chat window
	protected JTextArea _messagePane = new JTextArea(10, 32);
	private JTextField _typedText = new JTextField(32);

	private final CCNChatNet _chat;

	private String respond;

	private String requestAndroid;

	private boolean isRespondProvided;

	public CCNxServerDB(String namespace)
			throws MalformedContentNameStringException {

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
		
		
		setTitle("Database Server" + "[" + namespace + "]");
		setBounds(100, 100, 200, 800);

		Container content = getContentPane();
	
		content.add(new JScrollPane(_messagePane), BorderLayout.CENTER);
	

		// display the window, with focus on typing box
		setTitle("DB Server [" + namespace + "]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		_typedText.requestFocusInWindow();
		setVisible(true);
	}

	
	/**
	 * Add a message to the output.
	 * 
	 * @param message
	 */
	public void recvMessage(String message) {
		

		String messageTmp = message;
		//respond = messageTmp;

		if (messageTmp.contains("request\":true")) {
			_messagePane.insert("[ "+DATE_FORMAT.format(Calendar.getInstance().getTime())+" ]"+ "[--REQUEST--]:"+ message+"\n", _messagePane.getText().length());
			_messagePane.setCaretPosition(_messagePane.getText().length());
			String dbRespond = "";
			if(message.contains("]:")){
				String tmp[] = messageTmp.split("]: ");	
			requestAndroid=tmp[1];
			}else{
				requestAndroid = message;
			}
			
			
			
			System.out.println("JSON RESPONSE:" + message);
			isRespondProvided = true;

			if (messageTmp.contains(Request.LOGIN.toString())) {

				ObjectMapper readMap = new ObjectMapper();
				User userLogin = new User();
				try {
					userLogin = readMap.readValue(requestAndroid, User.class);
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

				User user = uSer.login(userLogin.getLogin(),
						userLogin.getPassword());
				if (user == null) {
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

				dbRespond = x;

			} else if (messageTmp.contains(Request.GET_DRIVERS.toString())) {

				UserService uSer = new UserServiceImpl();
				Manager manager = new Manager();
				manager = uSer.getAllDrivers();
				manager.setTag(Request.GET_DRIVERS.toString());
				manager.setRespond(true);

				ObjectMapper mapper = new ObjectMapper();

			
				// mapper.writeValue(System.out, user);
				try {
					dbRespond  = mapper.writeValueAsString(manager);
					System.out.println(dbRespond);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();

				}

				

			} else if (messageTmp.contains(Request.GET_ROUTES.toString())) {

				ObjectMapper readMap = new ObjectMapper();
				RouteList routeList = new RouteList();
				try {
					routeList = readMap.readValue(requestAndroid,
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
				routeList.setUserList(rs.getRoutesForDriver(routeList
						.getUserId()));
				routeList.setRespond(true);
				routeList.setRequest(false);

				try {
					dbRespond = readMap.writeValueAsString(routeList);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (messageTmp.contains(Request.ADD_ROUTE.toString())) {

				ObjectMapper readMap = new ObjectMapper();
				RouteRequest routeInformation = new RouteRequest();
				try {
					routeInformation = readMap.readValue(requestAndroid,
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

				

				RouteService rs = new RouteServiceImpl();
				rs.addRoute(routeInformation.getStart(),
						routeInformation.getKoniec(),
						routeInformation.getStartDate(),
						routeInformation.getEndDate(),
						routeInformation.getDistance(),
						routeInformation.getFuel(),
						routeInformation.getUserId());
				

				routeInformation.setRespond(true);
				try {
					dbRespond = readMap.writeValueAsString(routeInformation);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			try {
				_chat.sendMessage(dbRespond);
			} catch (ContentEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else{
			
			_messagePane.insert("[ "+DATE_FORMAT.format(Calendar.getInstance().getTime())+" ]"+ "[--RESPONSE--]:"+ message+"\n", _messagePane.getText().length());
			_messagePane.setCaretPosition(_messagePane.getText().length());
			
		}
	}

	private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("HH:mm");
	public static void usage() {
		System.err.println("usage: CCNChat <ccn URI>");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		CCNxServerDB client;
		try {
			client = new CCNxServerDB("/ccnchat");
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

