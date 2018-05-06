package cn.scau.bean;

public class Jiaoshi {
	private String gonghao;
	private String xingming;
	private String xingbie;
	private String dianhua;
	private String email;
	private String beizhu;
	private String mima;
	private Kecheng kecheng;
	private Jiaoxueban jiaoxueban;
	
	
	
	public Jiaoshi(String gonghao, String xingming, String xingbie, String dianhua, String email, String beizhu,
			String mima, Kecheng kecheng, Jiaoxueban jiaoxueban) {
		super();
		this.gonghao = gonghao;
		this.xingming = xingming;
		this.xingbie = xingbie;
		this.dianhua = dianhua;
		this.email = email;
		this.beizhu = beizhu;
		this.mima = mima;
		this.kecheng = kecheng;
		this.jiaoxueban = jiaoxueban;
	}

	public Jiaoxueban getJiaoxueban() {
		return jiaoxueban;
	}

	public void setJiaoxueban(Jiaoxueban jiaoxueban) {
		this.jiaoxueban = jiaoxueban;
	}

	public Kecheng getKecheng() {
		return kecheng;
	}

	public void setKecheng(Kecheng kecheng) {
		this.kecheng = kecheng;
	}

	
	
	public Jiaoshi(String gonghao, String xingming, String xingbie, String dianhua, String email, String beizhu,
			String mima, Kecheng kecheng) {
		super();
		this.gonghao = gonghao;
		this.xingming = xingming;
		this.xingbie = xingbie;
		this.dianhua = dianhua;
		this.email = email;
		this.beizhu = beizhu;
		this.mima = mima;
		this.kecheng = kecheng;
	}
	

	public Jiaoshi(String gonghao, String xingming, String xingbie, String dianhua, String email, String beizhu,
			String mima) {
		super();
		this.gonghao = gonghao;
		this.xingming = xingming;
		this.xingbie = xingbie;
		this.dianhua = dianhua;
		this.email = email;
		this.beizhu = beizhu;
		this.mima = mima;
	}

	public Jiaoshi() {
		super();
	}


	public String getGonghao() {
		return gonghao;
	}

	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}

	public String getXingming() {
		return xingming;
	}

	public void setXingming(String xingming) {
		this.xingming = xingming;
	}

	public String getXingbie() {
		return xingbie;
	}

	public void setXingbie(String xingbie) {
		this.xingbie = xingbie;
	}

	public String getDianhua() {
		return dianhua;
	}

	public void setDianhua(String dianhua) {
		this.dianhua = dianhua;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getBeizhu() {
		return beizhu;
	}

	public void setBeizhu(String beizhu) {
		this.beizhu = beizhu;
	}

	public String getMima() {
		return mima;
	}

	public void setMima(String mima) {
		this.mima = mima;
	}

}
