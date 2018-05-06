package cn.scau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.scau.bean.Jiaoshi;
import cn.scau.dao.JiaoshiDao;
import cn.scau.utils.JdbcUtils;

public class JiaoshiDaoImpl implements JiaoshiDao{

	public Jiaoshi findByGonghao(String gonghao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = " SELECT * FROM jiaoshi WHERE gonghao=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gonghao);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return new Jiaoshi(rs.getString("gonghao"), rs.getString("xingming"), rs.getString("xingbie"), 
						rs.getString("dianhua"), rs.getString("email"),rs.getString("beizhu"), rs.getString("mima"));
			}
			return null;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {}
		}
	}
	}
