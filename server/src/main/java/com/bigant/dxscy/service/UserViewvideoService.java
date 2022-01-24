package com.bigant.dxscy.service;

import com.bigant.dxscy.model.UserViewvideo;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface UserViewvideoService {

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
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link UserViewvideo}
     */
    PageInfo<UserViewvideo> findByPage(int pageNum, int pageSize);

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