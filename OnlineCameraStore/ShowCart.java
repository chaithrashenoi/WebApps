
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


public class ShowCart extends HttpServlet {
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
		String Address = request.getParameter("Address");
		context = getServletContext();
		int Checkout = 0;
		String check = request.getParameter("Checkout");
		context = getServletContext();
    		
    
    if(check != null ){
            Checkout = Integer.parseInt(check);
    }
   		
    
        HttpSession session = request.getSession(true);
    
       Cookie[] cookies = request.getCookies();
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      int count = 0;
      String CartSummary="";
      float grandtotal = 0;
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
                        CartSummary = CartSummary + Sku + ":" + Integer.parseInt(cookies[i].getValue()) + "_";
                        float totalprice = FillCartInfo(Sku, Integer.parseInt(cookies[i].getValue()), count, request, out);
                        count++;
                        grandtotal += totalprice;

                  }
                }
            }
      }
      
      NumberFormat formatter = NumberFormat.getNumberInstance();
      formatter.setMinimumFractionDigits(2);
      formatter.setMaximumFractionDigits(2);
      float shipping = 5*count;
      grandtotal = grandtotal + shipping;
      float tax = grandtotal * 8/100;
      grandtotal = grandtotal + tax;
      request.setAttribute("shipping", formatter.format(shipping));
      request.setAttribute("grandtotal", formatter.format(grandtotal));
      request.setAttribute("tax", formatter.format(tax));
      
      //Checkout = 1;
      if(Checkout == 1) {
            request.setAttribute("Address", Address);
            request.setAttribute("CartSummary", CartSummary);
            dispatcher = request.getRequestDispatcher("../../servlets/proj3/ReviewCheckout.jsp?count="+ count);
      }
      else {
            dispatcher = request.getRequestDispatcher("../../servlets/proj3/Cart.jsp?count="+ count);
            
      }
      dispatcher.forward(request, response);

}



	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
					{
                  doGet(request, response);
					}
          

          
 	private float FillCartInfo(String Sku, int quantity, int index, HttpServletRequest request, PrintWriter out) {
		String connectionURL = 
				"jdbc:mysql://opatija:3306/jadrn026?user=jadrn026&password=tornado";     
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String response="invalid";
    float totalprice = 0;
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
				float price=Float.parseFloat(resultSet.getString(3));
				String image_name=resultSet.getString(4);
        totalprice=price*quantity;

				request.setAttribute("quantity"+index, quantity);
				request.setAttribute("sku"+index, Sku);
				request.setAttribute("vendor"+index, vendor);
				request.setAttribute("man_id"+index, man_id);
				request.setAttribute("price"+index, price);
				request.setAttribute("totalprice"+index, Float.toString(totalprice));
				request.setAttribute("image_name"+index, image_name);
  
        out.println("sku:" + request.getAttribute("sku"+index) + "image_name:" + request.getAttribute("image_name"+index) + "<br>");
                  
      

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
		return( totalprice);
	}

}





