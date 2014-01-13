package Courier.CourierService.CCN;

import java.io.Serializable;
import java.util.List;

public class Request  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Serializable _object;
	public Serializable get_object() {
		return _object;
	}
	public void set_object(Serializable _object) {
		this._object = _object;
	}
	private String _nameOfService;
	private String _nameOfMethod;
	private boolean completed;
	public boolean isCompleted() {
		return completed;
	}
	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
	public String get_nameOfMethod() {
		return _nameOfMethod;
	}
	public void set_nameOfMethod(String _nameOfMethod) {
		this._nameOfMethod = _nameOfMethod;
	}
	private List<String> _parameters;
	
	public String get_nameOfService() {
		return _nameOfService;
	}
	public void set_nameOfService(String _nameOfService) {
		this._nameOfService = _nameOfService;
	}
	public List<String> get_parameter() {
		return _parameters;
	}
	public void set_parameter(List<String> _parameters) {
		this._parameters = _parameters;
	}

}
