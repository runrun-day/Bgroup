package servlet.admin;

import java.io.IOException;
import java.time.LocalDateTime;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Product;
import service.admin.AdminProductService;

/**
 * Servlet implementation class ProductListServlet
 */
@WebServlet("/ProductListServlet")
public class ProductListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Product product = (Product)session.getAttribute("product");
		if(product != null) {
			session.removeAttribute("product");
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/admin/adminMenu.jsp");
	    dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String nextPage = "";
//		分岐
		String next = request.getParameter("next");
		int productId = Integer.parseInt(request.getParameter("productId"));
		AdminProductService bo = new AdminProductService();
		
		System.out.println(next);
		HttpSession session = request.getSession();
		
		switch(next) {
		case "edit" ->{//編集
			Product product = bo.getProductInfo(productId);
			session.setAttribute("product", product);
		
			nextPage ="WEB-INF/jsp/admin/productEdit.jsp";
		}
		case "editCommit" ->{//編集結果
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			boolean result = bo.editProduct(productId, name, price);
//			セッション削除
			session.removeAttribute("product");
			
			nextPage ="WEB-INF/jsp/admin/editResult.jsp";
			if(!result) {
				request.setAttribute("errorMsg", "商品の編集ができませんでした。");
				nextPage ="WEB-INF/jsp/admin/productList.jsp";
			}
		}
		case "deleteCommit" ->{//削除結果
			LocalDateTime now = LocalDateTime.now();
			boolean result = bo.deleteProduct(productId, now);
			
			nextPage ="WEB-INF/jsp/admin/deleteResult.jsp";
			if(!result) {
				request.setAttribute("errorMsg", "商品の削除ができませんでした。");
				nextPage ="WEB-INF/jsp/admin/productList.jsp";
			}
		}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
	    dispatcher.forward(request, response);
	}

}
