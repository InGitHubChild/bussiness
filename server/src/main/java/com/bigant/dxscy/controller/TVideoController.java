package com.bigant.dxscy.controller;

import com.bigant.dxscy.model.TVideo;
import com.bigant.dxscy.service.TVideoService;
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
@RequestMapping("/tVideos")
@Api(tags = "")
public class TVideoController {
    @Autowired
    private TVideoService tVideoService;

    @GetMapping("/{id}")
    @ApiOperation("通过ID查询单个")
    public TVideo findById(@ApiParam("ID") @PathVariable("id") Integer id) {
        return tVideoService.findById(id);
    }

    @GetMapping
    @ApiOperation("分页查询")
    public PageInfo<TVideo> findByPage(@ApiParam("页号") @RequestParam(defaultValue = "1") Integer pageNum,
                                       @ApiParam("每页大小") @RequestParam(defaultValue = "10") Integer pageSize) {
        return tVideoService.findByPage(pageNum, pageSize);
    }

    @PostMapping
    @ApiOperation("新增")
    public void insert(@RequestBody TVideo tVideo) {
        tVideoService.insert(tVideo);
    }

    @PutMapping
    @ApiOperation("修改")
    public void update(@RequestBody TVideo tVideo) {
        tVideoService.update(tVideo);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("通过ID删除单个")
    public void deleteById(@ApiParam("ID") @PathVariable("id") Integer id) {
        tVideoService.deleteById(id);
    }
}
