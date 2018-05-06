package cn.scau.utils;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;
import com.mchange.v2.c3p0.ComboPooledDataSource;

public class JdbcUtils {
	private static  DataSource ds  = new ComboPooledDataSource();
	
	public static  DataSource getDataSource() {
		return ds;
	}
	public static Connection getConection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
		    throw new RuntimeException(e);
		}
	}
}
