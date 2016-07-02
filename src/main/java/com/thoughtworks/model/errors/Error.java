package com.thoughtworks.model.errors;

public class Error {

    private final Integer status;
    private final String title;
    private final String detail;
    private final Source source;

    public Error(Integer status, String title, String detail) {
        this.status = status;
        this.title = title;
        this.detail = detail;
        this.source = new Source("Just for Test");
    }

    public Error(Integer status, String title, String detail, Source source) {
        this.status = status;
        this.title = title;
        this.detail = detail;
        this.source = source;
    }

    public Integer getStatus() {
        return status;
    }

    public String getTitle() {
        return title;
    }

    public String getDetail() {
        return detail;
    }

    public Source getSource() {
        return source;
    }
}

