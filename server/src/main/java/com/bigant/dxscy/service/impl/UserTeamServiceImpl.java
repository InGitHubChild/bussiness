package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.UserTeamDAO;
import com.bigant.dxscy.model.UserTeam;
import com.bigant.dxscy.service.UserTeamService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTeamServiceImpl implements UserTeamService {

    @Autowired
    private UserTeamDAO userTeamDAO;

    @Transactional(readOnly = true)
    @Override
    public UserTeam findById(Integer id) {
        return userTeamDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserTeam> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(userTeamDAO.findByPage());
    }

    @Override
    public void insert(UserTeam userTeam) {
        userTeamDAO.insert(userTeam);
    }

    @Override
    public void update(UserTeam userTeam) {
        userTeamDAO.update(userTeam);
    }

    @Override
    public void deleteById(Integer id) {
        userTeamDAO.deleteById(id);
    }

}