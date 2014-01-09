package com.ccnx_sb15gr3_Courier.app;


import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DriverTravelFragment extends Fragment implements OnClickListener {

	private Button startBtn;
	private Button stopBtn;
	private TextView counterTxt;
	private TextView fromEditTxt;
	private String destinationCity;
	private int distance;

	private long startTime = 0L;

	private Handler customHandler = new Handler();

	long timeInMilliseconds = 0L;
	long updatedTime = 0L;
	private long timeSwapBuff = 0L;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub

		View rootView = inflater.inflate(R.layout.driver_travel_view,
				container, false);
		startBtn = (Button) rootView.findViewById(R.id.startBtn);
		startBtn.setOnClickListener(this);
		stopBtn = (Button) rootView.findViewById(R.id.stopBtn);
		stopBtn.setOnClickListener(this);
		counterTxt = (TextView) rootView.findViewById(R.id.timeTxtView);
		fromEditTxt = (TextView) rootView.findViewById(R.id.startEditTxt);
		// tc = new TimerCounter(this);

		return rootView;

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.startBtn: {

			if (fromEditTxt.getText().length() < 1) {
				Toast.makeText(getActivity(), "Wprowadź punkt startowy",
						Toast.LENGTH_SHORT).show();

			} else {
				fromEditTxt.setClickable(false);

				startTime = SystemClock.uptimeMillis();
				customHandler.postDelayed(updateTimerThread, 0);
				stopBtn.setText(getString(R.string.pause));

			}
			break;
		}
		case R.id.stopBtn: {
			if (stopBtn.getText().equals(getString(R.string.stop))) {

				Toast.makeText(this.getActivity(),
						counterTxt.getText().toString(), Toast.LENGTH_SHORT)
						.show();
				final Dialog confirmDialog = new Dialog(getActivity());

				confirmDialog.setContentView(R.layout.driver_count_conf);

				confirmDialog.setTitle("Potwierdzenie trasy");
				Button okBtn = (Button) confirmDialog.findViewById(R.id.okBtn);
				okBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						destinationCity = ((EditText) confirmDialog
								.findViewById(R.id.destPlaceEditTxt)).getText()
								.toString();
						distance = Integer.valueOf(((EditText) confirmDialog
								.findViewById(R.id.routeKmEditTxt)).getText()
								.toString());
						confirmDialog.dismiss();

						addRouteToDB(fromEditTxt.getText().toString(),
								destinationCity, distance);
					}
				});

				Button cncBtn = (Button) confirmDialog
						.findViewById(R.id.cncBtn);
				cncBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						confirmDialog.dismiss();

					}
				});

				confirmDialog.show();

			} else {
				timeSwapBuff += timeInMilliseconds;
				stopBtn.setText(getString(R.string.stop));
			}
			customHandler.removeCallbacks(updateTimerThread);

			// tc.stopTimer();
			// counterTxt.setText("00:00");

			break;
		}

		default:
			break;
		}

	}

	protected void addRouteToDB(String startCity, String destinationCity,
			int distance) {

	}

	private Runnable updateTimerThread = new Runnable() {

		public void run() {

			timeInMilliseconds = SystemClock.uptimeMillis() - startTime;

			updatedTime = timeSwapBuff + timeInMilliseconds;

			int secs = (int) (updatedTime / 1000);
			int mins = secs / 60;
			secs = secs % 60;
			counterTxt.setText("" + mins + ":" + String.format("%02d", secs));
			customHandler.postDelayed(this, 0);
		}

	};

}
