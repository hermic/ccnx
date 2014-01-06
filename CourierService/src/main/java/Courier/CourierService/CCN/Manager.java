package Courier.CourierService.CCN;

import java.io.IOException;
import java.sql.Timestamp;

import org.ccnx.ccn.CCNHandle;
import org.ccnx.ccn.config.ConfigurationException;
import org.ccnx.ccn.config.UserConfiguration;
import org.ccnx.ccn.impl.CCNFlowControl.SaveType;
import org.ccnx.ccn.impl.support.Log;
import org.ccnx.ccn.io.content.CCNStringObject;
import org.ccnx.ccn.profiles.security.KeyProfile;
import org.ccnx.ccn.protocol.ContentName;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;

public class Manager {

	private final ContentName _namespace;
	
	public Manager(String namespace) throws MalformedContentNameStringException
	{
		_namespace = ContentName.fromURI(namespace);
	}

	public void listen() throws ConfigurationException, IOException, MalformedContentNameStringException {		
		if (_namespace.toString().startsWith("ccnx:/")) {
			UserConfiguration.setDefaultNamespacePrefix(_namespace.toString().substring(5));		
		} else {
			UserConfiguration.setDefaultNamespacePrefix(_namespace.toString());
		}

		CCNHandle tempReadHandle = CCNHandle.getHandle();

		// Writing must be on a different handle, to enable us to read back text we have
		// written when nobody else is reading.
		CCNHandle tempWriteHandle = CCNHandle.open();	

//		_readString = new CCNStringObject(_namespace, (String)null, SaveType.RAW, tempReadHandle);
//		_readString.updateInBackground(true); 
//		
//		String introduction = UserConfiguration.userName() + " has entered " + _namespace;
//		_writeString = new CCNStringObject(_namespace, introduction, SaveType.RAW, tempWriteHandle);
//		_writeString.save();
//
//		// Publish the user's friendly name under a new ContentName
//		String friendlyNameNamespaceStr = _namespaceStr + "/members/";  
//		_friendlyNameNamespace = KeyProfile.keyName(ContentName.fromURI(friendlyNameNamespaceStr), _writeString.getContentPublisher());
//		Log.info("**** Friendly Namespace is " + _friendlyNameNamespace);
//		
//		//read the string here.....	
//		_readNameString = new CCNStringObject(_friendlyNameNamespace, (String)null, SaveType.RAW, tempReadHandle);
//		_readNameString.updateInBackground(true);
//		
//		String publishedNameStr = UserConfiguration.userName();
//		Log.info("*****I am adding my own friendly name as " + publishedNameStr);
//		_writeNameString = new CCNStringObject(_friendlyNameNamespace, publishedNameStr, SaveType.RAW, tempWriteHandle);
//		_writeNameString.save();
//		
//		
//		try {
//			addNameToHash(_writeNameString.getContentPublisher(), _writeNameString.string());
//		}
//		catch (IOException e) {
//			System.err.println("Unable to read from " + _writeNameString + "for writing to hashMap");
//			e.printStackTrace();
//		}
//		
//		
//		// Need to do synchronization for updates that come in while we're processing last one.
//		
//		while (!_finished) {
//			try {
//				synchronized(_readString) {
//					_readString.wait(CYCLE_TIME);
//				}
//			} catch (InterruptedException e) {
//			}
//			
//			if (_readString.isSaved()) {
//				Timestamp thisUpdate = _readString.getVersion();
//				if ((null == _lastUpdate) || thisUpdate.after(_lastUpdate)) {
//					Log.info("Got an update: " + _readString.getVersion());
//					_lastUpdate = thisUpdate;	
//					
//					//lookup friendly name to display for this user.....
//					String userFriendlyName = getFriendlyName(_readString.getContentPublisher());
//								
//						if (userFriendlyName.equals("")) {
//						
//							// Its not in the hashMap.. So, try and read the user's friendly name from the ContentName and then add it to the hashMap....
//							String userNameStr = _namespaceStr + "/members/";  
//							_friendlyNameNamespace = KeyProfile.keyName(ContentName.fromURI(userNameStr), _readString.getContentPublisher());
//													
//							try {
//								_readNameString = new CCNStringObject(_friendlyNameNamespace, (String)null, SaveType.RAW, tempReadHandle);
//							} catch (Exception e) {
//						
//							}
//						
//							_readNameString.update(WAIT_TIME_FOR_FRIENDLY_NAME); // for now, I am just waiting for 2.5 secs.. Otherwise, I might have to update in background and have a callback
//												
//							if (_readNameString.available()) {
//							 
//								if (! _readString.getContentPublisher().equals(_readNameString.getContentPublisher())) {
//									showMessage(_readString.getContentPublisher(), _readString.getPublisherKeyLocator(), thisUpdate, _readString.string());						 
//								} else { 											 
//									addNameToHash(_readNameString.getContentPublisher(), _readNameString.string());
//									showMessage(_readNameString.string(), thisUpdate, _readString.string());
//								}
//							} else {
//								showMessage(_readString.getContentPublisher(), _readString.getPublisherKeyLocator(), thisUpdate, _readString.string());
//							}
//						 
//						} else {
//							showMessage(userFriendlyName, thisUpdate, _readString.string());	
//						}				
//				}	
//			}
//		}
	}
}
