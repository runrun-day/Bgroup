package filter;

import java.io.IOException;
import java.util.Objects;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.UserAccount;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(
		urlPatterns = { "/LoginFilter" }, 
		servletNames = { 
				"SubscriptionOrderServlet", 
				"ConfirmContentServlet", 
				"MenuNavigationServlet", 
				"SubscriptionListServlet"
		})
public class LoginFilter extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public LoginFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		chain.doFilter(request, response);
		// アカウントをセッションスコープに保持しているか確認
		HttpSession session = ((HttpServletRequest)request).getSession();
		UserAccount account = (UserAccount) session.getAttribute("account");
		System.out.println(Objects.isNull(account));
		if(Objects.isNull(account)) {
			((HttpServletResponse)response).sendRedirect("LoginServlet");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
