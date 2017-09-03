package com.cn.maplefashion.application;

import org.json.JSONObject;

import android.app.Application;

import com.cn.maplefashion.keeper.UserKeeper;
import com.cn.maplefashion.utils.SysAppCrashHandler;

public class AppApplication extends Application {

	private static AppApplication mInstance;
	
	private JSONObject loginUser;
	private String token;

	public static AppApplication getInstance() {
		return mInstance;
	}
	
	@Override
	public void onCreate() {
		SysAppCrashHandler handler = SysAppCrashHandler.getInstance();
		handler.init(getApplicationContext());
		Thread.setDefaultUncaughtExceptionHandler(handler);
		super.onCreate();
		mInstance = this;
	}
	
	public String getToken() {
		if(token==null)
			return UserKeeper.getStringValue(getApplicationContext(), UserKeeper.TOKEN);
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	} 

	public JSONObject getLoginUser() {
		return loginUser;
	}

	public void setLoginUser(JSONObject loginUser) {
		this.loginUser = loginUser;
	}
}
