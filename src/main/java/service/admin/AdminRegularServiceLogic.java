package service.admin;

import java.util.List;

import dao.RegularServiceDAO;
import model.RegularService;

public class AdminRegularServiceLogic {

	//定期
		private RegularServiceDAO rsDao = new RegularServiceDAO();
		
//		注文一覧の表示処理用
		public List<RegularService> getOrdersList() {
			List<RegularService> orderList = rsDao.getOrders();
			return orderList;
		}
//		注文履歴詳細の表示処理用	
		public List<RegularService> getrsOrderDetail(int orderId) {
			List<RegularService> orderList = rsDao.getOrderDetail(orderId);
			return orderList;
		}
}
