package com.xiaoshan.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gukc007 on 2017-05-04.
 */
@Entity
public class OperationEntity extends EntityBase{

    private String name;

    private String code;

    @ManyToMany(targetEntity = UserEntity.class, mappedBy = "operations")
    private Set<UserEntity> users = new HashSet<>();

    public OperationEntity() {
    }

    public OperationEntity(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
