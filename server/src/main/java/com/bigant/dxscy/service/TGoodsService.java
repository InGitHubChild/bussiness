package com.bigant.dxscy.service;

import com.bigant.dxscy.model.TGoods;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface TGoodsService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TGoods}
     */
    TGoods findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link TGoods}
     */
    PageInfo<TGoods> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param tGoods
     */
    void insert(TGoods tGoods);

    /**
     * 修改
     *
     * @param tGoods
     */
    void update(TGoods tGoods);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}