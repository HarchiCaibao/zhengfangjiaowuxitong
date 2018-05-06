package cn.scau.jiaoshi.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.scau.dao.impl.PingjiaoshujuDaoImpl;
import cn.scau.utils.AvgUtils;

public class JsPingjiaoshujuService {
	private PingjiaoshujuDaoImpl pjsjDaoImpl = new PingjiaoshujuDaoImpl();
	public Map<String, Double> getPjshujuAvg(String jxbbianhao,String gonghao) {
		
		Map<String, List<Integer>> pjshujusMap  = pjsjDaoImpl.findPjshuju(jxbbianhao, gonghao);
		Map<String, Double> avgs = new HashMap<>();
		List<Integer> chuqins = pjshujusMap.get("chuqins");
		List<Integer> ketangs = pjshujusMap.get("ketangs");
		List<Integer> ssguanxis = pjshujusMap.get("ssguanxis");
		List<Integer> zuoyeliangs = pjshujusMap.get("zuoyeliangs");
		List<Integer> shouhuos = pjshujusMap.get("shouhuos");
		try {
			avgs.put("chuqinAvg", AvgUtils.calculatAvg(chuqins));
			avgs.put("ketangAvg", AvgUtils.calculatAvg(ketangs));
			avgs.put("ssguanxiAvg", AvgUtils.calculatAvg(ssguanxis));
			avgs.put("zuoyeliangAvg", AvgUtils.calculatAvg(zuoyeliangs));
			avgs.put("shouhuoAvg", AvgUtils.calculatAvg(shouhuos));
			return avgs;
		} catch (Exception e) {//若没有评教
			return null;
		}
	}
	
	public List<String> getPjJianyi(String jxbbianhao,String gonghao){
		return pjsjDaoImpl.findJianyi(jxbbianhao, gonghao);
	}
}
