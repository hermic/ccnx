package com.ccnx_sb15gr3_Courier.app;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.ccnx.android.ccnlib.JsonMessage.Request;
import org.ccnx.android.ccnlib.RouteRequest;

import com.ccnx_sb15gr3_Courier.model.RouteInformation;
import com.ccnx_sb15gr3_Courier.model.RouteList;
import com.ccnx_sb15gr3_Courier.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DriverDetailsActivity extends Activity implements CCNxListener {

	private TextView driverName;
	private ListView routesLtView;
	private String values [] = {"trasa1","trasa2"};
	private String driver;
	private User user;
	private Set<RouteInformation> routeList;
	private String[] routes;
	private RouteList routeRequest;
	private String email;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.driver_details);
		driverName = (TextView) findViewById(R.id.driverNameTxtView);
		routesLtView = (ListView) findViewById(R.id.routesListView);
		
		SharedPreferences settings = getSharedPreferences("DefaultSettings", 0);
		
		Bundle extras = getIntent().getExtras();
		try {
			user = (User) extras.getSerializable("DRIVER_DETAILS");
			
		} catch (Exception e) {
			
		}
		
		if(user !=null){
			getRouteList(user.getUserId());
		}else{
			
			int userId = settings.getInt("USER_ID", 2);
			driver = settings.getString("USER", "DAMMY");
			email = settings.getString("USER_EMAIL", "DAMMY");
			getRouteList(userId);
			
		}
		
		
		//driver=extras.getString("DRIVER");
		
		
		//getRouteList(driver);
		
		 
		 routesLtView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				Intent intent = new Intent(DriverDetailsActivity.this, RouteDetailsActivity.class);
				intent.putExtra("ROUTE", routes[position]);
				
				startActivity(intent);
				
			}
			 
		 });
	
	}

	private void getRouteList(int fromLogin) {
		ConnectorTask test = new ConnectorTask(this,this);
		test.setUserId(fromLogin);
		test.execute(Request.GET_ROUTES.toString());
		
	}

	private void initList(){
		routeList = user.getRouteInformations();
		routes =  new String[routeList.size()];
		int i=0;
		for (RouteInformation route : routeList) {
			routes[i]="<> "+ route.getRoute().getStartPoint()+" ---> "+route.getRoute().getEndPoint()+" <>";
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
		        android.R.layout.simple_list_item_1, routes);
	 routesLtView.setAdapter(adapter);
	 driverName.setText(user.getLogin());
	}
	
	private void getRouteList(String driverName){
		 
	}

	@Override
	public void messageToUI(String message) {
		
		ObjectMapper readMap = new ObjectMapper();
		routeRequest=new RouteList();
		try {
			routeRequest = readMap.readValue(message, RouteList.class);
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
		
		
		Log.d("MS",message);
		List<RouteInformation> routeInfo = routeRequest.getUserList();
		if(routeInfo!=null){
			
			routes =  new String[routeInfo.size()];
			int i=0;
			for (RouteInformation route : routeInfo) {
				routes[i]="<> "+ route.getRoute().getStartPoint()+" ---> "+route.getRoute().getEndPoint()+" <>";
			}
			ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
			        android.R.layout.simple_list_item_1, routes);
		 routesLtView.setAdapter(adapter);
		 if(routeInfo.size()>1){
			 driverName.setText("DRIVER NAME: "+routeInfo.get(0).getUser().getLogin()+" @:"+routeInfo.get(0).getUser().getEmail());
		 }
		 else{
				/*driverName.setText("DRIVER NAME: "+driver+" @:"+email);
				String info[]={"Kierowca nie posiada żadnych tras"};
				
				adapter = new ArrayAdapter<String>(this,
				        android.R.layout.simple_list_item_1,info );
			 routesLtView.setAdapter(adapter);*/
			
		}
		}
		
	 
		
	}
	
}
