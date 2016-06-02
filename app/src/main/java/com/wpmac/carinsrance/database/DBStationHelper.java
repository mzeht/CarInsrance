package com.wpmac.carinsrance.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * 数据库的操作
 * @author 陈玉伟
    2015-4-10上午11:44:38
 */
public class DBStationHelper {

	public static final String DB_NAME="station.db";
	private SQLiteDatabase db;
	public DBStationHelper(Context context, String path) {
		db = context.openOrCreateDatabase(path, Context.MODE_PRIVATE, null);
	}
	public SQLiteDatabase getDb() {
		return db;
	}


}
