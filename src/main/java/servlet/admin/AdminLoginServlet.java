package servlet.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/adminLogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		
		//エラーメッセージ
		String errorMsg = "パスワードが違います";
		
		//name="action"にvalue="login" またはvalue="userlogin"が入っているかで分岐
		String next = request.getParameter("next");
		System.out.println(next);
		
		HttpSession session = request.getSession();
		
		switch (next) {
		case "login" -> {
			//パスワードの取得
			String password = request.getParameter("password");
			
			//パスワードが正しければ管理者メニュー画面に遷移
			if ("1234".equals(password)) {
				session.setAttribute("password", password);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/adminMenu.jsp");
				dispatcher.forward(request, response);
			//パスワードが異なればエラーメッセージを表示し、
			//管理者ログイン画面に遷移
			} else {
				request.setAttribute("errorMsg", errorMsg);
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/adminLogin.jsp");
				dispatcher.forward(request, response);
			}
		}
		case "userlogin" -> {
			//TOPページに遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("top.jsp");
			dispatcher.forward(request, response);
		}
		case "logout" -> {
			//セッションを破棄
			session.invalidate();
			
			//管理者ログインに遷移
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/adminLogin.jsp");
			dispatcher.forward(request, response);
		}
		}
	}
}
