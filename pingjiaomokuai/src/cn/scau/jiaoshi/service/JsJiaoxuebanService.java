package cn.scau.jiaoshi.service;

import java.util.List;

import cn.scau.bean.Jiaoxueban;
import cn.scau.bean.PageBean;
import cn.scau.dao.impl.JiaoxuebanDaoImpl;

public class JsJiaoxuebanService {
	private JiaoxuebanDaoImpl jiaoshijxbDaoImpl = new JiaoxuebanDaoImpl();
	public List<Integer> showXuenian(String gonghao) {
		return jiaoshijxbDaoImpl.findXuenians(gonghao);
	}
	public PageBean<Jiaoxueban> getJiaoxueban(String gonghao,int currentPage,int everyPageSize){
		return jiaoshijxbDaoImpl.findJiaoxueban(gonghao, currentPage, everyPageSize);
	}
	
}
