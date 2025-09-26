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
import jakarta.servlet.http.HttpSession;

import model.Order;
import model.Product;
import model.UserAccount;
import service.user.OrdersService;
import service.user.ProductService;
import service.user.RegularServiceLogic;
import service.user.UserService;


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
//	    メニュー画面 商品表示処理
	    	List<Product> products = new ArrayList<>();
	    	ProductService pbo = new ProductService();
	    	products = pbo.getProducts();
	    	//メニュー画面表示の商品リストを保存
	    	request.setAttribute("products", products);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/user/userMenu.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	    String nextPage = "";
	    String next = request.getParameter("next");
	    System.out.println("next = " + next);

	    // デバッグ用に例外を投げてみる
//	    if ("orderCommit".equals(next)) {
//	        throw new RuntimeException("ここまで来たよ！");
//	    }
	    
		HttpSession session = request.getSession();
		switch (next) {
		case "orderCommit" -> { // 注文確定処理
            UserAccount account = (UserAccount) session.getAttribute("account");
            List<Order> cart = (List<Order>) session.getAttribute("cart");
//     デバック用       
//            System.out.println("=== Debug: 注文登録処理開始 ===");
//            if (account != null) {
//                System.out.println("ユーザーID: " + account.getUserId());
//                System.out.println("ユーザー名: " + account.getName());
//            } else {
//                System.out.println("account が null です");
//            }
//
//            if (cart != null) {
//                System.out.println("カート内商品数: " + cart.size());
//                for (Order item : cart) {
//                    System.out.println("商品ID: " + item.getProductId()
//                                       + " / 数量: " + item.getNum()
//                                       + " / 商品名: " + item.getProductName()
//                                       + " / 金額: " + item.getAmount());
//                }
//            } else {
//                System.out.println("cart が null です");
//            }
//            処理用
            if (account != null && cart != null && !cart.isEmpty()) {
                OrdersService ordersService = new OrdersService();
                boolean success = ordersService.insertOrder(account.getUserId(), cart);

                if (success) {
                    // 登録成功 → 完了画面へ
                    nextPage = "/WEB-INF/jsp/user/cartCommit.jsp";
                    // カートをクリア
                    session.removeAttribute("cart");
                } else {
                    // 登録失敗 → 確認画面に戻す
                    request.setAttribute("msg", "注文処理に失敗しました。");
                    nextPage = "/WEB-INF/jsp/user/cartCheck.jsp";
                }
            } else {
                // セッションにユーザー情報やカートがなければ確認画面に戻す
                request.setAttribute("msg", "注文内容が存在しません。");
                nextPage = "/WEB-INF/jsp/user/cartCheck.jsp";
            }
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

		}
		case "update" ->{//登録確認から登録結果まで
			UserAccount account = (UserAccount)session.getAttribute("form");				
			UserService us = new UserService();
			boolean success = false;
			nextPage = "WEB-INF/jsp/user/userInfomartionCommit.jsp";
			if(account != null) {
				UserAccount ac = (UserAccount)session.getAttribute("account");
				int id = ac.getUserId();
				success = us.updateUserInfo(id,account);
			}
//			登録できなかった場合エラーで返す
			if(!success) {
				request.setAttribute("errorMsg", "登録に失敗しました");
				nextPage = "WEB-INF/jsp/user/userInfomartion.jsp";
			}
			//セッション削除
	        session.removeAttribute("form");
			
		}
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}

}
