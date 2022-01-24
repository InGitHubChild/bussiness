package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.TUserinfo;
import com.github.pagehelper.Page;

/**
 *
 */
public interface TUserinfoDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TUserinfo}
     */
    TUserinfo findById(Integer id);

    /**
     * 分页查询
     *
     * @return {@link TUserinfo}
     */
    Page<TUserinfo> findByPage();

    /**
     * 新增
     *
     * @param tUserinfo
     */
    void insert(TUserinfo tUserinfo);

    /**
     * 修改
     *
     * @param tUserinfo
     */
    void update(TUserinfo tUserinfo);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}