package com.ccnx_sb15gr3_Courier.app;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class RouteDetailsActivity extends Activity {
	

	private EditText driverName;
	private EditText car;
	private EditText from;
	private EditText to;
	private EditText fuel;
	private EditText distance;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.route_details);
		
		driverName = (EditText) findViewById(R.id.driverEditTxt);
		car = (EditText) findViewById(R.id.carNameEditTxt);
		from = (EditText) findViewById(R.id.fromEditTxt);
		to = (EditText) findViewById(R.id.toEditTxt);
		fuel = (EditText) findViewById(R.id.fuelEditTxt);
		distance = (EditText) findViewById(R.id.distanceEditTxt);
		
		Bundle extras = getIntent().getExtras();
		driverName.setText(extras.getString("ROUTE"));
		
		
	}

}
