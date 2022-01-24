package com.bigant.dxscy.service;

import com.bigant.dxscy.model.TVideo;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface TVideoService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link TVideo}
     */
    TVideo findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link TVideo}
     */
    PageInfo<TVideo> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param tVideo
     */
    void insert(TVideo tVideo);

    /**
     * 修改
     *
     * @param tVideo
     */
    void update(TVideo tVideo);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}