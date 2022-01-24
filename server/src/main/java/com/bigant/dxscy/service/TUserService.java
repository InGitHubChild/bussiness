package com.bigant.dxscy.service;

import com.bigant.dxscy.model.TUser;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface TUserService {

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
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link TUser}
     */
    PageInfo<TUser> findByPage(int pageNum, int pageSize);

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