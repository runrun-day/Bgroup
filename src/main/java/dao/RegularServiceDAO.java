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
import model.RegularService;

public class RegularServiceDAO {
	public List<RegularService> getOrders(){
		List<RegularService> orderList = new ArrayList<>();
//		DBManagerからgetConnection()でSQL接続
		try (Connection conn = DBManager.getConnection()) {
			// SELECT文を準備
			String sql = "select A.regular_service_id,B.name,A.start_date from regular_service as A join user as B on A.user_id = B.user_id order by A.start_date desc;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
	
	
			// SELECT文を実行し、結果表（ResultSet）を取得
			ResultSet rs = pStmt.executeQuery();
	
			// 結果表に格納されたレコードの内容を表示
			while (rs.next()) {
				int regularServiceId = rs.getInt("regular_service_id");
				String name = rs.getString("name");//
				Timestamp orderDate = rs.getTimestamp("start_date");
//				注文日時の年月日のみ表示できるようフォーマット
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String date = sdf.format(orderDate);
				RegularService rsOrder = new RegularService(regularServiceId,name,date);;
				orderList.add(rsOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return orderList;
		}

//	orderIdから注文詳細データ(定期便利用ありなし含め)表示処理
	public List<RegularService> getOrderDetail(int orderId) {
        List<RegularService> list = new ArrayList<>();

        String sql = 
        		"select \r\n"
        		+ "D.name AS user_name,\r\n"
        		+ "B.start_date,\r\n"
        		+ "C.name AS product_name,\r\n"
        		+ "A.num AS quantity,\r\n"
        		+ "C.price,\r\n"
        		+ "(A.num * C.price) AS subtotal,\r\n"
        		+ "B.span AS span\r\n"
        		+ "from regular_service_detail as A\r\n"
        		+ "join shop.regular_service as B\r\n"
        		+ "on A.regular_service_id = B.regular_service_id\r\n"
        		+ "join products as C\r\n"
        		+ "on A.product_id = C.product_id\r\n"
        		+ "join user as D\r\n"
        		+ "on B.user_id = D.user_id\r\n"
        		+ "where B.regular_service_id = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, orderId);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String userName = rs.getString("user_name");
                Timestamp orderDate = rs.getTimestamp("start_date");
                String productName = rs.getString("product_name");
                int num = rs.getInt("quantity");
                int price = rs.getInt("price");
                int amount = rs.getInt("subtotal");
                int span = rs.getInt("span");
                RegularService rsOrder = new RegularService(userName, orderDate, productName, num, price, amount,span);
                list.add(rsOrder);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
	}
	
	// ユーザーごとの定期便注文一覧を取る用
	public List<RegularService> getOrdersByUser(int userId) {
		List<RegularService> list = new ArrayList<>();

		String sql = "SELECT r.regular_service_id, "
				+ "d.regular_service_detail_id, r.start_date, "
				+ "p.name AS product_name, d.num, p.price, "
				+ "(d.num * p.price) AS subtotal, r.span "
				+ "FROM regular_service r "
				+ "JOIN regular_service_detail d ON r.regular_service_id = d.regular_service_id "
				+ "JOIN products p ON d.product_id = p.product_id "
				+ "WHERE r.user_id = ? "
				+ "ORDER BY r.start_date DESC";

		try (Connection conn = DBManager.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int regularServiceId = rs.getInt("regular_service_id");
				int regularServiceDetailId = rs.getInt("d.regular_service_detail_id");
				Timestamp orderDate = rs.getTimestamp("start_date");
				String productName = rs.getString("product_name");
				int num = rs.getInt("num");
				int price = rs.getInt("price");
				int amount = rs.getInt("subtotal");
				int span = rs.getInt("span");

				RegularService rsOrder = new RegularService(null, regularServiceDetailId, orderDate, productName, num,
						price, amount, span);
				rsOrder.setRegularServiceId(regularServiceId);

				// 日付を文字列にフォーマットしてセット
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				rsOrder.setDate(sdf.format(orderDate));

				list.add(rsOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	// 定期便削除用
	public boolean delete(int regularServiceDetailId) {
		String sql = "DELETE R, D FROM REGULAR_SERVICE AS R\n"
				+ "JOIN REGULAR_SERVICE_DETAIL AS D\n"
				+ "ON R.REGULAR_SERVICE_ID = D.REGULAR_SERVICE_ID\n"
				+ "WHERE R.REGULAR_SERVICE_ID = ?;";

		try (Connection conn = DBManager.getConnection(); // DBに接続
				PreparedStatement pStmt = conn.prepareStatement(sql)) { // SQLを準備

			// 削除対象のIDをセット
			pStmt.setInt(1, regularServiceDetailId);

			int rows = pStmt.executeUpdate();
			return rows > 0; // 削除件数が1件以上なら成功

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 定期便登録用
		public boolean rsinsertOrder(int userId, List<Order> cart) {
			
			String sqlOrder = "INSERT INTO regular_service (user_id, start_date) VALUES (?, ?)";
		    String sqlDetail = "INSERT INTO regular_service_detail (regular_service_id, product_id, num,span) VALUES (?, ?, ?,?)";

		    try (Connection conn = DBManager.getConnection()) {

		        int rsorderId = 0;
		        
		        try (PreparedStatement ps = conn.prepareStatement(sqlOrder, PreparedStatement.RETURN_GENERATED_KEYS)) {
		            ps.setInt(1, userId);
		            ps.setTimestamp(2, cart.get(0).getOrderDate());
		            ps.executeUpdate();

		            try (ResultSet rs = ps.getGeneratedKeys()) {
		                if (rs.next()) {
		                	rsorderId = rs.getInt(1); 
		                }
		            }
		        }
		        
		        System.out.println("=== Debug: OrdersDAO.insertOrder ===");
		        System.out.println("userId: " + userId);

		        // orders INSERT の直後
		        System.out.println("新規 orderId = " + rsorderId);

		        // order_detail INSERT の直前
		        for (Order item : cart) {
		            System.out.println("Detail登録 → orderId=" + rsorderId
		                               + " / productId=" + item.getProductId()
		                               + " / num=" + item.getNum());
		        }

		        try (PreparedStatement ps = conn.prepareStatement(sqlDetail)) {
		            for (Order item : cart) {
		                ps.setInt(1, rsorderId);
		                ps.setInt(2, item.getProductId());
		                ps.setInt(3, item.getNum());
		                ps.setInt(4, item.getSpan());
		                ps.addBatch(); 
		            }
		            ps.executeBatch();
		        }
		        return true;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        return false;
		    }
		}
}
