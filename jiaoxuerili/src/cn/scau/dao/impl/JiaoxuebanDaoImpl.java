package cn.scau.dao.impl;

import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.dao.JiaoxuebanDao;
import cn.scau.utils.JdbcUtils;

public class JiaoxuebanDaoImpl implements JiaoxuebanDao {
	
	public List<JiaoxuebanCustom> findCustomByGh(String gonghao) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "select jiaoxueban.*,kecheng.xuefen,jiaoshi.xingming from jiaoxueban,kecheng,jiaoshi where jiaoshi.gonghao = ? "
					+ "and jiaoshi.gonghao=jiaoxueban.gonghao "
					+ "and jiaoxueban.mingcheng = kecheng.mingcheng";
			List<JiaoxuebanCustom> jsJxbCustoms = qr.query(sql, new BeanListHandler<JiaoxuebanCustom>(JiaoxuebanCustom.class), gonghao);
			return jsJxbCustoms;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}
	
	public List<JiaoxuebanCustom> findCustomByXh(String xuehao) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "SELECT jiaoxueban.*,kecheng.xuefen FROM jiaoxueban,kecheng,chengji WHERE chengji.xuehao= ? AND chengji.jxbbianhao = jiaoxueban.jxbbianhao AND jiaoxueban.mingcheng= kecheng.mingcheng";
			List<JiaoxuebanCustom> xsJxbCustoms = qr.query(sql, new BeanListHandler<JiaoxuebanCustom>(JiaoxuebanCustom.class), xuehao);
			return xsJxbCustoms;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	@Override
	public List<JiaoxuebanCustom> findByXnXqGh(String gonghao, String xuenian, String xueqi) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "select jiaoxueban.*,kecheng.xuefen,jiaoshi.xingming from "
					+ "jiaoxueban,kecheng,jiaoshi where jiaoshi.gonghao = ? and "
					+ "jiaoshi.gonghao=jiaoxueban.gonghao and "
					+ "jiaoxueban.mingcheng = kecheng.mingcheng and "
					+ "jiaoxueban.kaikexuenian=? and jiaoxueban.kaikexueqi=?";
			List<JiaoxuebanCustom> jsbCustom = qr.query(sql, 
														new BeanListHandler<JiaoxuebanCustom>(JiaoxuebanCustom.class),
														gonghao,xuenian,xueqi);
			return jsbCustom;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	@Override
	public List<JiaoxuebanCustom> findByXnXqXh(String xuehao, String xuenian, String xueqi) {
		DataSource ds = JdbcUtils.getDataSource();
		try {
			QueryRunner qr = new QueryRunner(ds);
			String sql = "select jiaoxueban.*,kecheng.xuefen from " + 
					"jiaoxueban,kecheng,chengji where chengji.xuehao= ? AND " + 
					"chengji.jxbbianhao = jiaoxueban.jxbbianhao AND " + 
					" jiaoxueban.mingcheng= kecheng.mingcheng AND " + 
					"jiaoxueban.kaikexuenian= ? AND " + 
					"jiaoxueban.kaikexueqi=?";
			List<JiaoxuebanCustom> jsbCustom = qr.query(sql, 
														new BeanListHandler<JiaoxuebanCustom>(JiaoxuebanCustom.class),
														xuehao,xuenian,xueqi);
			return jsbCustom;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
		
	}
}
