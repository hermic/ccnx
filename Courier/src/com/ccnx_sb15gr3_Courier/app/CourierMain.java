package com.ccnx_sb15gr3_Courier.app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class CourierMain extends Activity implements OnClickListener {
	
	private Button loginBtn;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_view);
		loginBtn = (Button) findViewById(R.id.loginBnt);
		loginBtn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_courier_main, menu);
		return true;
	}

	@Override
	public void onClick(View view) {
		if(view.getId()==loginBtn.getId()){
			Intent intent = new Intent(this, DriverActivity.class);
			this.startActivity(intent);
		}
		
	}

}
