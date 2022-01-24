package com.bigant.dxscy.service;

import com.bigant.dxscy.model.UserCommentblog;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface UserCommentblogService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link UserCommentblog}
     */
    UserCommentblog findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link UserCommentblog}
     */
    PageInfo<UserCommentblog> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param userCommentblog
     */
    void insert(UserCommentblog userCommentblog);

    /**
     * 修改
     *
     * @param userCommentblog
     */
    void update(UserCommentblog userCommentblog);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}