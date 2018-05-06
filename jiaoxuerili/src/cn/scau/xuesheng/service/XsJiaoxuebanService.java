package cn.scau.xuesheng.service;

import java.util.List;

import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.dao.impl.JiaoxuebanDaoImpl;

//11.10晚改
public class XsJiaoxuebanService {
	private JiaoxuebanDaoImpl jxbDaoImpl = new JiaoxuebanDaoImpl();

	// 通过学号查出该学生的教学班信息
	public List<JiaoxuebanCustom> showJxbByXh(String xuehao) {
		return jxbDaoImpl.findCustomByXh(xuehao);
	}

	// 获得对应学年，学期某学生的课程
	public List<JiaoxuebanCustom> XsShowJxbByXnXq(String xuehao, String xuenian, String xueqi) {
		return jxbDaoImpl.findByXnXqXh(xuehao, xuenian, xueqi);
	}
}
