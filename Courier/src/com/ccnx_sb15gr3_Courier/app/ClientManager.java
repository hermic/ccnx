package com.ccnx_sb15gr3_Courier.app;

import java.io.Serializable;
import java.util.ArrayList;

import org.ccnx.ccn.io.ErrorStateException;
import org.ccnx.ccn.io.content.ContentGoneException;
import org.ccnx.ccn.io.content.ContentNotReadyException;

import com.ccnx_sb15gr3_Courier.app.chat.ChatCallback;
import com.ccnx_sb15gr3_Courier.app.chat.ChatWorker;

import Courier.CourierService.CCN.CCNGenericObject;
import Courier.CourierService.CCN.Request;
import Courier.CourierService.CCN.Respond;
import CourierModels.Route;
import CourierModels.RouteInformation;
import CourierModels.User;
import android.content.Context;
import android.widget.EditText;

public class ClientManager implements ChatCallback {

	private volatile static ClientManager _managerInstance;
	private ArrayList<Request> _pendingRequests;
	private ClientWorker _worker;
	private String _username;
	private String _namespace;
	private String _remoteport;
	private String _remotehost;
	private Context _context;
	private boolean _isCCNXReady;
	private Respond _respond;
	
	public static ClientManager getInstance()
	{
		if (_managerInstance == null)
		{
			synchronized (ClientWorker.class) 
			{
				if (_managerInstance == null)
				{
					_managerInstance = new ClientManager();
				}
			}
		}
		
		return _managerInstance;
	}
	
	public void Init(String username, String namespace, String remotehost, String remoteport, Context context)
	{
		_username = username;
		_namespace = namespace;
		_remotehost = remotehost;
		_remoteport = remoteport;
		_context = context;
		_pendingRequests = new ArrayList<Request>();
	}
	
	public void RunWorker()
	{
		_worker = new ClientWorker(_context, this);
		_worker.start(_username, _namespace, _remotehost, _remoteport);
	}
	
	public void recv(Serializable message)
	{
		try {
			this._respond = (Respond) ((CCNGenericObject) message).object();
			this._pendingRequests.remove(this._respond.get_request());
		} catch (ContentNotReadyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ContentGoneException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ErrorStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void send(Serializable message)
	{
		boolean isSucced =_worker.send(message);
		if (!isSucced)
		{
			this._pendingRequests.clear();
		}
	}
	
	public void ccnxServices(boolean ok) {
		if ( !ok ) {
			//TODO: proper exception handling.
			recv("CCN Service error!\n");
		}
		else
		{
			this._isCCNXReady = true;
		}
	}
	
	public boolean CheckForCCNX()
	{
		while(!this._isCCNXReady)
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public User login(String name, String password)
	{
		Request request = new Request();
		User user = new User();
		user.setLogin(name);
		user.setPassword(password);
		request.set_nameOfService("UserService");
		request.set_nameOfMethod("Login");
		request.set_object(user);
		
		this.proceedRequest(request);
		
		
		return null;
	}
	
	public void SaveRoute()
	{
		
	}
	
	public Route GetRoute()
	{
		return null;
	}

	public RouteInformation GetRouteInformationForUser(User user)
	{
		return null;		
	}
	
	private Respond proceedRequest(Request request)
	{
		this._pendingRequests.add(request);
		this.send(request);
		while(!_pendingRequests.isEmpty())
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		return this._respond;
	}
}
