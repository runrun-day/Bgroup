package service.user;

import java.util.ArrayList;
import java.util.List;

import dao.OrdersDAO;
import model.Order;

public class OrdersService {

	
		//注文
		private OrdersDAO dao = new OrdersDAO();
		
//		注文一覧の表示処理用
		public ArrayList<Order> getOrdersList() {
			ArrayList<Order> orderList = dao.getOrders();
			return orderList;
		}
		
//		注文履歴詳細の表示処理用	
		public List<Order> getOrderDetail(int orderId) {
			List<Order> orderList = dao.getOrderDetail(orderId);
			return orderList;
		}
		
//		// ユーザーごとの注文履歴一覧を取得
//		public List<Order> getOrdersByUser(int userId) {
//			List<Order> orderList = dao.getOrdersByUser(userId);
//			return orderList;
//		}
		
		// ユーザーの注文履歴(定期便含めて)表示
		public List<Order> getOrderListByUser(int userId){
			return dao.getOrderListByUser(userId);
		}
		
		// 注文登録処理用
	    public boolean insertOrder(int userId, List<Order> cart) {
	        return dao.insertOrder(userId, cart);
	    }
}
