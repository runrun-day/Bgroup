package dao;

import java.sql.SQLException;
import java.util.List;

import model.RegularService;

public class RegularServiceDAO {

    // 定期便登録（ヘッダ + 明細同時処理）
    public boolean insert(RegularService regularService) throws SQLException {
    	
    }

    // ユーザー別定期便取得（JOINで明細も）
    public List<RegularService> findByUserId(int userId) throws SQLException {
    	
    }

    // 定期便IDで取得
    public RegularService findById(int regularServiceId) throws SQLException {
    	
    }

    // 管理者用：全定期便取得（JOIN）
    public List<RegularService> findAll() throws SQLException {
    	
    }

}
