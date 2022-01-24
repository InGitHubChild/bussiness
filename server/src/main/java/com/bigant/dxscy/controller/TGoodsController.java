package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.TGoods;
import com.bigant.dxscy.service.TGoodsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 */
@RestController
@RequestMapping("/tGoodss")
@Api(tags = "")
public class TGoodsController {
    @Autowired
    private TGoodsService tGoodsService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public TGoods findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return tGoodsService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<TGoods> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                       @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return tGoodsService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody TGoods tGoods) {
        tGoodsService.insert(tGoods);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody TGoods tGoods) {
        tGoodsService.update(tGoods);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        tGoodsService.deleteById(id);
    }
}
