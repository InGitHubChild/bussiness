package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.TGoodsDAO;
import com.bigant.dxscy.model.TGoods;
import com.bigant.dxscy.service.TGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TGoodsServiceImpl implements TGoodsService {

    @Autowired
    private TGoodsDAO tGoodsDAO;

    @Transactional(readOnly = true)
    @Override
    public TGoods findById(Integer id) {
        return tGoodsDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<TGoods> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(tGoodsDAO.findByPage());
    }

    @Override
    public void insert(TGoods tGoods) {
        tGoodsDAO.insert(tGoods);
    }

    @Override
    public void update(TGoods tGoods) {
        tGoodsDAO.update(tGoods);
    }

    @Override
    public void deleteById(Integer id) {
        tGoodsDAO.deleteById(id);
    }

}