package service.admin;

import java.util.ArrayList;
import java.util.List;

import dao.OrdersDAO;
import dao.RegularServiceDAO;
import model.Order;

public class AdminOrdersService {
	//注文
	private OrdersDAO dao = new OrdersDAO();
	//定期
	private RegularServiceDAO rsDao = new RegularServiceDAO();
	
//	注文一覧の表示処理用
	public ArrayList<Order> getOrdersList() {
		ArrayList<Order> orderList = dao.getOrders();
		return orderList;
	}
//	注文履歴詳細の表示処理用	
	public List<Order> getOrderDetail(int orderId) {
		List<Order> orderList = dao.getOrderDetail(orderId);
		return orderList;
	}
	
}
