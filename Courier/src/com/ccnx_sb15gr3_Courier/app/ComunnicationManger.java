package com.ccnx_sb15gr3_Courier.app;

import java.io.Serializable;
import java.util.ArrayList;

import Courier.CourierService.CCN.Request;
import Courier.CourierService.CCN.Respond;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.ccnx_sb15gr3_Courier.app.chat.ChatCallback;
import com.ccnx_sb15gr3_Courier.app.chat.ChatWorker;

public class ComunnicationManger implements ChatCallback {
	
	private volatile static ComunnicationManger _managerInstance;
	private ArrayList<Request> _pendingRequests;
	
	private String _username;
	private String _namespace;
	private String _remoteport;
	private String _remotehost;
	private Context _context;
	private boolean _isCCNXReady;
	private Respond _respond;
	
	public static ComunnicationManger getInstance()
	{
		if (_managerInstance == null)
		{
			synchronized (ClientWorker.class) 
			{
				if (_managerInstance == null)
				{
					_managerInstance = new ComunnicationManger ();
				}
			}
		}
		
		return _managerInstance;
	}

	protected ChatWorker _worker;
	protected TextView tv = null;
	protected ScrollView sv = null;

	/**
	 * In the UI thread, post a message to the screen
	 */
	protected void ScreenOutput(String s) {
		tv.append(s);
		tv.invalidate();

		// Now scroll to the bottom
		sv.post(new Runnable() {
			public void run() {
				sv.fullScroll(ScrollView.FOCUS_DOWN);
			}

		});
	}

	/**
	 * this will be called when we receive a chat line.
	 * It is executed in the UI thread.
	 */
	private Handler _handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			String text = (String) msg.obj;
			ScreenOutput(text);
			msg.obj = null;
			msg = null;
		}
	};

	/**
	 * Send a message to the network
	 */
	protected void UiToCcn(String s) {
		_worker.send(s);
	}

	/**
	 * From ChatCallback, when we get a message from CCN
	 */
	public void recv(String message) {
		Message msg = Message.obtain();
		msg.obj = message;
		_handler.sendMessage(msg);		
	}

	/**
	 * Called by ChatWorker based on CCNx service state
	 */
	@Override
	public void ccnxServices(boolean ok) {
		if ( ok ) {
			recv("CCN Services now ready -- let's chat!\n");
			
			// activate the edit text by sending a Runnable to
			// the UI handler.
			_handler.post(
					new Runnable() {
						@Override
						public void run() {
						//	EditText etPost = (EditText) findViewById(R.id.etPost);
						//	etPost.setEnabled(true);
						}
					}
			);
		} else {
			recv("CCN Service error, cannot chat!\n");
		}
	}

	@Override
	public void recv(Serializable message) {
		// TODO Auto-generated method stub
		
	}

}
