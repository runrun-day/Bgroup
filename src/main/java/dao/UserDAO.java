package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Login;
import model.UserAccount;

public class UserDAO {

//	ユーザーログインの確認処理
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

//	ユーザー登録画面でメールアドレスの重複がないか確認する処理
	public UserAccount findbyemail(String email){
		UserAccount account = null;
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {

			// SELECT文を準備
			String sql = "SELECT EMAIL FROM USER WHERE EMAIL = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, email);
		
			
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を表示
			if (rs.next()) {		
				String mail = rs.getString("EMAIL");
				account = new UserAccount();
				account.setEmail(email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	
	}
	
//	ユーザー登録画面でメールアドレスの重複がないか確認する処理
	public UserAccount checkByTel(String tel){
		UserAccount account = null;
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {

			// SELECT文を準備
			String sql = "SELECT TEL FROM USER WHERE TEL = ? ";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, tel);
		
			
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を表示
			if (rs.next()) {		
				String tel2 = rs.getString("TEL");
				account = new UserAccount();
				account.setTel(tel2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return account;
	}
	
//	ユーザー新規登録処理
	public boolean userCreate(UserAccount account) {
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {

			// SELECT文を準備
			String sql = "INSERT INTO USER (NAME,EMAIL,POSTCODE,ADDRESS,TEL,PASSWORD) VALUES (?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// プレースホルダに値をセット
            pStmt.setString(1, account.getName());
            pStmt.setString(2, account.getEmail());
            pStmt.setString(3, account.getPostcode());
            pStmt.setString(4, account.getAddress());
            pStmt.setString(5, account.getTel());
            pStmt.setString(6, account.getPassward());
		
			
         // 実行結果（影響を受けた行数）を取得
            int rows = pStmt.executeUpdate();
            return rows > 0; // 1件以上登録できれば成功

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
	}	
		
	}
	
	//ユーザー削除処理
	public boolean userDeleteById(int userId) {
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {

			// SELECT文を準備
			String sql = "INSERT INTO USER (NAME,EMAIL,POSTCODE,ADDRESS,TEL,PASSWORD) VALUES (?,?,?,?,?,?)";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			// プレースホルダに値をセット
            pStmt.setInt(1, userId);
            
		
			
         // 実行結果（影響を受けた行数）を取得
            int rows = pStmt.executeUpdate();
            return rows > 0; // 1件以上登録できれば成功

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
	}	
		
//		try {
//            // 1. データベース接続
//            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//            // 2. トランザクション開始
//            connection.setAutoCommit(false);
//
//            // 3. 定期便明細テーブル（regular_service_detail）から該当するデータを削除
//            String deleteRegularServiceDetailSql = "DELETE FROM regular_service_detail WHERE regular_service_id IN (SELECT regular_service_id FROM regular_service WHERE user_id = ?)";
//            stmt = connection.prepareStatement(deleteRegularServiceDetailSql);
//            stmt.setInt(1, userId);
//            stmt.executeUpdate();
//
//            // 4. 定期便テーブル（regular_service）から該当するデータを削除
//            String deleteRegularServiceSql = "DELETE FROM regular_service WHERE user_id = ?";
//            stmt = connection.prepareStatement(deleteRegularServiceSql);
//            stmt.setInt(1, userId);
//            stmt.executeUpdate();
//
//            // 5. 注文明細テーブル（order_detail）から該当するデータを削除
//            String deleteOrderDetailSql = "DELETE FROM order_detail WHERE order_id IN (SELECT order_id FROM orders WHERE user_id = ?)";
//            stmt = connection.prepareStatement(deleteOrderDetailSql);
//            stmt.setInt(1, userId);
//            stmt.executeUpdate();
//
//            // 6. 注文履歴テーブル（orders）から該当するデータを削除
//            String deleteOrderSql = "DELETE FROM orders WHERE user_id = ?";
//            stmt = connection.prepareStatement(deleteOrderSql);
//            stmt.setInt(1, userId);
//            stmt.executeUpdate();
//
//            // 7. ユーザーテーブル（user）から該当するユーザーを削除
//            String deleteUserSql = "DELETE FROM user WHERE user_id = ?";
//            stmt = connection.prepareStatement(deleteUserSql);
//            stmt.setInt(1, userId);
//            stmt.executeUpdate();
//
//            // 8. コミットして変更を確定
//            connection.commit();
//            isSuccess = true;
//
//        } catch (SQLException e) {
//            // エラーが発生した場合はロールバック
//            if (connection != null) {
//                try {
//                    connection.rollback();
//                } catch (SQLException rollbackEx) {
//                    rollbackEx.printStackTrace();
//                }
//            }
//            e.printStackTrace();
//        } finally {
//            // リソースをクローズ
//            try {
//                if (stmt != null) {
//                    stmt.close();
//                }
//                if (connection != null) {
//                    connection.setAutoCommit(true);  // 自動コミットを元に戻す
//                    connection.close();
//                }
//            } catch (SQLException closeEx) {
//                closeEx.printStackTrace();
//            }
//        }
//
//        return isSuccess;
//    }
	}
	
	//
	public UserAccount findByTel(String tel){
		UserAccount account = null;
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {

			// SELECT文を準備
			String sql = "select user_id,name,email,postcode,address,tel\n"
					+ "from user\n"
					+ "where tel = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, tel);
		
			
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();

			// 結果表に格納されたレコードの内容を表示
			if (rs.next()) {		
				int userId = rs.getInt("USER_ID");
				String name = rs.getString("NAME");
				String postcode = rs.getString("POSTCODE");
				String address = rs.getString("ADDRESS");
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
