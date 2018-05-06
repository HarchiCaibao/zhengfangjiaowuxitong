package cn.scau.xuesheng.service;

import java.util.List;

import cn.scau.dao.impl.ChengjiDaoImpl;

public class XsChengjiService {
	private ChengjiDaoImpl cjdao= new ChengjiDaoImpl();
	public void setYes(String xuehao,String jxbbianhao) {
		cjdao.updatePingjiao(xuehao,jxbbianhao);
	}
	public boolean isAllYes(String xuehao) {
		return cjdao.findIsAllYes(xuehao);
	}
	public List<String> isYes(String xuehao) {
		return cjdao.findIsYes(xuehao);
	}
}
