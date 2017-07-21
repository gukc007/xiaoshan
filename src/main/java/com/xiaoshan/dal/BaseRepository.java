package com.xiaoshan.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Created by gukc007 on 2017-04-24.
 */
@NoRepositoryBean
public interface BaseRepository<T> extends CrudRepository<T, Long>, QueryByExampleExecutor<T>,
        PagingAndSortingRepository<T, Long>{

    List<T> findByIdIn(List<Long> ids);
}
