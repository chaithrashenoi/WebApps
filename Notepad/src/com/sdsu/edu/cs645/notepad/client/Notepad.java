package com.sdsu.edu.cs645.notepad.client;


import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;


public class Notepad implements EntryPoint {
	
	

	private final NotepadServiceAsync notepadService = GWT.create(NotepadService.class);
	private RichTextArea area;
	private TextBox titleTextbox;

	private HTML statusLogin;
	private HTML status;
	private String mUserName;
	private String mFileName;
	
	public void onModuleLoad() {
		loadLoginPanel();
	}

	public void loadLoginPanel() {
		FlowPanel p = buildLoginPanel();
		RootPanel.get().clear();
		RootPanel.get().add(p);
	}

	private void loadMainPanel(String userName){
		FlowPanel p = buildPanel(userName);
		RootPanel.get().clear();
		RootPanel.get().add(p);
	}
		

	
	private FlowPanel buildLoginPanel(){
		Grid g = new Grid(3,2);
		g.setWidget(0, 0, new Label("Username:"));
		final TextBox user = new TextBox();
		g.setWidget(0, 1, user);
		final PasswordTextBox pass = new PasswordTextBox();
		g.setWidget(1, 0, new Label("Password:"));
		g.setWidget(1, 1, pass);
		final FlowPanel panel = new FlowPanel();
		panel.add(new HTML("<h2>Login to Online Notepad</h2>"));
		statusLogin = new HTML();
		statusLogin.addStyleName("status");
		Button clear = new Button("Clear");
		Button submit = new Button("Submit");
		submit.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent evt) {

		checkValidLogin(user.getText(), pass.getText());
		
		}	
		
	});

		clear.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent evt) {
			user.setText("");
			pass.setText("");

		}	
		
	});

		user.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent evt) {
			statusLogin.setText("");

		}	
		
	});
		
		pass.addClickHandler(new ClickHandler(){
		public void onClick(ClickEvent evt) {
			statusLogin.setText("");

		}	
		
	});
		g.setWidget(2, 0, clear);
		g.setWidget(2, 1, submit);

		panel.add(g);
		panel.add(statusLogin);
		g.setStyleName("login-grid");
		return panel;
		
		

	}

	private void loadLoadDialog() {
		LoadDialog dialog =  new LoadDialog();
		dialog.center();
	}

	private void loadSaveDialog() {
		if(mFileName == "") {
			SaveDialog dialog =  new SaveDialog();
			dialog.center();
		}
		else {
	    	savePanel(mFileName, mUserName);
		}
	}

	@SuppressWarnings("unchecked")
	private void addAreaTimeStamp() {
		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback(){
			public void onSuccess(Object o){
				area.setHTML(""+o);
				
			}
			public void onFailure(Throwable err){
			}
		};
		notepadService.getTimeStamp(callback);
	}
	
	private FlowPanel buildPanel(String userName){
		FlowPanel main = new FlowPanel();
		main.add(new HTML("<h1> Online Notepad </h1>"));
		FlowPanel buttonPanel = new FlowPanel();
		buttonPanel.setStyleName("button-panel");
		Button load = new Button("Load");
		load.setStyleName("my-Button");
		load.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent e) {
		        	status.setHTML("");
					loadLoadDialog();
			}
		});
		
		Grid g = new Grid(1,7);

		this.mUserName = userName;
		mFileName = "";

		Button store = new Button("Save");
		store.setStyleName("my-Button");

		Button delete = new Button("Delete");
		delete.setStyleName("my-Button");
		delete.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent e) {
	        	status.setHTML("");
				deleteFile(mFileName, mUserName);
			}
		});	

		Label titleLabel = new Label("Title:");
		titleLabel.setStyleName("title-label");

		titleTextbox = new TextBox();
		titleTextbox.setStyleName("title-text");
		titleTextbox.setEnabled(false);


		Button New = new Button("New");
		New.setStyleName("my-Button");
		New.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent e) {
	        	status.setHTML("");
				mFileName = "";
				titleTextbox.setText("");
				addAreaTimeStamp();
			}
		});	
			
		
		store.addClickHandler(new ClickHandler(){
			public void onClick(ClickEvent e) {
	        	status.setHTML("");
				loadSaveDialog();
			}
		});
		
		Button logout = new Button("Logout");
		logout.setStyleName("my-Button");
		logout.addClickHandler(new ClickHandler(){
				public void onClick(ClickEvent e) {
		        	status.setHTML("");
					loadLoginPanel();
			}
		});

		g.setWidget(0, 0, titleLabel);
		g.setWidget(0, 1, titleTextbox);
		g.setWidget(0, 2, New);
		g.setWidget(0, 3, load);
		g.setWidget(0, 4, store);
		g.setWidget(0, 5, logout);
		g.setWidget(0, 6, delete);

		buttonPanel.add(g);

		main.add(buttonPanel);
		area = new RichTextArea();
		area.addClickHandler(new ClickHandler() {
		  public void onClick(ClickEvent event) {
			  status.setHTML("");
		  }
	  });
		
		addAreaTimeStamp();
		
		main.add(area);
		status = new HTML();
		status.addStyleName("status");
		main.add(status);
	
		area.setFocus(true);
		return main;
	}	
	
	private void deleteFile(String Filename, String UserName ) {
		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback(){
			public void onSuccess(Object o){
				status.setText("Note " + mFileName + " deleted");
				mFileName = "";
				area.setFocus(true);
				area.setHTML("");
				titleTextbox.setText("");
				addAreaTimeStamp();
			}
			public void onFailure(Throwable err){
				status.setText("Failed to Delete");
	
			}
		
		};

		notepadService.delete(Filename, UserName, callback);

	}

	private void savePanel(String Filename, String UserName ) {
		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback(){
			public void onSuccess(Object o){
				status.setText("Note " + mFileName + " Saved");
				area.setFocus(true);
				titleTextbox.setText(mFileName);

	
			}
			public void onFailure(Throwable err){
				status.setText("Failed to Save");
	
			}
		
		};

		notepadService.store(area.getText(), Filename, UserName, callback);

	}
	
	@SuppressWarnings("unchecked")
	private void loadPanel(String FileName, String UserName) {
		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback(){
		public void onSuccess(Object o){
			area.setHTML(""+o);
			status.setText("Note " + mFileName + " loaded");
			area.setFocus(true);
			titleTextbox.setText(mFileName);
		}
		public void onFailure(Throwable err){
			status.setText("Failed to load");

		}
		
	};
	
	notepadService.load(FileName, UserName, callback);
	
	}
	

    private  class LoadDialog extends DialogBox {
	    @SuppressWarnings("unchecked")
		public LoadDialog() {
			  final Grid g = new Grid(3,2);
			  

	    	
			final FlowPanel dialogPanel = new FlowPanel();
			  g.setWidget(0, 0, new Label("Select Note to load:"));
			
				final ListBox fileListBox = new ListBox();
				g.setWidget(1, 0, fileListBox);

			  setAnimationEnabled(true);
			  setGlassEnabled(true);
		      
			
	
			@SuppressWarnings("rawtypes")
			AsyncCallback callback = new AsyncCallback(){
				public void onSuccess(Object o){
				String[] listOfFiles = (String[]) o;
				  for (int i = 0; i < listOfFiles.length; i++) {
					  fileListBox.addItem(listOfFiles[i]);
				  }
				  fileListBox.setVisibleItemCount(8);
				  fileListBox.setSelectedIndex(0);
				  
				  fileListBox.setStyleName("listbox");

				  Button load = new Button("Load");
					g.setWidget(2, 0, load);
				  
				  
				  load.addClickHandler(new ClickHandler() {
					  public void onClick(ClickEvent event) {
						LoadDialog.this.hide();
						mFileName=fileListBox.getItemText(fileListBox.getSelectedIndex());
						System.out.println("loadPanel:" + mUserName + ":" + mFileName );
						loadPanel(mFileName, mUserName);
					  }
				  });
			      Button cancel = new Button("Cancel");
					g.setWidget(2, 1, cancel);
			      
			      cancel.addClickHandler(new ClickHandler() {
			        public void onClick(ClickEvent event) {
			        	LoadDialog.this.hide();
			        }
			      });	      
			      dialogPanel.add(g);				  
				  setWidget(dialogPanel);
				}
				public void onFailure(Throwable err){
				}
			};
			
			notepadService.loadFileList(mUserName, callback);

	    }
    }

    
    private  class SaveDialog extends DialogBox {
	    public SaveDialog() {
		  Grid g = new Grid(2,2);
		  final FlowPanel dialogPanel = new FlowPanel();
		  g.setWidget(0, 0, new Label("Title:"));
			final TextBox filename = new TextBox();
			g.setWidget(0, 1, filename);
	      
	      setAnimationEnabled(true);
	      setGlassEnabled(true);
	      Button save = new Button("Save");
	      g.setWidget(1, 0, save);

	      save.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	SaveDialog.this.hide();
	        	mFileName=filename.getText();
	        	savePanel(mFileName,mUserName );
	        }
	      });
	      Button cancel = new Button("Cancel");
			g.setWidget(1, 1, cancel);

	      cancel.addClickHandler(new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	SaveDialog.this.hide();
	        }
	      });
	      
	      
	      

	      dialogPanel.add(g);
	      setWidget(dialogPanel);
	    }
	  }

	
	@SuppressWarnings("unchecked")
	private void checkValidLogin(final String userName, String password) {
		@SuppressWarnings("rawtypes")
		AsyncCallback callback = new AsyncCallback(){
			public void onSuccess(Object o){
				
			Boolean b = (Boolean)o;
			if (b){
				statusLogin.setText("Success Username or Password");
				loadMainPanel(userName);
				
			}else {
				statusLogin.setText("Invalid Username or Password");
			}
		}
			public void onFailure(Throwable err){
				statusLogin.setText("failed to login:" + err);
	
			}
		};
		notepadService.login(userName, password,callback);

	}
	
}	
	
	
