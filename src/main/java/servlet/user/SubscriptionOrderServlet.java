package servlet.user;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import service.user.RegularServiceLogic;

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

		switch (next) {
		case "orderCommit" -> { //注文情報確認

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

			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}
		case "update" -> { //修正確認

		}
		}
	}

}
