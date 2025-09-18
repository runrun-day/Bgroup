package model;

import java.io.Serializable;

//商品
public class Product implements Serializable {
	private int productId;
    private String name;
    private int price;
    private String imageRename;

	// コンストラクタ
	public Product() {}
	
	public Product(String name,int price,String imageRename) {
		this.name = name;
		this.price = price;
		this.imageRename = imageRename;
	}
	
	public Product(int productId,String name,int price,String imageRename) {
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.imageRename = imageRename;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", price=" + price + ", imageRename="
				+ imageRename + "]";
	}

}