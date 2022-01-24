package com.bigant.dxscy.service;

import com.bigant.dxscy.model.UserTeam;
import com.github.pagehelper.PageInfo;

/**
 *
 */
public interface UserTeamService {

    /**
     * 通过ID查询单个
     *
     * @param id ID
     * @return {@link UserTeam}
     */
    UserTeam findById(Integer id);

    /**
     * 分页查询
     *
     * @param pageNum  页号
     * @param pageSize 每页大小
     * @return {@link UserTeam}
     */
    PageInfo<UserTeam> findByPage(int pageNum, int pageSize);

    /**
     * 新增
     *
     * @param userTeam
     */
    void insert(UserTeam userTeam);

    /**
     * 修改
     *
     * @param userTeam
     */
    void update(UserTeam userTeam);

    /**
     * 通过ID删除单个
     *
     * @param id ID
     */
    void deleteById(Integer id);

}