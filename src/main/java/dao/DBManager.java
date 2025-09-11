package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	/** データベース接続URL */
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/shop?useSSL=false&serverTimezone=UTC";

	/** データベース接続ユーザー */
	private static final String DB_USER = "root";

	/** データベース接続パスワード */
	private static final String DB_PASS = "root";

	/**
	 * データベースに接続するためのメソッドです。
	 * <p>
	 * JDBCドライバを読み込み、指定した接続情報を用いて
	 * {@link java.sql.Connection} オブジェクトを生成して返します。
	 * </p>
	 * 
	 * @return データベース接続オブジェクト ({@link Connection})
	 * @throws IllegalStateException データベース接続やドライバ読み込みに失敗した場合
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			// JDBCドライバを読み込む
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			// データベースへ接続
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			return conn;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new IllegalStateException("DB接続に失敗しました", e);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw new IllegalStateException("ライブラリの読み込みに失敗しました", e);
		}
	}
}
