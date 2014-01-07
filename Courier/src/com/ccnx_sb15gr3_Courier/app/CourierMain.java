package com.ccnx_sb15gr3_Courier.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;


public class CourierMain extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	private Button loginBtn;
	private CheckBox isManCheckBox;
	private boolean isManager;
	private TextView loginTxtView; 
	private TextView passwordTxtView;

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
	}



	@Override
	public void onClick(View view) {
		
		if(loginTxtView.getText().length()<1 || passwordTxtView.getText().length()<1){
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
			intent.putExtra(getString(R.string.login), loginTxtView.getText());
			intent.putExtra(getString(R.string.password), passwordTxtView.getText());
			this.startActivity(intent);
			this.finish();
		}
		
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

}
