package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.TMainorder;
import com.bigant.dxscy.service.TMainorderService;
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
@RequestMapping("/tMainorders")
@Api(tags = "")
public class TMainorderController {
    @Autowired
    private TMainorderService tMainorderService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public TMainorder findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return tMainorderService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<TMainorder> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                           @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return tMainorderService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody TMainorder tMainorder) {
        tMainorderService.insert(tMainorder);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody TMainorder tMainorder) {
        tMainorderService.update(tMainorder);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        tMainorderService.deleteById(id);
    }
}
