package service.admin;

import java.util.ArrayList;

import dao.OrdersDAO;
import model.Order;

public class AdminOrdersService {
	private OrdersDAO dao = new OrdersDAO();
	
//	注文一覧の表示処理用
	public ArrayList<Order> getOrdersList() {
		ArrayList<Order> orderList = dao.getOrders();
		return orderList;
	}
}
