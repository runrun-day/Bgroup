package servlet.admin;

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
import service.admin.AdminOrdersService;

/**
 * Servlet implementation class OrderListServlet
 */
@WebServlet("/OrderListServlet")
public class OrderListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//管理者メニューへ戻る
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/adminMenu.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Order> orderList = new ArrayList<>();
		AdminOrdersService bo = new AdminOrdersService();
		
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
//		intに変換
		int orderId = Integer.parseInt(request.getParameter("orderId"));
		
		orderList = bo.getOrderDetail(orderId);
		int total = 0;
		for (Order rs : orderList) {
		    total += rs.getAmount(); 
		}
		if (orderList != null) { 
//		    リクエストスコープに保存
			request.setAttribute("orderList", orderList);
			request.setAttribute("total", total);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/orderDetail.jsp");
		    dispatcher.forward(request, response);
		}
	}

}
