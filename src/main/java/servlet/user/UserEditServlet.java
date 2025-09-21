package servlet.user;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Product;
import model.UserAccount;
import service.user.ProductService;
import service.user.UserService;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String next = request.getParameter("next");
		switch(next) {
//		登録確認画面へ
			case "check" ->{}}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/userMenu.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String nextPage = "";
		
//		name="action"にvalue="signup" またはvalue="login"が入っているかで分岐
		String next = request.getParameter("next");
		System.out.println(next);
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
//		check
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
//		check
	    String tel = request.getParameter("tel");
//    	check
	    String password = request.getParameter("passward");
	    String password2 = request.getParameter("passward2");
	    
		switch(next) {
//		登録確認画面へ
			case "check" ->{
				UserService bo = new UserService();
				nextPage = "WEB-INF/jsp/user/userInfomartionCheck.jsp";
//				passwardのパッケージマッチング
				boolean result = password.matches("[A-Z0-9]{12}");
				
				if(!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "パスワードが条件を満たしていません");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, password));
					nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
				}
				
	//			passwardとpassward2が同じかの確認分岐
			    result = password2.equals(password);

				if(!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "パスワードと確認用パスワードが一致しません");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, password));
					nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
				}
				
				//電話番号にハイフンが含まれていないか→含まれている場合true
				result = !tel.contains("-");
				
				if (!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "電話番号に-(ハイフン)が含まれています");
	//				登録画面へ
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, password));
			     	nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
				}
				
				// 電話番号が固有であるかの確認分岐
//				登録時と異なり自身以外で記入電話番号を使用している者がいないかの確認
				UserAccount account = (UserAccount) session.getAttribute("account");
				int id = account.getUserId();
				result = bo.otherTelCheck(id,tel);
				
				if (!result) { //accountがNullの場合trueなのでfalseのエラー処理
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "この電話番号は既にユーザー登録済みです");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, password));
			     	nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
				}
				
//				郵便番号のパッケージマッチング
				result = postcode.matches("[0-9]{7}")&& !postcode.contains("-");
				
				if(!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "郵便番号の入力に誤りがあります(-(ハイフン)なしの7文字)");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, password));
					nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
				}
				
				// メルアドが固有であるかの確認分岐	
//				登録時と異なり自身以外で記入メルアドを使用している者がいないかの確認
				result = bo.otherEmailCheck(id,email);
				
				if (!result) { //accountがNullの場合trueなのでfalseのエラー処理
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "このメールアドレスは既にユーザー登録済みです");
	//				登録画面へ
					
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, password));
			     	nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
				}	

				if(result) {
//					パスワードのハッシュ化 SHA-256ハッシュ化
					try {
						MessageDigest md = MessageDigest.getInstance("SHA-256");
						md.update(password.getBytes());
			      		byte[] hashBytes = md.digest();
			      		password = Base64.getEncoder().encodeToString(hashBytes);
					}catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
				    }
//					入力内容のユーザーインスタンス作成
					UserAccount form = new UserAccount(name,email,postcode,address,tel, password); 
//					セッションスコープにユーザー登録内容を保存
					session.setAttribute("form", form);
				}
			}
			
//			case "commit" ->{//登録確認から登録結果まで
//				UserAccount account = (UserAccount)session.getAttribute("form");				
//				UserService us = new UserService();
//				boolean success = false;
//				nextPage = "WEB-INF/jsp/user/userInfomartionCommit.jsp";
//				if(account != null) {
//					success = us.userCreat(account);
//				}
////				登録できなかった場合エラーで返す
//				if(!success) {
//					request.setAttribute("errorMsg", "登録に失敗しました");
//					nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
//				}
//				//セッション削除
//		        session.removeAttribute("form");
//				
//			}
			case "back_1" ->{//入力画面から戻る処理
//				商品一覧表示用の処理
				List<Product> products = new ArrayList<>();
		    	ProductService pbo = new ProductService();
		    	products = pbo.getProducts();
		    	
		    	//メニュー画面表示の商品リストを保存
		    	request.setAttribute("products", products);
				
				nextPage = "WEB-INF/jsp/user/userMenu.jsp";
			}
			case "back_2" ->{//確認画面から戻る処理
				UserAccount account = (UserAccount)session.getAttribute("form");
//				リクエストスコープに保存
				request.setAttribute("form",account);
//				セッション削除
				session.removeAttribute("form");
				nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
	    dispatcher.forward(request, response);
	}

}
