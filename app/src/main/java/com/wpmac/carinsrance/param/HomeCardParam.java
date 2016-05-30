package com.wpmac.carinsrance.param;

import android.os.Bundle;

import com.wpmac.carinsrance.comment.RequestParam;

/**
 * Created by wpmac on 16/5/31.
 */
public class HomeCardParam extends RequestParam {

    public final static String method = "https://leancloud.cn:443/1.1/classes/HomecartList?limit=10&&order=-updatedAt&&";
    @Override
    public Bundle getParam() {
        Bundle bundle=new Bundle();
        bundle.putString("method", method);
        return bundle;
    }
}
