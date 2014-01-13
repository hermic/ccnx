package CourierModels;

import org.ccnx.ccn.apps.ccnchat.CCNChatNet.CCNChatCallback;
import org.ccnx.ccn.protocol.ContentName;
import org.ccnx.ccn.protocol.MalformedContentNameStringException;

public class CCNManager {
	private ContentName _namespace;

	public CCNManager(CCNChatCallback callback, String namespace) throws MalformedContentNameStringException
	{
		_namespace = ContentName.fromURI(namespace);
	}
}
