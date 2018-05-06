package cn.scau.xuesheng.service;

import cn.scau.bean.Pingjiaoshuju;
import cn.scau.dao.impl.PingjiaoshujuDaoImpl;

public class XsPingjiaoshujuService {

	private PingjiaoshujuDaoImpl pjshDaoImpl= new PingjiaoshujuDaoImpl();
	
	public void savePingjiaoshuju(Pingjiaoshuju pingjiaoshuju) {	
		pjshDaoImpl.insert(pingjiaoshuju);
	}
}
