package com.ccnx_sb15gr3_Courier.app;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.ccnx.android.ccnlib.JsonMessage.Request;
import org.ccnx.android.ccnlib.RouteRequest;

import com.ccnx_sb15gr3_Courier.model.Route;
import com.ccnx_sb15gr3_Courier.model.RouteInformation;
import com.ccnx_sb15gr3_Courier.model.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.media.MediaRouter.RouteInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DriverTravelSumm extends Activity implements CCNxListener {
	
	private EditText startAdressEdtTxt;
	private EditText endAdressEdtTxt;
	private Button sentBtn;
	private EditText distance;
	private TextView timeTxtView;
	private TextView dateTxtView;
	private RouteRequest route;
	private AndriodDate startDate;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				setContentView(R.layout.driver_travel_summ);
				
				startAdressEdtTxt = (EditText) findViewById(R.id.startAdressEditTxt);
				endAdressEdtTxt = (EditText) findViewById(R.id.endAdressEditTxt);
				sentBtn = (Button) findViewById(R.id.sendInfoBtn);
				timeTxtView = (TextView) findViewById(R.id.timeTxtView);
				distance = (EditText) findViewById(R.id.kmEditTxt);
				dateTxtView = (TextView) findViewById(R.id.dateTxtView);
				
				String date = java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
				dateTxtView.setText(date);
				
				Bundle extras = getIntent().getExtras();
				timeTxtView.setText(extras.getString("TIME"));
				startAdressEdtTxt.setText(extras.getString("START_POINT"));
			//	startDate=(AndriodDate) extras.getSerializable("START_TIME");
				sentBtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						if(distance.getText().length()<1 || endAdressEdtTxt.getText().length()<1){
							Toast.makeText(DriverTravelSumm.this, "Wypełnij wszystkie pola", Toast.LENGTH_SHORT).show();
						}else{
							uploadData();
							
						}
						
					}
				});
				
				
	}
	
	private void uploadData(){
	
			route = new RouteRequest();
			route.setRequest(true);
			route.setTag(Request.ADD_ROUTE.toString());
			route.setStart(startAdressEdtTxt.getText().toString());
			route.setKoniec(endAdressEdtTxt.getText().toString());
			//Set<RouteInformation> routeInf = new HashSet<RouteInformation>();
			
			RouteInformation routeInfo = new RouteInformation();
			route.setDistance(Double.valueOf(distance.getText().toString()));
			route.setStartDate(Calendar.getInstance().getTime());
			route.setEndDate(Calendar.getInstance().getTime());
			route.setFuel((float)(routeInfo.getDistance()*0.1));
			User user = new User();
			SharedPreferences settings = getSharedPreferences("DefaultSettings", 0);
			user.setUserId(settings.getInt("USER_ID", 2));
			route.setUserId(settings.getInt("USER_ID", 2));
			//routeInfo.setRoute(route);
			//routeInf.add(routeInfo);
			
			//route.setRouteInformations(routeInf);
			ConnectorTask test = new ConnectorTask(this,this);
			test.setRouteInfo(route);
			test.execute(Request.ADD_ROUTE.toString());
				   
			
			
	
	}

	@Override
	public void messageToUI(String message) {
		Toast.makeText(this, "Dane zostały poprawnie zapisane do bazy danych", Toast.LENGTH_SHORT).show();	
		
	}
		
		
	

}
