package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.TVideo;
import com.github.pagehelper.Page;

/**
 *
 */
public interface TVideoDAO {

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
     * @return {@link TVideo}
     */
    Page<TVideo> findByPage();

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