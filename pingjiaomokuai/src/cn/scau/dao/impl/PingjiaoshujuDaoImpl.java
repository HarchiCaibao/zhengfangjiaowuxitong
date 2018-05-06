package cn.scau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.scau.bean.Pingjiaoshuju;
import cn.scau.dao.PingjiaoshujuDao;
import cn.scau.utils.JdbcUtils;

public class PingjiaoshujuDaoImpl implements PingjiaoshujuDao {

	public void insert(Pingjiaoshuju pingjiaoshuju) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = JdbcUtils.getConnection();
			String sql="INSERT INTO pingjiaoshuju(xuehao,gonghao,jxbbianhao,chuqin,ketang,ssguanxi,zuoyeliang,shouhuo,jianyi) VALUES (?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,pingjiaoshuju.getXuehao());
			pstmt.setString(2, pingjiaoshuju.getGonghao());
			pstmt.setString(3, pingjiaoshuju.getJxbbianhao());
			pstmt.setInt(4, pingjiaoshuju.getChuqin());
			pstmt.setInt(5, pingjiaoshuju.getKetang());
			pstmt.setInt(6, pingjiaoshuju.getSsguanxi());
			pstmt.setInt(7, pingjiaoshuju.getZuoyeliang());
			pstmt.setInt(8, pingjiaoshuju.getShouhuo());
			pstmt.setString(9, pingjiaoshuju.getJianyi());
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

	//将评教各项分数放在list里返回
	public Map<String, List<Integer>> findPjshuju(String jxbbianhao, String gonghao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Integer> chuqinlist = new ArrayList<>();
		List<Integer> ketanglist = new ArrayList<>();
		List<Integer> ssguanxilist = new ArrayList<>();
		List<Integer> zuoyelianglist = new ArrayList<>();
		List<Integer> shouhuolist = new ArrayList<>();
		Map<String, List<Integer>> pjshujusMap = new HashMap<String, List<Integer>>();
		try {
			con = JdbcUtils.getConnection();
			String sql="SELECT * FROM pingjiaoshuju WHERE gonghao= ? AND jxbbianhao = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gonghao);
			pstmt.setString(2, jxbbianhao);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				chuqinlist.add(rs.getInt("chuqin"));
				ketanglist.add(rs.getInt("ketang"));
				ssguanxilist.add(rs.getInt("ssguanxi"));
				zuoyelianglist.add(rs.getInt("zuoyeliang"));
				shouhuolist.add(rs.getInt("shouhuo"));
			}
			pjshujusMap.put("chuqins", chuqinlist);
			pjshujusMap.put("ketangs", ketanglist);
			pjshujusMap.put("ssguanxis", ssguanxilist);
			pjshujusMap.put("zuoyeliangs", zuoyelianglist);
			pjshujusMap.put("shouhuos", shouhuolist);
			return pjshujusMap;
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

	//查询出建议（代码有冗余，待改进！！！）
	public List<String> findJianyi(String jxbbianhao, String gonghao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> jianyis = new ArrayList<>();
		try {
			con = JdbcUtils.getConnection();
			String sql="SELECT * FROM pingjiaoshuju WHERE gonghao= ? AND jxbbianhao = ?"
					+ " AND delet=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gonghao);
			pstmt.setString(2, jxbbianhao);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String jianyi = rs.getString("jianyi");
				if (jianyi.trim().isEmpty()||jianyi==null){
					continue;
				}else {
					jianyis.add(jianyi);
				}
			}
			return jianyis;
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
	public List<Pingjiaoshuju> findpjForAd(String jxbbianhao, String gonghao) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Pingjiaoshuju> jianyis = new ArrayList<>();
		try {
			con = JdbcUtils.getConnection();
			String sql="SELECT * FROM pingjiaoshuju WHERE gonghao= ? AND jxbbianhao = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gonghao);
			pstmt.setString(2, jxbbianhao);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				jianyis.add(new Pingjiaoshuju(rs.getInt("pjid"),rs.getString("jianyi"), 
						rs.getBoolean("delet")));
			}
			return jianyis;
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
	public void updateBatch(Integer[] pjids) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = JdbcUtils.getConnection();
			String sql="UPDATE pingjiaoshuju SET delet=1 WHERE pjid IN(";
			int idLength = pjids.length;
			//只更新一条数据
			if (idLength==1) {
				sql = sql.concat("?)");
			}else {//更新多条数据
				for (int i = 0; i < pjids.length-1; i++) {
					sql = sql.concat("?,");
				}
				sql = sql.concat("?)");
			}
			pstmt = con.prepareStatement(sql);
			//赋值
			for (int j = 1; j <= pjids.length; j++) {
				pstmt.setInt(j, pjids[j-1]);
			}
			//执行更新
			pstmt.executeUpdate();		
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
