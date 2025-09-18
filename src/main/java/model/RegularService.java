package model;

import java.io.Serializable;
import java.sql.Timestamp;


// 定期便
public class RegularService implements Serializable {

	private int regularServiceDetailId; // 定期便明細ID オート
	private int regularServiceId; // 定期便ID オート
    private int userId; //ユーザーID オート
    private int productId; //商品ID オート
    private int num; //注文個数
    private Timestamp orderDate; //注文日(start_date)
    
    //追加
    private String date; //注文日のみ
    
    private String userName; //ユーザー名
    private String email; //メールアドレス
    private String postcode; //郵便番号
    private String address; //住所
    private String tel; //電話番号
    private String productName; //商品名
    private int price; //価格
    private String imageRename; // 画像
    private int span; //定期便
    private int amount; //num*price
	
    
    // コンストラクタ
    public RegularService() {}

//    定期便一覧
    public RegularService(int regularServiceId,String userName,String date) {
    		this.regularServiceId = regularServiceId;
    		this.userName = userName;
    		this.date = date;
    }
//    定期便詳細
    public RegularService(String userName,Timestamp orderDate,String productName,int num,
    		int price,int amount,int span) {
	    	this.num = num;
	    	this.orderDate = orderDate;
	    	this.userName = userName;
	    	this.productName = productName;
	    	this.price = price;
	    	this.span = span;
	    	this.amount = amount;
    }
    
  
//定期便削除用
public RegularService(String userName,int regularServiceDetailId, Timestamp orderDate,String productName,int num,
		int price,int amount,int span) {
		this.regularServiceDetailId = regularServiceDetailId;
	    	this.num = num;
	    	this.orderDate = orderDate;
	    	this.userName = userName;
	    	this.productName = productName;
	    	this.price = price;
	    	this.span = span;
	    	this.amount = amount;
}
    
	// ゲッターセッター
	public int getRegularServiceDetailId() {
		return regularServiceDetailId;
	}


	public void setRegularServiceDetailId(int regularServiceDetailId) {
		this.regularServiceDetailId = regularServiceDetailId;
	}


	public int getRegularServiceId() {
		return regularServiceId;
	}


	public void setRegularServiceId(int regularServiceId) {
		this.regularServiceId = regularServiceId;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getProductId() {
		return productId;
	}


	public void setProductId(int productId) {
		this.productId = productId;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}

	public Timestamp getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Timestamp orderDate) {
		this.orderDate = orderDate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public String getImageRename() {
		return imageRename;
	}


	public void setImageRename(String imageRename) {
		this.imageRename = imageRename;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}


	public int getSpan() {
		return span;
	}


	public void setSpan(int span) {
		this.span = span;
	}
    

    
}
