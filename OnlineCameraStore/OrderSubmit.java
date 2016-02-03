
package proj3;

import java.security.*;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.util.*;
import java.sql.*;
import java.text.NumberFormat;


public class OrderSubmit extends HttpServlet {
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
		context = getServletContext();
		int Checkout = 0;
		String CartSummary = request.getParameter("CartSummary");
		int CartCount = Integer.parseInt(request.getParameter("CartCount"));
		context = getServletContext();
    		
    
   		
    
        HttpSession session = request.getSession(true);
    
       Cookie[] cookies = request.getCookies();
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      int count = 0;
      float grandtotal = 0;
      if ((cookies != null) && (cookies.length > 0)) {
            for (int i = 0; i < cookies.length; i++) {
                  String name = cookies[i].getName();
                   String Sku = name.replaceFirst("jadrn026SKU", "");
                if (!name.equals(Sku)) {
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                }
            }
      }
      
  
      String[] products = CartSummary.split("_");
      //out.println("CartSummaryHead:" + products[0] + "<br>");

      for(int i=0;i < CartCount; i++) {
        String[] info = products[i].split(":");
        String sku = info[0];
        int quantity = Integer.parseInt(info[1]);
        UpdateProductInfo( sku, quantity, request);
        inserMerchantOut(sku, quantity);
      }
      

     dispatcher = request.getRequestDispatcher("../../proj3.html");
      dispatcher.forward(request, response);
 
}



	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
					{
                  doGet(request, response);
					}
          

  private String inserMerchantOut(String sku, int quantity) {
		String connectionURL = "jdbc:mysql://opatija:3306/jadrn026?user=jadrn026&password=tornado";
		Connection connection = null;
		Statement statement = null;
		int result = -1;
		String response = "invalid";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			String query = "insert into merchandise_out3 values(\"" + sku + "\",\"" + quantity + "\")";
			result = statement.executeUpdate(query);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null)
					statement.close();
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
			}
		}
		return (response);
	}
  
  private int UpdateProductInfo(String Sku, int quantity, HttpServletRequest request) {
      String connectionURL = 
          "jdbc:mysql://opatija:3306/jadrn026?user=jadrn026&password=tornado";     
      Connection connection = null;
      Statement statement = null;
		int result = -1;
  
      try {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        connection = DriverManager.getConnection(connectionURL);
        statement = connection.createStatement();
  			String query = "UPDATE on_hand3 SET quantity = quantity - "+ quantity +" WHERE sku= \""+ Sku +"\"";
        result = statement.executeUpdate(query);
      }
  
  
      catch(Exception e) {
        e.printStackTrace();
      }
      finally {
        try {
          if(statement != null)
            statement.close();
          if(connection != null)
            connection.close();
        }
        catch(SQLException e) {} 
      }
      return( quantity);
  }          
  

}





