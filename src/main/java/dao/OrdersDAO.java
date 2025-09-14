package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import model.Order;

public class OrdersDAO {
	
	
	public ArrayList<Order> getOrders(){
		ArrayList<Order> orderList = new ArrayList<>();
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {
	
			// SELECT文を準備
			String sql = "select A.order_id,B.name,A.order_date from orders as A join user as B on A.user_id = B.user_id order by A.order_date desc;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表に格納されたレコードの内容を表示
			while (rs.next()) {
				int orderId = rs.getInt("A.order_id");
				String name = rs.getString("B.name");
				Timestamp orderDate = rs.getTimestamp("A.order_date");
//				注文日時の年月日のみ表示できるようフォーマット
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String date = sdf.format(orderDate);
				Order order = new Order(orderId,name,date);
				System.out.println(order);
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(orderList);
		return orderList;
		}
}