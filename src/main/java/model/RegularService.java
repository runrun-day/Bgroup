package model;

import java.io.Serializable;
import java.time.LocalDateTime;


// 定期便
public class RegularService implements Serializable {

	private int regularServiceDetailId; // 定期便明細ID オート
	private int regularServiceId; // 定期便ID オート
    private int userId; //ユーザーID オート
    private int productId; //商品ID オート
    private int num; //注文個数
    private LocalDateTime orderDate; //注文日
    
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

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
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
