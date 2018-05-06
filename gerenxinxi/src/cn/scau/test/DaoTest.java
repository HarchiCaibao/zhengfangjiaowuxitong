package cn.scau.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.regex.Pattern;

import org.junit.Test;

import cn.scau.bean.JsJianjie;
import cn.scau.bean.PageBean;
import cn.scau.dao.impl.JiaoshiTxDaoImpl;
import cn.scau.dao.impl.JsjianjieDaoImpl;

public class DaoTest {
	@Test
	public void testInsert() {
		JsjianjieDaoImpl jsJjDaoImpl = new JsjianjieDaoImpl();
		for(int i=201;i<288;i++) {
		JsJianjie jsJianjie = new JsJianjie();
		jsJianjie.setChushengriqi("1998.12.24");
		jsJianjie.setDianhua("123");
		jsJianjie.setGonghao(String.valueOf(i));
		jsJianjie.setHdjiangli("无");
		jsJianjie.setJiguan("广州");
		jsJianjie.setJxjingli("高中");
		jsJianjie.setMinzu("汉");
		jsJianjie.setJkzhuangkuang("良好");
		jsJianjie.setxingbie("nan");
		jsJianjie.setXingming("晓王"+String.valueOf(i));
		jsJianjie.setXueli("博士");
		jsJianjie.setXuewei("教授");
		jsJianjie.setYjjingli("java");
		jsJianjie.setYjxingqu("大数据");
		jsJianjie.setYouxiang("12134546@qq.com");
		jsJianjie.setZhicheng("jiaoshou");
		jsJianjie.setYjsdaoshi("博士生");
		jsJianjie.setZzmianmao("党员");
		jsJjDaoImpl.insert(jsJianjie);
		}
	}
	@Test
	public void testShow() throws IOException {
		InputStream in = null;
		JiaoshiTxDaoImpl jstx = new JiaoshiTxDaoImpl();
		in = jstx.slectTouxiangByGh("103");
		if (in==null) {
			System.out.println("查不到！");
		}else {
			System.out.println(in);
		}
		if (in!=null) {
			in.close();
		}
	}
	@Test
	public void testSelect() {
		JsjianjieDaoImpl jsjianjieDaoImpl = new JsjianjieDaoImpl();
	    PageBean<JsJianjie> jsBean  = jsjianjieDaoImpl.selectXinxi(1, 10);
	    System.err.println(jsBean);
	}
	@Test 
	public void testFindGh() {
		JsjianjieDaoImpl jsjianjieDaoImpl = new JsjianjieDaoImpl();
		String xingming = "小马";
		String xm = jsjianjieDaoImpl.findGonghaoByXm(xingming);
		System.out.println(xm);
	}
	@Test 
	public void testZhengze() {
		String date = "((?:(?:[1]{1}\\\\d{1}\\\\d{1}\\\\d{1})|(?:[2]{1}\\\\d{3}))[-:\\\\/.](?:[0]?[1-9]|[1][012])[-:\\\\/.](?:(?:[0-2]?\\\\d{1})|(?:[3][01]{1})))(?![\\\\d])";
		String d1 = "1992.12.4";
		String d2 = "1992.13.02";
		String d3 = "1998-12-04";
		String d4 = "1998/12/04";
		String d5 = "1998\12\04";
		Pattern pattern = Pattern.compile(date);
		if(pattern.matcher(d1).matches()) {System.out.println("正确的！");}
		if(!pattern.matcher(d2).matches()) {System.out.println("错误的！");}
		if(!pattern.matcher(d3).matches()) {System.out.println("错误的！");}
		if(!pattern.matcher(d4).matches()) {System.out.println("错误的！");}
		if(!pattern.matcher(d5).matches()) {System.out.println("错误的！");}
		
	}
	@Test 
	public void testZhengze2() {
		String email = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
		String d1 = "1501859625@qq.com";
		String d2 = "13652096152vcsj";
		String d3 = "16352987@163.com";
		String d4 = "16544654641@18869.com";
		Pattern pattern = Pattern.compile(email);
		if(pattern.matcher(d1).matches()) {System.out.println("d1正确的！");}
		if(pattern.matcher(d2).matches()) {System.out.println("d2错误的！");}
		if(pattern.matcher(d3).matches()) {System.out.println("d3正确的！");}
		if(pattern.matcher(d4).matches()) {System.out.println("d4错误的！");}
		
	}
	
	
}
