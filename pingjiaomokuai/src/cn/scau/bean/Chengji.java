package cn.scau.bean;

public class Chengji {
	private String xuehao;
	private String jxbbianhao;
	private String pingjiao;

	public Chengji(String xuehao, String jxbbianhao,int chengji,String pingjiao) {
		this.xuehao = xuehao;
		this.jxbbianhao = jxbbianhao;
		this.pingjiao = pingjiao;
		this.chengji = chengji;
	}

	public Chengji() {
		super();
	}

	public String getXuehao() {
		return xuehao;
	}

	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}

	public String getJxbbianhao() {
		return jxbbianhao;
	}

	public void setJxbbianhao(String jxbbianhao) {
		this.jxbbianhao = jxbbianhao;
	}

	public String getPingjiao() {
		return pingjiao;
	}

	public void setPingjiao(String pingjiao) {
		this.pingjiao = pingjiao;
	}

	public int getChengji() {
		return chengji;
	}

	public void setChengji(int chengji) {
		this.chengji = chengji;
	}

	private int chengji;
}
