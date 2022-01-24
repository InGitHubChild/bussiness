package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.TMainorderDAO;
import com.bigant.dxscy.model.TMainorder;
import com.bigant.dxscy.service.TMainorderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TMainorderServiceImpl implements TMainorderService {

    @Autowired
    private TMainorderDAO tMainorderDAO;

    @Transactional(readOnly = true)
    @Override
    public TMainorder findById(Integer id) {
        return tMainorderDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<TMainorder> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(tMainorderDAO.findByPage());
    }

    @Override
    public void insert(TMainorder tMainorder) {
        tMainorderDAO.insert(tMainorder);
    }

    @Override
    public void update(TMainorder tMainorder) {
        tMainorderDAO.update(tMainorder);
    }

    @Override
    public void deleteById(Integer id) {
        tMainorderDAO.deleteById(id);
    }

}