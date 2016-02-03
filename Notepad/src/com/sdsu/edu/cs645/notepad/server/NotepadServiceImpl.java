package com.sdsu.edu.cs645.notepad.server;

import com.sdsu.edu.cs645.notepad.client.NotepadService;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.text.*;
import java.util.*;
import java.io.*;



@SuppressWarnings("serial")
public class NotepadServiceImpl extends RemoteServiceServlet implements
		NotepadService {
	
    public String[] loadFileList(String UserName) throws IllegalArgumentException{
		String path = getServletContext().getRealPath("/");
		File folder = new File(path+"/notes/"+ UserName + "/");
		File[] listOfFiles = folder.listFiles();
		String[] fileList = new String[listOfFiles.length];
	    for (int i = 0; i < listOfFiles.length; i++) {
    	  fileList[i] = listOfFiles[i].getName();
	    }
	    return fileList;
    }
    
    public String load(String FileName, String UserName) throws IllegalArgumentException{
		String path = getServletContext().getRealPath("/");
		String filenameServer = path+"/notes/"+ UserName + "/" + FileName;
		String s="";
		String line;		
		System.out.println("server load:" + filenameServer );
		try{
			 
			BufferedReader in = new BufferedReader(new FileReader(filenameServer));
			while ((line= in.readLine()) !=null )
				s += line;
			in.close();
			
		}
		catch(Exception e){}
		
		s += "<br/><br/><br/>";
		s += getTimeStamp();
				
				
		return s;
    }

    public String getTimeStamp() throws IllegalArgumentException{
		Calendar calendar = Calendar.getInstance();
    	SimpleDateFormat DateFormat = new SimpleDateFormat("MM-dd-yyyy     hh:mm a");
    	String Time = DateFormat.format(calendar.getTime() );		
    	String timeStamp = "Entry time: " + Time + 
    			"<br/> -----------------------------------------"
    			+ "-----------------------------------------------------------------"
    			+ "------------------------------<br/><br/>";
		return timeStamp;
    }

    public synchronized void store(String s, String FileName, String UserName) throws IllegalArgumentException{
		String path = getServletContext().getRealPath("/");
		String filenameServer = path+"/notes/"+ UserName + "/" + FileName;
		System.out.println("filenameServer = " + filenameServer);

		try {
			
			PrintWriter out = new PrintWriter(new FileWriter(filenameServer));
			s = s.replaceAll("\r\n|\n","<br/>");
			out.print(s);
			out.close();
		}
		catch (Exception e){
		    e.printStackTrace();
		}
    }
	
    public synchronized void delete(String FileName, String UserName) throws IllegalArgumentException{
		String path = getServletContext().getRealPath("/");
		String filenameServer = path+"/notes/"+ UserName + "/" + FileName;
		System.out.println("filenameServer = " + filenameServer);

		try {
			
    		File file = new File(filenameServer);
    		file.delete();
		}
		catch (Exception e){
		    e.printStackTrace();
		}
    }

    public Boolean login(String s, String p) {
		String path = getServletContext().getRealPath("/");

		return EncryptionUtilities.isValid(path+"/passwords.dat", s,p);
   }
	

}









	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

