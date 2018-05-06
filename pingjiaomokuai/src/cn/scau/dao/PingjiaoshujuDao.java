package cn.scau.dao;

import java.util.List;
import java.util.Map;

import cn.scau.bean.Pingjiaoshuju;

public interface PingjiaoshujuDao {
	//插入学生评教的评教数据
	void insert(Pingjiaoshuju pingjiaoshuju);
	//查询指定老师对应教学班的评教数据，将每个评教项的数据保存在list集合里
	Map<String, List<Integer>> findPjshuju(String jxbbianhao,String gonghao);
	//查询指定老师对应教学班的学生评教建议（与findPjshuju()的代码冗余，待解决）
	List<String> findJianyi(String jxbbianhao,String gonghao);
	//查询出指定老师所教班级的所有评教信息，供管理员选择
	List<Pingjiaoshuju> findpjForAd(String jxbbianhao,String gonghao);
	//批量删除学生评教建议
	void updateBatch(Integer[] pjids);
}
