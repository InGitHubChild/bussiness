package com.bigant.dxscy.service;

import com.bigant.dxscy.model.UserCollectvideo;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface UserCollectvideoService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link UserCollectvideo}
     */
    UserCollectvideo findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link UserCollectvideo}
     */
    PageInfo<UserCollectvideo> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param userCollectvideo
     */
    void insert(UserCollectvideo userCollectvideo);

    /**
     * 修改
     *
     * @param userCollectvideo
     */
    void update(UserCollectvideo userCollectvideo);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}