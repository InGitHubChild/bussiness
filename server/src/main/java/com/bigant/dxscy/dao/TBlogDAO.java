package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.TBlog;
import com.github.pagehelper.Page;

/**
 *
 */
public interface TBlogDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TBlog}
     */
    TBlog findById(Integer id);

    /**
     * 分页查询
     *
     * @return {@link TBlog}
     */
    Page<TBlog> findByPage();

    /**
     * 新增
     *
     * @param tBlog
     */
    void insert(TBlog tBlog);

    /**
     * 修改
     *
     * @param tBlog
     */
    void update(TBlog tBlog);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}