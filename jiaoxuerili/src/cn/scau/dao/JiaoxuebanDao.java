package cn.scau.dao;

import java.util.List;
import cn.scau.bean.JiaoxuebanCustom;
//11.10晚改
public interface JiaoxuebanDao {
	//通过工号查询教学班增强类的信息（面对教师的）
    List<JiaoxuebanCustom> findCustomByGh(String gonghao);
   //通过学号查询教学班增强类的信息（面对学生的）
    List<JiaoxuebanCustom> findCustomByXh(String xuehao);
    //通过学年及学期，工号查出该老师的课程
    List<JiaoxuebanCustom> findByXnXqGh(String gonghao,String xuenian,String xueqi);
    //通过学年学期，学号查出学生的课程
    List<JiaoxuebanCustom> findByXnXqXh(String xuehao,String xuenian,String xueqi);  
}
