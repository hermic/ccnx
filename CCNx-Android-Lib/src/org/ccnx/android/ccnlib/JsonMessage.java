package org.ccnx.android.ccnlib;

import java.util.UUID;

public class JsonMessage {
	
	public String uuid = UUID.randomUUID().toString();
	
	public enum Request{LOGIN,GET_DRIVERS,GET_ROUTES,ADD_ROUTE};
	
	private boolean isRequest;
	private boolean isRespond;
	private String Tag;
	private String className;
	public boolean isRequest() {
		return isRequest;
	}
	public void setRequest(boolean isRequest) {
		this.isRequest = isRequest;
	}
	public boolean isRespond() {
		return isRespond;
	}
	public void setRespond(boolean isRespond) {
		this.isRespond = isRespond;
	}
	public String getTag() {
		return Tag;
	}
	public void setTag(String tag) {
		Tag = tag;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}

}