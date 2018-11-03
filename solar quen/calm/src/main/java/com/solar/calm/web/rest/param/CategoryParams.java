package com.solar.calm.web.rest.param;

public class CategoryParams extends BaseParams {

    private String name;
    private String icon;
    private String parentId;

    public CategoryParams() {
    }

    public CategoryParams(String name, String icon, String parentId) {
        this.name = name;
        this.icon = icon;
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
