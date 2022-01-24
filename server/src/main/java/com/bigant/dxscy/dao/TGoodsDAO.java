package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.TGoods;
import com.github.pagehelper.Page;

/**
 *
 */
public interface TGoodsDAO {

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
     * @return {@link TGoods}
     */
    Page<TGoods> findByPage();

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