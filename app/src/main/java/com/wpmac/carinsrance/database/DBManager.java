package com.wpmac.carinsrance.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.wpmac.carinsrance.bean.ShangPingBean;


/**
 * 数据库操作管理
 * 
 * @author Administrator
 * 
 */
public class DBManager {

	public static final String TAG = "TAG_DB";
	public static final String FORMAT = "^[a-z,A-Z].*$";
	public static DBManager manager;
	public static Context context;
	private Object lock = new Object();
	// 扩展数据库表
	private SQLiteDatabase stationDb;
	// 本地数据库
	private SQLiteDatabase localDb;

	public static void initialize(Context paramContext) {
		manager = new DBManager(paramContext);
	}

	public void dispose() {
		if (stationDb != null) {
			stationDb.close();
		}
	}

	/**
	 * 获取数据库操作单例
	 * 
	 * @return
	 */
	public synchronized static DBManager getInstance() {
		return manager;
	}

	public DBManager(Context paramContext) {
		this.context = paramContext;

		localDb = new DBHelper(paramContext).getWritableDatabase();

	}
	
	/**
	 * 查询所有搜索的企业
	 * 
	 * @return
	 */
//	public ArrayList<SearchEnterpriseBean> queryAllMySearchEnterprises() {
//		ArrayList<SearchEnterpriseBean> result = new ArrayList<SearchEnterpriseBean>();
//		Cursor cursor = null;
//
//		try {
//
//			synchronized (lock) {
//				cursor = localDb
//						.rawQuery(
//								"SELECT * from "+ MySearchEnterprise.TABLE_NAME ,null);
//				if (cursor != null && cursor.getCount() > 0) {
//					//L.i("查询到站点数量：" + cursor.getCount());
//					while (cursor.moveToNext()) {
//						SearchEnterpriseBean bean = new SearchEnterpriseBean();
//						bean.setCorp_name(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME)));
//						bean.setCorp_id(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_ENTERPRISE_ID)));
//						bean.setCompany_ID(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_COMPANY_ID)));
//						bean.setGet_date(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_COMPANY_GETDATE)));
//						bean.setTime(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_TIME)));
//						bean.setRes_date(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_AREACODE)));
//
//						result.add(bean);
//					}
//				}
//
//			}
//
//		} catch (Exception e) {
//			//L.e(TAG, e.getMessage());
//
//		} finally {
//			if (cursor != null && !cursor.isClosed()) {
//				cursor.close();
//			}
//		}
//
//		return result;
//	}
	/**
	 * 删除搜索历史
	 */
	public void deletehistroy() {
		synchronized (lock) {
          String sql= "delete from "+ DBConstant.Cart.TABLE_NAME;
          localDb.execSQL(sql);
			

		}
	}
//	/**
//	 * 通过企业名称搜索的企业
//	 *
//	 * @return
//	 */
//	public ArrayList<ShangPingBean.ResultsBean> queryAllMySearchEnterprisesByName(String name) {
//		ArrayList<ShangPingBean.ResultsBean> result = new ArrayList<ShangPingBean.ResultsBean>();
//		Cursor cursor = null;
//
//		try {
//
//			synchronized (lock) {
//				cursor = localDb
//						.rawQuery(
//								"SELECT * from "+ DBConstant.Cart.TABLE_NAME
//								+" where "+MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME+" like "+"'%"+name+"%'"+";",null);
//				if (cursor != null && cursor.getCount() > 0) {
//					L.i("查询到站点数量：" + cursor.getCount());
//					while (cursor.moveToNext()) {
//						SearchEnterpriseBean bean = new SearchEnterpriseBean();
//						bean.setCorp_name(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME)));
//						bean.setCorp_id(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_ENTERPRISE_ID)));
//						bean.setCompany_ID(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_COMPANY_ID)));
//						bean.setGet_date(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_COMPANY_GETDATE)));
//						bean.setTime(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_TIME)));
//
//						bean.setRes_date(cursor.getString(cursor
//								.getColumnIndex(MySearchEnterprise.KEY_HISTORY_AREACODE)));
//
//						result.add(bean);
//					}
//				}
//
//			}
//
//		} catch (Exception e) {
//			//L.e(TAG, e.getMessage());
//
//		} finally {
//			if (cursor != null && !cursor.isClosed()) {
//				cursor.close();
//			}
//		}
//
//		return result;
//	}
	/**
//	 * 搜索的企业插入数据库
//	 *
//	 * @return
//	 */
//	public void  insertOrUpdateSearchComapny(ShangPingBean.ResultsBean shangpingbean)
//	{
//		Cursor cursor = null;
//
//		try {
//
//			synchronized (lock) {
//			ContentValues cv = new ContentValues();
//			cv.put(DBConstant.Cart.KEY_NAME, shangpingbean.getName());
//			cv.put(MySearchEnterprise.KEY_HISTORY_ENTERPRISE_ID, searchEnterpriseBean.getCorp_id());
//			cv.put(MySearchEnterprise.KEY_HISTORY_COMPANY_ID, searchEnterpriseBean.getCompany_ID());
//			cv.put(MySearchEnterprise.KEY_HISTORY_COMPANY_GETDATE, searchEnterpriseBean.getGet_date());
//			cv.put(MySearchEnterprise.KEY_HISTORY_TIME, searchEnterpriseBean.getTime());
//			cv.put(MySearchEnterprise.KEY_HISTORY_AREACODE, searchEnterpriseBean.getRes_date());
//
//			cursor = localDb
//					.rawQuery(
//							"SELECT * from "
//									+ DBConstant.Cart.TABLE_NAME
//									+ " where "
//									+ MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME
//									+ " = '"
//									+ searchEnterpriseBean.getCorp_name()+"'",
//							null);
//			if (cursor != null && cursor.getCount() > 0) {
//				//L.i("查询到公司数量：" + cursor.getCount());
//				localDb.update(
//						MySearchEnterprise.TABLE_NAME,
//						cv,
//						MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME
//								+ " = '"
//								+ searchEnterpriseBean.getCorp_name()+ "'",
//						null);
//			} else {
//				localDb.insert(MySearchEnterprise.TABLE_NAME, null, cv);
//			}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (cursor != null) {
//				cursor.close();
//				cursor = null;
//			}
//		}
//
//	}
//
//	/**
//	 * 搜索的企业插入数据库
//	 *
//	 * @return
//	 */
//	public void  insertOrUpdateSearchEnterprise(SearchEnterpriseBean searchEnterpriseBean)
//	{
//		Cursor cursor = null;
//
//		try {
//
//			synchronized (lock) {
//			ContentValues cv = new ContentValues();
//			cv.put(MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME, searchEnterpriseBean.getCorp_name());
//			cv.put(MySearchEnterprise.KEY_HISTORY_ENTERPRISE_ID, searchEnterpriseBean.getCorp_id());
//			cv.put(MySearchEnterprise.KEY_HISTORY_COMPANY_ID, searchEnterpriseBean.getCompany_ID());
//			cv.put(MySearchEnterprise.KEY_HISTORY_COMPANY_GETDATE, searchEnterpriseBean.getGet_date());
//			cursor = localDb
//					.rawQuery(
//							"SELECT * from "
//									+ MySearchEnterprise.TABLE_NAME
//									+ " where "
//									+ MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME
//									+ " = '"
//									+ searchEnterpriseBean.getCorp_name()+"'",
//							null);
//			if (cursor != null && cursor.getCount() > 0) {
//				//L.i("查询到公司数量：" + cursor.getCount());
//				localDb.update(
//						MySearchEnterprise.TABLE_NAME,
//						cv,
//						MySearchEnterprise.KEY_HISTORY_ENTERPRISE_NAME
//								+ " = '"
//								+ searchEnterpriseBean.getCorp_name()+ "'"
//								+" and " +MySearchEnterprise.KEY_HISTORY_ENTERPRISE_ID
//								+" = '"+searchEnterpriseBean.getCorp_id()+"'",
//						null);
//			} else {
//				localDb.insert(MySearchEnterprise.TABLE_NAME, null, cv);
//			}
//			}
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (cursor != null) {
//				cursor.close();
//				cursor = null;
//			}
//		}
//
//	}
//
//	/**
//	 * 查询所有的关注企业
//	 *
//	 * @return
//	 */
//	public ArrayList<SearchEnterpriseBean> queryAllMyAttentionEnterprises() {
//		ArrayList<SearchEnterpriseBean> result = new ArrayList<SearchEnterpriseBean>();
//		Cursor cursor = null;
//
//		try {
//
//			synchronized (lock) {
//				cursor = localDb
//						.rawQuery(
//								"SELECT * from "+ MyAttentionEnterprise.TABLE_NAME,null);
//				if (cursor != null && cursor.getCount() > 0) {
//					//L.i("查询到站点数量：" + cursor.getCount());
//					while (cursor.moveToNext()) {
//						SearchEnterpriseBean bean=new SearchEnterpriseBean();
//						bean.setCorp_name(cursor.getString(cursor
//								.getColumnIndex(MyAttentionEnterprise.KEY_ENTERPRISE_NAME)));
//						bean.setCompany_ID(cursor.getString(cursor
//								.getColumnIndex(MyAttentionEnterprise.KEY_COMPANY_ID)));
//						bean.setCorp_id(cursor.getString(cursor
//								.getColumnIndex(MyAttentionEnterprise.KEY_CORP_ID)));
//						bean.setGet_date(cursor.getString(cursor
//								.getColumnIndex(MyAttentionEnterprise.KEY_GET_DATE)));
//
//						result.add(bean);
//					}
//				}
//
//			}
//
//		} catch (Exception e) {
//			//L.e(TAG, e.getMessage());
//
//		} finally {
//			if (cursor != null && !cursor.isClosed()) {
//				cursor.close();
//			}
//		}
//
//		return result;
//	}
	

	public void deleteAllCart() {
		synchronized (lock) {
			String sql= "delete from "+ DBConstant.Cart.TABLE_NAME;
			localDb.execSQL(sql);


		}
	}
	/**
	 * 添加到购物车
	 */
	public void insertOrUpdateCart(ShangPingBean.ResultsBean bean)
	{
		Cursor cursor = null;

		try {

			synchronized (lock) { 
			ContentValues cv = new ContentValues();
			cv.put(DBConstant.Cart.KEY_NAME, bean.getName());
			cv.put(DBConstant.Cart.KEY_ID, bean.getId());
			cv.put(DBConstant.Cart.KEY_TYPE, bean.getType());
			cv.put(DBConstant.Cart.KEY_PINPAI, bean.getPinpai());
			cv.put(DBConstant.Cart.KEY_PRICE, bean.getPrice());

			cursor = localDb
					.rawQuery(
							"SELECT * from "
									+ DBConstant.Cart.TABLE_NAME
									+ " where "
									+ DBConstant.Cart.KEY_ID
									+ " = '"
									+ bean.getId()+"'",
							null);
			if (cursor != null && cursor.getCount() > 0) {
				//L.i("查询到公司数量：" + cursor.getCount());
				localDb.update(
						DBConstant.Cart.TABLE_NAME,
						cv,
						DBConstant.Cart.KEY_ID
								+ " = '"
								+ bean.getId()+ "'",
						null);
			} else {
				localDb.insert(DBConstant.Cart.TABLE_NAME, null, cv);
			}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
				cursor = null;
			}
		}

	}
	
	
//	/**
//	 * 根据企业名称查询企业
//	 *
//	 * @param earthStationId
//	 * @return
//	 */
//	public int queryMyAttentionEnterprises(String corp_name) {
//
//		SearchEnterpriseBean item = null;
//		int i =0;
//		Cursor cursor = null;
//		try {
//			synchronized (lock) {
//				cursor = localDb.rawQuery("SELECT * from "
//						+ MyAttentionEnterprise.TABLE_NAME + " where "
//						+ MyAttentionEnterprise.KEY_ENTERPRISE_NAME + " = '"
//						+ corp_name + "'", null);
//				 i = cursor.getCount();
//				if (cursor != null) {
//					cursor.close();
//				}
//			}
//		} catch (Exception e) {
//			//L.e(TAG, e.getMessage());
//
//		} finally {
//			if (cursor != null && !cursor.isClosed()) {
//				cursor.close();
//			}
//		}
//		return i;
//	}

}
