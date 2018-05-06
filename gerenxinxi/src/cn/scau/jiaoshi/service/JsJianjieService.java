package cn.scau.jiaoshi.service;

import cn.scau.bean.JsJianjie;
import cn.scau.bean.PageBean;
import cn.scau.dao.impl.JsjianjieDaoImpl;

public class JsJianjieService {
	private JsjianjieDaoImpl jsJjDaoImpl = new JsjianjieDaoImpl();
	//保存用户提交的除头像的信息
	public void save(JsJianjie jsJianjie) {
		jsJjDaoImpl.insert(jsJianjie);
	}
	
	//分页查询显示教师们的信息
	public PageBean<JsJianjie> show(int currentPage,int everyPageSize){
		return jsJjDaoImpl.selectXinxi(currentPage,everyPageSize);
	}
	//查询指定教师的信息
	public JsJianjie showByGh(String gonghao) {
		return jsJjDaoImpl.selectByGh(gonghao);
	}
	//更新教师信息
	public void updateXinxi(String gonghao,JsJianjie jsJianjie) {
		jsJjDaoImpl.updateByGh(gonghao, jsJianjie);
	}
	//根据姓名获得教师工号
	public String getGonghao(String xingming) {
		return jsJjDaoImpl.findGonghaoByXm(xingming);		
	}
}
