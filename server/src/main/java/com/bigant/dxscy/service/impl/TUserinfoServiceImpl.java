package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.TUserinfoDAO;
import com.bigant.dxscy.model.TUserinfo;
import com.bigant.dxscy.service.TUserinfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TUserinfoServiceImpl implements TUserinfoService {

    @Autowired
    private TUserinfoDAO tUserinfoDAO;

    @Transactional(readOnly = true)
    @Override
    public TUserinfo findById(Integer id) {
        return tUserinfoDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<TUserinfo> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(tUserinfoDAO.findByPage());
    }

    @Override
    public void insert(TUserinfo tUserinfo) {
        tUserinfoDAO.insert(tUserinfo);
    }

    @Override
    public void update(TUserinfo tUserinfo) {
        tUserinfoDAO.update(tUserinfo);
    }

    @Override
    public void deleteById(Integer id) {
        tUserinfoDAO.deleteById(id);
    }

}