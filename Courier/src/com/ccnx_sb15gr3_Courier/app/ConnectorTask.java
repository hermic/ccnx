package com.ccnx_sb15gr3_Courier.app;

import java.io.Serializable;

import org.ccnx.android.ccnlib.JsonMessage.Request;
import org.ccnx.android.ccnlib.RouteRequest;



import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import com.ccnx_sb15gr3_Courier.app.chat.ChatCallback;
import com.ccnx_sb15gr3_Courier.app.chat.ChatWorker;
import com.ccnx_sb15gr3_Courier.model.Route;
import com.ccnx_sb15gr3_Courier.model.RouteInformation;
import com.ccnx_sb15gr3_Courier.model.RouteList;
import com.ccnx_sb15gr3_Courier.model.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

public class ConnectorTask extends AsyncTask<String, Void, String>  implements ChatCallback{
    private ChatWorker _worker;
	
    
    private boolean isConnected=false;
    private boolean isReadyToSend= false;
    private String respond="";
    
    
    
    private String requestSting;
    private Gson gson = new Gson();


	private String respondString;


	private boolean isRespondProvided;
	private Request request;

@Override
protected String doInBackground(String... urls) {
	
	

	request=Request.valueOf(urls[0]);
	
	
	switch (request) {
	
	case LOGIN:{
		
				Log.d("Request Login", urls[1]);
				
				
				User user = new User();
				user.setLogin(urls[1]);
				user.setPassword(urls[2]);
				user.setTag("LOGIN");
				user.setRequest(true);
				//requestSting=gson.toJson(user);
				ObjectMapper mapper = new ObjectMapper();
				
				
				// mapper.writeValue(System.out, user);
				try {
					 requestSting = mapper.writeValueAsString(user);
				
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					
					
				}
				Log.d("JSON", requestSting);
				
				
				
		
		break;
	}
	
	case GET_DRIVERS:{
		User user = new User();
		user.setType("KIEROWCA");
		user.setRequest(true);
		user.setTag(Request.GET_DRIVERS.toString());
		ObjectMapper mapper = new ObjectMapper();
		
		
		// mapper.writeValue(System.out, user);
		try {
			 requestSting = mapper.writeValueAsString(user);
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
		Log.d("JSON", requestSting);
		
		break;
	}
	case GET_ROUTES:{
		
		ObjectMapper mapper = new ObjectMapper();
		
		RouteList routeList = new RouteList();
		routeList.setUserID(userId);
		routeList.setTag(Request.GET_ROUTES.toString());
		routeList.setRequest(true);
		
		// mapper.writeValue(System.out, user);
		try {
			 requestSting = mapper.writeValueAsString(routeList);
		
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			
		}
	
	Log.d("JSON", requestSting);
		
		break;
	}
	case ADD_ROUTE:{
		Log.d("ADD ROUTE REQUEST", "");
		
		ObjectMapper mapper = new ObjectMapper();
		
	
			// mapper.writeValue(System.out, user);
			try {
				 requestSting = mapper.writeValueAsString(route);
			
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
			}
		
		Log.d("JSON", requestSting);
		break;
	}
	

	default:
		break;
	};

	
	
	String response = "";

	int i = 0;
	while(true){
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(isReadyToSend){
			Log.d("TASK:", "Sending..");
			try {
				Thread.sleep(1000);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			UiToCcn(requestSting);
			isReadyToSend=false;
		}
		
		Log.d("TASK:", "IS WAITING --is response flag:"+isRespondProvided);
		
		if(isRespondProvided){
			Log.d("TASK_RESPOND:", "Respond provided");
			return respond;
		}
		
	}
	

	/*while (true) {
		
		if (respond.contains("entered")) {
			Log.d("PREPERE TO SENT","");
			

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				UiToCcn(requestSting);
				
				while(!isRespondProvided){
					
					
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
				}
				return respond;
				

				
			
			
		}
	i++;}*/

}
private ProgressDialog ringProgressDialog;


private RouteRequest route;
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		 _worker =  new ChatWorker(context, this);
	     _worker.start("test", "/ccnchat", "10.0.2.2", "9695");
	     ringProgressDialog= ProgressDialog.show(context, "Proszę czekać ...", "Logowanie ...", true);
		   ringProgressDialog.setCancelable(true);
	}

	protected void UiToCcn(String s) {
		_worker.send(s);
	}

    @Override
    protected void onPostExecute(String result) {
    // loginTxtView.setText(respond);
    	 ringProgressDialog.dismiss();
    	 ccnxListener.messageToUI(respondString);
    }

	@Override
	public void recv(Serializable message) {
		Message msg = Message.obtain();
		msg.obj = message;
		Log.d("MESSAGE RECV",(String)msg.obj);

		String messageTmp=(String)msg.obj;
		if(messageTmp.contains("entered")){
			Log.d("RECIVER:", "Connected -- READY TO SEND");
			isReadyToSend=true;
		
			
		}
		respond=messageTmp;
		
		
		if(messageTmp.contains("respond\":true")){
			String tmp[]=messageTmp.split("]: ");
			respondString=tmp[1];
			Log.d("JSON RESPONSE",respondString);
			
			
			if(messageTmp.contains(Request.LOGIN.toString())){
				isRespondProvided=true;
				
			}else if(messageTmp.contains(Request.GET_DRIVERS.toString())){
				isRespondProvided=true;
				
			
		}else if(messageTmp.contains(Request.GET_ROUTES.toString())){
			isRespondProvided=true;
			
		}else if(messageTmp.contains(Request.ADD_ROUTE.toString())){
			
			isRespondProvided=true;
			
		}
		
		
						
			
		}
	
		
	
		
		//Toast.makeText(getApplicationContext(), (String) msg.obj, Toast.LENGTH_LONG).show();*/
		
	}
private int userId;
	
	public void setRouteInfo(RouteRequest route2){
		this.route = route2;
	}

	@Override
	public void ccnxServices(boolean ok) {
		if ( ok ) {
			
		
			//Toast.makeText(getApplicationContext(), "CCN Services now ready -- let's chat!", Toast.LENGTH_LONG).show();
		isConnected=true;
		Log.d("CCNX:Ready:", ""+isConnected);
		recv("READY");
			
		}else{
			//Toast.makeText(getApplicationContext(), "CCN Service error, cannot chat!", Toast.LENGTH_LONG).show();
		
		}
		
	}

private CCNxListener ccnxListener;


private Context context;


public ConnectorTask(CCNxListener ccnxListener, Context context){
	this.context = context;
    this.ccnxListener=ccnxListener;
}

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

  }

