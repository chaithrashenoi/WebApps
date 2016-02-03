package com.sdsu.edu.cs645.notepad.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>NotepadService</code>.
 */
public interface NotepadServiceAsync {
	void loadFileList(String userName, AsyncCallback<String[]> callback)
			throws IllegalArgumentException;
	void getTimeStamp(AsyncCallback<String> callback)
			throws IllegalArgumentException;
	void load(String fileName, String userName, AsyncCallback<String> callback)
			throws IllegalArgumentException;
	@SuppressWarnings("rawtypes")
	void store(String s, String filename, String userName, AsyncCallback callback)
			throws IllegalArgumentException;
	void login(String u, String p, AsyncCallback<Boolean> callback)
			throws IllegalArgumentException;
	@SuppressWarnings("rawtypes")
	void delete(String filename, String userName, AsyncCallback callback)
			throws IllegalArgumentException;

}
