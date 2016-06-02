package com.wpmac.carinsrance.param;

import android.os.Bundle;

import com.wpmac.carinsrance.comment.RequestParam;

/**
 * Created by wpmac on 16/6/1.
 */
public class ShangpingParam extends RequestParam {
    public final static String method = "https://leancloud.cn:443/1.1/classes/ShangpingList";
    public String type;
    public String pinpai;
    public String price;
    @Override
    public Bundle getParam() {
        Bundle bundle=new Bundle();
        bundle.putString("method", method);
        bundle.putString("type", type);
        bundle.putString("pinpai", pinpai);
        bundle.putString("price", price);
        return bundle;
    }
}
