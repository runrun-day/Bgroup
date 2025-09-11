package servlet.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Login;
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
		
//		name="action"にvalue="signup" またはvalue="login"が入っているかで分岐
		String next = request.getParameter("next");
		System.out.println(next);
		switch(next) {
//		ユーザー登録へフォワード
		case "signup" ->{
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/user/userRegistration.jsp");
					dispatcher.forward(request,response);
		}
//		メルアド/パスワード認証でユーザーホームへフォワード 
		case "login" ->{
//			フォーム入力したメルアドとパスワードの取得
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			
			// ログイン処理の実行
		    Login login = new Login(mail, password);
		    UserLoginLogic bo = new UserLoginLogic();
		    boolean result = bo.execute(login);
		    System.out.println(result);

		    // ログイン処理の成否によって処理を分岐
		    if (result) { // ログイン成功時
		      // セッションスコープにメルアドを保存
		      HttpSession session = request.getSession();
		      session.setAttribute("mail", mail);
		      // フォワード
		      RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/userMenu.jsp");
		      dispatcher.forward(request, response);
		    } else { // ログイン失敗時
		      // エラーメッセージ
		     	login = new Login();
		     	login.setErrorMsg("ユーザー名またはパスワードが違います");
//				エラーメッセージをリクエストスコープに保存
		     	request.setAttribute("login",login);
		     	RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
			    dispatcher.forward(request, response);
		      
				}

		}
		}
	}

}
