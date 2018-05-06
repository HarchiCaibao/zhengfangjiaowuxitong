package cn.scau.admin.service;

import java.util.List;

import cn.scau.bean.Pingjiaoshuju;
import cn.scau.dao.impl.PingjiaoshujuDaoImpl;

public class AdJianyiCheckService {
	private PingjiaoshujuDaoImpl pjsjDaoImpl = new PingjiaoshujuDaoImpl();
	public List<Pingjiaoshuju> getPjJianyi(String jxbbianhao,String gonghao){
		return pjsjDaoImpl.findpjForAd(jxbbianhao, gonghao);
	}
}
