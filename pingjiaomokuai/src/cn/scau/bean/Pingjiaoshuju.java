package cn.scau.bean;

public class Pingjiaoshuju {
	private int pjid;  //评教数据唯一标志
	private String xuehao;
	private String gonghao;
	private String jxbbianhao;
	private Integer chuqin;
	private Integer ketang;
	private Integer ssguanxi;
	private Integer zuoyeliang;
	private Integer shouhuo;
	private String jianyi;
	private boolean delet;
	
	
	public Pingjiaoshuju(Integer pjid,String jianyi, boolean delet) {
		this.pjid = pjid; 
		this.jianyi = jianyi;
		this.delet = delet;
	}
	public Pingjiaoshuju() {
		super();
	}
	
	public int getPjid() {
		return pjid;
	}
	public void setPjid(int pjid) {
		this.pjid = pjid;
	}
	public boolean isDelet() {
		return delet;
	}
	public void setDelet(boolean delet) {
		this.delet = delet;
	}
	public Integer getShouhuo() {
		return shouhuo;
	}
	public void setShouhuo(Integer shouhuo) {
		this.shouhuo = shouhuo;
	}
	public Integer getZuoyeliang() {
		return zuoyeliang;
	}
	public void setZuoyeliang(Integer zuoyeliang) {
		this.zuoyeliang = zuoyeliang;
	}
	public String getXuehao() {
		return xuehao;
	}
	public void setXuehao(String xuehao) {
		this.xuehao = xuehao;
	}
	public String getGonghao() {
		return gonghao;
	}
	public void setGonghao(String gonghao) {
		this.gonghao = gonghao;
	}
	public String getJxbbianhao() {
		return jxbbianhao;
	}
	public void setJxbbianhao(String jxbbianhao) {
		this.jxbbianhao = jxbbianhao;
	}
	public Integer getSsguanxi() {
		return ssguanxi;
	}
	public void setSsguanxi(Integer ssguanxi) {
		this.ssguanxi = ssguanxi;
	}
	public String getJianyi() {
		return jianyi;
	}
	public void setJianyi(String jianyi) {
		this.jianyi = jianyi;
	}
	public Integer getChuqin() {
		return chuqin;
	}
	public void setChuqin(Integer chuqin) {
		this.chuqin = chuqin;
	}
	public Integer getKetang() {
		return ketang;
	}
	public void setKetang(Integer ketang) {
		this.ketang = ketang;
	}
	
}
