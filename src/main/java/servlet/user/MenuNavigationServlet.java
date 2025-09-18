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

import model.Order;
import model.RegularService;
import model.UserAccount;
import service.admin.AdminOrdersService;
import service.user.RegularServiceLogic;

/**
 * Servlet implementation class MenuNavigationServlet
 */
@WebServlet("/MenuNavigationServlet")
public class MenuNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "";
		String next = request.getParameter("next");

		//注文履歴を取得
		List<Order> orderList = new ArrayList<>();
		AdminOrdersService bo = new AdminOrdersService();

		//定期便の履歴を取得
		List<RegularService> rsList = new ArrayList<>();
		RegularServiceLogic rsLogic = new RegularServiceLogic();

		if ("cartIn".equals(next)) {
			nextPage = "/WEB-INF/jsp/user/cartIn.jsp";
		} else if ("orderLog".equals(next)) {
			nextPage = "/WEB-INF/jsp/user/orderLog.jsp";
		} else if ("regularService".equals(next)) {
			// ログインユーザー情報を取得
			UserAccount account = (UserAccount) request.getSession().getAttribute("account");

			if (account != null) {
				int userId = account.getUserId();

				// ユーザーごとの定期便データを取得
				rsList = rsLogic.getOrdersListByUser(userId);

				// 合計金額を定期便だけで計算
				int total = 0;
				for (RegularService rs : rsList) {
					total += rs.getAmount();
				}

				// JSPに渡す
				request.setAttribute("rsList", rsList);
				request.setAttribute("total", total);
				
				  nextPage = "/WEB-INF/jsp/user/regularService.jsp";

				    RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
				    dispatcher.forward(request, response);
				    return;
			}
			nextPage = "/WEB-INF/jsp/user/regularService.jsp";
		} else if ("userInfomartion".equals(next)) {
			nextPage = "/WEB-INF/jsp/user/userInfomartion.jsp";
		}

		//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");

		String orderIdStr = request.getParameter("orderId");

		if (orderIdStr != null && !orderIdStr.isEmpty()) {
			try {
				int orderId = Integer.parseInt(orderIdStr);
				orderList = bo.getOrderDetail(orderId);
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		} else {
			// ログインユーザーの注文履歴一覧を取得
			UserAccount account = (UserAccount) request.getSession().getAttribute("account");
			if (account != null) {
				int userId = account.getUserId();
				orderList = bo.getOrderDetail(userId);
			}
		}

		int total = 0;
		//注文(定期便含む)の合計金額
		if (orderList != null) {
			for (Order rs : orderList) {
				total += rs.getAmount();
			}
			request.setAttribute("orderList", orderList);
		}

		request.setAttribute("total", total);

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "";
		String next = request.getParameter("next");

		if ("home".equals(next)) {
			nextPage = "/WEB-INF/jsp/user/userMenu.jsp";
		} else if ("back".equals(next)) {
			nextPage = "/WEB-INF/jsp/user/userMenu.jsp";
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
