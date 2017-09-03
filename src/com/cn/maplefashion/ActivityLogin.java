package com.cn.maplefashion;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

/**
 * 登錄頁
 * 
 * @author Terence
 *
 */
public class ActivityLogin extends Activity implements OnClickListener {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		findViewById(R.id.btn_login).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_login:
			if(((EditText)findViewById(R.id.et_phone)).getText().toString().trim().length()==0) {
				
			} else if (((EditText)findViewById(R.id.et_pwd)).getText().toString().trim().length()==0) {
				
			} else {
				
			}
			break;
		}
	}
}
