package Courier.CourierService.CCN;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;


import org.ccnx.ccn.apps.ccnchat.CCNChatNet;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.io.ErrorStateException;
import org.ccnx.ccn.io.content.ContentEncodingException;
import org.ccnx.ccn.io.content.ContentGoneException;
import org.ccnx.ccn.io.content.ContentNotReadyException;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;

import Courier.CourierModels.CCNRequest;
import CourierModels.Manager;
import CourierModels.User;



public class ServiceWorker implements Runnable, CCNObjectCallBack {

	protected final Thread _thd;
	protected Manager _manager;	
	
	public ServiceWorker() throws MalformedContentNameStringException
	{
		_manager = new Manager(this, "ccnx:/courier");
		_thd = new Thread(this, "ServiceWorker");
	}
	
	public void run() {
		
	}
	
	public void start() throws ConfigurationException, MalformedContentNameStringException, IOException {
		_thd.start();
		_manager.listen();
	}

	public void recvObject(Serializable msg) {
		CCNGenericObject req = (CCNGenericObject) msg;
		Request request;
		try {
			try
			{
				request = (Request) req.object();
			}
			catch(ClassCastException ex)
			{
				return;
			}
			
			if (request != null)
			{
				User user = new User();
				user.setLogin("Jacek");
				user.setEmail("aaa@wp.pl");
				Respond respond = new Respond();
				respond.set_request(request);
				respond.set_returnObjet(user);
				try {
					sendObject(respond);
				} catch (ContentEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (ContentNotReadyException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ContentGoneException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ErrorStateException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void sendObject(Serializable obj) throws ContentEncodingException, IOException
	{
		_manager.sendMessage(obj);
	}
}
