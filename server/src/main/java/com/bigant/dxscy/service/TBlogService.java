package com.bigant.dxscy.service;

import com.bigant.dxscy.model.TBlog;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface TBlogService {

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
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link TBlog}
     */
    PageInfo<TBlog> findByPage(int pageNum, int pageSize);

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