package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.UserCollectvideoDAO;
import com.bigant.dxscy.model.UserCollectvideo;
import com.bigant.dxscy.service.UserCollectvideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserCollectvideoServiceImpl implements UserCollectvideoService {

    @Autowired
    private UserCollectvideoDAO userCollectvideoDAO;

    @Transactional(readOnly = true)
    @Override
    public UserCollectvideo findById(Integer id) {
        return userCollectvideoDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserCollectvideo> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(userCollectvideoDAO.findByPage());
    }

    @Override
    public void insert(UserCollectvideo userCollectvideo) {
        userCollectvideoDAO.insert(userCollectvideo);
    }

    @Override
    public void update(UserCollectvideo userCollectvideo) {
        userCollectvideoDAO.update(userCollectvideo);
    }

    @Override
    public void deleteById(Integer id) {
        userCollectvideoDAO.deleteById(id);
    }

}