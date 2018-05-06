package cn.scau.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import cn.scau.bean.Jiaoshi;
import cn.scau.bean.Jiaoxueban;
import cn.scau.bean.Kecheng;
import cn.scau.dao.XueshengDao;
import cn.scau.utils.JdbcUtils;

public class XueshengDaoImpl implements XueshengDao {
	
	public List<Jiaoshi> findJaioshiByxuehao(String xuehao) {
		int i = 0;
		List<Jiaoshi> jiaoshiList = new ArrayList<Jiaoshi>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String xnxq = getXnXq();
		//得到当前开课学年
		int xn = Integer.valueOf(xnxq.substring(0, xnxq.indexOf("-")));
		//得到当前开课学期
		int index = Integer.valueOf(xnxq.indexOf("-"))+1;
		int xq = Integer.valueOf(xnxq.substring(index));
		
		try {
			con = JdbcUtils.getConnection();
			String sql = "select jiaoshi.*,kecheng.*,chengji.jxbbianhao"
					+ " from jiaoshi,chengji,jiaoxueban,kecheng WHERE chengji.xuehao=?"
					+ " and chengji.jxbbianhao=jiaoxueban.jxbbianhao"
					+ " and jiaoxueban.gonghao = jiaoshi.gonghao"
					+ " and jiaoxueban.kcbianhao = kecheng.kcbianhao"
					+ " and jiaoxueban.kaikexuenian=?" 
					+ " and jiaoxueban.kaikexueqi=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, xuehao);
			pstmt.setInt(2, xn);
			pstmt.setInt(3, xq);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				jiaoshiList.add(i,new Jiaoshi(rs.getString("gonghao"),
						rs.getString("xingming"), 
						rs.getString("xingbie"),
						rs.getString("dianhua"),
						rs.getString("email"), 
						rs.getString("beizhu"), rs.getString("mima"),
						new Kecheng(rs.getString("kcbianhao"), 
						rs.getString("mingcheng")),
						new Jiaoxueban(rs.getString("jxbbianhao"))
						));
				i++;
			}		
			return jiaoshiList;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
			}
		}
	}
	/**
	 * 获取当前时间的年与月
	 * 根据年月确定当前的学年与当前学期
	 * 9月到次年1月为第一学期，二月到8月为第二学期
	 * @return 当前学年与当前学期字符串，以“-”分开
	 */
	private static String getXnXq() {
		String xnxq = "";
		Calendar now = Calendar.getInstance();
		String year = String.valueOf(now.get(Calendar.YEAR));
		int mon = now.get(Calendar.MONTH)+1;
		String xq;
		//九月到次年一月为第一学期
		if (mon>=9&&mon<=12||mon==1) {
			xq = "1";
		}else {//二月到8月为第二学期
			xq = "2";
		}
		xnxq = year+"-"+xq;
		return xnxq;
	}
}
