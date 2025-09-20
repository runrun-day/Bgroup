package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
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
	
	public Product getProductInfo(int productId) {
		Product product = new Product();
		try (Connection conn = DBManager.getConnection()) {
			// SELECT文を準備
			String sql = "select product_id,name,price,image_rename\n"
					+ "from products\n"
					+ "where delete_date is null and product_id =?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, productId);
	
	
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表に格納されたレコードの内容を表示
			if (rs.next()) {
				int id = rs.getInt("product_id");
				String name = rs.getString("name");//
				int price = rs.getInt("price");
				String imageRename = rs.getString("image_rename");

				product = new Product(id,name,price,imageRename);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return product;
	}

	public boolean editProduct(int productId,String name,int price) {
		try (Connection conn = DBManager.getConnection()) {
			// SELECT文を準備
			String sql = "update products\n"
					+ "set name = ? ,price = ?\n"
					+ "where product_id = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, name);
			pStmt.setInt(2, price);
			pStmt.setInt(3, productId);
			
			// 実行結果（影響を受けた行数）を取得
            int rows = pStmt.executeUpdate();
            return rows > 0; // 1件以上登録できれば成功
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean deleteProduct(int productId,LocalDateTime datetime) {
		try (Connection conn = DBManager.getConnection()) {
			// SELECT文を準備
			String sql = "update products\n"
					+ "set delete_date = ?\n"
					+ "where product_id = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setObject(1, datetime);
			pStmt.setInt(2, productId);
			
			// 実行結果（影響を受けた行数）を取得
            int rows = pStmt.executeUpdate();
            return rows > 0; // 1件以上登録できれば成功
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Product> findAll() {
		// 結果を格納するリスト
		List<Product> pList = new ArrayList<>();
		// 実行するSQL文
		String sql = "SELECT PRODUCT_ID, NAME, PRICE, IMAGE_RENAME FROM PRODUCTS WHERE DELEAT_DATE IS NULL;";

		// try-with-resources文を使うことで、close処理を自動で行ってくれる
		try (Connection conn = DBManager.getConnection(); // DBに接続
				PreparedStatement pStmt = conn.prepareStatement(sql); // SQLを準備
				ResultSet rs = pStmt.executeQuery()) { // SQLを実行し、結果を取得

			// 結果セットから1件ずつ取り出す
			while (rs.next()) {
				Product product = new Product(
						rs.getInt("PRODUCT_ID"),
						rs.getString("NAME"),
						rs.getInt("PRICE"),
						rs.getString("IMAGE_RENAME"));
				pList.add(product); // リストに追加
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null; // 例外発生時は null を返す
		}

		return pList;
	}
}
