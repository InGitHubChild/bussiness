package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.UserTeam;
import com.bigant.dxscy.service.UserTeamService;
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
@RequestMapping("/userTeams")
@Api(tags = "")
public class UserTeamController {
    @Autowired
    private UserTeamService userTeamService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public UserTeam findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return userTeamService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<UserTeam> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                         @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return userTeamService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody UserTeam userTeam) {
        userTeamService.insert(userTeam);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody UserTeam userTeam) {
        userTeamService.update(userTeam);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        userTeamService.deleteById(id);
    }
}
