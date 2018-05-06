package cn.scau.bean;

import java.util.List;


/**
 * 
 * @author 王汉志
 *分页数据的Bean，封装了分页所需的信息
 * @param <T>分页数据的类型
 */
public class PageBean<T> {
	private int currentPage;//当前页数
	private int total; //总记录数
	private int pageSize;//每页记录数，page size
	private List<T> beanList;//当前页的记录内容
	
	
	
	//计算总页数
	public int getTotalPage() {
		int totalPage = total / pageSize;
		return total%pageSize==0 ? totalPage : totalPage+1;
	}
	
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	
}
