package com.xue.serviceedu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xue.common_utils.ResponseResult;
import com.xue.serviceedu.pojo.EduTeacher;
import com.xue.serviceedu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import com.xue.serviceedu.pojo.vo.TeacherQuery;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author xue
 * @since 2021-05-11
 */
@Api(value = "教师管理")
@RestController
@RequestMapping("/eduservice/eduteacher")
@Slf4j
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("查看所有教师列表")
    @GetMapping("findAll")
    public ResponseResult test(){

        List<EduTeacher> list = eduTeacherService.list(null);


        return list != null ? ResponseResult.success().data("items",list):ResponseResult.error();
    }

    @ApiOperation("根据id逻辑删除教师")
    @DeleteMapping("{Id}")
    public ResponseResult deleteTest(@ApiParam(name ="教师Id",value = "Id",required = true) @PathVariable String Id){
        boolean b = eduTeacherService.removeById(Id);
        return  b ? ResponseResult.success():ResponseResult.error();
    }

    @ApiOperation("多条件分页查询教师列表")
    @PostMapping("pageByCondition/{current}/{size}")
    public ResponseResult findByPage(@PathVariable Long current, @PathVariable Long size,
                                     @RequestBody(required = false) TeacherQuery teacherQuery){
        // 1.创建一个page对象。将url中获取的参数填入其中。
        Page<EduTeacher> page = new Page<EduTeacher>(current, size);

        // 2. 创建wapper对象，并将条件填入其中
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        Date begin = teacherQuery.getGmtCreate();
        Date end = teacherQuery.getGmtModified();
        if (StringUtils.hasLength(name)){
            wrapper.like("name",name);
        }
        if (!ObjectUtils.isEmpty(level)){
            wrapper.ge("level",level);
        }

        if (!ObjectUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }

        if (!ObjectUtils.isEmpty(end)){
            wrapper.le("gmt_modified",end);
        }

        //
      eduTeacherService.page(page,wrapper);

        return ResponseResult.success().data("teacherList",page);

    }

    // 新增教师
    @ApiOperation(value = "新增教师")
    @PostMapping("addTeacher")
    public ResponseResult add(@RequestBody EduTeacher eduTeacher){
        boolean save = eduTeacherService.save(eduTeacher);
        if (save){
            return ResponseResult.success().message("添加成功");
        }
        return ResponseResult.error();
    }

//  根据Id传值
    @ApiOperation(value = "根据Id查看教师信息")
    @GetMapping("getInfo/{id}")
    public ResponseResult teacherInfo(@PathVariable Integer id){
        EduTeacher byId = eduTeacherService.getById(id);
        return  ResponseResult.success().data("teacher",byId);
    }

    //  修改教师信息
    @ApiOperation(value = "根据Id修改教师信息")
    @PostMapping("updateInfo")
    public ResponseResult updateTeacher(@RequestBody EduTeacher eduTeacher){
        boolean b = eduTeacherService.updateById(eduTeacher);
        return  ResponseResult.success();
    }

//    public String tets(){
//        return "test";
//    }
}

