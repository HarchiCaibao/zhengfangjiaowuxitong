package cn.scau.bean;

public class Kecheng {
	private String kcbianhao;
	private String mingcheng;
	private int xuefen;
	private int kaikexueqi;
	public Kecheng() {
		super();
		
	}
	public Kecheng(String kcbianhao, String mingcheng) {
		this.kcbianhao = kcbianhao;
		this.mingcheng = mingcheng;
	}
	
	public int getXuefen() {
		return xuefen;
	}
	public void setXuefen(int xuefen) {
		this.xuefen = xuefen;
	}
	public int getKaikexueqi() {
		return kaikexueqi;
	}
	public void setKaikexueqi(int kaikexueqi) {
		this.kaikexueqi = kaikexueqi;
	}
	public String getKcbianhao() {
		return kcbianhao;
	}
	public void setKcbianhao(String kcbianhao) {
		this.kcbianhao = kcbianhao;
	}
	public String getMingcheng() {
		return mingcheng;
	}
	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}
}
