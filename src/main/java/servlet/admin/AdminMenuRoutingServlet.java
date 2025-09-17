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
import model.RegularService;
import service.admin.AdminOrdersService;
import service.admin.AdminRegularServiceLogic;

/**
 * Servlet implementation class AdminMenuRoutingServlet
 */
@WebServlet("/AdminMenuRoutingServlet")
public class AdminMenuRoutingServlet extends HttpServlet {
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
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String next = request.getParameter("next");
		System.out.println(next);
//		異動ページ 初期値空欄
		String nextPage = "";
	
		switch(next) {
//		登録確認画面へ
			case "prodacts" ->{//商品一覧
			}
			case "registration" ->{//商品登録
				nextPage ="WEB-INF/jsp/admin/productCreate.jsp";
			}
			case "orders" ->{//注文一覧
			//注文一覧表示処理
				ArrayList<Order> orderList = new ArrayList<>();
				AdminOrdersService bo = new AdminOrdersService();
				orderList = bo.getOrdersList();
				if (orderList != null) { 
//				    リクエストスコープに保存
					request.setAttribute("orderList", orderList);
				    System.out.println(orderList);
				    nextPage ="WEB-INF/jsp/admin/orderList.jsp";
				}
			}
			case "regular" ->{//定期便
				List<RegularService> orderList = new ArrayList<>();
				AdminRegularServiceLogic bo = new AdminRegularServiceLogic();
				orderList = bo.getOrdersList();
				if (orderList != null) { 
//				    リクエストスコープに保存
					request.setAttribute("orderList", orderList);
				    System.out.println(orderList);
				    nextPage ="WEB-INF/jsp/admin/regularShipmentList.jsp";
				}
			}
			case "users" ->{//ユーザー検索
			}
			case "dashboard" ->{//ダッシュボード
			}
		}//switch文終わり
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
	    dispatcher.forward(request, response);
	}

}
