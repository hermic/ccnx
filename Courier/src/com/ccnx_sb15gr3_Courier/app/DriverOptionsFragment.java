package com.ccnx_sb15gr3_Courier.app;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

import com.ccnx_sb15gr3_Courier.app.chat.ChatMain;
import com.ccnx_sb15gr3_Courier.model.User;

import android.R;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class DriverOptionsFragment extends ListFragment implements UserService {
	
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
		
		    if(StaticUser.getUser()!=null){
		    	Toast.makeText(getActivity(), StaticUser.getUser().toString(), Toast.LENGTH_LONG).show();
		    }
		    
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
		
		
		//Toast.makeText(getActivity(), user.getLogin().toString(), Toast.LENGTH_LONG).show();
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

	@Override
	public void userToActivity(User user) {
		//this.user=user;
		//Toast.makeText(getActivity(), user.getLogin().toString(), Toast.LENGTH_LONG).show();
		
	}

	private User user;
	
	  private void storePoints(HashMap<String,User> list) throws IOException{
			// store in file
			FileOutputStream fos = getActivity().openFileOutput("points", Context.MODE_PRIVATE);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(list);
			os.close();

	    }

		private HashMap<String,User> getStoredPoints() throws IOException, ClassNotFoundException{
			HashMap<String,User> storedList = new HashMap<String,User>();
			// get stored file
			FileInputStream fis = getActivity().openFileInput("points");
			ObjectInputStream is = new ObjectInputStream(fis);

			storedList = (HashMap<String,User>) is.readObject();
			is.close();
			return storedList;
		}
}
