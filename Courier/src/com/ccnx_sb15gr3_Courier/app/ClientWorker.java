package com.ccnx_sb15gr3_Courier.app;

import java.io.IOException;
import java.io.Serializable;

import org.ccnx.android.ccnlib.CCNxConfiguration;
import org.ccnx.android.ccnlib.CCNxServiceCallback;
import org.ccnx.android.ccnlib.CCNxServiceControl;
import org.ccnx.android.ccnlib.CCNxServiceStatus.SERVICE_STATUS;
import org.ccnx.android.ccnlib.CcndWrapper.CCND_OPTIONS;
import org.ccnx.android.ccnlib.RepoWrapper.REPO_OPTIONS;
import org.ccnx.ccn.apps.ccnchat.CCNChatNet;
import org.ccnx.ccn.apps.ccnchat.CCNChatNet.CCNChatCallback;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.profiles.ccnd.CCNDaemonException;
import org.ccnx.ccn.profiles.ccnd.SimpleFaceControl;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;

import Courier.CourierService.CCN.CCNObjectCallBack;
import CourierModels.CCNManager;
import CourierModels.Manager;
import android.content.Context;
import android.util.Log;



/**
 * All the CCNx code for Chat is in this worker thread.  It's basically the code
 * from the original ccnChat wrapped inside the worker thread.
 */
public class ClientWorker implements Runnable, CCNxServiceCallback, CCNObjectCallBack {
	protected final static String TAG="ChatWorker";

	/**
	 * Create a worker thread to handle all the CCNx calls.
	 * 
	 * @param ctx The UI context, needed to start/stop services
	 * @param callback The UI callback when we receive a chat message or a CCNx service status
	 */
	public ClientWorker(Context ctx, ClientManager callback) {
		_context = ctx;
		_thd = new Thread(this, "ClientWorker");
		_callback = callback;

		// Use a shared key directory
		CCNxConfiguration.config(ctx, false);
	}

	/**
	 * Start the worker thread, along with CCN services
	 * @param username Your "handle" on the Chat
	 * @param namespace The chat ccnx:/ namespace
	 * @throws MalformedContentNameStringException 
	 */
	public synchronized void start(String username, String namespace, String remotehost, String remoteport) {
		if( false == _running ) {
			try {
				_remotehost = remotehost;
				_remoteport = remoteport;
				_manager = new Manager(this, namespace);
				_running = true;
				_finished = false;
				_thd.start();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Exit the worker thread, but keep services running
	 */
	public synchronized void stop() {
		if( !_finished ) {
			_finished = true;
			try {
				_manager.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			}
			_ccnxService.disconnect();
		}
	}

	/**
	 * Exit the worker thread and shutdown services
	 */
	public synchronized void shutdown() {
		if( !_finished ) {
			_finished = true;
			try {
				_manager.shutdown();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				_ccnxService.stopAll();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Sent a message to the network
	 * @param text
	 * @return true if sent, false if some CCN error
	 */
	public boolean send(Serializable text) {

		try {
			_manager.sendMessage(text);
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	/**
	 * Runnable method
	 */
	@Override
	public void run() {
		service_run();
	}

	// ==============================================================================
	// Internal implementation

	protected Manager _manager;
	protected final ClientManager _callback;
	protected final Context _context;
	protected CCNxServiceControl _ccnxService;

	protected final Thread _thd;
	protected boolean _running = false;
	protected boolean _finished = true;
	
	protected String _remotehost = null;
	protected String _remoteport = "9695";

	/*********************************************/
	// These are all run in the CCN thread

	/**
	 * @param args
	 */
	protected void service_run() {

		// Startup CCNx in a blocking call
		if( !initializeCCNx() ) {
			Log.e(TAG, "Could not start CCNx services!");
		} else {
			Log.i(TAG,"Starting ccnChatNet.listen() loop");
			
			// Now do the Chat event loop
			try {
				_manager.listen();
			} catch (ConfigurationException e) {
				System.err.println("Configuration exception running ccnChat: "
						+ e.getMessage());
				e.printStackTrace();
			} catch (IOException e) {
				System.err.println("IOException handling chat messages: "
						+ e.getMessage());
				e.printStackTrace();
			} catch(Exception e) {
				System.err.println("Exception handling chat messages: "
						+ e.getMessage());
				e.printStackTrace();	
			}
		}

		Log.i(TAG, "service_run() exits");
	}

	private boolean initializeCCNx() {
		_ccnxService = new CCNxServiceControl(_context);
		_ccnxService.registerCallback(this);
		_ccnxService.setCcndOption(CCND_OPTIONS.CCND_DEBUG, "1");
		_ccnxService.setRepoOption(REPO_OPTIONS.REPO_DEBUG, "WARNING");
		return _ccnxService.startAll();
	}

	/**
	 * Called from CCNxServiceControl
	 */
	@Override
	public void newCCNxStatus(SERVICE_STATUS st) {
		// NOw pass on the status to the app
		if( null != _callback ) {
			switch(st) {
			case START_ALL_DONE:
				try {
					// If we specified a remote host, use it not multicast
					if( null != _remotehost && _remotehost.length() > 0 ) {
						SimpleFaceControl.getInstance().connectTcp(_remotehost, Integer.parseInt(_remoteport));
					} else {
						SimpleFaceControl.getInstance().openMulicastInterface();
					}
					_callback.ccnxServices(true);
				} catch (CCNDaemonException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					_callback.ccnxServices(false);
				}

				break;
			case START_ALL_ERROR:
				_callback.ccnxServices(false);
				break;
			}
		}	
	}

	/**
	 * called from ccnChatNet when there's a new message.
	 * Pass it on to the UI.
	 */
//	@Override
//	public void recvMessage(String message) {
//		Log.d(TAG, "recv text = " + message);
//		_chatCallback.recv(message);
//	}

	@Override
	public void recvObject(Serializable msg) {
		_callback.recv(msg);
		
	}
}

