package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.TUserDAO;
import com.bigant.dxscy.model.TUser;
import com.bigant.dxscy.service.TUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TUserServiceImpl implements TUserService {

    @Autowired
    private TUserDAO tUserDAO;

    @Transactional(readOnly = true)
    @Override
    public TUser findById(Integer id) {
        return tUserDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<TUser> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(tUserDAO.findByPage());
    }

    @Override
    public void insert(TUser tUser) {
        tUserDAO.insert(tUser);
    }

    @Override
    public void update(TUser tUser) {
        tUserDAO.update(tUser);
    }

    @Override
    public void deleteById(Integer id) {
        tUserDAO.deleteById(id);
    }

}