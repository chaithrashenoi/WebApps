package com.sdsu.edu.cs645.notepad.server;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;

import java.util.*;
import java.io.*;


public class EncryptionUtilities{
  public static final String ALGORITHM = "SHA";
  public static final String ENCODING = "UTF-8";
  
  public static synchronized String encrypt(String plaintext){
        MessageDigest msgDigest = null;
        String encryptedPassword = null;
        try {
          msgDigest = MessageDigest.getInstance(ALGORITHM);
          msgDigest.update(plaintext.getBytes(ENCODING));
          byte b [] = msgDigest.digest();
          encryptedPassword = (new BASE64Encoder()).encode(b);
        }
        catch (NoSuchAlgorithmException e) {}
        catch (UnsupportedEncodingException e) {}
        return encryptedPassword;
   
        }

    public static boolean isValid(String filename, String username, String password ) {
      try{
       String encryptedPassword = EncryptionUtilities.encrypt(password);
        Properties p = new Properties();
        FileInputStream in = new FileInputStream(filename);
        p.load(in);
        in.close();
        if(p.getProperty(username).equals(encryptedPassword)) {
        	return true;
        }
      }
      catch(Exception e) {
        e.printStackTrace();
        return false;
      }
	return false;
    }
  
 
}