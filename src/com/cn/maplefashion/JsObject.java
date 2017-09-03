package com.cn.maplefashion;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.cn.maplefashion.view.Alert;

public class JsObject {
	private static String Tag="com.cn.maplefashion.JsObject";
	private Context mContext;
	private boolean isReg=false;
	private Handler mHandler = null;
//	private MyAlertDialog showAlertDialog;
	
	public JsObject(Context context,Handler handler){
		this.mContext = context;
		this.mHandler = handler;
	}
	
	@JavascriptInterface
	public void doToast(String toast) {
		Log.e(Tag, toast);
		try{
			new Alert(mContext).doShow();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	@JavascriptInterface
	public void loginSuccess() {
		mHandler.sendEmptyMessage(ActivityWeb.LOGIN_SUCCESS);
	}

//	@JavascriptInterface
//	public void openBossSelect(String tag ,String token) {
//		try{
//			//调起选择老板界面
//			Intent intent = new Intent(mContext ,SelectBossActivity.class);
//			intent.putExtra("tag", tag);
//			intent.putExtra("token", token);
//			((Activity) mContext).startActivityForResult(intent, MainWebActivity.SELECT_BOSS_REQUEST);
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
//	}
//	
//	@JavascriptInterface
//	public void popToRoot() {
//		try{
//			Intent intent = new Intent();
//			intent.putExtra("mobile", ((Activity)mContext).getIntent().getStringExtra("mobile"));
//			((Activity)mContext).setResult(Activity.RESULT_OK ,intent);
//			((Activity)mContext).overridePendingTransition(R.anim.bin_from_right, R.anim.bout_to_left);
//			((Activity)mContext).finish();
//		} catch(Exception ex) {
//			ex.printStackTrace();
//		}
//	}
//	
//	@JavascriptInterface
//	public void WeiChatPay(String msg) {
//		Log.e(Tag, msg);
//		try{
//			api = WXAPIFactory.createWXAPI(mContext,weixinAppID);
//			if(!isReg){
//			    api.registerApp(weixinAppID);  
//			    isReg = true;
//			}
//			
//			String[] arys = msg.split("&");
//			HashMap<String,String> map = new HashMap<String,String>();
//			if(arys.length!=7) return;
//			for(int i=0;i<arys.length;i++){
//				String[] v = arys[i].split("=");
//				map.put(v[0], v[1]);
//			}
//			
//			PayReq req = new PayReq();
//			req.appId			= map.get("appid");
//			req.partnerId		= map.get("partnerid");
//			req.prepayId		= map.get("prepayid");
//			req.nonceStr		= map.get("noncestr");
//			req.timeStamp		= map.get("timestamp");
//			req.packageValue	= "Sign=WXPay";
//			req.sign			= map.get("sign");
//			req.extData			= "app data"; // optional
//			api.sendReq(req);
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//		
//	}
//	@JavascriptInterface
//	public void AlisPay(String msg) {
//		Log.e(Tag, msg);
//		try{
//			String[] arys = msg.split("&");
//			String payStr = "";
//			for(int i=0;i<arys.length;i++){
//				String[] v = arys[i].split("=");
//				if(v[0].equals("sign")) v[1] = "\""+v[1]+"\"";
//				payStr += (payStr.length()>0?"&":"")+v[0]+"="+v[1];
//			}
//			Log.e(Tag, "payStr:"+payStr);
//			final String payString = payStr;
//			
//			Runnable payRunnable = new Runnable() {
//
//				@Override
//				public void run() {
//					PayTask alipay = new PayTask((Activity)mContext);
//					String result = alipay.pay(payString, true);
//
//					Message msg = new Message();
//					msg.what = 3000;
//					msg.obj = result;
//					mAlisPayHandler.sendMessage(msg);
//				}
//			};
//
//			Thread payThread = new Thread(payRunnable);
//			payThread.start();
//			
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
//	@JavascriptInterface
//	public String DeviceType() {
//		return "android";
//	}
//	
//	@JavascriptInterface
//	public void openProcess() {
//		mAlisPayHandler.sendEmptyMessage(MainWebActivity.OPEN_PROCESS);
//	}
//	
//	@JavascriptInterface
//	public void closeProcess() {
//		mAlisPayHandler.sendEmptyMessage(MainWebActivity.CLOSE_PROCESS);
//	}
//	
//	@JavascriptInterface
//	public void popUrls() {
//		mAlisPayHandler.sendEmptyMessage(MainWebActivity.POP_URLS);
//	}
//	
//	@JavascriptInterface
//	public void openPayPassword() {
//		try{
//			//打开支付密码验证界面
//			Intent intent = new Intent(mContext ,PayPasswordActivity.class);
//			intent.putExtra("action", 1);
//			((Activity) mContext).startActivityForResult(intent, MainWebActivity.PAYPASSWORD_REQUEST);
//		} catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
//	
//	@JavascriptInterface
//	public void callMobile(String name ,String phone) {
//		try {
//			//拨打电话
//			showAlertDialog("确定要拨打["+name+"]的电话?", phone);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	void showAlertDialog(String msg ,final Object mobile) {
//		showAlertDialog = AlertDialogUtil.showAlertDialog(mContext, "提示", msg, "拨打", new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				((Activity)mContext).runOnUiThread(new Runnable() {
//					public void run() {
//						if(showAlertDialog!=null)
//							showAlertDialog.dismiss();
//		                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+mobile));  
//		                mContext.startActivity(intent);
//					}
//				});
//			}
//		}, null, new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				//不取消
//				((Activity)mContext).runOnUiThread(new Runnable() {
//					public void run() {
//						if(showAlertDialog!=null)
//							showAlertDialog.dismiss();
//					}
//				});
//			}
//		});
//	}
}
