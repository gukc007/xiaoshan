package com.xiaoshan.service.impl;

import com.google.common.collect.Lists;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.xiaoshan.common.BusinessExceptionBase;
import com.xiaoshan.common.helper.SecurityHelper;
import com.xiaoshan.dal.UserRepository;
import com.xiaoshan.datacontract.request.UserParamDto;
import com.xiaoshan.datacontract.request.UserLoginParam;
import com.xiaoshan.datacontract.response.UserResponse;
import com.xiaoshan.domain.OperationEntity;
import com.xiaoshan.domain.QOperationEntity;
import com.xiaoshan.domain.QUserEntity;
import com.xiaoshan.domain.UserEntity;
import com.xiaoshan.mapping.UserMapping;
import com.xiaoshan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by gukc007 on 2017-05-07.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Override
    public Long addUser(UserParamDto userParamDto) throws Exception{

        QUserEntity qUserEntity = QUserEntity.userEntity;
        if (jpaQueryFactory.selectFrom(qUserEntity)
                .where(qUserEntity.account.eq(userParamDto.getAccount())).fetchCount() > 0){
            throw new BusinessExceptionBase(BusinessExceptionBase.EnumExceptionType.UserAccountHasExist);
        }
        userParamDto.setPassword(SecurityHelper.md5(userParamDto.getPassword()));
        UserEntity userEntity = UserMapping.toEntity(userParamDto);
        List<String> codes = Lists.newArrayList("1002", "1003");
        QOperationEntity qOperationEntity = QOperationEntity.operationEntity;
        List<OperationEntity> operations = jpaQueryFactory.selectFrom(qOperationEntity)
                .where(qOperationEntity.code.in(codes)).fetch();
        operations.forEach(userEntity.getOperations()::add);

        userRepository.save(userEntity);
        return userEntity.getId();
    }

    @Override
    public UserResponse login(UserLoginParam userLoginParam) throws Exception {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        if (jpaQueryFactory.selectFrom(qUserEntity)
                .where(qUserEntity.account.eq(userLoginParam.getAccount())).fetchCount() <= 0) {
            throw new BusinessExceptionBase(BusinessExceptionBase.EnumExceptionType.UserNotExist);
        }
        String password = SecurityHelper.md5(userLoginParam.getPassword());
        UserEntity userEntity = jpaQueryFactory.selectFrom(qUserEntity)
                .where(qUserEntity.account.eq(userLoginParam.getAccount())
                        .and(qUserEntity.password.eq(password))).fetchOne();
        if (userEntity == null) {
            throw new BusinessExceptionBase(BusinessExceptionBase.EnumExceptionType.UserNameOrPasswordError);
        }
        return UserMapping.toUserResponse(userEntity);
    }

    @Override
    public UserResponse verfiyUser(String token) throws Exception {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        String[] accounts = SecurityHelper.getUserNameAndPassword(token);
        UserEntity entity = jpaQueryFactory.selectFrom(qUserEntity).where(qUserEntity.account.eq(accounts[0])
                .and(qUserEntity.password.eq(accounts[1]))).fetchOne();
        if (entity == null) {
            throw new BusinessExceptionBase(BusinessExceptionBase.EnumExceptionType.UserNoRight);
        }
        return UserMapping.toUserResponse(entity);
    }
}
