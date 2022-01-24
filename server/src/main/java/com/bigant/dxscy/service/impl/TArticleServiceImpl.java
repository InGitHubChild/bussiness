package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.TArticleDAO;
import com.bigant.dxscy.model.TArticle;
import com.bigant.dxscy.service.TArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TArticleServiceImpl implements TArticleService {

    @Autowired
    private TArticleDAO tArticleDAO;

    @Transactional(readOnly = true)
    @Override
    public TArticle findById(Integer id) {
        return tArticleDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<TArticle> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(tArticleDAO.findByPage());
    }

    @Override
    public void insert(TArticle tArticle) {
        tArticleDAO.insert(tArticle);
    }

    @Override
    public void update(TArticle tArticle) {
        tArticleDAO.update(tArticle);
    }

    @Override
    public void deleteById(Integer id) {
        tArticleDAO.deleteById(id);
    }

}