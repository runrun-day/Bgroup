package service;

import dao.OrdersDAO;
import model.Login;
import model.UserAccount;

public class UserLoginLogic {
	public boolean execute(Login login) {
		OrdersDAO dao = new OrdersDAO();
		UserAccount account = dao.findByLogin(login);
	    return account != null;
	  }
}
