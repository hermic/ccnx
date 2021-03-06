package Courier.CourierService.CCN;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.ccnx.ccn.CCNHandle;
import org.ccnx.ccn.impl.CCNFlowControl.SaveType;
import org.ccnx.ccn.impl.support.DataUtils;
import org.ccnx.ccn.io.ErrorStateException;
import org.ccnx.ccn.io.content.CCNNetworkObject;
import org.ccnx.ccn.io.content.ContentDecodingException;
import org.ccnx.ccn.io.content.ContentEncodingException;
import org.ccnx.ccn.io.content.ContentGoneException;
import org.ccnx.ccn.io.content.ContentNotReadyException;
import org.ccnx.ccn.protocol.ContentName;
import org.ccnx.ccn.protocol.PublisherPublicKeyDigest;

import Courier.CourierService.Helpers.ArraySerializer;

public class CCNResponseObject extends CCNNetworkObject<Respond> implements Serializable {
	private static final long serialVersionUID = 1L;

	public CCNResponseObject(Class<Respond> type, boolean contentIsMutable, ContentName name, Respond data, SaveType saveType, CCNHandle handle)
			throws IOException {
		super(type, contentIsMutable, name, data, saveType, handle);
	}
	
	public CCNResponseObject(ContentName name, CCNHandle handle)
throws ContentDecodingException, IOException {
super(Respond.class, false, name, (PublisherPublicKeyDigest)null, handle);
	}

	@Override
	protected Respond readObjectImpl(InputStream arg0)
			throws ContentDecodingException, IOException {
		
		byte [] contentBytes = DataUtils.getBytesFromStream(arg0);
		return (Respond)ArraySerializer.read(contentBytes);
	}

	@Override
	protected void writeObjectImpl(OutputStream arg0)
			throws ContentEncodingException, IOException {
		if (null == data())
			throw new ContentNotReadyException("No content available to save for object " + getBaseName());
			byte [] routeData = ArraySerializer.write(data());
			arg0.write(routeData);
		
	}
	
	public Respond object() throws ContentNotReadyException, ContentGoneException, ErrorStateException { return data(); }
}
