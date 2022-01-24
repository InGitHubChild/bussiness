package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.UserViewarticleDAO;
import com.bigant.dxscy.model.UserViewarticle;
import com.bigant.dxscy.service.UserViewarticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserViewarticleServiceImpl implements UserViewarticleService {

    @Autowired
    private UserViewarticleDAO userViewarticleDAO;

    @Transactional(readOnly = true)
    @Override
    public UserViewarticle findById(Integer id) {
        return userViewarticleDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserViewarticle> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(userViewarticleDAO.findByPage());
    }

    @Override
    public void insert(UserViewarticle userViewarticle) {
        userViewarticleDAO.insert(userViewarticle);
    }

    @Override
    public void update(UserViewarticle userViewarticle) {
        userViewarticleDAO.update(userViewarticle);
    }

    @Override
    public void deleteById(Integer id) {
        userViewarticleDAO.deleteById(id);
    }

}