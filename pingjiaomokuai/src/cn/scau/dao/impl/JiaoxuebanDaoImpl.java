package cn.scau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import cn.scau.bean.Jiaoxueban;
import cn.scau.bean.JiaoxuebanCustom;
import cn.scau.bean.PageBean;
import cn.scau.dao.JiaoxuebanDao;
import cn.scau.utils.JdbcUtils;

public class JiaoxuebanDaoImpl implements JiaoxuebanDao{

	public PageBean<Jiaoxueban> findJiaoxueban(String gonghao,int currentPage,int everyPageSize) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageBean<Jiaoxueban> jiaoxuebans = new PageBean<>();
		int total = getTotal2(gonghao);
		jiaoxuebans.setTotal(total);
		jiaoxuebans.setCurrentPage(currentPage);
		jiaoxuebans.setPageSize(everyPageSize);
		List< Jiaoxueban> list = new ArrayList<>();
		try {
			con = JdbcUtils.getConnection();
			String sql = "SELECT jiaoxueban.* FROM jiaoxueban " + 
					"WHERE jiaoxueban.gonghao = ? " + 
					"ORDER BY jiaoxueban.`kaikexuenian` DESC,jiaoxueban.`kaikexueqi` DESC " + 
					"LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gonghao);
			pstmt.setInt(2, (currentPage-1)*everyPageSize);
			pstmt.setInt(3, everyPageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(new JiaoxuebanCustom(rs.getString("jxbbianhao"), 
											  rs.getString("mingcheng"), 
						                      rs.getString("kcbianhao"),
											  rs.getString("gonghao"), 
											  rs.getInt("kaikexuenian"), 
											  rs.getInt("kaikexueqi"),
                                              "abc"));
			}
			jiaoxuebans.setBeanList(list);
			return jiaoxuebans;	
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
	public List<Integer> findXuenians(String gonghao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List< Integer> list = new ArrayList<>();
		try {
			con = JdbcUtils.getConnection();
			String sql = "SELECT jiaoxueban.* FROM jiaoxueban " + 
					"WHERE jiaoxueban.gonghao = ? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gonghao);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("kaikexuenian"));
			}
			list  = new ArrayList<>(new HashSet<>(list));
			Collections.sort(list);
			Collections.reverse(list);
			return list;	
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
	public PageBean<JiaoxuebanCustom> findJiaoshi(int kaikexuenian,int kaikexueqi,int currentPage,int everyPageSize) {
		int total = getTotal(kaikexuenian,kaikexueqi);
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PageBean<JiaoxuebanCustom> jxbPageBean = new PageBean<>();
		List<JiaoxuebanCustom> jiaoshis = new ArrayList<JiaoxuebanCustom>();
		try {
			con = JdbcUtils.getConnection();
			jxbPageBean.setCurrentPage(currentPage);
			jxbPageBean.setPageSize(everyPageSize);
			//获得总记录数并赋值
			jxbPageBean.setTotal(total);
			
			String sql = "SELECT jiaoxueban.*,jiaoshi.xingming FROM " + 
					"jiaoxueban,jiaoshi WHERE jiaoxueban.gonghao=jiaoshi.gonghao " + 
					"AND jiaoxueban.kaikexuenian=? " + 
					"AND jiaoxueban.kaikexueqi=? " + 
					"ORDER BY gonghao LIMIT ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kaikexuenian);
			pstmt.setInt(2, kaikexueqi);
			pstmt.setInt(3, (currentPage-1)*everyPageSize);
			pstmt.setInt(4, everyPageSize);
			rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next()) {
				jiaoshis.add(i, new JiaoxuebanCustom(
								rs.getString("jxbbianhao"),
								rs.getString("mingcheng"),
								rs.getString("kcbianhao"), 
								rs.getString("gonghao"), 
								rs.getInt("kaikexuenian"), 
								rs.getInt("kaikexueqi"), 
								rs.getString("xingming")));
				i++;
			}
			jxbPageBean.setBeanList(jiaoshis);
			return jxbPageBean;	
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
	//获得总记录数
	private int getTotal(int kaikexuenian,int kaikexueqi) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int total = 0;
		try {
			con = JdbcUtils.getConnection();
			//获得总记录数
			String sql = "select count(*) from jiaoxueban WHERE kaikexuenian=? AND kaikexueqi=? ";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, kaikexuenian);
			pstmt.setInt(2, kaikexueqi);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				total = rs.getInt(1);
			}
			return total;
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
	//获得总记录数
    private static int getTotal2(String gonghao) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			int total = 0;
			try {
				con = JdbcUtils.getConnection();
				//获得总记录数
				String sql = "SELECT count(*)FROM jiaoxueban WHERE jiaoxueban.gonghao = ? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, gonghao);
				rs = pstmt.executeQuery();
				if (rs.next()) {
					total = rs.getInt(1);
				}
				return total;
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
	public List<Integer> findXuenians() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> xuenians = new ArrayList<>();
		try {
			con = JdbcUtils.getConnection();
			String sql = "SELECT jiaoxueban.* FROM jiaoxueban";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				xuenians.add(rs.getInt("kaikexuenian"));
			}
			return xuenians;
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
