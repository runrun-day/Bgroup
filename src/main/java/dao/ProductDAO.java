package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
//ユーザー側メニュー画面<main>に商品一覧を表示する処理
	public List<Product> getProducts(){
		List<Product> products = new ArrayList<>();
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {
			// SELECT文を準備
			String sql = "select product_id,name,price,image_rename\n"
					+ "from products\n"
					+ "where delete_date is null;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
	
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表に格納されたレコードの内容を表示
			while (rs.next()) {
				int productId = rs.getInt("product_id");
				String name = rs.getString("name");//
				int price = rs.getInt("price");
				String imageRename = rs.getString("image_rename");

				Product product = new Product(productId,name,price,imageRename);
				System.out.println(product);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(products);
		return products;
	}
	
public boolean create(Product product) {
    	
        // DB登録
        try (Connection conn = DBManager.getConnection()) {
            String sql = "INSERT INTO products ( name, price, image_rename, delete_date) VALUES\n"
            		+ " (?, ?, ?, null)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            	
                pstmt.setString(1, product.getName());
                pstmt.setInt(2, product.getPrice());
                pstmt.setString(3, product.getImageRename());
                int result = pstmt.executeUpdate();
                return (result == 1);
            }
        } catch (SQLException e) {
          e.printStackTrace();
        }
        return false;

    }

}
