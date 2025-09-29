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
import model.Product;
import model.RegularService;
import model.UserAccount;
import service.user.OrdersService;
import service.user.ProductService;
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
		//	    メニュー画面 商品表示処理
		List<Product> products = new ArrayList<>();
		ProductService pbo = new ProductService();
		products = pbo.getProducts();

		//メニュー画面表示の商品リストを保存
		request.setAttribute("products", products);

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
		String numStr = request.getParameter("num");
		String regular = request.getParameter("regular");
		String spanStr = request.getParameter("span");

		int num = 1;
		if (numStr != null) {
			num = Integer.parseInt(numStr);
		}

		boolean isRegular = "true".equals(regular);
		int span = 0;
		if (spanStr != null) {
			span = Integer.parseInt(spanStr);
		}

		//注文履歴を取得
		List<Order> orderList = new ArrayList<>();
		OrdersService ordersSevice = new OrdersService();

		//定期便の履歴を取得
		List<RegularService> rsList = new ArrayList<>();
		RegularServiceLogic rsLogic = new RegularServiceLogic();

		switch (next) {
		case "カート" -> {
			nextPage = "/WEB-INF/jsp/user/cartIn.jsp";
		}
		case "注文履歴" -> {
			UserAccount account = (UserAccount) request.getSession().getAttribute("account");

			if (account != null) {
				int userId = account.getUserId();

				// ユーザー用のサービスで通常注文と定期便を取得
				OrdersService ordersService = new OrdersService();

				orderList = ordersService.getOrderListByUser(userId);

				// 日付ごとにまとめる（共通リスト）
				Map<Timestamp, List<Object>> orderHistoryMap = new LinkedHashMap<>();

				for (Order o : orderList) {
					Timestamp orderDate = o.getOrderDate();
					orderHistoryMap.computeIfAbsent(orderDate, k -> new ArrayList<>()).add(o);
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
			//			セッションスコープにユーザー情報あるのでそのままページ移動のみ
			nextPage = "/WEB-INF/jsp/user/userInfomartion.jsp";
		}
		case "cartIn" -> {
			HttpSession session = request.getSession();

			List<Order> cart = (List<Order>) session.getAttribute("cart");
			if (cart == null) {
				cart = new ArrayList<>();
			}

			// productId を取得
			String productIdStr = request.getParameter("productId");
			int productId = (productIdStr != null) ? Integer.parseInt(productIdStr) : 0;

			String productName = request.getParameter("productName");
			int price = Integer.parseInt(request.getParameter("price"));

			num = 1;
			int amount = price * num;
			boolean regularService = false;
			span = 0;

;

			// Order インスタンス作成
			Order newItem = new Order("", productName, num, price, amount, regularService, span);
			newItem.setProductId(productId); // ←ここ追加！！

			boolean exists = false;
			for (Order item : cart) {
				if (item.getProductId() == productId) { // productId で比較
					item.setNum(item.getNum() + 1);
					item.setAmount(item.getPrice() * item.getNum());
					exists = true;
					break;
				}
			}

			if (!exists) {
				cart.add(newItem);
			}

			session.setAttribute("cart", cart);
			nextPage = "/WEB-INF/jsp/user/cartIn.jsp";
		}
		case "updateCart" -> {
			HttpSession session = request.getSession();
			List<Order> cart = (List<Order>) session.getAttribute("cart");

			if (cart != null) {
				String productIdStr = request.getParameter("productId");
				if (productIdStr != null) {
					int productId = Integer.parseInt(productIdStr);

					for (Order item : cart) {
						if (item.getProductId() == productId) {
							numStr = request.getParameter("num_" + productId);
							String regularStr = request.getParameter("regular_" + productId);
							spanStr = request.getParameter("span_" + productId);

							if (numStr != null) {
								num = Integer.parseInt(numStr);
								item.setNum(num);
								item.setAmount(item.getPrice() * num);
							}

							// 定期便チェック
							item.setRegularService("true".equals(regularStr));

							// 定期便の期間
							if (spanStr != null && !spanStr.isEmpty()) {
								item.setSpan(Integer.parseInt(spanStr));
							} else {
								item.setSpan(0);
							}
							break;
						}
					}
					session.setAttribute("cart", cart);
				}
			}

			nextPage = "/WEB-INF/jsp/user/cartIn.jsp";
		}

		case "deleteCart" -> {
			HttpSession session = request.getSession();
			List<Order> cart = (List<Order>) session.getAttribute("cart");

			if (cart != null) {
				String productName = request.getParameter("productName");
				cart.removeIf(item -> item.getProductName().equals(productName));
				session.setAttribute("cart", cart);
			}

			nextPage = "/WEB-INF/jsp/user/cartIn.jsp";
		}

		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
