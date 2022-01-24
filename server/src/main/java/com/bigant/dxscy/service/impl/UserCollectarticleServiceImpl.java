package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.UserCollectarticleDAO;
import com.bigant.dxscy.model.UserCollectarticle;
import com.bigant.dxscy.service.UserCollectarticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserCollectarticleServiceImpl implements UserCollectarticleService {

    @Autowired
    private UserCollectarticleDAO userCollectarticleDAO;

    @Transactional(readOnly = true)
    @Override
    public UserCollectarticle findById(Integer id) {
        return userCollectarticleDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserCollectarticle> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(userCollectarticleDAO.findByPage());
    }

    @Override
    public void insert(UserCollectarticle userCollectarticle) {
        userCollectarticleDAO.insert(userCollectarticle);
    }

    @Override
    public void update(UserCollectarticle userCollectarticle) {
        userCollectarticleDAO.update(userCollectarticle);
    }

    @Override
    public void deleteById(Integer id) {
        userCollectarticleDAO.deleteById(id);
    }

}