package CourierModels;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="User")
public class User implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private int UserId;
	
	@Column(name="Login")
	private String Login;
	
	public int getUserId() {
		return UserId;
	}

	public void setUserId(int userId) {
		UserId = userId;
	}

	public String getLogin() {
		return Login;
	}

	public void setLogin(String login) {
		Login = login;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	@Column(name="Password")
	private String Password;
	
	@Column(name="Email")
	private String Email;
	
	@Column(name="Type",
	columnDefinition="enum('Kierownik', 'Kierowca')")
	private String Type;
	
}
