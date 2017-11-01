package com.azcx9d.common.util;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/*分页Bean*/
public class Page {
	
	private Integer totalRow=0; // 总数据记录数，默认为0
	
	private Integer pageSize = 10;// 每页记录数，默认是10
	private Integer currentPage=1;// 当前页索引，默认是第1页
    private Integer totalPage=1;// 总页数，默认也为1
    @SuppressWarnings("unchecked")
	private List pageList;//每页的对象数据列表
    private boolean firstPage;// 是否为第一页
	private boolean lastPage;// 是否为最后一页
	private boolean hasPreviousPage;// 是否有前一页
	private boolean hasNextPage;// 是否有后一页
	
//    private Integer currentGroup=1;//当前页索引组，默认为1
//    private Integer groupSize=10;//每组的页记录数 
//    private Integer totalGroup=1;//页索引组的总数，默认为1
//    private List<Integer> groupList = new ArrayList<Integer>();//页索引组
	

	// Constructors
	/** default constructor */
	public Page(){
	}
	/** minimal constructor */
	public Page(Integer currentPage){
		this.setCurrentPage(currentPage);
	}
	
	public Page(Integer currentPage,Integer pageSize){
		this.setCurrentPage(currentPage);
		this.setPageSize(pageSize);
	}
	
    /*初始化分页信息*/
    public void init() {
        this.firstPage = isFirstPage();
        this.lastPage = isLastPage();
        this.hasPreviousPage = isHasPreviousPage();
        this.hasNextPage = isHasNextPage();
        
//        this.countListGroup();
    }

	/*判断页的信息*/
    public boolean isFirstPage() {
    	boolean flag = currentPage == 1;
        return currentPage == 1;// 如是当前页是第1页
    }
    public boolean isLastPage() {
        return currentPage.equals(totalPage);// 如果当前页是最后一页
    }
    public boolean isHasPreviousPage() {
        return currentPage != 1;// 只要当前页不是第1页，就有上一页
    }
    public boolean isHasNextPage() {
        return !currentPage.equals(totalPage);// 只要当前页不是最后1页
    }

    /**
     * 获得当前页开始记录号
     * @param pageSize
     *            每页记录数
     * @param currentPage
     *            当前第几页
     * @return 当前页开始记录号
     */
    public Integer getOffset() {
    	/*判断可能会出现的页索引大于总页数的情况*/
    	if(this.currentPage > this.totalPage&&this.totalPage>0)
    		this.currentPage = totalPage;
        return pageSize * (currentPage - 1);
    }
    
    // exterior transter
    /**
     * 计算总页数,静态方法,供外部直接通过类名调用
     * @param pageSize 每页记录数
     * @param allRow 总记录数
     * @return 总页数
     */
    public static int countTotalPage(Integer pageSize,Integer allRow) {
    	return (allRow+pageSize-1)/pageSize;
    }
    /**
     * 计算当前页开始记录
     * @param pageSize 每页记录数
     * @param currentPage 当前第几页
     * @return 当前页开始记录号
     */
    public static int countOffset(Integer pageSize,Integer currentPage) {
        final int offset = pageSize * (currentPage - 1);
        return offset;
    }
    /**
     * 计算当前页,若请求的URL中没有"?page="或者小于1(为0的情况),则用1代替
     * @param page 传入的参数(可能为空,即0,则返回1)
     * @return 当前页
     */
    public static int countCurrentPage(Integer currentPage) {
        Integer curPage = (currentPage == null||currentPage < 1)? 1 : currentPage;
        return curPage;
    }
    
    // Property accessors
    public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		/*判断传入的页索引是否有效*/
		if(currentPage == null||currentPage < 1)
			this.currentPage = 1;
		else
			this.currentPage = currentPage;
	}
	public Integer getTotalRow() {
		return totalRow;
	}
	public void setTotalRow(Integer totalRow){
		this.totalRow = totalRow;
		/**
		 * 计算总页数，被动
		 * 当改变总记录数时，总页数、页索引组总数随之重新生成
		 */
		this.totalPage = (totalRow+this.pageSize-1)/this.pageSize;
//		this.totalGroup = (totalPage%groupSize == 0)?(totalPage/groupSize):(totalPage/groupSize+1);
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public boolean getFirstPage(){
		return this.firstPage;
	}
	public void setFirstPage(boolean firstPage) {
		this.firstPage = firstPage;
	}
	public boolean getLastPage(){
		return this.lastPage;
	}
	public void setLastPage(boolean lastPage) {
		this.lastPage = lastPage;
	}
	public boolean getHasPreviousPage(){
		return this.hasPreviousPage;
	}
	public boolean getHasNextPage(){
		return this.hasNextPage;
	}
	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}
	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}
	@SuppressWarnings("unchecked")
	public List getPageList() {
		return pageList;
	}
	@SuppressWarnings("unchecked")
	public void setPageList(List pageList) {
		this.pageList = pageList;
	}
//	public Integer getCurrentGroup() {
//		return currentGroup;
//	}
//	public void setCurrentGroup(Integer currentGroup) {
//		/*判断传入的页索引是否有效*/
//		if(currentGroup == null||currentGroup < 1)
//			this.currentGroup = 1;
//		else
//			this.currentGroup = currentGroup;
//	}
	
	/* 计算当前请求页所在的页索引组*/
//	public void countListGroup(){
//		//计算出当前组的最大和最小值
//		Integer max = this.currentGroup*this.groupSize;
//		Integer min = (this.currentGroup-1)*this.groupSize+1;
//		//判断当前页是否在该组内，若不是换一组
//		if (this.currentPage > max)
//			while (this.currentPage > this.currentGroup * this.groupSize) {
//				this.currentGroup += 1;
//			}
//		else if (this.currentPage < min)
//			while (this.currentPage < (this.currentGroup - 1) * this.groupSize
//					+ 1) {
//				this.currentGroup -= 1;
//			}
//		max = this.currentGroup*this.groupSize;
//		min = (this.currentGroup-1)*this.groupSize+1;
//		
//        for (int i=min,len=max;i<=len;i++) {
//        	if (i <= totalPage) 
//            	groupList.add(new Integer(i));
//        }
//        Collections.sort(groupList);
//	}
	
//	public Integer getGroupSize() {
//		return groupSize;
//	}
//	public void setGroupSize(Integer groupSize) {
//		this.groupSize = groupSize;
//	}
//	public Integer getTotalGroup() {
//		return totalGroup;
//	}
//	public void setTotalGroup(Integer totalGroup) {
//		this.totalGroup = totalGroup;
//	}
//	public List<Integer> getGroupList() {
//		return groupList;
//	}
//	public void setGroupList(List<Integer> groupList) {
//		this.groupList = groupList;
//	}
}