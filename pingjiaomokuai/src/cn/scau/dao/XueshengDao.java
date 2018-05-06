package cn.scau.dao;

import java.util.List;

import cn.scau.bean.Jiaoshi;

public interface XueshengDao {
	//通过学号查询学生所选的老师
	List<Jiaoshi> findJaioshiByxuehao(String xuehao);
}
