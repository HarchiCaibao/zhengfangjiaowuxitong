package cn.scau.dao;

import cn.scau.bean.Jiaoshi;

public interface JiaoshiDao {
	//通过工号查询老师
	Jiaoshi findByGonghao(String gonghao);
	
}
