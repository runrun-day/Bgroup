package service.user;

import java.util.Objects;

import dao.UserDAO;
import model.UserAccount;

public class UserService {
//	ユーザー登録画面でメールアドレスの重複がないか確認する処理
		public boolean emailCheck(String email) {
			UserDAO dao = new UserDAO();
			UserAccount account = dao.findbyemail(email);
			System.out.println(account);
//			accountがNullの場合trueで返す処理
		    return Objects.isNull(account);
		}

//		ユーザー登録画面で電話番号の重複がないか確認する処理
		public boolean telCheck(String tel) {
			UserDAO dao = new UserDAO();
			UserAccount account = dao.findbyetel(tel);
			System.out.println(account);
//			accountがNullの場合trueで返す処理
		    return Objects.isNull(account);
		}
		
//		ユーザー登録処理
		public boolean userCreat(UserAccount account) {
			UserDAO dao = new UserDAO();
//			登録されてればtrue
			return dao.userCreate(account);
		}
}
