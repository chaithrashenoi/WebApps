
package proj3;

import java.security.*;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.util.*;
import java.sql.*;


public class ShowCategory extends HttpServlet {
    	private ServletContext context=null;
	private RequestDispatcher dispatcher = null;
        private String toDo = "";  
          
    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
      {
				writeToFileDebug("ShowCat: called ------------------");
        ResourceBundle rb =
            ResourceBundle.getBundle("LocalStrings",request.getLocale());
         String Category = request.getParameter("category");
				writeToFileDebug("ShowCat:" + Category);


				int count = fetchCategoryInfo(Category, request);
        
        context = getServletContext();
        dispatcher = request.getRequestDispatcher("../../servlets/proj3/productList.jsp?count="+ count);
        dispatcher.forward(request, response);
    
      }

    

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
    
    
   public void writeToFileDebug(String toWrite) {
				String filename = "/srv/tomcat/webapps/jadrn026/WEB-INF/classes/proj3/debug.txt";

				try {
						PrintWriter out = new PrintWriter(
								new BufferedWriter(
										new FileWriter(filename, true)));
						out.println(toWrite);
						out.flush();
						out.close();
						}
				catch(IOException e) {
						e.printStackTrace();
						}
      }

	    
				private int fetchCategoryInfo(String Category, HttpServletRequest request) {
		String connectionURL = 
				"jdbc:mysql://opatija:3306/jadrn026?user=jadrn026&password=tornado";     
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		String response="invalid";
		int i = 0;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL);
			statement = connection.createStatement();
			String query = "SELECT manufacturers_id, product3.sku, cost, product_image, vendor_description, on_hand3.quantity " +
      "FROM product3 " +
       "LEFT OUTER JOIN on_hand3 ON on_hand3.sku = product3.sku " +
       "WHERE category_description= \"" + Category + "\" " ;
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();



			while(resultSet.next()) {
				if(numCols > 0 ) {
					String man_id = resultSet.getString(1);
					String sku=resultSet.getString(2);
					String price=resultSet.getString(3);
					String image_name=resultSet.getString(4);
					String vendor=resultSet.getString(5);

					String stock;
					int quantity=resultSet.getInt(6);
          if(resultSet.wasNull()) {
            stock="<font color=\"red\">Coming Soon</font>" ;  
          }
          else if(quantity == 0) {
            stock="<font color=\"black\">More on the Way</font>" ;  
          }
          else {
            stock="<font color=\"green\">In Stock</font>" ;  
          }
          


					request.setAttribute("sku"+i, sku);
					request.setAttribute("vendor"+i, vendor);
					request.setAttribute("man_id"+i, man_id);
					request.setAttribute("price"+i, price);
					request.setAttribute("image_name"+i, image_name);
					request.setAttribute("stock"+i, stock);
					i++;

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
						return( i );
			}
}



