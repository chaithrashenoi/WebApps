/*  DoRedir.java
    Sample login servlet
    Alan Riggins    
    CS645
    Spring 2013
 */
package proj2;

import java.security.*;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;


public class DoRedir extends HttpServlet {
    	private ServletContext context=null;
	private RequestDispatcher dispatcher = null;
        private String toDo = "";  
          
    public void doGet(HttpServletRequest request,
              HttpServletResponse response)
                        throws IOException, ServletException  {
        String action = request.getParameter("action");
        toDo = "../../servlet/proj2/ProcessRequest?action=" + action;
        response.sendRedirect(toDo);
    }    

    @Override
    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
        throws IOException, ServletException
    {
        doGet(request, response);
    }
}




