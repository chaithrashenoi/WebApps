package com.sdsu.edu.cs645.notepad.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("notepad")
public interface NotepadService extends RemoteService {
	String[] loadFileList(String userName) throws IllegalArgumentException;
	String getTimeStamp() throws IllegalArgumentException;
	String load(String fileName, String userName) throws IllegalArgumentException;
	void store(String s, String filename, String userName ) throws IllegalArgumentException;
	void delete(String filename, String userName ) throws IllegalArgumentException;
	Boolean login(String u, String p) throws IllegalArgumentException;

}
