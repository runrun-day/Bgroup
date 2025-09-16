package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
				RegularService rsOrder = new RegularService(regularServiceId,name,date);
				System.out.println(rsOrder);
				orderList.add(rsOrder);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		System.out.println(orderList);
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
}
