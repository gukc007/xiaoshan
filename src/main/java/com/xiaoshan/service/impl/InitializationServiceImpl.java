package com.xiaoshan.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xiaoshan.dal.OperationRepository;
import com.xiaoshan.dal.UserRepository;
import com.xiaoshan.domain.*;
import com.xiaoshan.domain.OperationEntity;
import com.xiaoshan.service.InitializationService;
import com.xiaoshan.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * Created by gukc007 on 2017-05-04.
 */
@Service
public class InitializationServiceImpl implements InitializationService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OperationRepository operationRepository;


    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public void initializeData() {

        System.out.println("初始化基础数据");

        QOperationEntity qOperationEntity = QOperationEntity.operationEntity;
        if (jpaQueryFactory.selectFrom(qOperationEntity).fetchCount() <=  0) {
            List<OperationEntity> operations = Lists.newArrayList();
            operations.add(new OperationEntity("土地元素管理", "1001"));
            operations.add(new OperationEntity("浏览所有土地", "1002"));
            operations.add(new OperationEntity("查看我的土地", "1003"));
            operationRepository.save(operations);
        }

        System.out.println("基础数据初始化完毕");

        UserEntity admin = userRepository.findOne(1L);
        if (admin == null) {
            System.out.println("管理员不存在,开始初始化管理员");
            admin = new UserEntity();
            admin.setId(1L);
            admin.setName("管理员");
            admin.setAccount("admin");
            admin.setPassword("admin");
            Set<OperationEntity> operations = Sets.newHashSet(jpaQueryFactory.selectFrom(qOperationEntity).fetch());
            admin.setOperations(operations);
            userRepository.save(admin);
            System.out.println("管理员处始化完毕");
        }else {
            System.out.println("管理员已存在,跳过管理员初始化");
        }
    }
}
