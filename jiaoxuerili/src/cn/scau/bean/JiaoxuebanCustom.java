package cn.scau.bean;

//Jiaoxueban的扩展类
public class JiaoxuebanCustom extends Jiaoxueban {
	
	/*添加课程属性
	 * Kecheng.xuefen
	 * */
	private int xuefen;
	
	//11.14添加教师姓名属性
	private String xingming;
	
	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public int getXuefen() {
		return xuefen;
	}

	public void setXuefen(int xuefen) {
		this.xuefen = xuefen;
	}

}
