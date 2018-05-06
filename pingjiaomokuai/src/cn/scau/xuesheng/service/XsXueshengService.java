package cn.scau.xuesheng.service;

import java.util.List;

import cn.scau.bean.Jiaoshi;
import cn.scau.dao.impl.XueshengDaoImpl;

public class XsXueshengService {
	private XueshengDaoImpl xueshengDaoImpl = new XueshengDaoImpl();
	public List<Jiaoshi> getJiaoshi(String xuehao){
		return xueshengDaoImpl.findJaioshiByxuehao(xuehao);
	}
}
