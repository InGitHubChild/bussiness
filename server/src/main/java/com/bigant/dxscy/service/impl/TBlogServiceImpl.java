package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.TBlogDAO;
import com.bigant.dxscy.model.TBlog;
import com.bigant.dxscy.service.TBlogService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TBlogServiceImpl implements TBlogService {

    @Autowired
    private TBlogDAO tBlogDAO;

    @Transactional(readOnly = true)
    @Override
    public TBlog findById(Integer id) {
        return tBlogDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<TBlog> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(tBlogDAO.findByPage());
    }

    @Override
    public void insert(TBlog tBlog) {
        tBlogDAO.insert(tBlog);
    }

    @Override
    public void update(TBlog tBlog) {
        tBlogDAO.update(tBlog);
    }

    @Override
    public void deleteById(Integer id) {
        tBlogDAO.deleteById(id);
    }

}