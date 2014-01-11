package com.ccnx_sb15gr3_Courier.app;

import java.util.Calendar;
import java.util.Date;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DriverTravelSumm extends Activity {
	
	private EditText startAdressEdtTxt;
	private EditText endAdressEdtTxt;
	private Button sentBtn;
	private EditText distance;
	private TextView timeTxtView;
	private TextView dateTxtView;
	

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
	
			final ProgressDialog ringProgressDialog = ProgressDialog.show(this, "Proszę czekać ...", "Zapisywanie danych ...", true);
				   ringProgressDialog.setCancelable(true);
				   
			
	Toast.makeText(this, "Dane zostały poprawnie zapisane do bazy danych", Toast.LENGTH_SHORT).show();	
	
	}
		
		
	

}
