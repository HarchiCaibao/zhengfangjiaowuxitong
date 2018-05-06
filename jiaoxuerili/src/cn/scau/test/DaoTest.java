package cn.scau.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.bean.Jiaoxuerili;
import cn.scau.dao.impl.JiaoxuebanDaoImpl;
import cn.scau.dao.impl.JiaoxueriliDaoImpl;

public class DaoTest {
	
	
	@Test
	public void testFindVoBygonghao() {
		JiaoxuebanDaoImpl jxb = new JiaoxuebanDaoImpl();
		List<JiaoxuebanCustom> jxbCustoms = jxb.findCustomByGh("102");
		System.out.println(jxbCustoms);
		List<String> xuenian = new ArrayList<>();
		//将学年转换为 xxxx-xxxx 的形式
		for(JiaoxuebanCustom jsc : jxbCustoms) {
			if(jsc.getKaikexueqi()==1) {
				int shangxueqi = jsc.getKaikexuenian() - 1;
				String xiaxueqi = String.valueOf(jsc.getKaikexuenian());
				xuenian.add(String.valueOf(shangxueqi)+"-"+xiaxueqi);
			}else {
				int xiaxueqi = jsc.getKaikexuenian() + 1;
				String shangxueqi = String.valueOf(jsc.getKaikexuenian());
				xuenian.add(shangxueqi+"-"+String.valueOf(xiaxueqi));
			}
		}
		xuenian = new ArrayList<>(new HashSet<>(xuenian));
		Collections.sort(xuenian);
		Collections.reverse(xuenian);
		System.out.println(xuenian);
	}
	@Test
	public void testBase() {
		JiaoxuebanDaoImpl jxb = new JiaoxuebanDaoImpl();
		List<JiaoxuebanCustom> jxbCustoms = jxb.findCustomByGh("102");
		List<JiaoxuebanCustom> jxbCustom = new ArrayList<>();
		for (JiaoxuebanCustom jiaoxuebanCustom : jxbCustoms) {
			if (jiaoxuebanCustom.getKaikexuenian()==2018) {
				jxbCustom.add(jiaoxuebanCustom);
			}
		}
		System.out.println(jxbCustom);
	}
	
	@Test
	public void testInsert() {
		JiaoxueriliDaoImpl jxr = new JiaoxueriliDaoImpl();
		jxr.insert("电信2", "1/2/理论课/血红色呢个们",2);
	}
	
	@Test
	public void testUpdate() {
		String jxbbianhao="电信1班，电信2班";
		JiaoxueriliDaoImpl jxr = new JiaoxueriliDaoImpl();
		jxr.update(jxbbianhao, "lisi");
	}
	
	@Test
	public void testfind() {
		String jxbbianhao="电信1班，电信3班";
		JiaoxueriliDaoImpl jxr = new JiaoxueriliDaoImpl();
		Jiaoxuerili jxrl = jxr.findjxrl(jxbbianhao);
		System.out.println(jxrl);
	
	}
	@Test
	public void testfind2() {
		String gonghao = "102";
		JiaoxuebanDaoImpl js = new JiaoxuebanDaoImpl();
		 List<JiaoxuebanCustom> jxrl = js.findCustomByGh(gonghao);
		System.out.println(jxrl);
	
	}
	
	
	@Test
	public void testString() {
		String string = "2!1%3*理论课?学生们听话";
		
		String str = string.substring(string.indexOf("*")+1, string.indexOf("?"));
		System.out.println(str);
	}
	
	@Test
	public void testfindByxuehao() {
		String xuehao = "2011";
		JiaoxuebanDaoImpl jxbdaoimpl = new JiaoxuebanDaoImpl();
		List<JiaoxuebanCustom> jiaoxuebans = jxbdaoimpl.findCustomByXh(xuehao);
		System.out.println(jiaoxuebans);
	
	}
	@Test
	public void testfindByXnXqGh() {
		String gonghao = "102";
		String xuenian = "2017";
		String xueqi = "1";
		JiaoxuebanDaoImpl jxbdaoimpl = new JiaoxuebanDaoImpl();
		List<JiaoxuebanCustom> jiaoxuebans = jxbdaoimpl.findByXnXqGh(gonghao, xuenian, xueqi);
		System.out.println(jiaoxuebans);
	
	}
	@Test
	public void testfindByXnXqXh() {
		String xuehao = "2011";
		String xuenian = "2017";
		String xueqi = "1";
		JiaoxuebanDaoImpl jxbdaoimpl = new JiaoxuebanDaoImpl();
		List<JiaoxuebanCustom> jiaoxuebans = jxbdaoimpl.findByXnXqXh(xuehao, xuenian, xueqi);
		System.out.println(jiaoxuebans);
	
	}
	
	@Test
	public void testbase() {
		int i = 10;
		System.out.printf("i=%d\n",i);
		System.out.printf("i=%5d",i);
	}
	
}
