package com.wpmac.carinsrance.manager;

import com.wpmac.carinsrance.bean.HomeCardBean;
import com.wpmac.carinsrance.comment.AbstractRequestListener;
import com.wpmac.carinsrance.comment.CustomError;
import com.wpmac.carinsrance.param.HomeCardParam;
import com.wpmac.carinsrance.util.HttpConnectionUtil;

import org.json.JSONException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 网络管理类
 *
 * @author 陈玉伟
 *         2015-9-24 下午2:03:11
 */
@SuppressWarnings("deprecation")
public class ConnectManager {

    private static ConnectManager mControlManager;

    public boolean mConnectionState = false;

    public Object lock = new Object();
    // 线程池
    private ExecutorService exector;

    private String json_error = "获取数据失败!", network_error = "网络异常!";

    private ConnectManager() {
        exector = Executors.newFixedThreadPool(5);

    }

    /**
     * 控制器单例
     *
     * @return
     */
    public static ConnectManager getInstance() {
        if (mControlManager == null) {
            mControlManager = new ConnectManager();
        }
        return mControlManager;
    }

    /**
     * 已买订单列表请求
     *
     * @param
     * @param params
     * @param callback
     */
    public void GetHomeCard(
            final HomeCardParam params,
            final AbstractRequestListener<HomeCardBean> callback) {
        exector.execute(new Runnable() {
            @Override
            public void run() {

                try {
                    String method = params.getParam().getString("method");

                    String response = HttpConnectionUtil.openUrl(
                             method, "GET", params.getParam());
                    HomeCardBean bean = (HomeCardBean) HomeCardBean
                            .HomeCardBean(response);
                    if (callback != null) {
                        callback.onComplete(bean);
                    }
                } catch (RuntimeException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onFault(new CustomError(network_error));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    if (callback != null) {
                        callback.onError(new CustomError(json_error));
                    }
                }
            }
        });
    }



}

