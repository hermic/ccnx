package com.ccnx_sb15gr3_Courier.app;



import com.ccnx_sb15gr3_Courier.app.chat.ChatMain;

import android.R;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DriverOptionsFragment extends ListFragment {
	
	private String[] values ={"Przeglądaj swoje trasy","Zgloś problem firmie","TestChat"};

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView =inflater.inflate(R.layout.list_content, container, false);
		
		    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
		        android.R.layout.simple_list_item_1, values );
		    setListAdapter(adapter);
		
		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	switch (position) {
	
	case 0:{
		startActivity(new Intent(getActivity(), DriverDetailsActivity.class));
		break;
	}
	case 1:{
		startActivity(new Intent(getActivity(), DriverActivity.class));
		
		break;

		
	}

	case 2:{
		startActivity(new Intent(getActivity(), ChatMain.class));
		break;
	}
	
	default:
		break;
	}
	}

}
