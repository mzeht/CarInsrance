package com.wpmac.carinsrance.util;

import com.wpmac.carinsrance.model.SelectSimleList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wpmac on 16/6/1.
 */
public class LocalList {

    private static  String [] buwei = {"制动系统","油品","雨刮器","轮胎","点火系统","空调系统","发送机","传感器","音响","仪表","车门","车身"};
    private static  String [] jiage = {"500","1000","1500","2000","2500","3000","3500","4000","4500","5000","5500","6000"};
    private static  String [] pinpai = {"品牌1","品牌2","品牌3","品牌4","品牌5","品牌6","品牌7"};

    public static List<SelectSimleList> getBuweiList(){
        List<SelectSimleList> list = new ArrayList<SelectSimleList>();
        for(int i=0;i<buwei.length;i++){
            SelectSimleList father = new SelectSimleList();
            father.setName(buwei[i]);
            list.add(father);
        }
        return  list;
    }
    public static List<SelectSimleList> getjiageList(){
        List<SelectSimleList> list = new ArrayList<SelectSimleList>();
        for(int i=0;i<jiage.length;i++){
            SelectSimleList father = new SelectSimleList();
            father.setName(jiage[i]);
            list.add(father);
        }
        return  list;
    }
    public static List<SelectSimleList> getpinpaiList(){
        List<SelectSimleList> list = new ArrayList<SelectSimleList>();
        for(int i=0;i<pinpai.length;i++){
            SelectSimleList father = new SelectSimleList();
            father.setName(pinpai[i]);
            list.add(father);
        }
        return  list;
    }
}
