package com.ccnx_sb15gr3_Courier.app;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DriverDetailsActivity extends Activity {

	private TextView driverName;
	private ListView routesLtView;
	private String values [] = {"trasa1","trasa2"};
	private String driver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driver_details);
		driverName = (TextView) findViewById(R.id.driverNameTxtView);
		routesLtView = (ListView) findViewById(R.id.routesListView);
		Bundle extras = getIntent().getExtras();
		driver=extras.getString("DRIVER");
		
		
		getRouteList(driver);
		
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, values );
		 routesLtView.setAdapter(adapter);
		 routesLtView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				Intent intent = new Intent(DriverDetailsActivity.this, RouteDetailsActivity.class);
				intent.putExtra("ROUTE", values[position]);
				startActivity(intent);
				
			}
			 
		 });
	
	}

	private void getRouteList(String driverName){
		final ProgressDialog ringProgressDialog = ProgressDialog.show(this, "Proszę czekać ...", "Pobieranie danych ...", true);
		   ringProgressDialog.setCancelable(true);
	}
	
}
