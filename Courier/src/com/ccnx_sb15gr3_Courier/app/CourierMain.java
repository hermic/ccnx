package com.ccnx_sb15gr3_Courier.app;

import java.io.IOException;
import java.io.Serializable;

import com.ccnx_sb15gr3_Courier.app.chat.ChatCallback;
import com.ccnx_sb15gr3_Courier.app.chat.ChatWorker;
import com.ccnx_sb15gr3_Courier.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;


import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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


public class CourierMain extends Activity implements OnClickListener, OnCheckedChangeListener,CCNxListener{
	
	private Button loginBtn;
	private CheckBox isManCheckBox;
	private boolean isManager;
	private TextView loginTxtView; 
	private TextView passwordTxtView;


	private Handler handler;
	private User user;
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
	/*	 handler = new Handler() {
			 
		        @Override
		        public void handleMessage(Message msg) {
		        super.handleMessage(msg);
		 
		        loginTxtView.setText(msg.obj.toString());
		 
		        }
		 
		    };*/

	}

	

	@Override
	public void onClick(View view) {
		
		/*ConnectorTask test = new ConnectorTask(this,this);
		test.execute("LOGIN","JA","PASSWORD");*/
		
		
	
		//User user = ClientManager.getInstance().login(loginTxtView.getText().toString(), passwordTxtView.getText().toString());
		if(loginTxtView.getText().length()<1 || passwordTxtView.getText().length()<1){
			Toast.makeText(this, R.string.emptyLoginData, Toast.LENGTH_LONG).show();
			
		}else{
			
			 ConnectorTask test = new ConnectorTask(this,this);
				test.execute("LOGIN",loginTxtView.getText().toString(),passwordTxtView.getText().toString());
		
		
	
		
		}
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(buttonView.getId()==isManCheckBox.getId() && isChecked){
			isManager=true;
		}else{
			isManager=false;
		}
		
	}
	
	 



	@Override
	public void messageToUI(String message) {
		/*Gson gson = new Gson();
		User userFromMessage = gson.fromJson(message, User.class);*/
		ObjectMapper readMap = new ObjectMapper();
		User userFromMessage=new User();
		try {
			userFromMessage = readMap.readValue(message, User.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Log.d("USER",userFromMessage.getPassword());
		Log.d("MS",message);
		this.user=userFromMessage;
		Toast.makeText(this, userFromMessage.getLogin()+"  zostałeś poprawnie zalogowany !", Toast.LENGTH_LONG).show();
		//StaticUser.setUser(user);
		//userService.userToActivity(userFromMessage);
		SharedPreferences settings = getSharedPreferences("DefaultSettings", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("USER", user.getLogin());
		editor.putInt("USER_ID", user.getUserId());
		editor.putString("IS_MANAGER", user.getType());
		editor.putString("USER_EMAIL", user.getEmail());
		editor.commit();
		if(isManager){
			
			
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
		/*Gson gson = new Gson();
		User user = gson.fromJson(message, User.class);
		Log.d("USER",user.getPassword());
		Log.d("MS",message);*/
		
	}

	
	 

}
