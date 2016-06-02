package com.wpmac.carinsrance.param;

import android.os.Bundle;

import com.wpmac.carinsrance.comment.RequestParam;

/**
 * Created by wpmac on 16/6/1.
 */
public class CustomerParam extends RequestParam {

    public final static String method = "https://leancloud.cn:443/1.1/classes/customers?limit=10&&order=-updatedAt&&";
    @Override
    public Bundle getParam() {
        Bundle bundle=new Bundle();
        bundle.putString("method", method);
        return bundle;
    }
}