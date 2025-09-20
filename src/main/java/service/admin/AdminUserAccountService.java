package service.admin;

import dao.UserDAO;
import model.UserAccount;

public class AdminUserAccountService {
	
	UserDAO dao = new UserDAO();

//	ユーザー情報
	public UserAccount findByTel(String tel){
		return dao.findByTel(tel);
}
	public boolean userDeleteById(int userId) {
		return dao.userDeleteById(userId);
	}
}