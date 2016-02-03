
package proj3;

import java.security.*;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.util.*;
import java.sql.*;


public class UpdateCart extends HttpServlet {
	private ServletContext context=null;
	private RequestDispatcher dispatcher = null;
	private String toDo = "";  

	@Override
	public void doGet(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
					{
		ResourceBundle rb =
				ResourceBundle.getBundle("LocalStrings",request.getLocale());
		String Sku = request.getParameter("sku");
		int Quantity = Integer.parseInt(request.getParameter("quantity"));
		context = getServletContext();
    
    Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        HttpSession session = request.getSession(true);
    
          int quantityCookie = Quantity;
        if ((cookies != null) && (cookies.length > 0) && session != null) {
          for (int i = 0; i < cookies.length; i++) {
            String skuCookie = cookies[i].getName();
            if (skuCookie.equals("jadrn026SKU" + Sku)) {
                  quantityCookie += Integer.parseInt(cookies[i].getValue());
                  cookie = cookies[i];
                  cookie.setValue(String.valueOf(quantityCookie));
            }
          }
        }
        if( cookie == null ) {
            cookie = new Cookie("jadrn026SKU" + Sku, String.valueOf(Quantity));
      }
      
      response.addCookie(cookie);
}



	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
					{
		doGet(request, response);
					}
}








