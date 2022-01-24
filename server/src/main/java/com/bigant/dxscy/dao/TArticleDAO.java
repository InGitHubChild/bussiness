package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.TArticle;
import com.github.pagehelper.Page;

/**
 *
 */
public interface TArticleDAO {

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
     * @return {@link TArticle}
     */
    Page<TArticle> findByPage();

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