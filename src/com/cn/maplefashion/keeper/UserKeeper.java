package com.cn.maplefashion.keeper;

import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @Title:  UserKeeper.java
 * 
 * @Description:  TODO<请描述此文件是做什么的>
 *
 * @author Terence
 * @data:  2015-12-21 下午11:27:33 
 * @version:  V1.0 
 *
 */
public class UserKeeper {
	public static final String PREFERENCES_NAME = "Maplefashion_user_info";
	public static final String MOBILE = "Mobile";
	public static final String PASSWORD = "user_pwd";
	public static final String TOKEN = "token";
	public static final String login_user_cookie = "login_user_cookie";
	
	public static final String PHPSESSID = "PHPSESSID";	//登錄用戶SessionId
	public static final String RECENTLYGOODS = "RecentlyGoods";	//瀏覽記錄
	
	private static final String userkeeper_json_key = "userkeeper_json_key";

	/**
	 * 清空sharepreference
	 * 
	 * @param context
	 */
	public static void clear(Context context) {
		SharedPreferences pref = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
		Editor editor = pref.edit();
		editor.clear();
		editor.commit();
	}

	/**
	 * 根据key保存信息
	 * 
	 * @param key
	 * @param value
	 */
	public static void SaveSharepreferenceByKey(Context context, String key, String value) {
		try {
			SharedPreferences preference = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
			String jsonStr = preference.getString(userkeeper_json_key, null);
			JSONObject object;
			if(jsonStr==null) {
				object = new JSONObject();
			} else {
				object = new JSONObject(jsonStr);
			}
			object.put(key, value);
			SharedPreferences.Editor editor = preference.edit();
			editor = preference.edit();
			editor.putString(userkeeper_json_key, object.toString());
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据key移除信息
	 * 
	 * @param key
	 * @param value
	 */
	public static void removeSharepreferenceByKey(Context context, String key) {
		try {
			SharedPreferences preference = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
			String jsonStr = preference.getString(userkeeper_json_key, null);
			JSONObject object;
			if(jsonStr==null) {
				object = new JSONObject();
			} else {
				object = new JSONObject(jsonStr);
			}
			if(object.has(key))
				object.remove(key);
			SharedPreferences.Editor editor = preference.edit();
			editor = preference.edit();
			editor.putString(userkeeper_json_key, object.toString());
			editor.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * 根据key获取信息
	 * 
	 * @param context
	 * @param key
	 *            名称
	 * @return 键对应的值，如果找不到对应的值， 则返回null
	 */
	public static String getStringValue(final Context context, final String key) {
		try {
			SharedPreferences preference = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
			String jsonStr = preference.getString(userkeeper_json_key, null);
			JSONObject object;
			if(jsonStr==null) {
				return null;
			} else {
				object = new JSONObject(jsonStr);
			}
			if(object.has(key))
				return object.getString(key);
			else return null;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
