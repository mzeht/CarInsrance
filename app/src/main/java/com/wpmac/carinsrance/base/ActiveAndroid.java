package com.wpmac.carinsrance.base;

import android.app.Application;

import com.wpmac.carinsrance.database.DBManager;


public final class ActiveAndroid {
    
	static Application app;
	public synchronized static void initialize(Application application) {
		initialize(application, false);
	}

	public synchronized static void initialize(Application application, boolean loggingEnabled) {
		app = application;
		
		//ThemeManager.initialize(application);
		//初始化动画管理器
		//AnimManager.initialize(application);
		//初始化文件目录
//		FileUtils.initialize();
		//初始化主题
		//ThemeManager.initialize(application);
		//初始化定位
		//GpsMyLocationProvider.initialize(application);
		
		BasePreference.initialize(application);
		
		//初始化数据库
		DBManager.initialize(application);
		//
		//DownLoadManager.initialize(application);

	}
	
	

	/**
	 * 释放资源
	 */
	public static void dispose() {
	
//		DBManager.getInstance().dispose();
//		DownLoadManager.getInstance().dispose();
	}


}