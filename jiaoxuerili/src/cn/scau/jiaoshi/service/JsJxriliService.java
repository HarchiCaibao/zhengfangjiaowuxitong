package cn.scau.jiaoshi.service;

import cn.scau.bean.Jiaoxuerili;
import cn.scau.dao.impl.JiaoxueriliDaoImpl;

public class JsJxriliService {
	
     private JiaoxueriliDaoImpl jxrldaoimpl = new JiaoxueriliDaoImpl();
     //保存某教学班的教学日历
     public void saveJxrili(String jxbbianhao,String jiaoxuerili,Integer zhouxueshi) {
    	 jxrldaoimpl.insert(jxbbianhao, jiaoxuerili,zhouxueshi);
     }
     //更新某教学班的教学日历
     public void updateJxrili(String jxbbianhao,String jiaoxuerili) {
    	 jxrldaoimpl.update(jxbbianhao, jiaoxuerili);
     }
     //查询某教学班的教学日历
     public Jiaoxuerili findJxrili(String jxbbianhao) {
    	 return jxrldaoimpl.findjxrl(jxbbianhao);
     }
}
