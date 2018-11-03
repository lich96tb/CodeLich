package com.solar.calm.web.rest.param;

public class SectionParams extends BaseParams {

    private String name;
    private String url;
    private String categoryId;
    private String parentId;
    private String duration;
    private String count;
    private boolean isFee;
    private boolean isBuy;
    private Integer order;
    private String type;
    private Long view;
    private String urlBackground;
    private String typeBackground;

    public SectionParams() {
    }

    public SectionParams(String name, String url, String categoryId, String parentId, String duration, String count, boolean isFee, boolean isBuy, Integer order, String type, Long view, String urlBackground, String typeBackground) {
        this.name = name;
        this.url = url;
        this.categoryId = categoryId;
        this.parentId = parentId;
        this.duration = duration;
        this.count = count;
        this.isFee = isFee;
        this.isBuy = isBuy;
        this.order = order;
        this.type = type;
        this.view = view;
        this.urlBackground = urlBackground;
        this.typeBackground = typeBackground;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean isFee() {
        return isFee;
    }

    public void setFee(boolean fee) {
        isFee = fee;
    }

    public boolean isBuy() {
        return isBuy;
    }

    public void setBuy(boolean buy) {
        isBuy = buy;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public String getUrlBackground() {
        return urlBackground;
    }

    public void setUrlBackground(String urlBackground) {
        this.urlBackground = urlBackground;
    }

    public String getTypeBackground() {
        return typeBackground;
    }

    public void setTypeBackground(String typeBackground) {
        this.typeBackground = typeBackground;
    }
}
