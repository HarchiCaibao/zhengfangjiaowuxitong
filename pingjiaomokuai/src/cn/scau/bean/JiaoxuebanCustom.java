package cn.scau.bean;

//教学班表的增强Bean，添加教师姓名
public class JiaoxuebanCustom extends Jiaoxueban {
	private  String xingming;

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}
	

	public JiaoxuebanCustom() {
		super();
	}
	//构造器
	public JiaoxuebanCustom(String jxbbianhao, String mingcheng, String kcbianhao, String gonghao, int kaikexuenian,
			int kaikexueqi,String xingming) {
		super.setJxbbianhao(jxbbianhao);
		super.setMingcheng(mingcheng);
		super.setKcbianhao(kcbianhao);
		super.setGonghao(gonghao);
		super.setKaikexuenian(kaikexuenian);
		super.setKaikexueqi(kaikexueqi);
		this.xingming = xingming;
	}
	
}
