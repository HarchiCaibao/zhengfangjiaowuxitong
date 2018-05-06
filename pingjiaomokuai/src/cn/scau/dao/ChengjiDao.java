package cn.scau.dao;

import java.util.List;

public interface ChengjiDao {
	//学生评教后置评教为yes
	void updatePingjiao(String xuehao,String jxbbianhao);
	//查询某个学生是否评教完
	boolean findIsAllYes(String xuehao);
	//通过jxbbianhao查询某个老师对应的教学班的学生是否完全评教
	boolean findIsAllYesByjxbbh(String jxbbianhao);
	//通过学号查询学生已经评教的jxbbianhao
	List<String> findIsYes(String xuehao);
	//通过jxbbianhao查询是否有学生选了这门课。
	boolean findIsSelect(String jxbbianhao);
}
