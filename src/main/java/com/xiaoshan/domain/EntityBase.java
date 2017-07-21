package com.xiaoshan.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by gukc007 on 2017-04-18.
 */
@MappedSuperclass
public class EntityBase {
    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    /**
     * 创建人
     */
    private Long createdBy;

    /**
     * 最后更新人
     */
    private Long updatedBy;

    @PrePersist
    protected void onCreate() {
        setCreatedDate(LocalDateTime.now());
        setLastModifiedDate(LocalDateTime.now());
        if (createdBy == null) {
//            setCreatedBy(CurrentRequestHelper.getCurrentUserIdOrDefault());
        }
        if (updatedBy == null) {
//            setUpdatedBy(CurrentRequestHelper.getCurrentUserIdOrDefault());
        }
    }

    @PreUpdate
    protected void onUpdate() {
        setLastModifiedDate(LocalDateTime.now());
//        setUpdatedBy(CurrentRequestHelper.getCurrentUserIdOrDefault());
    }

    public Long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Long createdBy) {
        this.createdBy = createdBy;
    }

    public Long getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(Long updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
