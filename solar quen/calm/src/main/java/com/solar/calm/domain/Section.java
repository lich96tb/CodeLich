package com.solar.calm.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Section.
 */
@Document(collection = "section")
public class Section implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    @Field("url")
    private String url;

    @Field("category_id")
    private String categoryId;

    @Field("parent_id")
    private String parentId;

    @Field("duration")
    private String duration;

    @Field("count")
    private String count;

    @Field("is_fee")
    private boolean isFee;

    @Field("is_buy")
    private boolean isBuy;

    @Field("order")
    private Integer order;

    @Field("type")
    private String type;

    @Field("view")
    private Long view;

    @Field("url_background")
    private String urlBackground;

    @Field("type_background")
    private String typeBackground;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Section name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public Section url(String url) {
        this.url = url;
        return this;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public Section categoryId(String categoryId) {
        this.categoryId = categoryId;
        return this;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getParentId() {
        return parentId;
    }

    public Section parentId(String parentId) {
        this.parentId = parentId;
        return this;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getDuration() {
        return duration;
    }

    public Section duration(String duration) {
        this.duration = duration;
        return this;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCount() {
        return count;
    }

    public Section count(String count) {
        this.count = count;
        return this;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public boolean getIsFee() {
        return isFee;
    }

    public Section isFee(boolean isFee) {
        this.isFee = isFee;
        return this;
    }

    public void setIsFee(boolean isFee) {
        this.isFee = isFee;
    }

    public boolean getIsBuy() {
        return isBuy;
    }

    public Section isBuy(boolean isBuy) {
        this.isBuy = isBuy;
        return this;
    }

    public void setIsBuy(boolean isBuy) {
        this.isBuy = isBuy;
    }

    public Integer getOrder() {
        return order;
    }

    public Section order(Integer order) {
        this.order = order;
        return this;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getType() {
        return type;
    }

    public Section type(String type) {
        this.type = type;
        return this;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getView() {
        return view;
    }

    public Section view(Long view) {
        this.view = view;
        return this;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public String getUrlBackground() {
        return urlBackground;
    }

    public Section urlBackground(String urlBackground) {
        this.urlBackground = urlBackground;
        return this;
    }

    public void setUrlBackground(String urlBackground) {
        this.urlBackground = urlBackground;
    }

    public String getTypeBackground() {
        return typeBackground;
    }

    public Section typeBackground(String typeBackground) {
        this.typeBackground = typeBackground;
        return this;
    }

    public void setTypeBackground(String typeBackground) {
        this.typeBackground = typeBackground;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Section section = (Section) o;
        if (section.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), section.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Section{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", url='" + getUrl() + "'" +
            ", categoryId='" + getCategoryId() + "'" +
            ", parentId='" + getParentId() + "'" +
            ", duration='" + getDuration() + "'" +
            ", count='" + getCount() + "'" +
            ", isFee='" + getIsFee() + "'" +
            ", isBuy='" + getIsBuy() + "'" +
            ", order=" + getOrder() +
            ", type='" + getType() + "'" +
            ", view=" + getView() +
            ", urlBackground='" + getUrlBackground() + "'" +
            ", typeBackground='" + getTypeBackground() + "'" +
            "}";
    }
}
