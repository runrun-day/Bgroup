package servlet.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Login;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUpServlet")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignUpServlet() {
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
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		
//		name="action"にvalue="signup" またはvalue="login"が入っているかで分岐
		String next = request.getParameter("next");
		System.out.println(next);
		switch(next) {
//		登録確認画面へ
		case "check" ->{
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String postcode = request.getParameter("postcode");
			String address = request.getParameter("address");
		    String tel = request.getParameter("tel");
//		    ２重check
		    String passward = request.getParameter("passward");
		    String passward2 = request.getParameter("passward2");
			
//			パスワードの確認、異なっていたら戻る
		    boolean passCheck = passward2.equals(passward);
			if(!passCheck) {
				Login login = new Login();
				login.setErrorMsg("パスワードと確認用パスワードが一致しません");
//				エラーメッセージをリクエストスコープに保存
				request.setAttribute("login",login);
		     	RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistration.jsp");
			    dispatcher.forward(request, response);
			}
			
			//電話番号とメルアドが固有であるかの確認
//			メモ見て
//			
			}
		}
	}

}
