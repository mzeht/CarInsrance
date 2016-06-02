package com.wpmac.carinsrance.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 数据库
 * @author Administrator
 *
 */
public class DBHelper extends SQLiteOpenHelper {

	
	//数据库名称?
	public static final String DB_NAME="myAttention.db";
	//数据库的版本
	public static final int    DB_VERSION=2;
	
	
	String myAttentionEnterpriserSql = "create table enterprises(name text primary key autoincrement, "
			+ "adress text, name text, createTime text, id text )";
	
	
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String TABLE_MY_CART_SQL =  "CREATE TABLE  IF NOT EXISTS "
				+ DBConstant.Cart.TABLE_NAME
				+"("+ DBConstant.Cart.KEY_ID+"  VARCHAR(100) not null primary key , "
				+ DBConstant.Cart.KEY_NAME +" VARCHAR(100) ,"
				+ DBConstant.Cart.KEY_PRICE +" VARCHAR(100) ,"
				+ DBConstant.Cart.KEY_PINPAI +" VARCHAR(100) ,"
				+ DBConstant.Cart.KEY_TYPE +" VARCHAR(100)"
				+");";
		  
		  db.execSQL(TABLE_MY_CART_SQL);
		
		  
//		  String TABLE_MY_HISTORY_ENTERPRISE_SQL =  "CREATE TABLE  IF NOT EXISTS "
//					+MySearchEnterprise.TABLE_NAME
//					+"("
//					+MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME+"  VARCHAR(100) not null primary key ,"
//					+ MySearchEnterprise.KEY_HISTORY_ENTERPRISE_ID  +" VARCHAR(100) ,"
//					+ MySearchEnterprise.KEY_HISTORY_COMPANY_ID +" VARCHAR(100) ,"
//					+ MySearchEnterprise.KEY_HISTORY_COMPANY_GETDATE +" VARCHAR(100) ,"
//					+ MySearchEnterprise.KEY_HISTORY_TIME +" VARCHAR(100) ,"
//					+ MySearchEnterprise.KEY_HISTORY_AREACODE +" VARCHAR(100)"
//					+");";
//
//			  db.execSQL(TABLE_MY_HISTORY_ENTERPRISE_SQL);
			  
		  				}

	
	
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		if(oldVersion!=newVersion){
			db.beginTransaction(); 
			 db.execSQL( "DROP TABLE IF EXISTS " + DBConstant.Cart.TABLE_NAME);
			 String TABLE_MY_CART_SQL =  "CREATE TABLE  IF NOT EXISTS "
						+ DBConstant.Cart.KEY_ID
						+"("
						+ DBConstant.Cart.KEY_TYPE+"  VARCHAR(100) not null primary key ,"
						+ DBConstant.Cart.KEY_PRICE  +" VARCHAR(100) ,"
						+ DBConstant.Cart.KEY_PINPAI +" VARCHAR(100) ,"
						+ DBConstant.Cart.KEY_NAME +" VARCHAR(100)"
						+");";
				  db.execSQL(TABLE_MY_CART_SQL);
			 db.setTransactionSuccessful(); 
			 db.endTransaction(); 
		}
		
	}

	
	
	

}
