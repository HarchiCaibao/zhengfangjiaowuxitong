package cn.scau.dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cn.scau.dao.JiaoshiTxDao;
import cn.scau.utils.JdbcUtils;

public class JiaoshiTxDaoImpl implements JiaoshiTxDao {

	public void insertByPh(String gonghao, String path) {
		InputStream in = null;
		PreparedStatement ps = null;
		Connection con = null;
		try {

			String url = path.substring(path.lastIndexOf("/"));
			in = JiaoshiTxDaoImpl.class.getClassLoader().getResourceAsStream("imgs" + url);
			String sql = "INSERT INTO touxiang(gonghao,touxiang) VALUES(?,?)";
			con = JdbcUtils.getConection();
			ps = con.prepareStatement(sql);
			ps.setString(1, gonghao);
			ps.setBinaryStream(2, in, in.available());
			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (in != null)
					in.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
			} catch (IOException | SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public InputStream slectTouxiangByGh(String gonghao) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		InputStream in = null;
		try {
			con = JdbcUtils.getConection();
			String sql = "SELECT touxiang FROM touxiang WHERE gonghao=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, gonghao);
			rs = ps.executeQuery();
			if (rs.next()) {
				in = rs.getBinaryStream("touxiang");
			}
			return in;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (in != null)
					in.close();
			} catch (IOException | SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	public void updateBySt(String gonghao, InputStream in) {
		PreparedStatement ps = null;
		Connection con = null;
		try {
			con = JdbcUtils.getConection();
			String sql = "update touxiang  set touxiang=? where gonghao = ?";
			ps = con.prepareStatement(sql);
			ps.setBinaryStream(1, in, in.available());
			ps.setString(2, gonghao);
			ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}finally {
			try {	
				if (ps != null)
					ps.close();
				if (con != null)
					con.close();
				if (in != null)
					in.close();
			} catch (IOException | SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}
}
