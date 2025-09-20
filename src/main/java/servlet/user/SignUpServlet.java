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
		response.sendRedirect("top.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
//		エラーなければデフォルトの登録確認ページへ飛ぶ
		String nextPage = "WEB-INF/jsp/user/userRegistrationConfirmation.jsp";
		
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
				UserService bo = new UserService();
//				passwardのパッケージマッチング
				boolean result = passward.matches("[A-Z0-9]{12}");
				
				if(!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "パスワードが条件を満たしていません");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
					nextPage = "WEB-INF/jsp/user/userRegistration.jsp";
				}
				
	//			passwardとpassward2が同じかの確認分岐
			    result = passward2.equals(passward);

				if(!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "パスワードと確認用パスワードが一致しません");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
					nextPage = "WEB-INF/jsp/user/userRegistration.jsp";
				}
				
				// 電話番号が固有であるかの確認分岐
				result = bo.telCheck(tel);
				
				if (!result) { //accountがNullの場合trueなのでfalseのエラー処理
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "この電話番号は既にユーザー登録済みです");
	//				登録画面へ
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
			     	nextPage = "WEB-INF/jsp/user/userRegistration.jsp";
				}
				
				//電話番号にハイフンが含まれていないか→含まれている場合true
				result = !tel.contains("-");
				
				if (!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "電話番号に-(ハイフン)が含まれています");
	//				登録画面へ
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
			     	nextPage = "WEB-INF/jsp/user/userRegistration.jsp";
				}
				
//				郵便番号のパッケージマッチング
				result = postcode.matches("[0-9]{7}")&& !postcode.contains("-");
				
				if(!result) {
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "郵便番号の入力に誤りがあります(-(ハイフン)なしの7文字)");
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
					nextPage = "WEB-INF/jsp/user/userRegistration.jsp";
				}
				
				// メルアドが固有であるかの確認分岐			
				result = bo.emailCheck(email);
				
				if (!result) { //accountがNullの場合trueなのでfalseのエラー処理
	//				エラーメッセージをリクエストスコープに保存
					request.setAttribute("errorMsg", "このメールアドレスは既にユーザー登録済みです");
	//				登録画面へ
					
					request.setAttribute("form", new UserAccount(name,email,postcode,address,tel, passward));
			     	nextPage = "WEB-INF/jsp/user/userRegistration.jsp";
				}	

				if(result) {
//					入力内容のユーザーインスタンス作成
					UserAccount form = new UserAccount(name,email,postcode,address,tel, passward); 
//					セッションスコープにユーザー登録内容を保存
					session.setAttribute("form", form);
				}
				
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
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
					
					RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/userRegistration.jsp");
				    dispatcher.forward(request, response);
				}
				//セッション削除
		        session.removeAttribute("form");
//				登録結果へ
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/registrationComplete.jsp");
			    dispatcher.forward(request, response);
			}
			case "back" ->{//確認から戻る処理
				UserAccount account = (UserAccount)session.getAttribute("form");
//				リクエストスコープに保存
				request.setAttribute("form",account);
//				セッション削除
				session.removeAttribute("form");
				RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/userRegistration.jsp");
			    dispatcher.forward(request, response);
			}
		}
	}

}
