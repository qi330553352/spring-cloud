package common.toolkit.java.entity.entity;

import java.util.List;

@Deprecated
@SuppressWarnings(value = { "all" })
public class Page<T> {
    private static final int   DEFAULT_PAGE_INDEX = 0;          // 记录开始数
    private static final int   DEFAULT_PAGE_SIZE  = 10;         // 默认的页面记录数

    protected int              pageIndex          = 0;          // 记录开始数
    protected int              pageSize           = 10;         // 页面记录数
    protected int              totalPage          = 1;          // 总页数
    protected int              pageCount;                       // 当前页的记录数
    protected boolean          isFirst            = false;
    protected List<T>          result             = null;
    protected long             totalData          = 0;
    public static final String JSON_DATA          = "json_data";

    public Page() {
        this.pageIndex = DEFAULT_PAGE_INDEX;
        this.pageSize = DEFAULT_PAGE_SIZE;
    }

    public Page(int pageIndex, int pageSize) {
        this.pageIndex = pageIndex > 0 ? pageIndex : DEFAULT_PAGE_INDEX;
        this.pageSize = pageSize > 0 ? pageSize : DEFAULT_PAGE_SIZE;
    }

    public long getTotalData() {
        return totalData;
    }

    public void setTotalData(long totalData) {
        this.totalData = totalData;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public boolean getIsFirst() {
        return isFirst;
    }

    public void setIsFirst(boolean isFirst) {
        this.isFirst = isFirst;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }

    public void setTotalPageCount() {
        if (this.getPageSize() == 0) {
            this.totalPage = 1;
        } else {
            this.totalPage = (int) (this.getTotalData() / this.getPageSize() + ((this.getTotalData() % this
                    .getPageSize()) == 0 ? 0 : 1));
            if (totalPage == 0)
                totalPage = 1;
        }
    }
}
