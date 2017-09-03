package com.cn.maplefashion;

import com.cn.maplefashion.view.Alert;

import android.content.Context;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebView;


public class BowinWebChromeClient extends WebChromeClient {
	private Context ctx;
	public BowinWebChromeClient(Context ctx){
		super();
		this.ctx = ctx;
	}
	@Override     
    public boolean onJsAlert(WebView view,String url,String message,JsResult result) {
		Alert alert = new Alert(ctx,message,result,false);
		alert.doShow();
        return true;   
    }      
	@Override       
    public boolean onJsConfirm(WebView view, String url, String message,JsResult result) {        
		Alert alert = new Alert(ctx,message,result,true);
		alert.doShow();
        return true;        
    } 
}
