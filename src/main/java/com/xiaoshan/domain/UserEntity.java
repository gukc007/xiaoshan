package com.xiaoshan.domain;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gukc007 on 2017-05-04.
 */
@Entity
public class UserEntity extends EntityBase{

    private String account;

    private String password;

    private String name;

    @ManyToMany(targetEntity = OperationEntity.class)
    @JoinTable(name = "user_operation_mapping")
    private Set<OperationEntity> operations = new HashSet<>();

    public UserEntity() {
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<OperationEntity> getOperations() {
        return operations;
    }

    public void setOperations(Set<OperationEntity> operations) {
        this.operations = operations;
    }
}
