package cn.scau.dao;

import cn.scau.bean.JsJianjie;
import cn.scau.bean.PageBean;

public interface JsjianjieDao {
	//保存教师除开头像的个人信息，
	void insert(JsJianjie jsJianjie);
	//分页查询数据已保存的教师个人信息,以分页数据形式返回
	 PageBean<JsJianjie> selectXinxi(int currentPage,int everyPageSize);
	//根据工号查询教师的个人信息
	JsJianjie selectByGh(String gonghao);
	//更新教师个人信息
	void updateByGh(String gonghao,JsJianjie jsJianjie);
	//通过姓名获得教师工号
	String findGonghaoByXm(String xingming);
}
