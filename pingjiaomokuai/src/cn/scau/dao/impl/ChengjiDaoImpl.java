package cn.scau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.scau.dao.ChengjiDao;
import cn.scau.utils.JdbcUtils;

public class ChengjiDaoImpl implements ChengjiDao {

	public void updatePingjiao(String xuehao,String jxbbianhao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "update chengji set pingjiao=\"yes\" where xuehao = ? and jxbbianhao=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, xuehao);
			pstmt.setString(2, jxbbianhao);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (SQLException e) {}
		}
		
	}

	public boolean findIsAllYes(String xuehao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "Select * from chengji where xuehao=? and pingjiao=\"no\"";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, xuehao);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
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

	public List<String> findIsYes(String xuehao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> jxbbianhaos = new ArrayList<String>();
		try {
			con = JdbcUtils.getConnection();
			String sql = "Select * from chengji where xuehao=?and pingjiao=\"yes\"";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, xuehao);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				jxbbianhaos.add(rs.getString("jxbbianhao"));
			}
			return jxbbianhaos;
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

	@Override
	public boolean findIsAllYesByjxbbh(String jxbbianhao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "Select * from chengji where jxbbianhao=? and pingjiao=\"no\"";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jxbbianhao);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return false;
			}
			return true;
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

	@Override
	public boolean findIsSelect(String jxbbianhao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			String sql = "Select * from chengji where jxbbianhao=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, jxbbianhao);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				return true;
			}
			return false;
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
