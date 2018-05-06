package cn.scau.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import cn.scau.bean.Jiaoshi;
import cn.scau.bean.Pingjiaoshuju;
import cn.scau.dao.impl.ChengjiDaoImpl;
import cn.scau.dao.impl.JiaoxuebanDaoImpl;
import cn.scau.dao.impl.PingjiaoshujuDaoImpl;
import cn.scau.dao.impl.XueshengDaoImpl;
import cn.scau.utils.AvgUtils;
public class DaoTest {
	
	
	@Test
	public void testFindJiaoshi() {
		String xuehao = "2011";
		XueshengDaoImpl xueshengDaoImpl =new XueshengDaoImpl();
		List<Jiaoshi> jiaoshis= xueshengDaoImpl.findJaioshiByxuehao(xuehao);
		System.out.println(jiaoshis);		
	}
	
	@Test
	public void testFindXuenians() {
		JiaoxuebanDaoImpl jx = new JiaoxuebanDaoImpl();
		List<Integer> xuenians = jx.findXuenians();
		System.out.println(xuenians);	
	}
	
	@Test
	public void testFindIsAllYes() {
		String xuehao = "2011";
		ChengjiDaoImpl chengjiDaoImpl = new ChengjiDaoImpl();
		boolean re = chengjiDaoImpl.findIsAllYes(xuehao);
		if(re) {System.out.println("未完成");}
		else {
			System.out.println("完成");
		}
	}
	
	@Test
	public void testInsert() {
		PingjiaoshujuDaoImpl pingjiaoshujuDaoImpl = new PingjiaoshujuDaoImpl();
		try {
			Pingjiaoshuju pingjiaoshuju = new Pingjiaoshuju();
			pingjiaoshuju.setXuehao("2011");
			pingjiaoshuju.setGonghao("102");
			pingjiaoshuju.setJxbbianhao("1");
			pingjiaoshuju.setChuqin(3);
			pingjiaoshuju.setKetang(8);
			pingjiaoshuju.setSsguanxi(10);
			pingjiaoshuju.setJianyi("老师很好人");
			System.out.println(pingjiaoshuju);
			pingjiaoshujuDaoImpl.insert(pingjiaoshuju);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void testJsFindPjshuju() {
		String gonghao = "102";
		String jxbbianhao = "1";
		PingjiaoshujuDaoImpl pj = new PingjiaoshujuDaoImpl();
		Map<String, List<Integer>> shujus = pj.findPjshuju(jxbbianhao, gonghao);
		List<Integer> list = shujus.get("ssguanxis");
		double avg = AvgUtils.calculatAvg(list);
		System.out.println(avg);
		System.out.println(shujus);
	}
	
	@Test
	public void testJsFindJianyi() {
		String gonghao = "102";
		String jxbbianhao = "1";
		PingjiaoshujuDaoImpl pj = new PingjiaoshujuDaoImpl();
		List<String> jianyis = pj.findJianyi(jxbbianhao, gonghao);
		for(int i = 0;i<jianyis.size();i++) {
			System.out.println(i+"、"+jianyis.get(i));
		}
	}
	
	@Test
	public void testJsFindJianyis() {
		String gonghao = "102";
		String jxbbianhao = "5";
		PingjiaoshujuDaoImpl pj = new PingjiaoshujuDaoImpl();
		List<Pingjiaoshuju> re = pj.findpjForAd(jxbbianhao, gonghao);
		System.out.println(re);
	}
	
	@Test
	public void testAvgUtils() {
		
		List<Integer> list = new ArrayList<>();
		list.add(10);
		list.add(8);
		list.add(9);
		list.add(9);
		double avg = AvgUtils.calculatAvg(list);
		System.out.println(avg);
		
	}
	
	@Test
	public void testXsKC() {
		XueshengDaoImpl xsd = new XueshengDaoImpl();
		List<Jiaoshi> js = xsd.findJaioshiByxuehao("2011");
		System.out.println(js);
	}
	
	@Test
	public void testFindJyforAd() {
		PingjiaoshujuDaoImpl pj = new PingjiaoshujuDaoImpl();
		
		List<Pingjiaoshuju> pjList = pj.findpjForAd("5", "102");
		System.out.println(pjList);
		
	}
	
	@Test
	public void testUpadateBatch() {
		Integer[] pjids = {1,2,4};
		PingjiaoshujuDaoImpl pj = new PingjiaoshujuDaoImpl();
		pj.updateBatch(pjids);
	}
	@Test
	public void testFindJiaoxueban() {
		JiaoxuebanDaoImpl jsDaoImpl = new JiaoxuebanDaoImpl();
		List<Integer> list = jsDaoImpl.findXuenians("102");
		System.out.println(list);
	}
	@Test
    public void testFindIsSelect() {
		ChengjiDaoImpl cj = new ChengjiDaoImpl();
		boolean t = cj.findIsSelect("8");
		System.out.println(t);
    }
	

}
