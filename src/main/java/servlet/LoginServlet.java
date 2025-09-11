package servlet;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Login;
import model.LoginLogic;
import service.UserLoginLogic;

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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		
//		name="action"にvalue="signup" またはvalue="login"が入っているかで分岐
		String action = request.getParameter("action");
		switch(action) {
//		ユーザー登録へフォワード
		case "signup" ->{
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("WEB-INF/jsp/userRegistration.jsp");
					dispatcher.forward(request,response);
		}
//		メルアド/パスワード認証でユーザーホームへフォワード 
		case "login" ->{
//			フォーム入力したメルアドとパスワードの取得
			String mail = request.getParameter("mail");
			String password = request.getParameter("password");
			
			// ログイン処理の実行
		    Login login = new Login(mail, password);
		    UserLoginLogic bo = new LoginLogic();
		    boolean result = bo.execute(login);

		    // ログイン処理の成否によって処理を分岐
		    if (result) { // ログイン成功時
		      // セッションスコープにユーザーIDを保存
		      HttpSession session = request.getSession();
		      session.setAttribute("userId", userId);
		      // フォワード
		      RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/loginOK.jsp");
		      dispatcher.forward(request, response);
		    } else { // ログイン失敗時
		      // リダイレクト
		      response.sendRedirect("LoginServlet");
				}

		}
		}
	}

}
