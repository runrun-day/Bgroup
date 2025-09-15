package model;

import java.io.Serializable;
import java.sql.Timestamp;

// 注文明細
public class Order implements Serializable {

	private int orderProductId; //注文明細IDオート
	private int orderId; //注文履歴ID オート
    private int userId; //ユーザーID オート
    private int productId; //商品ID オート
    private int num; //注文個数
    private Timestamp orderDate; //注文日時
    //追加！
    private String date; //注文日のみ
    private String userName; //ユーザー名
    private String email; //メールアドレス
    private String postcode; //郵便番号
    private String address; //住所
    private String tel; //電話番号
    private String productName; //商品名
    private int price; //価格
    private String imageRename; // 画像
    private int amount; //num*price

    //追加
    private boolean regularService; //定期便登録ありなしの判定

    // コンストラクタは必要に応じて追加
	public Order() {	}
	
	//注文一覧用
	public Order(int orderId,String userName,String date) {	
		this.orderId = orderId;
		this.userName = userName;
		this.date = date;
	}
	
//	userName, orderDate, productName, num, price, amount,regularService
	public Order(String userName, Timestamp orderDate, String productName, int num, int price, int amount,
			boolean regularService) {
		this.num = num;
		this.orderDate = orderDate;
		this.userName = userName;
		this.productName = productName;
		this.price = price;
		this.amount = amount;
		this.regularService = regularService;
	}
	
	
	// ゲッターセッター
	public int getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(int orderProductId) {
		this.orderProductId = orderProductId;
	}


	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
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
	
	public boolean isRegularService() {
		return regularService;
	}

	public void setRegularService(boolean regularService) {
		this.regularService = regularService;
	}

	@Override
	public String toString() {
		return "Order [orderProductId=" + orderProductId + ", orderId=" + orderId + ", userId=" + userId
				+ ", productId=" + productId + ", num=" + num + ", orderDate=" + orderDate + ", date=" + date
				+ ", userName=" + userName + ", email=" + email + ", postcode=" + postcode + ", address=" + address
				+ ", tel=" + tel + ", productName=" + productName + ", price=" + price + ", imageRename=" + imageRename
				+ ", amount=" + amount + "]";
	}

	
	
}
