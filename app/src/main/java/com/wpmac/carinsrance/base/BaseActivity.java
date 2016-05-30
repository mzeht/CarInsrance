package com.wpmac.carinsrance.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.debug.L;
import com.wpmac.carinsrance.manager.AppManager;
import com.wpmac.carinsrance.view.ToastView;


/**
 * @fileName BaseActivity.java
 * @description Activity基类
 * @version 1.0
 */
public abstract class BaseActivity extends Activity {
	/** Appliction基类对象 **/
	//protected BaseApplication mApplication;
	protected Typeface mTypeface ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AppManager.getAppManager().addActivity(this);
		// 获取BaseApplication对象
		//mApplication = (BaseApplication) getApplication();
		//mTypeface = Typeface.createFromAsset(getAssets(), "font/SofiaProLight.ttf");
	}

	
	/** 获取当前Activity **/
	protected Activity getActivity()
	{
		return this;
	}
	
	/** 获取当前Context **/
	protected Context getContext()
	{
		return this;
	}
	
	/** 短暂显示Toast提示(来自res) **/
	protected void showShortToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show();
	}

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	/** 长时间显示Toast提示(来自res) **/
	protected void showLongToast(int resId) {
		Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
	}

	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(this, text, Toast.LENGTH_LONG).show();
		
	

	}
	
	/*******Toast居中显示*********/
	protected void showCenterToast(String text)
	{
		ToastView toast = new ToastView(this, text);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
	}
	
	protected void showCenterToast(int resId)
	{
		ToastView toast = new ToastView(this, getString(resId));
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
	}
	

	/** Debug输出Log日志 **/
	protected void showLogDebug(String tag, String msg) {
		L.d(tag, msg);
	}

	/** Error输出Log日志 **/
	protected void showLogError(String tag, String msg) {
		L.e(tag, msg);
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(this, cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		   startActivity(action, null);
		   overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	} 

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
	}
	

	
	/** 含有标题和内容的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).show();
		return alertDialog;
	}

	/** 含有标题、内容、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
										  String positiveText,
										  DialogInterface.OnClickListener onPositiveClickListener,
										  String negativeText,
										  DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** 含有标题、内容、图标、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
										  int icon, String positiveText,
										  DialogInterface.OnClickListener onPositiveClickListener,
										  String negativeText,
										  DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(this).setTitle(title)
				.setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

//	/** 带有右进右出动画的退出 **/
//	public void finish() {
//		super.finish();
//		overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
//	}

	/** 默认退出 **/
	protected void defaultFinish() {
		super.finish();
	}

	/**
	 * 隐藏软键盘
	 */
	protected void hideKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
			if (getCurrentFocus() != null)
				imm.hideSoftInputFromWindow(getCurrentFocus()
						.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		}
	}
	
	//获取屏幕宽度
	public int getDisplayMetricsWidth() {
		int i = getActivity().getWindowManager().getDefaultDisplay().getWidth();
		int j = getActivity().getWindowManager().getDefaultDisplay().getHeight();
		return Math.min(i, j);
	}
}
