package com.wpmac.carinsrance.model;

/**
 * Created by wpmac on 16/5/27.
 */

import com.avos.avoscloud.AVClassName;
import com.avos.avoscloud.AVObject;

@AVClassName(HomebanerList.NAME_CLASS)
public class HomebanerList extends AVObject {

    static final String NAME_CLASS = "HomebanerList";
    private static final String NAME = "name";
    private static final String TYPE = "type";
    private static final String IMGURL = "imgUrl";


    public  String getNameClass() {
        return this.getString(NAME_CLASS);
    }

    public  String getNAME() {
        return this.getString(NAME);
    }

    public  String getTYPE() {
        return this.getString(TYPE);
    }

    public  String getIMGURL() {
        return this.getString(IMGURL);
    }
//
//    public String getContent() {
//        return this.getString(NAME);
//    }
//
//    public void setContent(String content) {
//        this.put(NAME, content);
//    }
}
