package com.wpmac.carinsrance.model;

import java.io.Serializable;

/**
 * Created by wpmac on 16/6/1.
 */
public class SelectSimleList implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;



    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
