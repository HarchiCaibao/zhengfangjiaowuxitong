package cn.scau.dao;

import java.util.List;

import cn.scau.bean.Jiaoxueban;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.bean.PageBean;

public interface JiaoxuebanDao {
	
	//分页查询出教学班信息
	PageBean<Jiaoxueban> findJiaoxueban(String gonghao,int currentPage,int everyPageSize);
	//查出学年
	List<Integer> findXuenians(String gonghao);
	
	
	//查出该校所有的教师的姓名，工号，教学班及开课名称
	PageBean<JiaoxuebanCustom> findJiaoshi(int kaikexuenian,int kaikexueqi,int currentPage,int everyPageSize);
	//查出学年
	List<Integer> findXuenians();
}
