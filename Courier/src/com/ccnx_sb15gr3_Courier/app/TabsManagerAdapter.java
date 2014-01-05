package com.ccnx_sb15gr3_Courier.app;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsManagerAdapter extends FragmentPagerAdapter {

	public TabsManagerAdapter(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int index) {
		switch (index) {
		case 0:
			
			return new ManagerDriverListFragment();

		case 1:
			return new ManagerReportsFragment();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
