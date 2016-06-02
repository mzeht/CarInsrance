package com.wpmac.carinsrance.database;

/**
 * 表结构的定义
 * @author 陈玉伟
    2015-4-10上午11:45:30
 */
public class DBConstant {
	/**
	 * 购物车列表
	 * @author 001368
	 *
	 */
	public static class Cart{
		public static final String TABLE_NAME="cart";
		public static final String KEY_ID ="ID";
		public static final String KEY_NAME ="NAME";
		public static final String KEY_TYPE ="TYPE";
		public static final String KEY_PINPAI ="PINPAI";
		public static final String KEY_IMAGURL ="IMGURL";
		public static final String KEY_DES ="DES";
		public static final String KEY_PRICE ="PRICE";
	}
	
	/**
	 * 搜索的企业表
	 */
	public static class MySearchEnterprise{
		public static final String TABLE_NAME = "table_history_enterprises";
		public static final String KEY_HISTORY_ENTERPRISE_NAME = "HISTORY_ENTERPRISE_NAME";
		public static final String KEY_HISTORY_ENTERPRISE_ID = "HISTROY_ENTERPRISE_ID";
		public static final String KEY_HISTORY_COMPANY_ID = "HISTROY_COMPANY_ID";
		public static final String KEY_HISTORY_COMPANY_GETDATE = "HISTROY_COMPANY_GETDATE";
		public static final String KEY_HISTORY_TIME = "KEY_HISTORY_TIME";
		public static final String KEY_HISTORY_AREACODE = "KEY_HISTORY_AREACODE";
		
		}

}
