package com.bigant.dxscy.service;

import com.bigant.dxscy.model.TArticle;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface TArticleService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TArticle}
     */
    TArticle findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link TArticle}
     */
    PageInfo<TArticle> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param tArticle
     */
    void insert(TArticle tArticle);

    /**
     * 修改
     *
     * @param tArticle
     */
    void update(TArticle tArticle);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}