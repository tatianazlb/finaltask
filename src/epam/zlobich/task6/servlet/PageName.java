package epam.zlobich.task6.servlet;

public enum PageName {
    INDEX_PAGE("index.jsp"),
    BUTTON_PAGE("button.jsp"),
    PARSING_PAGE("table.jsp");

    private String pageName;

    PageName() {
    }

    PageName(String pageValue) {
        this.pageName = pageValue;
    }

    public String getPageName() {
        return pageName;
    }

    public static PageName hasNameInPages(String value) {
        for (PageName pageName : PageName.values()) {
            if (pageName.getPageName().equals(value)) {
                return pageName;
            }
        }
        return null;
    }
}