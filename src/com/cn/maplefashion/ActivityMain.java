package com.cn.maplefashion;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.widget.ListView;

public class ActivityMain extends Activity {
	
	private Fragment contentFrame;
	private ListView leftDrawer;
//	private DrawerLayout
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
	}
	
	void init() {
		
	}

}
