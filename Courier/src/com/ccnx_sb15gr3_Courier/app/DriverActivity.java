package com.ccnx_sb15gr3_Courier.app;


import java.io.IOException;
import java.util.Date;

import com.ccnx_sb15gr3_Courier.model.Route;
import com.ccnx_sb15gr3_Courier.model.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import android.annotation.TargetApi;
import android.app.ActionBar.Tab;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.TabListener;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;



@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DriverActivity extends FragmentActivity implements TabListener,CCNxListener {
	
	
	private User user;
	private String tabs[] ={"Nowa Trasa", "Opcje"};
	private ViewPager viewPager;
    private TabsDriverAdapter mAdapter;
    private ActionBar actionBar;
    private String login;
    private String password;
	private Route route;

	@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_courier_main);
		Bundle extras = getIntent().getExtras();
		login = extras.getString(getString(R.string.login));
		password = extras.getString(getString(R.string.password));
		
		
		viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsDriverAdapter(getSupportFragmentManager());
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);    
        
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
            }
        login(login, password);
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    
	}

	
	
	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction arg1) {
		 viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab arg0, FragmentTransaction arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_courier_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle presses on the action bar items
	   if(R.id.menu_logout==item.getItemId()){
		   this.finish();
	   }
	 return super.onOptionsItemSelected(item);
	}
	
	private boolean login(String login, String password){
		
			   
			  /* ConnectorTask test = new ConnectorTask(this,this);
				test.execute("LOGIN","user2","123");*/
				
				
		
		return false;
		
	}

	public void setRoute(Route route){
		this.route = route;
	}

	@Override
	public void messageToUI(String message) {
		/*Gson gson = new Gson();
		User userFromMessage = gson.fromJson(message, User.class);*/
		/*ObjectMapper readMap = new ObjectMapper();
		User userFromMessage=new User();
		try {
			userFromMessage = readMap.readValue(message, User.class);
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
		
		Log.d("USER",userFromMessage.getPassword());
		Log.d("MS",message);
		this.user=userFromMessage;
		Toast.makeText(this, userFromMessage.getLogin()+"zostałeś poprawnie zalogowany !", Toast.LENGTH_LONG).show();
		//StaticUser.setUser(user);
		//userService.userToActivity(userFromMessage);
		SharedPreferences settings = getSharedPreferences("DefaultSettings", 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putString("USER", user.getLogin());
		editor.putInt("USER_ID", user.getUserId());
		editor.commit();*/
		
		
	}
	
	
	
	
}
