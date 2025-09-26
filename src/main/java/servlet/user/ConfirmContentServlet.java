package servlet.user;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Order;

/**
 * Servlet implementation class ConfirmContentServlet
 */
@WebServlet("/ConfirmContentServlet")
public class ConfirmContentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ConfirmContentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("WEB-INF/jsp/user/cartIn.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nextPage = "";
		String next = request.getParameter("next");
		HttpSession session = request.getSession();

		switch (next) {
		case "order" -> {
			// カート情報をセッションから取得
			List<Order> cart = (List<Order>) session.getAttribute("cart");

			if (cart != null) {
				for (Order item : cart) {
					String productId = String.valueOf(item.getProductId());

					System.out.println("=== デバッグ確認 ===");
					System.out.println("商品ID: " + productId);
					System.out.println("num param: " + request.getParameter("num_" + productId));
					System.out.println("regular param: " + request.getParameter("regular_" + productId));
					System.out.println("span param: " + request.getParameter("span_" + productId));

					// パラメータ取得
					String numStr = request.getParameter("num_" + productId);
					String regularStr = request.getParameter("regular_" + productId);
					String spanStr = request.getParameter("span_" + productId);

					// 数量更新
					if (numStr != null && !numStr.isEmpty()) {
						int num = Integer.parseInt(numStr);
						item.setNum(num);
						item.setAmount(item.getPrice() * num);
					}

					// 定期便チェック
					item.setRegularService("true".equals(regularStr));

					// 定期期間
					if ("true".equals(regularStr) && spanStr != null && !spanStr.isEmpty()) {
						item.setSpan(Integer.parseInt(spanStr));
					} else {
						item.setSpan(0);
					}
				}
				
				int totalAmount = 0;
				if (cart != null) {
				    for (Order item : cart) {
				        totalAmount += item.getAmount(); // num × price が入ってる
				    }
				}
				request.setAttribute("totalAmount", totalAmount);

				// 更新後のcartをセッションに保存
				session.setAttribute("cart", cart);
			}

			// JSPに渡す
			request.setAttribute("cart", cart);
			nextPage = "/WEB-INF/jsp/user/cartCheck.jsp";
		}
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request, response);
	}
}
