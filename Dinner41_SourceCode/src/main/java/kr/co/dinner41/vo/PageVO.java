package kr.co.dinner41.vo;

public class PageVO {
    private String showPageName;
    private int pageNumber;

    public PageVO(){}

    public PageVO(String showPageName, int pageNumber) {
        this.showPageName = showPageName;
        this.pageNumber = pageNumber;
    }

    public String getShowPageName() {
        return showPageName;
    }

    public void setShowPageName(String showPageName) {
        this.showPageName = showPageName;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public String toString() {
        return "PageVO{" +
                "showPageName='" + showPageName + '\'' +
                ", pageNumber=" + pageNumber +
                '}';
    }
}
