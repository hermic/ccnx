package com.ccnx_sb15gr3_Courier.app.chat;

import java.io.Serializable;

public interface ChatCallback {

	public void recv(Serializable message);

	/**
	 * @param ok true -> startup of CCNx services succeeded, false -> network failure
	 */
	public void ccnxServices(boolean ok);
}
