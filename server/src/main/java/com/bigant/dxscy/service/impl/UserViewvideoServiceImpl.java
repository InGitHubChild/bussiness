package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.UserViewvideoDAO;
import com.bigant.dxscy.model.UserViewvideo;
import com.bigant.dxscy.service.UserViewvideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserViewvideoServiceImpl implements UserViewvideoService {

    @Autowired
    private UserViewvideoDAO userViewvideoDAO;

    @Transactional(readOnly = true)
    @Override
    public UserViewvideo findById(Integer id) {
        return userViewvideoDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserViewvideo> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(userViewvideoDAO.findByPage());
    }

    @Override
    public void insert(UserViewvideo userViewvideo) {
        userViewvideoDAO.insert(userViewvideo);
    }

    @Override
    public void update(UserViewvideo userViewvideo) {
        userViewvideoDAO.update(userViewvideo);
    }

    @Override
    public void deleteById(Integer id) {
        userViewvideoDAO.deleteById(id);
    }

}