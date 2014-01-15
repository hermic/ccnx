package com.ccnx_sb15gr3_Courier.app;

import java.io.Serializable;

import com.ccnx_sb15gr3_Courier.app.chat.ChatCallback;
import com.ccnx_sb15gr3_Courier.app.chat.ChatWorker;

import CourierModels.User;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


public class CourierMain extends Activity implements OnClickListener, OnCheckedChangeListener{
	
	private Button loginBtn;
	private CheckBox isManCheckBox;
	private boolean isManager;
	private TextView loginTxtView; 
	private TextView passwordTxtView;
	private ComunnicationManger manager;

	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		loginBtn = (Button) findViewById(R.id.loginBnt);
		isManCheckBox = (CheckBox) findViewById(R.id.isManagerChckBx);
		loginTxtView = (TextView) findViewById(R.id.loginNameEditText);
		passwordTxtView = (TextView) findViewById(R.id.passwordEditTxt);
		loginBtn.setOnClickListener(this);
		isManCheckBox.setOnCheckedChangeListener(this);

		//ClientManager.getInstance().Init("test", "/ccnchat", "10.0.2.2", "9695", this);
		//ClientManager.getInstance().RunWorker();

		
		//boolean flag =ClientManager.getInstance().CheckForCCNX();	
		 handler = new Handler() {
			 
		        @Override
		        public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		 
		        loginTxtView.setText(msg.obj.toString());
		 
		        }
		 
		    };

	}

	

	@Override
	public void onClick(View view) {
		
		TestTask test = new TestTask();
		test.execute("test");
	
		//User user = ClientManager.getInstance().login(loginTxtView.getText().toString(), passwordTxtView.getText().toString());
		/*if(loginTxtView.getText().length()<1 || passwordTxtView.getText().length()<1){
			Toast.makeText(this, R.string.emptyLoginData, Toast.LENGTH_LONG).show();
			
		}else{
		
		
		if(view.getId()==loginBtn.getId() && isManager){
			Intent intent = new Intent(this, ManagerActivity.class);
			intent.putExtra(getString(R.string.login), loginTxtView.getText());
			intent.putExtra(getString(R.string.password), passwordTxtView.getText());
			this.startActivity(intent);
			this.finish();
			
			
		}else{
			Intent intent = new Intent(this, DriverActivity.class);
			intent.putExtra(getString(R.string.login), loginTxtView.getText().toString());
			intent.putExtra(getString(R.string.password), passwordTxtView.getText().toString());
			this.startActivity(intent);
			this.finish();
		}
		
		}*/
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(buttonView.getId()==isManCheckBox.getId() && isChecked){
			isManager=true;
		}else{
			isManager=false;
		}
		
	}
	
	 private class TestTask extends AsyncTask<String, Void, String>  implements ChatCallback{
		    private ChatWorker _worker;
			
		    private boolean isConnected=false;
		    
		    private String respond="";

		@Override
		protected String doInBackground(String... urls) {
			String response = "";

			int i = 0;

			while (true) {
				
				if (respond.length()>2) {
					Log.d("PREPERE TO SENT","");
					

						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						UiToCcn("Test" + i);
						
						while(true){
							
							if(respond.contains("hejka")){
								return response;
							
							
							}else{
								
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						}
						

						
					
					
				}
			i++;}

		}
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				 _worker =  new ChatWorker(getApplicationContext(), this);
			      _worker.start("test", "/ccnchat", "10.0.2.2", "9695");
			}

			protected void UiToCcn(String s) {
				_worker.send(s);
			}

		    @Override
		    protected void onPostExecute(String result) {
		     loginTxtView.setText(respond);
		    }

			@Override
			public void recv(Serializable message) {
				Message msg = Message.obtain();
				msg.obj = message;
				Log.d("MESSAGE RECV",(String)msg.obj);
				respond=(String)msg.obj;
				//Toast.makeText(getApplicationContext(), (String) msg.obj, Toast.LENGTH_LONG).show();*/
				
			}
	

			@Override
			public void ccnxServices(boolean ok) {
				if ( ok ) {
					//Toast.makeText(getApplicationContext(), "CCN Services now ready -- let's chat!", Toast.LENGTH_LONG).show();
				isConnected=true;
					
				}else{
					//Toast.makeText(getApplicationContext(), "CCN Service error, cannot chat!", Toast.LENGTH_LONG).show();
				
				}
				
			}
		  }

	
	 

}
