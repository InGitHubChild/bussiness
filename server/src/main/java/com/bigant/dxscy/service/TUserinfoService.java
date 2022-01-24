package com.bigant.dxscy.service;

import com.bigant.dxscy.model.TUserinfo;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface TUserinfoService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TUserinfo}
     */
    TUserinfo findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link TUserinfo}
     */
    PageInfo<TUserinfo> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param tUserinfo
     */
    void insert(TUserinfo tUserinfo);

    /**
     * 修改
     *
     * @param tUserinfo
     */
    void update(TUserinfo tUserinfo);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}