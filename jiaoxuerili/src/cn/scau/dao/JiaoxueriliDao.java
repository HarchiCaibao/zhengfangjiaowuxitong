package cn.scau.dao;

import cn.scau.bean.Jiaoxuerili;

public interface JiaoxueriliDao {
	//插入教学日历
	void insert(String jxbbianhao,String jiaoxuerili,Integer zhouxueshi);
	//更新教学日历
	void update(String jxbbianhao,String jiaoxuerili);
	//通过教学班号查询教学日历
	Jiaoxuerili findjxrl(String jxbbianhao);
}
