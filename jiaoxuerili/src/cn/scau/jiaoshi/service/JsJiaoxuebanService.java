package cn.scau.jiaoshi.service;

import java.util.List;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.dao.impl.JiaoxuebanDaoImpl;

public class JsJiaoxuebanService {
	private JiaoxuebanDaoImpl jxbDaoImpl = new JiaoxuebanDaoImpl();
	
	//获得某工号对应的教学日历信息
	public List<JiaoxuebanCustom> showJxbByGh(String gonghao){
		return jxbDaoImpl.findCustomByGh(gonghao);
	}
	//获得对应学年，学期某老师的课程
	public List<JiaoxuebanCustom> showJxbByXnXq(String gonghao,String xuenian,String xueqi){
		return jxbDaoImpl.findByXnXqGh(gonghao, xuenian, xueqi);
	}
}
