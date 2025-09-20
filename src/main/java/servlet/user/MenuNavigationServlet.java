package servlet.user;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Order;
import model.RegularService;
import model.UserAccount;
import service.user.OrdersService;
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
		// ユーザーホームに戻る
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/userMenu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "";
		String next = request.getParameter("next");

		//注文履歴を取得
		List<Order> orderList = new ArrayList<>();
		OrdersService ordersSevice = new OrdersService();

		//定期便の履歴を取得
		List<RegularService> rsList = new ArrayList<>();
		RegularServiceLogic rsLogic = new RegularServiceLogic();

		switch (next) {
		case "カート" -> {
			// セッションスコープ準備
			HttpSession session = request.getSession();
			
			List<String> orders = (List<String>)session.getAttribute("orders");
			// 注文リストがなければ新たに作成
				if (orders == null) {
					orders = new ArrayList<>();
				}

				// ユーザーホーム兼商品画面から商品名を取得
				String productName = request.getParameter("productName");
				
				// 注文リストに商品名を追加
				if (productName != null && !productName.isEmpty()) {
					orders.add(productName);
				}
				System.out.println("productName" + orders);
			
			// セッションスコープに保存
			session.setAttribute("orders", orders);
			
			nextPage = "/WEB-INF/jsp/user/cartIn.jsp";
		}
		case "注文履歴" -> {
			UserAccount account = (UserAccount) request.getSession().getAttribute("account");

			if (account != null) {
				int userId = account.getUserId();

				// ユーザー用のサービスで通常注文と定期便を取得
				OrdersService ordersService = new OrdersService();
				orderList = ordersService.getOrdersByUser(userId);
				rsList = rsLogic.getOrdersListByUser(userId);

				// 日付ごとにまとめる（共通リスト）
				Map<Timestamp, List<Object>> orderHistoryMap = new LinkedHashMap<>();

				// 通常注文を追加
				for (Order o : orderList) {
					Timestamp orderDate = o.getOrderDate();
					orderHistoryMap.computeIfAbsent(orderDate, k -> new ArrayList<>()).add(o);
				}

				// 定期便注文を追加
				for (RegularService rs : rsList) {
					Timestamp orderDate = rs.getOrderDate();
					orderHistoryMap.computeIfAbsent(orderDate, k -> new ArrayList<>()).add(rs);
				}
				request.setAttribute("orderHistoryMap", orderHistoryMap);
			}
			nextPage = "/WEB-INF/jsp/user/orderLog.jsp";
		}
		case "定期便" -> {
			// ログインユーザー情報を取得
			UserAccount account = (UserAccount) request.getSession().getAttribute("account");

			if (account != null) {
				int userId = account.getUserId();

				// ユーザーごとの定期便データを取得
				rsList = rsLogic.getOrdersListByUser(userId);

				// 日付毎の定期便商品一覧を表示
				Map<Timestamp, List<RegularService>> regularServiceList = new LinkedHashMap<>();
				Map<Timestamp, Integer> dailyTotals = new HashMap<>();

				// 定期便商品を取得
				for (RegularService rs : rsList) {
					Timestamp orderDate = rs.getOrderDate();

					// 商品リストを日付ごとに格納
					regularServiceList.computeIfAbsent(orderDate, k -> new ArrayList<>()).add(rs);

					// 日ごとの合計金額を定期便だけで計算
					String dateKey = orderDate.toString();
					int currentAmount = dailyTotals.getOrDefault(orderDate, 0);
					dailyTotals.put(orderDate, currentAmount + rs.getAmount());
				}

				// JSPに渡す
				request.setAttribute("regularServiceList", regularServiceList);
				request.setAttribute("dailyTotals", dailyTotals);

				nextPage = "/WEB-INF/jsp/user/regularService.jsp";

				RequestDispatcher rd = request.getRequestDispatcher(nextPage);
				rd.forward(request, response);
				return;
			}
			nextPage = "/WEB-INF/jsp/user/regularService.jsp";
		}
		case "ユーザー情報" -> {
			nextPage = "/WEB-INF/jsp/user/userInfomartion.jsp";
		}
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
