package com.wpmac.carinsrance.comment;

import org.json.JSONException;


/**
 * 基类数据模型
 * @author Administrator
 *
 */
public abstract class BaseModel extends ResponseBean {

	
	public BaseModel()
	{
		
	}
	public BaseModel(String response) throws JSONException {
		super(response);
		// TODO Auto-generated constructor stub
	}

	
	
	
	/**
	 * 保存
	 */
	public  void save() {
		
	}
	
	/**
	 * 更新
	 */
	public void update()
	{
		
	}
	
	/**
	 * 删除
	 */
	public   void delete()
	{
		
	}
	

	
}
