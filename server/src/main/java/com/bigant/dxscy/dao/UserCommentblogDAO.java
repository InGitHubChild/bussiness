package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.UserCommentblog;
import com.github.pagehelper.Page;

/**
 *
 */
public interface UserCommentblogDAO {

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
     * @return {@link UserCommentblog}
     */
    Page<UserCommentblog> findByPage();

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