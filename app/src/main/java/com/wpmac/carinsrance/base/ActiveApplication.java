package com.wpmac.carinsrance.base;

import android.app.Application;

public class ActiveApplication extends Application {

	
	//初始化
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		ActiveAndroid.initialize(this);
		
	}
	
	
	//释放资源
	@Override
	public void onTerminate() {
		// TODO Auto-generated method stub
		super.onTerminate();
		ActiveAndroid.dispose();
	}
}
