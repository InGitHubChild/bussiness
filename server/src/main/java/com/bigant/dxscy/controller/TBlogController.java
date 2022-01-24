package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.TBlog;
import com.bigant.dxscy.service.TBlogService;
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
@RequestMapping("/tBlogs")
@Api(tags = "")
public class TBlogController {
    @Autowired
    private TBlogService tBlogService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public TBlog findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return tBlogService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<TBlog> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                      @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return tBlogService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody TBlog tBlog) {
        tBlogService.insert(tBlog);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody TBlog tBlog) {
        tBlogService.update(tBlog);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        tBlogService.deleteById(id);
    }
}
