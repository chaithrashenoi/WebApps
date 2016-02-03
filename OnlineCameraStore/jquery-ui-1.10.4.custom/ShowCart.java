
package proj3;

import java.security.*;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.util.*;
import java.sql.*;


public class RemoveCart extends HttpServlet {
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
		String SkuRemove = request.getParameter("skuRemove");
		context = getServletContext();
    		
    
        HttpSession session = request.getSession(true);
    
       Cookie[] cookies = request.getCookies();
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      int count = 0;
      if ((cookies != null) && (cookies.length > 0)) {
            for (int i = 0; i < cookies.length; i++) {
                  String name = cookies[i].getName();
                   String Sku = name.replaceFirst("jadrn026SKU", "");
                if (!name.equals(Sku)) {
                  if(Sku.equals(SkuRemove)) {
                        cookies[i].setMaxAge(0);
                        response.addCookie(cookies[i]);
                  }
                  else {
                        FillCartInfo(Sku, Integer.parseInt(cookies[i].getValue()), count, request);
                        count++;
                  }
                }
            }
      }
      
      dispatcher = request.getRequestDispatcher("../../servlets/proj3/Cart.jsp?count="+ count);
      dispatcher.forward(request, response);

}



	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
					{
                  doGet(request, response);
					}
          

          
 	private String FillCartInfo(String Sku, int quantity, int index, HttpServletRequest request) {
		String connectionURL = 
				"jdbc:mysql://opatija:3306/jadrn026?user=jadrn026&password=tornado";     
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String response="invalid";

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			String query = "SELECT vendor_description, manufacturers_id, " +  
					"cost, product_image FROM product3 WHERE sku= \"" + Sku + "\"";
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();

			resultSet.next();
			if(numCols > 0 ) {
				String vendor= resultSet.getString(1);
				String man_id=resultSet.getString(2);
				String price=resultSet.getString(3);
				String image_name=resultSet.getString(4);

				request.setAttribute("quantity"+index, quantity);
				request.setAttribute("sku"+index, Sku);
				request.setAttribute("vendor"+index, vendor);
				request.setAttribute("man_id"+index, man_id);
				request.setAttribute("price"+index, price);
				request.setAttribute("image_name"+index, image_name);



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
		return( response);
	}

}





