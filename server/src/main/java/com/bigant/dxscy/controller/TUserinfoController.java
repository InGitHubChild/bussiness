package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.TUserinfo;
import com.bigant.dxscy.service.TUserinfoService;
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
@RequestMapping("/tUserinfos")
@Api(tags = "")
public class TUserinfoController {
    @Autowired
    private TUserinfoService tUserinfoService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public TUserinfo findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return tUserinfoService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<TUserinfo> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                          @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return tUserinfoService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody TUserinfo tUserinfo) {
        tUserinfoService.insert(tUserinfo);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody TUserinfo tUserinfo) {
        tUserinfoService.update(tUserinfo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        tUserinfoService.deleteById(id);
    }
}
