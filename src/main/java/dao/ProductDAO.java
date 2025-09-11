package dao;

import java.sql.SQLException;
import java.util.List;

import model.Product;

public class ProductDAO {

	private final String JDBC_URL="jdbc:mysql://localhost:3306/shop?useSSL=false&serverTimezone=UTC";
	private final String DB_USER="root";
	private final String DB_PASS="root";
	
	// 商品一覧取得
    public List<Product> findAll() throws SQLException {
    	
    }

    // 商品IDで取得
    public Product findById(int productId) throws SQLException {
    	
    }

    // 商品登録
    public boolean insert(Product product) throws SQLException {
    	
    }

    // 商品更新
    public boolean update(Product product) throws SQLException {
    	
    }

    // 商品削除
    public boolean delete(int productId) throws SQLException {
    	
    }

}
