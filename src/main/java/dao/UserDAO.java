package dao;

import java.sql.SQLException;
import java.util.List;

import model.UserAccount;

public class UserDAO {

	private final String JDBC_URL="jdbc:mysql://localhost:3306/shop?useSSL=false&serverTimezone=UTC";
	private final String DB_USER="root";
	private final String DB_PASS="root";
	
    // ユーザー一覧取得（管理者用）
    public List<UserAccount> findAll() throws SQLException {
    }

    // ユーザーIDで取得
    public UserAccount findById(int userId) throws SQLException {
    	
    }

    // ログイン認証
    public UserAccount findByEmail(String email) throws SQLException {
    	
    }

    // 新規ユーザー登録
    public boolean insert(UserAccount account) throws SQLException {
    	
    }

    // ユーザー更新
    public boolean update(UserAccount account) throws SQLException {
    	
    }

    // ユーザー削除
    public boolean delete(int userId) throws SQLException {
    	
    }

}
