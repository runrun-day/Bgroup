package servlet.user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Login;
import model.Product;
import model.UserAccount;
import service.user.ProductService;
import service.user.UserLoginLogic;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
	      dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String nextPage = "";
//		分岐
		String next = request.getParameter("next");
		HttpSession session = request.getSession();
		System.out.println(next);
		
		switch(next) {
//		ユーザー登録へフォワード
		case "signup" ->{
			nextPage ="WEB-INF/jsp/user/userRegistration.jsp";
		}
//		メルアド/パスワード認証でユーザーホームへフォワード 
		case "login" ->{
//			フォーム入力したメルアドとパスワードの取得
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			
			// ログイン処理の実行
		    Login login = new Login(mail, password);
		    UserLoginLogic bo = new UserLoginLogic();
		    UserAccount account = bo.execute(login);

		    // ログイン処理の成否によって処理を分岐
		    if (account != null) { // ログイン成功時
		      
			    	List<Product> products = new ArrayList<>();
			    	ProductService pbo = new ProductService();
			    	products = pbo.getProducts();
			    	
			    	//メニュー画面表示の商品リストを保存
			    	request.setAttribute("products", products);
			    	// セッションスコープにユーザー情報保存
			    session.setAttribute("account", account);

		      
			    nextPage ="WEB-INF/jsp/user/userMenu.jsp";
		    } else { // ログイン失敗時
		      // エラーメッセージ
		     	login = new Login();
		     	login.setErrorMsg("ユーザー名またはパスワードが違います");
//				エラーメッセージをリクエストスコープに保存
		     	request.setAttribute("login",login);
		     	nextPage ="top.jsp";
				}
		}
		case "back" ->{
			nextPage ="top.jsp";
		}
		case "logout" ->{
			//セッション破棄してTOPへ戻す
			session.invalidate();
			nextPage ="top.jsp";
		}
		}
		RequestDispatcher dispatcher =
				request.getRequestDispatcher(nextPage);
				dispatcher.forward(request,response);
	}

}
