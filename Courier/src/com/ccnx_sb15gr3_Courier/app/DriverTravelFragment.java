package com.ccnx_sb15gr3_Courier.app;




import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DriverTravelFragment extends Fragment implements OnClickListener{
	
	private Button startBtn;
	private Button stopBtn;
	private TextView counterTxt;



	private long startTime = 0L;

	private Handler customHandler = new Handler();

	long timeInMilliseconds = 0L;
	long updatedTime = 0L;
	private long timeSwapBuff=0L;

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
		//tc = new TimerCounter(this);
		
		return rootView;
		
	}
	
	

	@Override
	public void onClick(View v) {
		
		
		switch (v.getId()) {
		case R.id.startBtn:{
			/*test*/
			startTime = SystemClock.uptimeMillis();
			customHandler.postDelayed(updateTimerThread, 0);
			stopBtn.setText(getString(R.string.pause));
			
			
			break;
		}
		case R.id.stopBtn:{
			if(stopBtn.getText().equals(getString(R.string.stop))){
				
				Toast.makeText(this.getActivity(), counterTxt.getText().toString(), Toast.LENGTH_SHORT).show();
				
				
			}else{
				timeSwapBuff += timeInMilliseconds;
				stopBtn.setText(getString(R.string.stop));
			}
			customHandler.removeCallbacks(updateTimerThread);
			

			
			//tc.stopTimer();
			//counterTxt.setText("00:00");
			
			break;
		}
			
		

		default:
			break;
		}
		
	}


	
	private Runnable updateTimerThread = new Runnable() {

		

		

		public void run() {

			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

			updatedTime =timeSwapBuff+ timeInMilliseconds;

			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			counterTxt.setText("" + mins + ":"
					+ String.format("%02d", secs));
			customHandler.postDelayed(this, 0);
		}

	};
	
	
	
	

}

	



