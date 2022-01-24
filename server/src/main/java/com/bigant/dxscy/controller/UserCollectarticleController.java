package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.UserCollectarticle;
import com.bigant.dxscy.service.UserCollectarticleService;
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
@RequestMapping("/userCollectarticles")
@Api(tags = "")
public class UserCollectarticleController {
    @Autowired
    private UserCollectarticleService userCollectarticleService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public UserCollectarticle findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return userCollectarticleService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<UserCollectarticle> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                                   @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return userCollectarticleService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody UserCollectarticle userCollectarticle) {
        userCollectarticleService.insert(userCollectarticle);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody UserCollectarticle userCollectarticle) {
        userCollectarticleService.update(userCollectarticle);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        userCollectarticleService.deleteById(id);
    }
}
