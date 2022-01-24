package com.q7w.Controller;

import com.q7w.Entity.ClazzCourse;
import com.q7w.Entity.ClszzStu;

import com.q7w.Entity.StudentClass;
import com.q7w.Service.StuClassService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 15:03
 **/
@RestController
@Api(tags = "班级接口")
@RequestMapping("/v1/clazz")
public class ClazzController {
    @Autowired
    StuClassService stuClassService;
    @PostMapping("/add")
    @ApiOperation("班级新增")
    public ResponseData addclass(@RequestBody StudentClass clazz){
        int status = stuClassService.add(clazz);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增班级成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"班级新增失败");
    }
    @PutMapping("/modify")
    @ApiOperation("班级修改")
    public ResponseData modifyclass(@RequestBody StudentClass clazz){
        int status = stuClassService.modefy(clazz);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单新增失败");
    }
    @GetMapping("/list")
    @ApiOperation("班级列表")
    public ResponseData list(){
        return new ResponseData(ExceptionMsg.SUCCESS,stuClassService.liatall());

    }
    @PostMapping("/stu")
    @ApiOperation("学生入班")
    public ResponseData addstu(@RequestBody ClszzStu stuClass){
        List stulists = new ArrayList();
        stulists.add(stuClass.getUid());
        int status = stuClassService.addstu(stuClass.getCid(),stulists);
        if (status>=1){return new ResponseData(ExceptionMsg.SUCCESS,"学生班级关联成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"学生班级关联失败");
    }
    @GetMapping("/stu")
    @ApiOperation("班级学生列表")
    public ResponseData stulist(@RequestParam Long cid){
        return new ResponseData(ExceptionMsg.SUCCESS,stuClassService.listallstubyclass(cid));
    }
    @GetMapping("/course")
    @ApiOperation("查看班级关联课程")
    public ResponseData listclazzcourse(@RequestParam Long cid){

        return new ResponseData(ExceptionMsg.SUCCESS,stuClassService.listcluzzcourse(cid));
    }
    @GetMapping("/courseclass")
    @ApiOperation("查看课程关联班级")
    public ResponseData listcourseclazz(@RequestParam Long course){
        return new ResponseData(ExceptionMsg.SUCCESS,stuClassService.listcoursecluzz(course));
    }
    @PostMapping("/asscourse")
    @ApiOperation("班级关联课程")
    public ResponseData asscourse(@RequestBody ClazzCourse clazzCourse){
        int status = stuClassService.assocourse(clazzCourse.getClassid(), clazzCourse.getCourse());
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"关联成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"关联失败");
    }
    @DeleteMapping("/asscourse")
    @ApiOperation("班级重新关联课程")
    public ResponseData delasscourse(@RequestBody ClazzCourse clazzCourse){
        int status = stuClassService.resetcourse(clazzCourse.getClassid());
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"已删除关联信息"); }
        return new ResponseData(ExceptionMsg.FAILED,"关联失败");
    }

}
