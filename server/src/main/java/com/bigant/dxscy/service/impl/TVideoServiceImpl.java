package com.bigant.dxscy.service.impl;

import com.bigant.dxscy.dao.TVideoDAO;
import com.bigant.dxscy.model.TVideo;
import com.bigant.dxscy.service.TVideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class TVideoServiceImpl implements TVideoService {

    @Autowired
    private TVideoDAO tVideoDAO;

    @Transactional(readOnly = true)
    @Override
    public TVideo findById(Integer id) {
        return tVideoDAO.findById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public PageInfo<TVideo> findByPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return PageInfo.of(tVideoDAO.findByPage());
    }

    @Override
    public void insert(TVideo tVideo) {
        tVideoDAO.insert(tVideo);
    }

    @Override
    public void update(TVideo tVideo) {
        tVideoDAO.update(tVideo);
    }

    @Override
    public void deleteById(Integer id) {
        tVideoDAO.deleteById(id);
    }

}