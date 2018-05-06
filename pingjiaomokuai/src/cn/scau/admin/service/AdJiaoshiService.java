package cn.scau.admin.service;

import java.util.List;

import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.bean.PageBean;
import cn.scau.dao.impl.JiaoxuebanDaoImpl;

public class AdJiaoshiService {
	private JiaoxuebanDaoImpl jiaoshijxbDaoImpl = new JiaoxuebanDaoImpl();
	public PageBean<JiaoxuebanCustom> showJiaoshis(int kaikexuenian,int kaikexueqi,int currentPage,int everyPageSize){
		return jiaoshijxbDaoImpl.findJiaoshi(kaikexuenian,kaikexueqi,currentPage,everyPageSize);
	}
	public List<Integer> showXuenians(){
		return jiaoshijxbDaoImpl.findXuenians();
	}
}
