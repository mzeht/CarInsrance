package com.wpmac.carinsrance.base;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;

public class BasePreference extends Preference {
    private static BasePreference mBasePreference;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    //数据库名称
    private String SP_NAME = "";

    private static final String MAP_SERVER_URL = "MAP_SERVER_URL";
    private static final String ISLOGIN = "ISLOGIN";
    private static final String USER_ID = "USER_ID";
    private static final String INVITE_CODE = "";
    private static final String ISREGISTER = "ISREGISTER";
    public static final String SING_IN = "SING_IN";
    public static final String OPEN_ID = "OPEN_ID";
    public static final String ACCESS_TOKEN = "ACCESS_TOKEN";
    private static final String USER_LOG = "";
    private static final String HOT_WORD_STRING = "HOT_WORD_STRING";

    private static final String SUGGRSTED_READING_STRING = "SUGGRSTED_READING_STRING";


    /**
     * 获取单例
     *
     * @return
     */
    public static BasePreference getInstance() {
        return mBasePreference;
    }

    /**
     * 构造函数
     *
     * @param context
     */
    private BasePreference(Context context) {
        super(context);
        mBasePreference = this;
        sp = context.getSharedPreferences(SP_NAME, 0);
        editor = sp.edit();
    }

    /**
     * 单例初始化
     *
     * @param paramContext
     */
    public static void initialize(Context paramContext) {

        if (mBasePreference == null) {
            mBasePreference = new BasePreference(paramContext);
        }

    }

    /**
     * 保存OpenId
     *
     * @param
     */
    public void saveOpenId(String openId) {
        editor.putString(OPEN_ID, openId);
        editor.commit();
    }

    /**
     * 获取OpenId
     *
     * @return
     */
    public String getOpenId() {
        return sp.getString(OPEN_ID, "");
    }

    /**
     * 保存AccessToken
     *
     * @param
     */
    public void saveAccessToken(String accessToken) {
        editor.putString(ACCESS_TOKEN, accessToken);
        editor.commit();
    }

    /**
     * 获取AccessToken
     *
     * @return
     */
    public String getAccessToken() {
        return sp.getString(ACCESS_TOKEN, "");
    }

    /**
     * 保存签到状态
     *
     * @param
     */
    public void saveSingInDay(String singIn) {
        editor.putString(SING_IN, singIn);
        editor.commit();
    }

    /**
     * 获取签到状态
     *
     * @return
     */
    public String getSingInDay() {
        return sp.getString(SING_IN, "");
    }


    /**
     * 保存登陆状态
     *
     * @param
     */
    public void saveIsLogin(String s) {
        editor.putString(ISLOGIN, s);
        editor.commit();
    }

    /**
     * 获取登陆状态
     *
     * @return
     */
    public String getIsLogin() {
        return sp.getString(ISLOGIN, "");
    }

    /**
     * 保存注册状态
     *
     * @param
     */
    public void saveIsRegister(String s) {
        editor.putString(ISREGISTER, s);
        editor.commit();
    }

    /**
     * 获取注册状态
     *
     * @return
     */
    public String getIsRegister() {
        return sp.getString(ISREGISTER, "");
    }
//	

    /**
     * 保存用户userId
     *
     * @param userid
     */
    public void saveUserId(String userid) {
        editor.putString(USER_ID, userid);
        editor.commit();
    }

    /**
     * 获取用户userId
     *
     * @param
     */
    public String getUserId() {

        return sp.getString(USER_ID, "");
    }


    /**
     * 保存用户邀请码
     *
     * @param userid
     */
    public void saveInviteCode(String userid) {
        editor.putString(INVITE_CODE, userid);
        editor.commit();
    }

    /**
     * 获取用户邀请码
     *
     * @param
     */
    public String getInviteCode() {

        return sp.getString(INVITE_CODE, "");
    }


    /**
     * 保存用户设备号
     *
     * @param
     */
    public void saveUserLog(String userLog) {
        editor.putString(USER_LOG, userLog);
        editor.commit();
    }

    /**
     * 获取用户设备号
     *
     * @param
     */
    public String getUserLog() {

        return sp.getString(USER_LOG, "");
    }

    /**
     * 获取服务器地址
     *
     * @return
     */
    public String getMapServerUrl() {
        return sp.getString(MAP_SERVER_URL, "http://192.168.8.105:9008/");
    }

    public void saveMapServerUrl(String serverUrl) {
        editor.putString(MAP_SERVER_URL, serverUrl);
        editor.commit();
    }


//	public void saveHotTagBean(HotTagBean hotTagBean)
//	{
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try {
//			// 创建对象输出流，并封装字节流
//			ObjectOutputStream oos = new ObjectOutputStream(baos);
//			// 将对象写入字节流
//			oos.writeObject(hotTagBean);
//			// 将字节流编码成base64的字符窜
//			String oAuth_Base64 = new String(Base64.encodeBase64(baos
//					.toByteArray()));
//
//			editor.putString("HOT_WORD_STRING", oAuth_Base64);
//
//			editor.commit();
//		} catch (IOException e) {
//			// TODO Auto-generated
//			e.printStackTrace();
//		}
//	}

//	public HotTagBean getHotTagBean()
//	{
//		HotTagBean hotTagBean = new HotTagBean();
//		String productBase64 = sp.getString(HOT_WORD_STRING, "");
//
//		//读取字节
//		byte[] base64 = Base64.decodeBase64(productBase64.getBytes());
//
//		//封装到字节流
//		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
//		try {
//			//再次封装
//			ObjectInputStream bis = new ObjectInputStream(bais);
//			try {
//				//读取对象
//				hotTagBean = (HotTagBean) bis.readObject();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (StreamCorruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return hotTagBean;
//
//	}


//	public void saveSuggestedReadingGetBean(SuggestedReadingGetBean suggestedReadingGetBean)
//	{
//
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		try {
//			// 创建对象输出流，并封装字节流
//			ObjectOutputStream oos = new ObjectOutputStream(baos);
//			// 将对象写入字节流
//			oos.writeObject(suggestedReadingGetBean);
//			// 将字节流编码成base64的字符窜
//			String oAuth_Base64 = new String(Base64.encodeBase64(baos
//					.toByteArray()));
//
//			editor.putString("SUGGRSTED_READING_STRING", oAuth_Base64);
//
//			editor.commit();
//		} catch (IOException e) {
//			// TODO Auto-generated
//			e.printStackTrace();
//		}
//	}

//	public SuggestedReadingGetBean getSuggestedReadingGetBean()
//	{
//
//
//		SuggestedReadingGetBean suggestedReadingGetBean = new SuggestedReadingGetBean();
//		String productBase64 = sp.getString(SUGGRSTED_READING_STRING, "");
//
//		//读取字节
//		byte[] base64 = Base64.decodeBase64(productBase64.getBytes());
//
//		//封装到字节流
//		ByteArrayInputStream bais = new ByteArrayInputStream(base64);
//		try {
//			//再次封装
//			ObjectInputStream bis = new ObjectInputStream(bais);
//			try {
//				//读取对象
//				suggestedReadingGetBean = (SuggestedReadingGetBean) bis.readObject();
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (StreamCorruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return suggestedReadingGetBean;
//
//	}

}
