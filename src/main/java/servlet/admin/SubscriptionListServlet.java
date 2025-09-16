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

import model.RegularService;
import service.admin.AdminRegularServiceLogic;

/**
 * Servlet implementation class SubscriptionListServlet
 */
@WebServlet("/SubscriptionListServlet")
public class SubscriptionListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		// TODO Auto-generated method stub
		List<RegularService> orderList = new ArrayList<>();
		AdminRegularServiceLogic bo = new AdminRegularServiceLogic();
		
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
//		intに変換
		int rsorderId = Integer.parseInt(request.getParameter("rsorderId"));
		System.out.println(rsorderId);
		
		orderList = bo.getrsOrderDetail(rsorderId);
		int total = 0;
		for (RegularService rs : orderList) {
		    total += rs.getAmount(); 
		}
//		こういう書き方もある ストリーム構文
//		int total = orderList.stream().mapToInt(RegularService::getAmount).sum();
		System.out.println(total);
		if (orderList != null) { 
//		    リクエストスコープに保存
			request.setAttribute("orderList", orderList);
			request.setAttribute("total", total);
		    
		    RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/regularShipmentDetail.jsp");
		    dispatcher.forward(request, response);
		}
	}

}
