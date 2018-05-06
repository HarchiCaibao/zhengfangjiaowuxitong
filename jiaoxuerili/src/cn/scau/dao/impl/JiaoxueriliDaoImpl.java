package cn.scau.dao.impl;

import java.sql.SQLException;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.scau.bean.Jiaoxuerili;
import cn.scau.dao.JiaoxueriliDao;
import cn.scau.utils.JdbcUtils;

public class JiaoxueriliDaoImpl implements JiaoxueriliDao {
	
	public void insert(String jxbbianhao,String jiaoxuerili,Integer zhouxueshi) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "INSERT INTO jiaoxuerili VALUES(?,?,?)";
			qr.update(sql, jxbbianhao,zhouxueshi,jiaoxuerili);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
	public void update(String jxbbianhao, String jiaoxuerili) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "UPDATE jiaoxuerili SET jiaoxuerili=? WHERE jxbbianhao=?";
			qr.update(sql, jiaoxuerili,jxbbianhao);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	
	public Jiaoxuerili findjxrl(String jxbbianhao) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "SELECT * FROM jiaoxuerili WHERE jxbbianhao=?";
			Jiaoxuerili jiaoxuerili = qr.query(sql, new BeanHandler<Jiaoxuerili>(Jiaoxuerili.class), jxbbianhao);
		    return jiaoxuerili;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
}
