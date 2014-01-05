package com.ccnx_sb15gr3_Courier.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class CourierMain extends Activity implements OnClickListener, OnCheckedChangeListener {
	
	private Button loginBtn;
	private CheckBox isManCheckBox;
	private boolean isManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		loginBtn = (Button) findViewById(R.id.loginBnt);
		isManCheckBox = (CheckBox) findViewById(R.id.isManagerChckBx);
		loginBtn.setOnClickListener(this);
		isManCheckBox.setOnCheckedChangeListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_courier_main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		if(view.getId()==loginBtn.getId() && isManager){
			Intent intent = new Intent(this, ManagerActivity.class);
			this.startActivity(intent);
			
			
		}else{
			Intent intent = new Intent(this, DriverActivity.class);
			this.startActivity(intent);
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
