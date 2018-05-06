package cn.scau.dao.impl;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.scau.bean.JsJianjie;
import cn.scau.bean.PageBean;
import cn.scau.dao.JsjianjieDao;
import cn.scau.utils.JdbcUtils;

public class JsjianjieDaoImpl implements JsjianjieDao {
	
	public void insert(JsJianjie jsJianjie) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "insert into jsjianjie values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			qr.update(sql, jsJianjie.getGonghao(),jsJianjie.getXingming(),jsJianjie.getxingbie(),
					       jsJianjie.getMinzu(),jsJianjie.getJiguan(),jsJianjie.getChushengriqi(),
					       jsJianjie.getJkzhuangkuang(),jsJianjie.getXueli(),jsJianjie.getXuewei(),
					       jsJianjie.getZhicheng(),jsJianjie.getYjsdaoshi(),jsJianjie.getDianhua(),jsJianjie.getZzmianmao(),
					       jsJianjie.getYouxiang(),jsJianjie.getJxjingli(),jsJianjie.getYjjingli(),
					       jsJianjie.getYjxingqu(),jsJianjie.getHdjiangli() );
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
	
	//分页查询信息
	public PageBean<JsJianjie> selectXinxi(int currentPage,int everyPageSize) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			//分页信息对象
			PageBean<JsJianjie> jsjianjiePage = new PageBean<>();
			jsjianjiePage.setCurrentPage(currentPage);
			jsjianjiePage.setPageSize(everyPageSize);
			
			//获得总记录数并赋值
			String sql = "select count(*) from jsjianjie ";
			Number number = (Number) qr.query(sql, new ScalarHandler());
			int total = number.intValue();
			jsjianjiePage.setTotal(total);
			
			String sql_2 = "select * from jsjianjie limit ?,?";
			List<JsJianjie> jsJianjies = qr.query(sql_2, new BeanListHandler<JsJianjie>(JsJianjie.class), 
							(currentPage-1)*everyPageSize,everyPageSize);	
			jsjianjiePage.setBeanList(jsJianjies);
			//返回分页信息对象
			return jsjianjiePage;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public JsJianjie selectByGh(String gonghao) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "select * from jsjianjie where gonghao=? ";
			JsJianjie jsJianjie = qr.query(sql, new BeanHandler<JsJianjie>(JsJianjie.class), gonghao);
			return jsJianjie;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void updateByGh(String gonghao,JsJianjie jsJianjie) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "update jsjianjie set Xingming=?,xingbie=?,minzu=?,jiguan=?, chushengriqi=?,jkzhuangkuang=?,xueli=?,xuewei=?,zhicheng=?,yjsdaoshi=?,dianhua=?,"
					+ "zzmianmao=?,youxiang=?,jxjingli=?,yjjingli=?,yjxingqu=?,hdjiangli=? where gonghao=?";
			qr.update(sql,jsJianjie.getXingming(),jsJianjie.getxingbie(),
					       jsJianjie.getMinzu(),jsJianjie.getJiguan(),jsJianjie.getChushengriqi(),
					       jsJianjie.getJkzhuangkuang(),jsJianjie.getXueli(),jsJianjie.getXuewei(),
					       jsJianjie.getZhicheng(),jsJianjie.getYjsdaoshi(),jsJianjie.getDianhua(),
					       jsJianjie.getZzmianmao(), jsJianjie.getYouxiang(),jsJianjie.getJxjingli(),
					       jsJianjie.getYjjingli(),jsJianjie.getYjxingqu(),jsJianjie.getHdjiangli(),
					       gonghao);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}

	
	public String findGonghaoByXm(String xingming) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "select * from jsjianjie where xingming=? ";
			JsJianjie jsJianjie = qr.query(sql, new BeanHandler<JsJianjie>(JsJianjie.class), xingming);
			return jsJianjie.getGonghao();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
}
