package service.user;

import java.util.List;

import dao.RegularServiceDAO;
import model.RegularService;

public class RegularServiceLogic {
	//定期
	private RegularServiceDAO rsDao = new RegularServiceDAO();
	
//	注文一覧の表示処理用
	public List<RegularService> getOrdersList() {
		List<RegularService> rsList = rsDao.getOrders();
		return rsList;
	}
	
//	注文履歴詳細の表示処理用	
	public List<RegularService> getOrderDetail(int orderId) {
		List<RegularService> rsList = rsDao.getOrderDetail(orderId);
		return rsList;
	}
	
	  //ユーザーごとの定期便一覧
    public List<RegularService> getOrdersListByUser(int userId) {
    	List<RegularService> rsList = rsDao.getOrdersByUser(userId);
    	return rsList;
    }
    
    // 定期便の削除処理用
    public boolean deleteRegularService(int regularServiceDetailId) {
        // regularServiceDetailIdが0以下なら削除しない
        if (regularServiceDetailId <= 0) {
            return false;
        }
        return rsDao.delete(regularServiceDetailId);
    }
}
