package com.ccnx_sb15gr3_Courier.app;

import java.io.Serializable;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import com.ccnx_sb15gr3_Courier.app.chat.ChatCallback;
import com.ccnx_sb15gr3_Courier.app.chat.ChatWorker;


public class ConnectorTask extends AsyncTask<String, Void, String>  implements ChatCallback{
    private ChatWorker _worker;
	
    
    private boolean isConnected=false;
    private String respond="";

@Override
protected String doInBackground(String... urls) {
	String response = "";

	int i = 0;

	while (true) {
		
		if (respond.length()>2) {
			Log.d("PREPERE TO SENT","");
			

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(urls[0].contains("LOGIN"))
				UiToCcn(urls[1]+" "+urls[2]);
				
				while(true){
					
					if(respond.contains("hejka")){
						return respond;
					
					
					}else{
						
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
				

				
			
			
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
     ccnxListener.messageToUI(respond);
    }

	@Override
	public void recv(Serializable message) {
		Message msg = Message.obtain();
		msg.obj = message;
		Log.d("MESSAGE RECV",(String)msg.obj);
		respond=(String)msg.obj;
		
		
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

