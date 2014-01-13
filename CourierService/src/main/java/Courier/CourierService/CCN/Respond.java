package Courier.CourierService.CCN;

import java.io.Serializable;

public class Respond implements Serializable {

	public Request get_request() {
		return _request;
	}
	public void set_request(Request _request) {
		this._request = _request;
	}
	public Serializable get_returnObjet() {
		return _returnObjet;
	}
	public void set_returnObjet(Serializable _returnObjet) {
		this._returnObjet = _returnObjet;
	}
	private Request _request;
	private Serializable _returnObjet;
}
