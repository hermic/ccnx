package com.ccnx_sb15gr3_Courier.app;




import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class DriverTravelFragment extends Fragment implements OnClickListener{
	
	private Button startBtn;
	private Button stopBtn;
	private TextView counterTxt;
	private int secs=0;
	private int hrs=0;
	private int mins=0;
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView =inflater.inflate(R.layout.driver_travel_view, container, false);
		startBtn = (Button) rootView.findViewById(R.id.startBtn);
		startBtn.setOnClickListener(this);
		stopBtn =(Button) rootView.findViewById(R.id.stopBtn);
		stopBtn.setOnClickListener(this);
		counterTxt = (TextView) rootView.findViewById(R.id.timeTxtView);
		
		return rootView;
		
	}
	
	

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.startBtn:{
			/*test*/
			
			
			
			break;
		}
		case R.id.stopBtn:{
			
			counterTxt.setText("00:00");
			
			break;
		}
			
		

		default:
			break;
		}
		
	}


	private void setTime(){
		 {
	         ++secs;
	       
			if(secs == 60)
	             {
	             mins++;
	             secs = 0;
	         }
	         if(mins == 60)
	             {
	             hrs++;
	             secs = 0;
	             mins = 0;
	         }
	}
		 counterTxt.setText(hrs+":"+mins+":"+secs); 
	}
	
	
	
	
	

}
