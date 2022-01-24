package com.bigant.dxscy.service;

import com.bigant.dxscy.model.TMainorder;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface TMainorderService {

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
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link TMainorder}
     */
    PageInfo<TMainorder> findByPage(int pageNum, int pageSize);

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