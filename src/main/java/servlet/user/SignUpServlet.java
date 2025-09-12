package servlet.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserAccount;
import service.user.UserService;

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
		HttpSession session = request.getSession();
		
		String name = request.getParameter("name");
//		check
		String email = request.getParameter("email");
		String postcode = request.getParameter("postcode");
		String address = request.getParameter("address");
//		check
	    String tel = request.getParameter("tel");
//    		check
	    String passward = request.getParameter("passward");
	    String passward2 = request.getParameter("passward2");
	    
		switch(next) {
//		登録確認画面へ
			case "check" ->{
				// メルアドが固有であるかの確認分岐			
				UserService bo = new UserService();
				boolean result = bo.emailCheck(email);
				if (result) { //accountがNullの場合trueなのでfalseのエラー処理
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "このメールアドレスは既にユーザー登録済みです");
	//				登録画面へ
					
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
			     	RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistration.jsp");
				    dispatcher.forward(request, response);
				}	
				
				// 電話番号が固有であるかの確認分岐
				result = bo.telCheck(tel);
				if (result) { //accountがNullの場合trueなのでfalseのエラー処理
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "この電話番号は既にユーザー登録済みです");
	//				登録画面へ
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
			     	RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistration.jsp");
				    dispatcher.forward(request, response);
				}
				
	//			passwardとpassward2が同じかの確認分岐
			    boolean passCheck = passward2.equals(passward);
				if(!passCheck) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "パスワードと確認用パスワードが一致しません");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
			     	RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistration.jsp");
				    dispatcher.forward(request, response);
				}

//				入力内容のユーザーインスタンス作成
				UserAccount form = new UserAccount(name,email,postcode,address,tel, passward); 
//				セッションスコープに保存
				session.setAttribute("form", form);
				RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistrationConfirmation.jsp");
			    dispatcher.forward(request, response);

			}
			case "commit" ->{//登録確認から登録結果まで
				UserAccount account = (UserAccount)session.getAttribute("form");				
				UserService us = new UserService();
				boolean success = false;
				if(account != null) {
					success = us.userCreat(account);
				}
//				登録できなかった場合エラーで返す
				if(!success) {
					request.setAttribute("errorMsg", "登録に失敗しました");
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("userRegistrationConfirmation.jsp");
				    dispatcher.forward(request, response);
				}
				//セッション削除
		        session.removeAttribute("form");
				
				RequestDispatcher dispatcher = request.getRequestDispatcher("registrationComplete.jsp");
			    dispatcher.forward(request, response);
			}
			case "back" ->{//確認から戻る処理
				UserAccount account = (UserAccount)session.getAttribute("form");
//				リクエストスコープに保存
				request.setAttribute("form",account);
//				セッション削除
				session.removeAttribute("form");
			}
		}
	}

}
