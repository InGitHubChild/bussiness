package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.UserCollectvideo;
import com.github.pagehelper.Page;

/**
 *
 */
public interface UserCollectvideoDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link UserCollectvideo}
     */
    UserCollectvideo findById(Integer id);

    /**
     * 分页查询
     *
     * @return {@link UserCollectvideo}
     */
    Page<UserCollectvideo> findByPage();

    /**
     * 新增
     *
     * @param userCollectvideo
     */
    void insert(UserCollectvideo userCollectvideo);

    /**
     * 修改
     *
     * @param userCollectvideo
     */
    void update(UserCollectvideo userCollectvideo);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}