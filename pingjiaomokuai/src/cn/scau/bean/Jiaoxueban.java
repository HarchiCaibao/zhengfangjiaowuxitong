package cn.scau.bean;

public class Jiaoxueban {
	private String jxbbianhao;
	private String mingcheng;
	private String kcbianhao;
	private String gonghao;
	private int kaikexuenian;
	private int kaikexueqi;
	
	public Jiaoxueban() {
		super();
	}
	
	public Jiaoxueban(String jxbbianhao) {
		super();
		this.jxbbianhao = jxbbianhao;
	}


	public Jiaoxueban(String jxbbianhao, String mingcheng, String kcbianhao, String gonghao, int kaikexuenian,
			int kaikexueqi) {
		this.jxbbianhao = jxbbianhao;
		this.mingcheng = mingcheng;
		this.kcbianhao = kcbianhao;
		this.gonghao = gonghao;
		this.kaikexuenian = kaikexuenian;
		this.kaikexueqi = kaikexueqi;
	}

	public String getJxbbianhao() {
		return jxbbianhao;
	}
	public void setJxbbianhao(String jxbbianhao) {
		this.jxbbianhao = jxbbianhao;
	}
	public String getMingcheng() {
		return mingcheng;
	}
	public void setMingcheng(String mingcheng) {
		this.mingcheng = mingcheng;
	}
	public String getKcbianhao() {
		return kcbianhao;
	}
	public void setKcbianhao(String kcbianhao) {
		this.kcbianhao = kcbianhao;
	}
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	public int getKaikexuenian() {
		return kaikexuenian;
	}
	public void setKaikexuenian(int kaikexuenian) {
		this.kaikexuenian = kaikexuenian;
	}
	public int getKaikexueqi() {
		return kaikexueqi;
	}
	public void setKaikexueqi(int kaikexueqi) {
		this.kaikexueqi = kaikexueqi;
	}
}
