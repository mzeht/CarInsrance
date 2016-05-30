package com.wpmac.carinsrance.bean;

import com.google.gson.Gson;
import com.wpmac.carinsrance.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by wpmac on 16/5/31.
 */
public class HomeCardBean extends ResponseBean {


    /**
     * updatedAt : 2016-05-30T17:15:49.821Z
     * ACL : {"*":{"read":true,"write":true}}
     * cpOne : {"id":8,"title":"减震器","imgUrl":"http://o7twp9p7v.bkt.clouddn.com/image/homepeijian/peijian10.jpg"}
     * objectId : 574c754571cfe4002827b96c
     * createdAt : 2016-05-30T17:15:49.821Z
     * cpThree : {"id":1,"title":"轮胎","imgUrl":"http://o7twp9p7v.bkt.clouddn.com/image/homepeijian/peijian12.jpg"}
     * title : 热门易损配件
     * id : 4
     * cpTwo : {"id":7,"title":"传感器","imgUrl":"http://o7twp9p7v.bkt.clouddn.com/image/homepeijian/peijian11.jpg"}
     */

    public static HomeCardBean HomeCardBean(String response) throws JSONException {

        if(response==null || response.equals("") || response.equals("{}") || response.equals("null")|| response.equals("[]"))
        {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        HomeCardBean model = new HomeCardBean();
        JSONObject singleObject = new JSONObject(response);
        model = gson.fromJson(singleObject.toString(),HomeCardBean.class);

        return model;
    }
    private List<ResultsBean> results;

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 8
         * title : 减震器
         * imgUrl : http://o7twp9p7v.bkt.clouddn.com/image/homepeijian/peijian10.jpg
         */

        private CpBean cpOne;
        /**
         * id : 1
         * title : 轮胎
         * imgUrl : http://o7twp9p7v.bkt.clouddn.com/image/homepeijian/peijian12.jpg
         */

        private CpBean cpThree;
        private String title;
        private int id;
        /**
         * id : 7
         * title : 传感器
         * imgUrl : http://o7twp9p7v.bkt.clouddn.com/image/homepeijian/peijian11.jpg
         */

        private CpBean cpTwo;

        public CpBean getCpOne() {
            return cpOne;
        }

        public void setCpOne(CpBean cpOne) {
            this.cpOne = cpOne;
        }

        public CpBean getCpThree() {
            return cpThree;
        }

        public void setCpThree(CpBean cpThree) {
            this.cpThree = cpThree;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public CpBean getCpTwo() {
            return cpTwo;
        }

        public void setCpTwo(CpBean cpTwo) {
            this.cpTwo = cpTwo;
        }

        public static class CpBean {
            private int id;
            private String title;
            private String imgUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }


    }
}
