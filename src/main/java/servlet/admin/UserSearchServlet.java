package servlet.admin;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserAccount;
import service.admin.AdminUserAccountService;

/**
 * Servlet implementation class UserSearchServlet
 */
@WebServlet("/UserSearchServlet")
public class UserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String next = request.getParameter("next");
		HttpSession session = request.getSession();
//		異動ページ 初期値空欄
		String nextPage = "";
		switch(next) {
//		登録確認画面へ
			case "back_1" ->{
					session.removeAttribute("userInfo");
					nextPage = "WEB-INF/jsp/admin/userSearch.jsp";
				}
			case "back_2" ->{
				nextPage = "WEB-INF/jsp/admin/searchResult.jsp";
			}
			case "back_3" -> {
				nextPage = "WEB-INF/jsp/admin/adminMenu.jsp";
            }
			}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String next = request.getParameter("next");
//		異動ページ 初期値空欄
		String nextPage = "";
		HttpSession session = request.getSession();
		AdminUserAccountService bo = new AdminUserAccountService();
		
		switch(next) {
//		登録確認画面へ
			case "search" ->{
//				電話番号取得
				String tel = request.getParameter("tel");
				UserAccount userInfo = bo.findByTel(tel);
				
//				ヒットしたユーザー情報をリクエストスコープに保存
				session.setAttribute("userInfo", userInfo);
				nextPage = "WEB-INF/jsp/admin/searchResult.jsp";
				System.out.println(Objects.isNull(userInfo));
			
//			エラー処理 ユーザー情報がない
				if(Objects.isNull(userInfo)) {
					request.setAttribute("errorMsg","該当のユーザは存在しません");
					nextPage = "WEB-INF/jsp/admin/userSearch.jsp";
				}
			}

			case "deletCheck" ->{
				nextPage = "WEB-INF/jsp/admin/userDeleteCheck.jsp";
				
			}
			case "deletCommit" ->{
				int userId = Integer.parseInt(request.getParameter("userId"));
//				データベース処理
				boolean result = bo.userDeleteById(userId);
				System.out.println("削除" + result);
//				ユーザー情報削除
				session.removeAttribute("userInfo");
//				完了画面
				nextPage = "WEB-INF/jsp/admin/userDeleteResult.jsp";
				if(!result)	{
//					データベースの削除ができなかった場合
					request.setAttribute("errorMsg","ユーザ削除ができませんでした");
					nextPage = "WEB-INF/jsp/admin/userSearch.jsp";
				}
			}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
