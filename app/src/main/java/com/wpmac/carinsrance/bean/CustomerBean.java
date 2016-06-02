package com.wpmac.carinsrance.bean;

import com.google.gson.Gson;
import com.wpmac.carinsrance.comment.ResponseBean;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by wpmac on 16/6/1.
 */
public class CustomerBean extends ResponseBean {

    /**
     * carnumber : sadsdadasd
     * updatedAt : 2016-06-01T10:09:09.930Z
     * ACL : {"*":{"read":true,"write":true}}
     * phone : 12333787876
     * name : toyang
     * objectId : 574eb44571cfe400282a9895
     * idcard : 567899912312312344
     * createdAt : 2016-06-01T10:09:09.930Z
     * type : 1
     * id : 1
     */

    private List<ResultsBean> results;

    public static CustomerBean CustomerBean(String response) throws JSONException {

        if(response==null || response.equals("") || response.equals("{}") || response.equals("null")|| response.equals("[]"))
        {
            throw new JSONException("数据解析异常");
        }
        Gson gson = new Gson();
        CustomerBean model = new CustomerBean();
        JSONObject singleObject = new JSONObject(response);
        model = gson.fromJson(singleObject.toString(),CustomerBean.class);

        return model;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        private String carnumber;
        private String phone;
        private String name;
        private String objectId;
        private String idcard;
        private int type;
        private int id;

        public String getCarnumber() {
            return carnumber;
        }

        public void setCarnumber(String carnumber) {
            this.carnumber = carnumber;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

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

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
