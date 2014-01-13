package Courier.CourierService.CCN;

import Courier.CourierService.*;
import Courier.CourierService.Helpers.ArraySerializer;
import CourierModels.Route;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.ccnx.ccn.CCNHandle;
import org.ccnx.ccn.impl.CCNFlowControl;
import org.ccnx.ccn.impl.CCNFlowControl.SaveType;
import org.ccnx.ccn.impl.support.DataUtils;
import org.ccnx.ccn.io.ErrorStateException;
import org.ccnx.ccn.io.content.CCNNetworkObject;
import org.ccnx.ccn.io.content.ContentDecodingException;
import org.ccnx.ccn.io.content.ContentEncodingException;
import org.ccnx.ccn.io.content.ContentGoneException;
import org.ccnx.ccn.io.content.ContentNotReadyException;
import org.ccnx.ccn.protocol.ContentName;
import org.ccnx.ccn.protocol.ContentObject;
import org.ccnx.ccn.protocol.KeyLocator;
import org.ccnx.ccn.protocol.PublisherPublicKeyDigest;

public class CCNRouteObject extends CCNNetworkObject<Route> {

	public CCNRouteObject(Class<Route> type, boolean contentIsMutable, ContentName name, Route data, SaveType saveType, CCNHandle handle)
			throws IOException {
		super(type, contentIsMutable, name, data, saveType, handle);
	}
	
	public CCNRouteObject(ContentName name, CCNHandle handle)
throws ContentDecodingException, IOException {
super(Route.class, false, name, (PublisherPublicKeyDigest)null, handle);
	}

	@Override
	protected Route readObjectImpl(InputStream arg0)
			throws ContentDecodingException, IOException {
		
		byte [] contentBytes = DataUtils.getBytesFromStream(arg0);
		return (Route)ArraySerializer.read(contentBytes);
	}

	@Override
	protected void writeObjectImpl(OutputStream arg0)
			throws ContentEncodingException, IOException {
		if (null == data())
			throw new ContentNotReadyException("No content available to save for object " + getBaseName());
			byte [] routeData = ArraySerializer.write(data());
			arg0.write(routeData);
		
	}
	
	public Route route() throws ContentNotReadyException, ContentGoneException, ErrorStateException { return data(); }

}
