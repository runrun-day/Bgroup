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
import service.user.RegularServiceLogic;
import service.user.UserService;

/**
 * Servlet implementation class SubscriptionOrderServlet
 */
@WebServlet("/SubscriptionOrderServlet")
public class SubscriptionOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private RegularServiceLogic service = new RegularServiceLogic();	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubscriptionOrderServlet() {
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
		String nextPage = "";
		String next = request.getParameter("next");
		HttpSession session = request.getSession();
		switch (next) {
		case "orderCommit" -> { //注文情報確認
			nextPage = "/WEB-INF/jsp/user/cartCommit.jsp";
		}
		case "rescission" -> { //ユーザー定期便
			nextPage = "/WEB-INF/jsp/user/regularServiceDelete.jsp";

			// 日本語の文字化けを防ぐ（フォームの入力を正しく受け取るため）
			request.setCharacterEncoding("UTF-8");

			// フォームから送られてきた値を取得
			String regularServiceIdStr = request.getParameter("regularServiceId");
			System.out.println("rs:" + regularServiceIdStr);
			int regularServiceId = Integer.parseInt(regularServiceIdStr);

			boolean success = false;
			// サービス層を呼び出して削除処理を実行
			success = service.deleteRegularService(regularServiceId);

			// 削除に失敗した場合はメッセージをセット
			if (!success) {
				request.setAttribute("msg", "登録解除できませんでした。");
			}

		}
		case "update" ->{//登録確認から登録結果まで
			UserAccount account = (UserAccount)session.getAttribute("form");				
			UserService us = new UserService();
			boolean success = false;
			nextPage = "WEB-INF/jsp/user/userInfomartionCommit.jsp";
			if(account != null) {
				UserAccount ac = (UserAccount)session.getAttribute("account");
				int id = ac.getUserId();
				success = us.updateUserInfo(id,account);
			}
//			登録できなかった場合エラーで返す
			if(!success) {
				request.setAttribute("errorMsg", "登録に失敗しました");
				nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
			}
			//セッション削除
	        session.removeAttribute("form");
			
		}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
