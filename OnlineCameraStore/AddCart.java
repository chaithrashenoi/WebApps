
package proj3;

import java.security.*;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.util.*;
import java.sql.*;


public class AddCart extends HttpServlet {
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
		int add = Integer.parseInt(request.getParameter("add"));
		context = getServletContext();
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
       //out.println("valueadd:" + add + "<br>");

      int quantityStock = fetchSkuQuantity(Sku, request);
      //out.println("stock:" + quantityStock + "<br>");
      
      int stockRemain = quantityStock;
      int inStock = 0;
   
    Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        HttpSession session = request.getSession(true);
    
          int quantityTotal = Quantity;
        if ((cookies != null) && (cookies.length > 0) && session != null) {
          for (int i = 0; i < cookies.length; i++) {
            String skuCookie = cookies[i].getName();
            if (skuCookie.equals("jadrn026SKU" + Sku)) {
                  if( add == 1) {
                        //out.println("adding to cookie:" + cookies[i].getValue() + "<br>");
                        //Add quantity to existing value if adding from product page
                        stockRemain = stockRemain - Integer.parseInt(cookies[i].getValue());
                        quantityTotal += Integer.parseInt(cookies[i].getValue());
                  }
                  cookie = cookies[i];
                  if( quantityTotal == 0 ) {
                        cookie.setMaxAge(0);
                  }
            }
          }
        }

      //out.println("add:" + quantityTotal + "<br>");
      
      if(quantityTotal <= quantityStock ) {
            if( cookie == null ) {
                  cookie = new Cookie("jadrn026SKU" + Sku, String.valueOf(quantityTotal));
            }
            else{
                  cookie.setValue(String.valueOf(quantityTotal));
            }
            response.addCookie(cookie);
            inStock = 1;
      }

      out.println(stockRemain + "||" + inStock);
      
      //Cookie[] cookies1 = request.getCookies();
      //response.setContentType("text/html");
      //PrintWriter out = response.getWriter();
      //if ((cookies != null) && (cookies.length > 0)) {
      //      for (int i = 0; i < cookies1.length; i++) {
      //            String name = cookies1[i].getName();
      //             String Sku1 = name.replaceFirst("jadrn026SKU", "");
      //
      //          if (!name.equals(Sku1)) {
      //            out.println("sku:" + Sku1 + "quantity:" + cookies1[i].getValue() + "<br>");
      //          }
      //      }
      //}

   }



	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
					{
		doGet(request, response);
					}
          
          
          
          
          
	private int fetchSkuQuantity(String Sku, HttpServletRequest request) {
		String connectionURL = 
				"jdbc:mysql://opatija:3306/jadrn026?user=jadrn026&password=tornado";     
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String response="invalid";
      int quantity = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			String query = "SELECT quantity FROM on_hand3 WHERE sku= \"" + Sku + "\"";
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();
			resultSet.next();
			if(numCols > 0 ) {
					quantity=resultSet.getInt(1);
          if(resultSet.wasNull()) {
            quantity = 0;  
          }
			}

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








