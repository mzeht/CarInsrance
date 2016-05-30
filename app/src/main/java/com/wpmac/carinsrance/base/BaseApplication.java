package com.wpmac.carinsrance.base;

import com.avos.avoscloud.AVOSCloud;
import com.avos.avoscloud.AVObject;
import com.wpmac.carinsrance.model.HomebanerList;

/**
 * Created by wpmac on 16/4/6 14:40.
 * Email mzeht@outlook.com
 */
public class BaseApplication extends ActiveApplication{

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "xQLsCJnfsBKIbAYDGUpXrLgV-gzGzoHsz", "9Cy0zWB3wcQvS6vrUBMkFo82");
        AVObject.registerSubclass(HomebanerList.class);

    }
}
