package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.UserAccount;

public class UserDAO {

//	ユーザーログインの確認
	public UserAccount findByLogin(Login login){
		UserAccount account = null;
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {

			// SELECT文を準備
			String sql = "SELECT USER_ID,NAME,POSTCODE,ADDRESS,TEL,EMAIL,PASSWORD FROM USER WHERE EMAIL = ? AND PASSWORD = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, login.getEmail());
			pStmt.setString(2, login.getPassward());
			
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を表示
			if (rs.next()) {
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("NAME");
				String postcode = rs.getString("POSTCODE");
				String address = rs.getString("ADDRESS");
				String tel = rs.getString("TEL");
				String email = rs.getString("EMAIL");
				account = new UserAccount(userId,name,email,postcode,address,tel);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}


}
