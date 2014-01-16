package com.ccnx_sb15gr3_Courier.app;

import java.io.Serializable;

import org.ccnx.android.ccnlib.JsonMessage.Request;



import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import com.ccnx_sb15gr3_Courier.app.chat.ChatCallback;
import com.ccnx_sb15gr3_Courier.app.chat.ChatWorker;
import com.ccnx_sb15gr3_Courier.model.User;
import com.google.gson.Gson;

public class ConnectorTask extends AsyncTask<String, Void, String>  implements ChatCallback{
    private ChatWorker _worker;
	
    
    private boolean isConnected=false;
    private boolean isReadyToSend= false;
    private String respond="";
    
    
    
    private String requestSting;
    private Gson gson = new Gson();


	private String respondString;


	private boolean isRespondProvided=false;
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
				requestSting=gson.toJson(user);
				Log.d("JSON", requestSting);
				
				
				
		
		break;
	}
	
	case GET_DRIVERS:{
		break;
	}
	case GET_ROUTES:{
		break;
	}
	

	default:
		break;
	};

	
	
	String response = "";

	int i = 0;

	while (true) {
		
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
	i++;}

}
	
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		 _worker =  new ChatWorker(context, this);
	     _worker.start("test", "/ccnchat", "10.0.2.2", "9695");
	}

	protected void UiToCcn(String s) {
		_worker.send(s);
	}

    @Override
    protected void onPostExecute(String result) {
    // loginTxtView.setText(respond);
     ccnxListener.messageToUI(respondString);
    }

	@Override
	public void recv(Serializable message) {
		Message msg = Message.obtain();
		msg.obj = message;
		Log.d("MESSAGE RECV",(String)msg.obj);
		String messageTmp=(String)msg.obj;
		respond=messageTmp;
		
		
		if(messageTmp.contains("isRespond\":true")){
			String tmp[]=messageTmp.split("]: ");
			respondString=tmp[1];
			Log.d("JSON RESPONSE",respondString);
			isRespondProvided=true;
			
			if(messageTmp.contains(Request.LOGIN.toString())){
				
				
			}else if(messageTmp.contains(Request.GET_DRIVERS.toString())){
				
			
		}else if(messageTmp.contains(Request.GET_ROUTES.toString())){
			
		}else if(messageTmp.contains(Request.ADD_ROUTE.toString())){
			
		}
		
		
						
			
		}else{
			isRespondProvided=false;
		}
	
		
	
		
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

private CCNxListener ccnxListener;


private Context context;


public ConnectorTask(CCNxListener ccnxListener, Context context){
	this.context = context;
    this.ccnxListener=ccnxListener;
}

  }

