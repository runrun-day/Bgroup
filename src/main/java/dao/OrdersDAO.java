package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
				String name = rs.getString("B.name");//
				Timestamp orderDate = rs.getTimestamp("A.order_date");
//				注文日時の年月日のみ表示できるようフォーマット
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String date = sdf.format(orderDate);
				Order order = new Order(orderId,name,date);
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return orderList;
		}

//	orderIdから注文詳細データ(定期便利用ありなし含め)表示処理
	public List<Order> getOrderDetail(int orderId) {
        List<Order> list = new ArrayList<>();

        String sql = 
        		"SELECT \r\n"
        		+ "u.name AS user_name,\r\n"
        		+ "o.order_id,\r\n"
        		+ "o.order_date,\r\n"
        		+ "p.name AS product_name,\r\n"
        		+ "od.num AS num,\r\n"
        		+ "p.price,\r\n"
        		+ "(od.num * p.price) AS subtotal,\r\n"
        		+ "CASE WHEN rs.regular_service_id IS NULL THEN 0 ELSE 1 END AS regular_flag,\r\n"
        		+ "rs.span AS span\r\n"
        		+ "FROM orders o\r\n"
        		+ "JOIN user u \r\n"
        		+ "ON o.user_id = u.user_id\r\n"
        		+ "JOIN order_detail od \r\n"
        		+ "ON o.order_id = od.order_id\r\n"
        		+ "JOIN products p \r\n"
        		+ "ON od.product_id = p.product_id\r\n"
        		+ "LEFT JOIN regular_service rs \r\n"
        		+ "ON rs.user_id = u.user_id AND o.order_date = rs.start_date\r\n"
        		+ "WHERE o.order_id = ?\r\n"
        		+ "ORDER BY o.order_date DESC;";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("user_name");
                Timestamp orderDate = rs.getTimestamp("order_date");
                String productName = rs.getString("product_name");
                int num = rs.getInt("num");
                int price = rs.getInt("price");
                int amount = rs.getInt("subtotal");
//              定期便であれば１ true
                boolean regularService = rs.getInt("regular_flag") == 1;
                int span = rs.getInt("span");
                Order odv = new Order(userName, orderDate, productName, num, price, amount,regularService,span);
                list.add(odv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
	
//	// ユーザーの注文履歴表示
//		public List<Order> getOrdersByUser(int userId) {
//		    List<Order> orderList = new ArrayList<>();
//
//		    String sql =
//		        "SELECT o.order_id, o.order_date, p.name AS product_name, od.num, p.price, " +
//		        "(od.num * p.price) AS subtotal " +
//		        "FROM orders o " +
//		        "JOIN order_detail od ON o.order_id = od.order_id " +
//		        "JOIN products p ON od.product_id = p.product_id " +
//		        "WHERE o.user_id = ? " +
//		        "ORDER BY o.order_date DESC";
//
//		    try (Connection conn = DBManager.getConnection();
//		         PreparedStatement pstmt = conn.prepareStatement(sql)) {
//
//		        pstmt.setInt(1, userId);
//		        ResultSet rs = pstmt.executeQuery();
//
//		        while (rs.next()) {
//		            int orderId = rs.getInt("order_id");
//		            Timestamp orderDate = rs.getTimestamp("order_date");
//		            String productName = rs.getString("product_name");
//		            int num = rs.getInt("num");
//		            int price = rs.getInt("price");
//		            int amount = rs.getInt("subtotal");
//
//		            Order order = new Order(orderId, orderDate, productName, num, price, amount);
//		            orderList.add(order);
//		        }
//		    } catch (SQLException e) {
//		        e.printStackTrace();
//		    }
//
//		    return orderList;
//		}
		
		// ユーザーの注文履歴(定期便含めて)表示
		public List<Order> getOrderListByUser(int userId) {
		    List<Order> orderList = new ArrayList<>();

		    String sql =
		        "SELECT \r\n"
		        + "u.name AS user_name,\r\n"
		        + "o.order_id,\r\n"
		        + "o.order_date,\r\n"
		        + "p.name AS product_name,\r\n"
		        + "od.num AS num,\r\n"
		        + "p.price,\r\n"
		        + "(od.num * p.price) AS subtotal,\r\n"
		        + "rs.span AS span\r\n"
		        + "FROM orders o\r\n"
		        + "JOIN order_detail od\r\n"
		        + "ON o.order_id = od.order_id\r\n"
		        + "JOIN user u \r\n"
		        + "ON o.user_id = u.user_id\r\n"
		        + "JOIN products p \r\n"
		        + "ON od.product_id = p.product_id\r\n"
		        + "LEFT JOIN regular_service rs \r\n"
		        + "ON o.order_date = rs.start_date\r\n"
		        + "WHERE o.user_id = ? \r\n"
		        + "ORDER BY o.order_date DESC";

		    try (Connection conn = DBManager.getConnection();
		         PreparedStatement pstmt = conn.prepareStatement(sql)) {

		        pstmt.setInt(1, userId);
		        ResultSet rs = pstmt.executeQuery();

		        while (rs.next()) {
		            int orderId = rs.getInt("order_id");
		            Timestamp orderDate = rs.getTimestamp("order_date");
		            String productName = rs.getString("product_name");
		            int num = rs.getInt("num");
		            int price = rs.getInt("price");
		            int amount = rs.getInt("subtotal");
		            int span = rs.getInt("span");

		            Order order = new Order(orderId, orderDate, productName, num, price, amount,span);
		            orderList.add(order);
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return orderList;
		}
}