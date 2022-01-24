package com.bigant.dxscy.service;

import com.bigant.dxscy.model.UserCollectarticle;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface UserCollectarticleService {

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
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link UserCollectarticle}
     */
    PageInfo<UserCollectarticle> findByPage(int pageNum, int pageSize);

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