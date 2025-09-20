package servlet.admin;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Product;
import service.admin.AdminProductService;

/**
 * Servlet implementation class ProductRegistrationServlet
 */
@MultipartConfig(fileSizeThreshold=1024*1024)
@WebServlet("/ProductRegistrationServlet")
public class ProductRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/admin/adminMenu.jsp");
        rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String next = request.getParameter("next");
		String nextPage = "";
		AdminProductService product = new AdminProductService(); 
		HttpSession session = request.getSession();
		
		switch (next) {
		case "check" -> {
			String name = request.getParameter("name");
			int price = Integer.parseInt(request.getParameter("price"));
			
			String uploadName = product.upload(request,getServletContext().getRealPath("/temp"));
			
			Product p = new Product(name,price,uploadName);
			request.setAttribute("product", p); 
			nextPage= "/WEB-INF/jsp/admin/registrationConfirmation.jsp";
		}
		case "commit" -> {
			request.setCharacterEncoding("UTF-8");
	        String name = request.getParameter("name");
	        int price = Integer.parseInt(request.getParameter("price"));
	        String uploadName = request.getParameter("imageRename");
	        String newFileName = product.move(getServletContext().getRealPath("/temp"), uploadName);
	        
	        if (newFileName == null) {
	            request.setAttribute("errMsg", "ファイルのアップロードに失敗しました"); 
	            nextPage= "/WEB-INF/jsp/admin/productCreate.jsp";
	        } else {
	        	//Pictureインスタンスの生成 idはテーブル登録時に連番が入るので0にする
	        	
		        	Product p = new Product(name,price,newFileName); 
		        	boolean result = product.create(p);
		            request.setAttribute("product", p); 
		            
		            if(!result) {
		                request.setAttribute("errMsg", "DBの登録に失敗しました"); 
		                nextPage= "/WEB-INF/jsp/admin/productCreate.jsp";
		            }
		            
	        }
	        nextPage= "/WEB-INF/jsp/admin/createResult.jsp";
		}
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
        rd.forward(request, response);


	}
		}
