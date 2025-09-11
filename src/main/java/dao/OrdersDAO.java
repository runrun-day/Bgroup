package dao;

import java.sql.SQLException;
import java.util.List;

import model.Order;

public class OrdersDAO {

	public boolean findByLogin(Order order) throws SQLException {
	
}
	
	 // 注文登録（注文ヘッダ + 明細を同時処理）
    public boolean insert(Order order) throws SQLException {
    	
    }

    // ユーザー別 注文履歴取得（JOINで明細も取得）
    public List<Order> findByUserId(int userId) throws SQLException {
    	
    }

    // 注文IDで詳細取得
    public Order findById(int orderId) throws SQLException {
    	
    }

    // 管理者用：全注文履歴取得（ユーザー情報もJOIN）
    public List<Order> findAll() throws SQLException {
    	
    }

}
