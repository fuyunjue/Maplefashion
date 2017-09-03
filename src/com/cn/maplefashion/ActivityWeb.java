package com.cn.maplefashion;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.cn.maplefashion.keeper.UserKeeper;
import com.cn.maplefashion.utils.AppUtil;
import com.cn.maplefashion.utils.Statck;

/**
 * web容器
 * 
 * @author Terence
 * 
 */
@SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" }) 
public class ActivityWeb extends Activity {

	private static String Tag = "com.cn.maplefashion.ActivityWeb";
	private static final String LOGIN_URL = "http://www.maplefashion.hk/main/index.php";
	public static final int LOGIN_SUCCESS = 0X01;	//登錄成功
	private Context ctx;
	private WebView web;
	private boolean isBack = false;
	private boolean isFirst = true;
	private Statck<String> urls = new Statck<String>();
	private ProgressDialog progressDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		ctx = this;

		web = (WebView) findViewById(R.id.wv_Main);

	 	CookieSyncManager.createInstance(ctx);
        
		WebSettings setting = web.getSettings();
		setting.setJavaScriptEnabled(true);
		setting.setDefaultTextEncodingName("GBK");
		setting.setCacheMode(android.webkit.WebSettings.LOAD_NO_CACHE);
		web.addJavascriptInterface(new JsObject(ctx, mHandler), "maplefashionJsObject");
		web.setWebChromeClient(new BowinWebChromeClient(ctx));

		web.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
		        CookieManager cookieManager = CookieManager.getInstance();
		        String cookie = cookieManager.getCookie(url);
		        if(cookie==null || cookie.length()==0) {
		        	cookieManager.setCookie(url, UserKeeper.getStringValue(ctx, UserKeeper.login_user_cookie));
		        	cookieManager.setAcceptCookie(true);
		        	CookieSyncManager.getInstance().sync();
		        }
				if (progressDialog == null || !progressDialog.isShowing())
					progressDialog = AppUtil.getProgressDialog(ctx, false, false);
				return false;
			}

			@Override
			public void onPageFinished(WebView view, final String url) {
				if (!isBack && (urls.getStatckSize() == 0 || !urls.getTopObjcet().equals(url))) {
					Log.e(Tag, "push:" + url);
					urls.push(url);
				} else
					isBack = false;
				runOnUiThread(new Runnable() {
					public void run() {
						if (progressDialog != null)
							progressDialog.cancel();
					}
				});
				
				if(LOGIN_URL.equals(url)) {
			        CookieManager cookieManager = CookieManager.getInstance();
			        final String CookieStr = cookieManager.getCookie(url); //获取cookie
			        
			        runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(ctx, url + " " + CookieStr, Toast.LENGTH_LONG).show();
							UserKeeper.SaveSharepreferenceByKey(ctx, UserKeeper.login_user_cookie, CookieStr);
						}
					});
				}

		        
//		        if(CookieStr == null || CookieStr.length()==0) {
//		            web.loadUrl(Constants.ENTRANCE_URL_LOGIN);
//		        } else{
//		        	web.loadUrl(Constants.ENTRANCE_URL_HOME);
//		        }
			}

			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				runOnUiThread(new Runnable() {
					public void run() {
						if (progressDialog != null)
							progressDialog.cancel();
					}
				});
				super.onReceivedError(view, errorCode, description, failingUrl);
			}
		});
		if (progressDialog == null || !progressDialog.isShowing())
			progressDialog = AppUtil.getProgressDialog(ctx, false, false);
		web.loadUrl(LOGIN_URL);
		isFirst = false;

	}

	@SuppressLint("HandlerLeak")
	Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case LOGIN_SUCCESS:
				web.loadUrl(LOGIN_URL);
				break;
				default:
					break;
			}
		};
	};

}
