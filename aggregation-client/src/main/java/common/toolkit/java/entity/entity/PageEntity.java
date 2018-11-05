package common.toolkit.java.entity.entity;

import java.util.List;

/**
 * 分页包装类
 * @author nileader
 */
public class PageEntity<T> {

    private List<T> results;
    private long    totalResults;
    private int     totalPages;
    private int     pageNum;
    private int     pageSize;
    private int     page;        // 当前页

    public PageEntity() {
    }

    public PageEntity(int pageNum, int pageSize) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public long getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(long totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages() {
        if (this.getPageSize() == 0) {
            this.totalPages = 1;
        } else {
            this.totalPages = (int) (this.totalResults / this.getPageSize() + ((this.totalResults % this.getPageSize()) == 0 ? 0
                    : 1));
            if (totalPages == 0)
                totalPages = 1;
        }

        this.totalPages = totalPages;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

}
