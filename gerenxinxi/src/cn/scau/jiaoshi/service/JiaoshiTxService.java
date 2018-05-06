package cn.scau.jiaoshi.service;

import java.io.InputStream;
import cn.scau.dao.impl.JiaoshiTxDaoImpl;

public class JiaoshiTxService {
	private JiaoshiTxDaoImpl jsTxDaoImpl = new JiaoshiTxDaoImpl();
	 
	//保存系统提供的默认头像
	public void saveXitongTx(String gonghao,String path) {
		jsTxDaoImpl.insertByPh(gonghao, path);
	}
	//查出用户的头像
	public InputStream showJiaoshiTx(String gonghao) {
		return jsTxDaoImpl.slectTouxiangByGh(gonghao);
	}
	//更新用户头像，源文件为用户上传的图片
	public void updateGerenTx(String gonghao,InputStream in) {
		jsTxDaoImpl.updateBySt(gonghao, in);
	}
}