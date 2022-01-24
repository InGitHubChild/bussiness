package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.UserViewvideo;
import com.bigant.dxscy.service.UserViewvideoService;
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
@RequestMapping("/userViewvideos")
@Api(tags = "")
public class UserViewvideoController {
    @Autowired
    private UserViewvideoService userViewvideoService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public UserViewvideo findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return userViewvideoService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<UserViewvideo> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                              @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return userViewvideoService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody UserViewvideo userViewvideo) {
        userViewvideoService.insert(userViewvideo);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody UserViewvideo userViewvideo) {
        userViewvideoService.update(userViewvideo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        userViewvideoService.deleteById(id);
    }
}
