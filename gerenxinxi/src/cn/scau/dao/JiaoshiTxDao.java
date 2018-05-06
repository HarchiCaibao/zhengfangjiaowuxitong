package cn.scau.dao;

import java.io.InputStream;

public interface JiaoshiTxDao {
	//保存系统提供的默认头像
	void insertByPh(String gonghao,String path);
	//查出某用户的头像
	InputStream slectTouxiangByGh(String gonghao);
	//保存用户上传的头像
	void updateBySt(String gonghao,InputStream in);
}
