package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.UserCollectarticle;
import com.github.pagehelper.Page;

/**
 *
 */
public interface UserCollectarticleDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link UserCollectarticle}
     */
    UserCollectarticle findById(Integer id);

    /**
     * 分页查询
     *
     * @return {@link UserCollectarticle}
     */
    Page<UserCollectarticle> findByPage();

    /**
     * 新增
     *
     * @param userCollectarticle
     */
    void insert(UserCollectarticle userCollectarticle);

    /**
     * 修改
     *
     * @param userCollectarticle
     */
    void update(UserCollectarticle userCollectarticle);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}