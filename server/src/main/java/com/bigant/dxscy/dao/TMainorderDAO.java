package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.TMainorder;
import com.github.pagehelper.Page;

/**
 *
 */
public interface TMainorderDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TMainorder}
     */
    TMainorder findById(Integer id);

    /**
     * 分页查询
     *
     * @return {@link TMainorder}
     */
    Page<TMainorder> findByPage();

    /**
     * 新增
     *
     * @param tMainorder
     */
    void insert(TMainorder tMainorder);

    /**
     * 修改
     *
     * @param tMainorder
     */
    void update(TMainorder tMainorder);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}