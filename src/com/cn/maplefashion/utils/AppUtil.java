package com.cn.maplefashion.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.ActivityManager.RunningServiceInfo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import com.cn.maplefashion.application.AppApplication;
import com.cn.maplefashion.keeper.UserKeeper;

/**
 * 
 * @author Terence
 *
 */
public class AppUtil {
	
	/**
	 * 获取安装在本机上该应用的版本号
	 * @return
	 */
	public static String getVersion(Context context) {
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
			String version = info.versionName;
			return version; 
		} catch (Exception e) {
			e.printStackTrace();
			return "v0";
		}
		
	}
	
	/**
	 * 判断service是否已启动
	 * @param className
	 * @return true为已启动，false为未启动
	 */
	public static boolean isServiceWorked(String className ,Context context) {  
        ActivityManager myManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);  
        ArrayList<RunningServiceInfo> runningService = (ArrayList<RunningServiceInfo>) myManager.getRunningServices(30);  
        for (int i = 0; i < runningService.size(); i++) {  
            if (runningService.get(i).service.getClassName().toString().equals(className)) {  
                return true;  
            }  
        }  
        return false;  
    }  
	
	/**
	 * 初始化登录信息（退出登录、退出当前应用时执行该方法）
	 * @param context
	 */
	public static void clearLoginUser(Context context) {
		AppApplication.getInstance().setLoginUser(null);
//		AppApplication.getInstance().setActive(false);
//		UserKeeper.SaveSharepreferenceByKey(context.getApplicationContext(), UserKeeper.login_user, "false");
	}

	/** 
     * 程序是否在前台运行 
     *  
     * @return true：在前台      false：在后台
     */  
    public static boolean isAppOnForeground(Context context) {  
    	ActivityManager activityManager = (ActivityManager) context.getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);  
        String packageName = context.getApplicationContext().getPackageName();  
        List<RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();  
        if (appProcesses == null)  {
        	return false;  
        }
        for (RunningAppProcessInfo appProcess : appProcesses) {  
        	if (appProcess.processName.equals(packageName) 
        			&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) 
                            return true;  
        }  
        return false;  
    } 
	
	/**
	 * 是否有可用网络 移动
	 */
	public static boolean isNetworkConnected(Context context) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo network = cm.getActiveNetworkInfo();
		if (network != null) {
			return network.isAvailable();
		}
		return false;
	}

	/**
	 * Wifi是否可用
	 */
	public static boolean isWifiEnable(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		return wifiManager.isWifiEnabled();
	}
	
	/**
     * 验证手机号是否符合大陆的标准格式
     * @param mobiles
     * @return
     */
    public static boolean isMobileNumberValid(String mobiles) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }
	
    
    /**
     * 获取设备唯一uuid
     * 
     * @param context
     * @return
     */
    public static String getdeviceUuid (Context context) {
    	TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
     
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String uniqueId = deviceUuid.toString();
        return uniqueId+"-KP";
    }
    
    /**
	 * 初始化进度条
	 * 
	 * @param activity
	 * @param b1
	 * @param b2
	 * @return
	 */
	public static ProgressDialog getProgressDialog(Context context, boolean b1, boolean b2) {
		return ProgressDialog.show(context, "", "", b1, b2);
	}
	
	/**
	 * 判断字符串是否为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean IsEmptyOrNullString(String s) {
		return (s == null) || (s.trim().length() == 0);
	}
}
