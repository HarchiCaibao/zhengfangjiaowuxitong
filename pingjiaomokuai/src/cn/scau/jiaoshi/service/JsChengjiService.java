package cn.scau.jiaoshi.service;

import cn.scau.dao.impl.ChengjiDaoImpl;

public class JsChengjiService {
	private ChengjiDaoImpl jsChengjiDaoImpl = new ChengjiDaoImpl();
	public boolean xsIsAllfinishPj(String jxbbianhao) {
		return jsChengjiDaoImpl.findIsAllYesByjxbbh(jxbbianhao);
	}
	public boolean xsIsSelect(String jxbbianhao) {
		return jsChengjiDaoImpl.findIsSelect(jxbbianhao);
	}
}
