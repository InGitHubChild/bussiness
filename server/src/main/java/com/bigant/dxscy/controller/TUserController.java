package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.TUser;
import com.bigant.dxscy.service.TUserService;
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
@RequestMapping("/tUsers")
@Api(tags = "")
public class TUserController {
    @Autowired
    private TUserService tUserService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public TUser findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return tUserService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<TUser> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                      @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return tUserService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody TUser tUser) {
        tUser.setUserPhone("asdf");
        tUserService.insert(tUser);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody TUser tUser) {
        tUserService.update(tUser);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        tUserService.deleteById(id);
    }
}
