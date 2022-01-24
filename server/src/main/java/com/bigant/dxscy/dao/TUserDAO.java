package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.TUser;
import com.github.pagehelper.Page;

/**
 *
 */
public interface TUserDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TUser}
     */
    TUser findById(Integer id);

    /**
     * 分页查询
     *
     * @return {@link TUser}
     */
    Page<TUser> findByPage();

    /**
     * 新增
     *
     * @param tUser
     */
    void insert(TUser tUser);

    /**
     * 修改
     *
     * @param tUser
     */
    void update(TUser tUser);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}