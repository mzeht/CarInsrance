package com.wpmac.carinsrance.base;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.wpmac.carinsrance.R;
import com.wpmac.carinsrance.debug.L;
import com.wpmac.carinsrance.view.ToastView;

import org.xutils.x;

/**
 * @fileName BaseFragment.java
 * @description Fragment基类
 */
public abstract class BaseFragment extends Fragment {

	private boolean injected= false;

	protected View mView;
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		injected=true;

		return x.view().inject(this,inflater,container);
	}

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        if(injected){
            x.view().inject(this,this.getView());
        }
    }

    /**
	 * 当前Fragment是否已添加
	 * 	避免：java.lang.IllegalStateException: Fragment already added : ForecastFragment{41c59f58 #3 id=0x7f050046}
	 */
	private boolean isAddeded;
	
	public void setIsAddeded(boolean arg0){
		isAddeded = arg0;
	}
	
	public boolean isAddeded() {
		// TODO Auto-generated method stub
		return isAddeded || super.isAdded();
	}
	
	
	
	/** 绑定界面UI **/
	protected abstract void findViewById();

	/** 界面UI事件监听 **/
	protected abstract void setEventListener();

	/** 界面数据初始化 **/
	protected abstract void initData();
	
	
	
	

	/** 通过ID绑定UI **/
	protected View findViewById(int id) {
		return mView.findViewById(id);
	}

	/** 短暂显示Toast提示(来自res) **/
	protected void showShortToast(int resId) {
		Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_SHORT)
				.show();
	}

	/** Toast居中显示 **/
	protected void showCenterToast(String text) {
		ToastView toast = new ToastView(getActivity(), text);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/** Toast居中显示 **/
	protected void showCenterToast(int resId) {
		ToastView toast = new ToastView(getActivity(), getString(resId));
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

	/** 短暂显示Toast提示(来自String) **/
	protected void showShortToast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_SHORT).show();
	}

	/** 长时间显示Toast提示(来自res) **/
	protected void showLongToast(int resId) {
		Toast.makeText(getActivity(), getString(resId), Toast.LENGTH_LONG)
				.show();
	}

	/** 长时间显示Toast提示(来自String) **/
	protected void showLongToast(String text) {
		Toast.makeText(getActivity(), text, Toast.LENGTH_LONG).show();
	}

	/** Debug输出Log日志 **/
	protected void showLogDebug(String tag, String msg) {
		L.i(tag, msg);
	}

	/** Error输出Log日志 **/
	protected void showLogError(String tag, String msg) {
		L.i(tag, msg);
	}

	/** 通过Class跳转界面 **/
	protected void startActivity(Class<?> cls) {
		startActivity(cls, null);
	}

	/** 含有Bundle通过Class跳转界面 **/
	protected void startActivity(Class<?> cls, Bundle bundle) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), cls);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		getActivity().startActivity(intent);
		getActivity().overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}

	/** 通过Action跳转界面 **/
	protected void startActivity(String action) {
		startActivity(action, null);
	}

	/** 含有Bundle通过Action跳转界面 **/
	protected void startActivity(String action, Bundle bundle) {
		Intent intent = new Intent();
		intent.setAction(action);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		getActivity().startActivity(intent);
		getActivity().overridePendingTransition(R.anim.push_left_in,
				R.anim.push_left_out);
	}

	/** 含有标题和内容的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
				.setTitle(title).setMessage(message).show();
		return alertDialog;
	}

	/** 含有标题、内容、图标、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
										  int icon, String positiveText,
										  DialogInterface.OnClickListener onPositiveClickListener,
										  String negativeText,
										  DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
				.setTitle(title).setMessage(message).setIcon(icon)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}

	/** 含有标题、内容、两个按钮的对话框 **/
	protected AlertDialog showAlertDialog(String title, String message,
										  String positiveText,
										  DialogInterface.OnClickListener onPositiveClickListener,
										  String negativeText,
										  DialogInterface.OnClickListener onNegativeClickListener) {
		AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
				.setTitle(title).setMessage(message)
				.setPositiveButton(positiveText, onPositiveClickListener)
				.setNegativeButton(negativeText, onNegativeClickListener)
				.show();
		return alertDialog;
	}



	// 关闭键盘
	public void CloseKeyBoard() {
		InputMethodManager imm = (InputMethodManager) getActivity()
				.getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(getActivity().getCurrentFocus()
				.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
	}

}
