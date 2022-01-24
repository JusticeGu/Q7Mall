package com.q7w.Controller;

import com.q7w.Entity.Course;
import com.q7w.Entity.StudentClass;
import com.q7w.Service.CourseService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author xiaogu
 * @date 2021/8/9 15:03
 **/
@RestController
@Api(tags = "课程接口")
@RequestMapping("/v1/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("")
    @ApiOperation("课程新增")
    public ResponseData addcourse(@RequestBody Course course){
        int status = courseService.add(course);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单新增失败");
    }
    @PutMapping("")
    @ApiOperation("课程修改")
    public ResponseData modifycourse(@RequestBody  Course course){
        int status = courseService.modefy(course);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单新增失败");
    }
    @GetMapping("")
    @ApiOperation("课程列表")
    public ResponseData list(){
        return new ResponseData(ExceptionMsg.SUCCESS,courseService.liatall());

    }

}
