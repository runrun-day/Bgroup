package model;

import java.io.Serializable;

public class Login implements Serializable {
	private String email;
	private String passward;
//	ログインエラー用
	private String errorMsg;
	
	public Login() {}
	
	public Login(String email, String passward) {
		this.email = email;
		this.passward = passward;
	}
	
	public Login(String email, String passward, String errorMsg) {
		this.email = email;
		this.passward = passward;
		this.errorMsg = errorMsg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMessage) {
		this.errorMsg = errorMessage;
	}
	
	
}
