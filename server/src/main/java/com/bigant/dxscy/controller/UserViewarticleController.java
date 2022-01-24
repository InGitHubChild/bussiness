package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.UserViewarticle;
import com.bigant.dxscy.service.UserViewarticleService;
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
@RequestMapping("/userViewarticles")
@Api(tags = "")
public class UserViewarticleController {
    @Autowired
    private UserViewarticleService userViewarticleService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public UserViewarticle findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return userViewarticleService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<UserViewarticle> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                                @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return userViewarticleService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody UserViewarticle userViewarticle) {
        userViewarticleService.insert(userViewarticle);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody UserViewarticle userViewarticle) {
        userViewarticleService.update(userViewarticle);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        userViewarticleService.deleteById(id);
    }
}
