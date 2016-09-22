package com.dian.util;

public class Page {

	private int currentPage = 1;// 当前页
	private int pageSize = 10;// 每页显示记录数 常量
	private int totalRecord;// 总记录数

	private Page(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		if (currentPage < 1) {
			this.currentPage = 1;
			return;
		}
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	/**
	 * 判断是否有上一页
	 * 
	 * @param currentPage
	 * @return
	 */
	public boolean getHasPrePage() {
		return currentPage == 1 ? false : true;
	}

	/**
	 * 判断是否有下一页
	 * 
	 * @param totalPage
	 * @param currentPage
	 * @return
	 */
	public boolean getHasNextPage() {
		return currentPage == getTotalPage() || getTotalPage() == 0 ? false : true;
	}

	/**
	 * 获取开始的记录编号
	 * 
	 * @param everyPage
	 * @param currentPage
	 * @return
	 */
	public int getBeginIndex() {
		return (currentPage - 1) * pageSize;
	}

	/**
	 * 获得总页数
	 * 
	 * @return
	 */
	public int getTotalPage() {
		if (totalRecord != 0 && totalRecord % pageSize == 0) {
			return totalRecord / pageSize;
		}
		return totalRecord / pageSize + 1;
	}

	public static Page createPage(int totalRecord) {
		return new Page(totalRecord);
	}

	public static Page getPageByCur(int totalRecord, int cur) {
		Page page = new Page(totalRecord);
		page.setCurrentPage(cur);
		return page;
	}

	public static Page getPageByCur(Page page, int cur) {
		page.setCurrentPage(cur);
		return page;
	}
}
