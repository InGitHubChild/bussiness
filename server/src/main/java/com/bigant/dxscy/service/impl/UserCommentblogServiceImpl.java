package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.UserCommentblogDAO;
import com.bigant.dxscy.model.UserCommentblog;
import com.bigant.dxscy.service.UserCommentblogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserCommentblogServiceImpl implements UserCommentblogService {

    @Autowired
    private UserCommentblogDAO userCommentblogDAO;

    @Transactional(readOnly = true)
    @Override
    public UserCommentblog findById(Integer id) {
        return userCommentblogDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<UserCommentblog> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(userCommentblogDAO.findByPage());
    }

    @Override
    public void insert(UserCommentblog userCommentblog) {
        userCommentblogDAO.insert(userCommentblog);
    }

    @Override
    public void update(UserCommentblog userCommentblog) {
        userCommentblogDAO.update(userCommentblog);
    }

    @Override
    public void deleteById(Integer id) {
        userCommentblogDAO.deleteById(id);
    }

}