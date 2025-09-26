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


}
