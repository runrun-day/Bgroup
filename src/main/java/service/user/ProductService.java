package service.user;

import java.util.List;

import dao.ProductDAO;
import model.Product;

public class ProductService {
	//画像パス！
	public static final String FILE_PATH = "C:/Btest/images/uploads";
	
	ProductDAO dao = new ProductDAO();
	
	//ユーザー側メニュー画面<main>に商品一覧を表示する処理
	public List<Product> getProducts() {
		return dao.getProducts();
	}

	public Product getProductByName(String name) {
	    List<Product> products = getProducts(); // 全商品取得
	    for (Product p : products) {
	        if (p.getName().equals(name)) {
	            return p;
	        }
	    }
	    return null; // 見つからない場合
	}

}
