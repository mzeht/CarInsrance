package com.wpmac.carinsrance.base;

import android.app.Application;
import com.avos.avoscloud.AVOSCloud;

/**
 * Created by wpmac on 16/4/6 14:40.
 * Email mzeht@outlook.com
 */
public class BaseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "xQLsCJnfsBKIbAYDGUpXrLgV-gzGzoHsz", "9Cy0zWB3wcQvS6vrUBMkFo82");
    }
}
