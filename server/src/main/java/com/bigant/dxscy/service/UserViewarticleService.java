package com.bigant.dxscy.service;

import com.bigant.dxscy.model.UserViewarticle;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface UserViewarticleService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link UserViewarticle}
     */
    UserViewarticle findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link UserViewarticle}
     */
    PageInfo<UserViewarticle> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param userViewarticle
     */
    void insert(UserViewarticle userViewarticle);

    /**
     * 修改
     *
     * @param userViewarticle
     */
    void update(UserViewarticle userViewarticle);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}