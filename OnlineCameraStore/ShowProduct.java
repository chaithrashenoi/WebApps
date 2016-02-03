
package proj3;

import java.security.*;

import java.io.*;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.http.Cookie;
import java.util.*;
import java.sql.*;


public class ShowProduct extends HttpServlet {
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
		fetchSkuInfo(Sku, request);
		context = getServletContext();
		dispatcher = request.getRequestDispatcher("../../servlets/proj3/product.jsp");
		dispatcher.forward(request, response);



					}



	@Override
	public void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException
					{
		doGet(request, response);
					}




	private String fetchSkuInfo(String Sku, HttpServletRequest request) {
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
			String query = "SELECT vendor_description, manufacturers_id, description, features, " +  
					"cost, retail, product_image, on_hand3.quantity FROM product3 " +
          "LEFT OUTER JOIN on_hand3 ON on_hand3.sku = product3.sku " +
          "WHERE product3.sku= \"" + Sku + "\"";
			resultSet = statement.executeQuery(query);
			ResultSetMetaData md = resultSet.getMetaData();
			int numCols = md.getColumnCount();

			resultSet.next();
			if(numCols > 0 ) {
				String vendor= resultSet.getString(1);
				String man_id=resultSet.getString(2);
				Blob descriptionBlob =resultSet.getBlob(3);
				Blob featuresBlob =resultSet.getBlob(4);
				String price=resultSet.getString(5);
				String retail=resultSet.getString(6);
				String image_name=resultSet.getString(7);

					String stock;
					int quantity=resultSet.getInt(8);
          if(resultSet.wasNull()) {
            stock="<font color=\"red\">Coming Soon</font>" ;  
          }
          else if(quantity == 0) {
            stock="<font color=\"black\">More on the Way</font>" ;  
          }
          else {
            stock="<font color=\"#006B24\">In Stock</font>" ;  
          }
        

				int blobLengthDesc = (int) descriptionBlob.length();  
				byte[] blobAsBytesDesc = descriptionBlob.getBytes(1, blobLengthDesc);
				String description = new String(blobAsBytesDesc);
				descriptionBlob.free();

				int blobLengthFeatures = (int) featuresBlob.length();  
				byte[] blobAsBytesFeature = featuresBlob.getBytes(1, blobLengthFeatures);
				String features = new String(blobAsBytesFeature);
				featuresBlob.free();

        features = features.replace("-", "");
        features = features.replace("\\", "\n");
        features = features.replace("\n", "<li>");
        features = "<ul><li>" + features + "</ul>";

				request.setAttribute("sku", Sku);
				request.setAttribute("vendor", vendor);
				request.setAttribute("man_id", man_id);
				request.setAttribute("price", price);
				request.setAttribute("image_name", image_name);
				request.setAttribute("description", description);
				request.setAttribute("features", features);
				request.setAttribute("retail", retail);
				request.setAttribute("stock", stock);



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




