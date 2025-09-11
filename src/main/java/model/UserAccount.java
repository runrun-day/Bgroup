package model;

import java.io.Serializable;

//ユーザー
public class UserAccount implements Serializable {
    private int userId;
    private String name;
    private String email;
    private String postcode;
    private String address;
    private String tel;
    private String passward;
	
	public UserAccount() {}
	
	// コンストラクタ
	public UserAccount(String email,String passward) {
		this.email = email;
		this. passward = passward;
	}
	
	
	//取得用
    public UserAccount(int userId, String name, String email, String postcode, String address, String tel) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.postcode = postcode;
        this.address = address;
        this.tel = tel;
    }

    // 新規登録
    public UserAccount(String name, String email, String postcode, String address, String tel, String passward) {
        this.name = name;
        this.email = email;
        this.postcode = postcode;
        this.address = address;
        this.tel = tel;
        this. passward = passward;
    }

	public String getPassward() {
		return passward;
	}

	public void setPassward(String passward) {
		this.passward = passward;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

    
}