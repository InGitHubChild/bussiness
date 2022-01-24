package com.bigant.dxscy.dao;

import com.bigant.dxscy.model.UserTeam;
import com.github.pagehelper.Page;

/**
 *
 */
public interface UserTeamDAO {

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
     * @return {@link UserTeam}
     */
    Page<UserTeam> findByPage();

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