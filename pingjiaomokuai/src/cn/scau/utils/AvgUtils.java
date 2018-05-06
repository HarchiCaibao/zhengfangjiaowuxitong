package cn.scau.utils;

import java.util.List;

public class AvgUtils {
	public static double calculatAvg(List<Integer> list) {
		Integer[] arry  = new Integer[list.size()];
		arry = list.toArray(arry);
		int max = arry[0];
		int min = arry[0];
		double sum = 0,avg=0;
		for(int i=0;i<arry.length;i++) {
			sum+=arry[i];
			if (arry[i]>max) {
				max = arry[i];
			}if (arry[i]<min) {
				min = arry[i];
			}
		}		
		avg = (sum-max-min)/((arry.length)-2);
		return avg;
	}
}
