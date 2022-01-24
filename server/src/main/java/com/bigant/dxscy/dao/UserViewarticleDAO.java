package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.UserViewarticle;
import com.github.pagehelper.Page;

/**
 *
 */
public interface UserViewarticleDAO {

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
     * @return {@link UserViewarticle}
     */
    Page<UserViewarticle> findByPage();

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