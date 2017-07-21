package com.xiaoshan.domain;

import javax.persistence.Entity;
import java.math.BigDecimal;

/**
 * Created by admin on 2017/7/11.
 */
@Entity
public class ItemEntity extends EntityBase{
    private String name;

    private BigDecimal price;

    private String description;

    private String picture;

    private Long amount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
