package com.cn.maplefashion.view;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cn.maplefashion.R;

public class Alert extends AlertDialog implements OnClickListener {
	private View view;
	private TextView msg;
	private TextView title;
	private Button btnOk;
	private Button btnCancel;
	private JsResult rtn;
	private OnResultListener lst;

	public interface OnResultListener {
		void OnResult(View v, boolean isOk);
	}

	private LinearLayout llTitle;

	public Alert(Context context) {
		super(context);
	}

	public Alert(Context context, String Text, JsResult result, boolean isCancel) {
		this(context, "提示", Text, result, isCancel, null);
	}

	public Alert(Context context, String Text, JsResult result,
			boolean isCancel, OnResultListener listener) {
		this(context, "提示", Text, result, isCancel, listener);
	}

	public Alert(Context context, String Title, String Text, JsResult result,
			boolean isCancel, OnResultListener listener) {
		super(context, 0);
		Context themeContext = getContext();
		rtn = result;

		LayoutInflater inflater = (LayoutInflater) themeContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//		view = inflater.inflate(R.layout.c_alert, null);
//		msg = (TextView) view.findViewById(R.id.txtMsg);
//		title = (TextView) view.findViewById(R.id.txtTitle);
//		llTitle = (LinearLayout) view.findViewById(R.id.llTitle);
//
//		btnOk = (Button) view.findViewById(R.id.btnOk);
//		btnCancel = (Button) view.findViewById(R.id.btnCancel);
//		btnOk.setOnClickListener(this);
//		btnCancel.setOnClickListener(this);
//
//		if (!isCancel) {
//			((LinearLayout) view.findViewById(R.id.llButton))
//					.removeView(btnCancel);
//		}
		lst = listener;

		msg.setText(Text);
		title.setText(Title);

		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		float width = dm.widthPixels * 0.8f;
		ViewGroup.LayoutParams lp = llTitle.getLayoutParams();
		lp.width = (int) width;
		llTitle.setLayoutParams(lp);
	}

	public void doShow() {
		show();
		setContentView(view);
	}

	@Override
	public void onClick(View v) {
//		if (v.getId() == R.id.btnOk && v instanceof Button) {
//			if (rtn != null)
//				rtn.confirm();
//			if (lst != null)
//				lst.OnResult(v, true);
//			this.dismiss();
//		}
//		if (v.getId() == R.id.btnCancel && v instanceof Button) {
//			if (rtn != null)
//				rtn.cancel();
//			if (lst != null)
//				lst.OnResult(v, false);
//			this.dismiss();
//		}
	}
}
