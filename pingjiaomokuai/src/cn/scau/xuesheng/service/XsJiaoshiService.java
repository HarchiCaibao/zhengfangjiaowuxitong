package cn.scau.xuesheng.service;

import cn.scau.bean.Jiaoshi;
import cn.scau.dao.impl.JiaoshiDaoImpl;

public class XsJiaoshiService {
	private JiaoshiDaoImpl jiaoshidao = new JiaoshiDaoImpl();
	public Jiaoshi showJiaoshi(String gonghao) {
		return jiaoshidao.findByGonghao(gonghao);
	}
}
