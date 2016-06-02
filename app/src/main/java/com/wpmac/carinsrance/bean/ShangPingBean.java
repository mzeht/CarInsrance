package com.wpmac.carinsrance.bean;

import com.google.gson.Gson;
import com.wpmac.carinsrance.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by wpmac on 16/6/1.
 */
public class ShangPingBean extends ResponseBean {


    /**
     * updatedAt : 2016-05-31T19:37:46.724Z
     * ACL : {"*":{"read":true,"write":true}}
     * name : peijian
     * objectId : 574de80a71cfe400282859aa
     * createdAt : 2016-05-31T19:37:46.724Z
     * type : 制动系统
     * imgUrl : http://o7twp9p7v.bkt.clouddn.com/image/homepeijian/peijian1.jpg
     * id : 147
     * price : 800
     * pinpai : 品牌1
     */

    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public static ShangPingBean ShangPingBean(String response) throws JSONException {

        if(response==null || response.equals("") || response.equals("{}") || response.equals("null")|| response.equals("[]"))
        {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        ShangPingBean model = new ShangPingBean();
        JSONObject singleObject = new JSONObject(response);
        model = gson.fromJson(singleObject.toString(),ShangPingBean.class);

        return model;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String name;
        private String objectId;
        private String createdAt;
        private String type;
        private String imgUrl;
        private int id;
        private int price;
        private String pinpai;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getObjectId() {
            return objectId;
        }

        public void setObjectId(String objectId) {
            this.objectId = objectId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getPinpai() {
            return pinpai;
        }

        public void setPinpai(String pinpai) {
            this.pinpai = pinpai;
        }
    }
}
