package com.ccnx_sb15gr3_Courier.app;





import java.io.IOException;
import java.util.List;

import org.ccnx.android.ccnlib.JsonMessage.Request;

import com.ccnx_sb15gr3_Courier.model.Manager;
import com.ccnx_sb15gr3_Courier.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import android.R;
import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class ManagerDriverListFragment extends ListFragment implements CCNxListener{

	
	private String[] values ={"Test1","Test2","Test3","Test4"};
	private List<User> userList;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		View rootView =inflater.inflate(R.layout.list_content, container, false);
		
		getDriverList();
		   
		
		return rootView;
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		Intent intent = new Intent(getActivity(), DriverDetailsActivity.class);
		intent.putExtra("DRIVER", drivers[position]);
		intent.putExtra("DRIVER_DETAILS", userList.get(position));
		
		
		startActivity(intent);
	}
	
	private void getDriverList(){
		 ConnectorTask test = new ConnectorTask(this,getActivity());
			test.execute(Request.GET_DRIVERS.toString());
	}
	
	private String drivers[];

	@Override
	public void messageToUI(String message) {
		ObjectMapper readMap = new ObjectMapper();
		Manager userListsFromMessage=new Manager();
		try {
			userListsFromMessage = readMap.readValue(message, Manager.class);
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
		userList = userListsFromMessage.getUsers();
		drivers = new String[userList.size()];
		int i=0;
		for (User user : userList) {
			drivers[i]=user.getLogin();
			i++;
		}
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
			        android.R.layout.simple_list_item_1, drivers );
			    setListAdapter(adapter);
	}
}
