package CourierModels;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.Timestamp;

import Courier.CourierService.CCN.CCNGenericObject;
import Courier.CourierService.CCN.CCNObjectCallBack;
import Courier.CourierService.CCN.CCNRouteObject;
import Courier.CourierService.CCN.Request;
import Courier.CourierService.CCN.Respond;
import Courier.CourierService.Helpers.*;

import org.ccnx.ccn.CCNHandle;
import org.ccnx.ccn.apps.ccnchat.CCNChatNet.CCNChatCallback;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.config.UserConfiguration;
import org.ccnx.ccn.impl.CCNFlowControl.SaveType;
import org.ccnx.ccn.impl.support.Log;
import org.ccnx.ccn.io.CCNInputStream;
import org.ccnx.ccn.io.CCNOutputStream;
import org.ccnx.ccn.io.content.CCNNetworkObject;
import org.ccnx.ccn.io.content.CCNStringObject;
import org.ccnx.ccn.io.content.ContentDecodingException;
import org.ccnx.ccn.io.content.ContentEncodingException;
import org.ccnx.ccn.profiles.security.KeyProfile;
import org.ccnx.ccn.protocol.ContentName;
import org.ccnx.ccn.protocol.ContentObject;
import org.ccnx.ccn.protocol.KeyLocator;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;
import org.ccnx.ccn.protocol.PublisherPublicKeyDigest;

import com.sun.org.apache.bcel.internal.generic.Type;

public class Manager {

	private Timestamp _lastUpdate;
	private static final long CYCLE_TIME = 1000;
	private CCNObjectCallBack _callback;
	private boolean _finished = false;
	private final ContentName _namespace;
	private CCNGenericObject _readString;
	private CCNGenericObject _writeObject;
	private ContentName _friendlyNameNamespace;
	private CCNGenericObject _readNameString;
	
	public Manager(CCNObjectCallBack callback, String namespace) throws MalformedContentNameStringException
	{
		_namespace = ContentName.fromURI(namespace);
		_callback = callback;
	}
	
	public void listen() throws ConfigurationException, IOException, MalformedContentNameStringException {		
		if (_namespace.toString().startsWith("ccnx:/")) {
			UserConfiguration.setDefaultNamespacePrefix(_namespace.toString().substring(5));		
		} else {
			UserConfiguration.setDefaultNamespacePrefix(_namespace.toString());
		}

		CCNHandle tempReadHandle = CCNHandle.getHandle();
		CCNHandle tempWriteHandle = CCNHandle.open();	
		_readString = new CCNGenericObject(Serializable.class, false, _namespace, (Serializable)null, SaveType.RAW, tempReadHandle);
		_readString.updateInBackground(true); 		

		_writeObject = new CCNGenericObject(Serializable.class, false, _namespace, (Serializable)null, SaveType.RAW, tempWriteHandle);
		
		while (!_finished) {
			try {
				synchronized(_readString) {
					_readString.wait(CYCLE_TIME);
				}
			} catch (InterruptedException e) {
			}
			
			if (_readString.isSaved()) {
				Timestamp thisUpdate = _readString.getVersion();
				if ((null == _lastUpdate) || thisUpdate.after(_lastUpdate)) {
					Log.info("Got an update: " + _readString.getVersion());
					_lastUpdate = thisUpdate;	
					String userFriendlyName = _readString.getContentPublisher().toString();		
						if (userFriendlyName.equals("")) {
							String userNameStr = _namespace + "/members/";  
							_friendlyNameNamespace = KeyProfile.keyName(ContentName.fromURI(userNameStr), _readString.getContentPublisher());
													
							try {
								//_readNameString = new CCNGenericObject(Serializable.class, false, _namespace, rout, SaveType.RAW, tempReadHandle);
							} catch (Exception e) {
						
							}
						
							_readNameString.update(2500); // for now, I am just waiting for 2.5 secs.. Otherwise, I might have to update in background and have a callback
												
							if (_readNameString.available()) {
							 
								if (! _readString.getContentPublisher().equals(_readNameString.getContentPublisher())) {
									//showMessage(_readString.getContentPublisher(), _readString.getPublisherKeyLocator(), thisUpdate, _readString.string());						 
								}
							} else {
								showMessage(_readString.getContentPublisher(), _readString.getPublisherKeyLocator(), thisUpdate, (Serializable) _readString);
							}
						 
						} else {
							System.out.println("here else last");
							showMessage(userFriendlyName, thisUpdate, (Serializable) _readString);	
						}				
				}	
			}
		}
	}

	private void showMessage(PublisherPublicKeyDigest publisher, KeyLocator keyLocator, Timestamp time, Serializable message) {
		_callback.recvObject(message);
		
	}
	private void showMessage(String sender, Timestamp time, Serializable message) {
		_callback.recvObject(message);
 	}
	
    /**
     * Turn off everything.
     * @throws IOException 
     */
	public void shutdown() throws IOException {
		_finished = true;
		if (null != _readString) {
			_readString.cancelInterest();
		}
	}
	
	public synchronized void sendMessage(Serializable message) throws ContentEncodingException, IOException {
		boolean succcess = _writeObject.save(message);
		System.out.println(succcess);
	}
}
