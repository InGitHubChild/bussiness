package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.UserViewvideo;
import com.github.pagehelper.Page;

/**
 *
 */
public interface UserViewvideoDAO {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link UserViewvideo}
     */
    UserViewvideo findById(Integer id);

    /**
     * 分页查询
     *
     * @return {@link UserViewvideo}
     */
    Page<UserViewvideo> findByPage();

    /**
     * 新增
     *
     * @param userViewvideo
     */
    void insert(UserViewvideo userViewvideo);

    /**
     * 修改
     *
     * @param userViewvideo
     */
    void update(UserViewvideo userViewvideo);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}