package com.q7w.Controller;

import com.q7w.Entity.ClazzCourse;
import com.q7w.Entity.Exam;
import com.q7w.Entity.ExamCourse;
import com.q7w.Entity.Score;
import com.q7w.Service.ExamService;
import com.q7w.Service.StuClassService;
import com.q7w.Vo.ExamScoreVO;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/8/9 15:03
 **/
@RestController
@Api(tags = "考试接口")
@RequestMapping("/v1/school/exam")
public class ExamController {
    @Autowired
    ExamService examService;
    @Autowired
    StuClassService stuClassService;
    @PostMapping("")
    @ApiOperation("考试新增")
    public ResponseData addexam(@RequestBody Exam exam){
        int status = examService.add(exam);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"考试新增成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"考试新增失败");
    }
    @GetMapping("")
    @ApiOperation("考试列表")
    public ResponseData exam(){
        return new ResponseData(ExceptionMsg.SUCCESS,examService.liatall());
    }
    @GetMapping("/info")
    @ApiOperation("考试详情")
    public ResponseData examinfo(@RequestParam Long kid){
        //List<ExamCourse> infos = examService.getinfobyexam(kid);
        return new ResponseData(ExceptionMsg.SUCCESS,"infos");
    }

    @PostMapping("/asso")
    @ApiOperation("考试关联课程")
    public ResponseData assocourse(@RequestBody ExamCourse examCourse){
//        List<Long> examc= new ArrayList<Long>();
//        examc.add(examCourse.getCourse());
        int status = examService.asocourse(examCourse);
        if (status == 1) {
            return new ResponseData(ExceptionMsg.SUCCESS,"考试课程信息设置成功");
        }else {
            return new ResponseData(ExceptionMsg.FAILED,"考试课程信息设置失败");
        }
    }
    @GetMapping("/asso")
    @ApiOperation("查看考试关联所有课程")
    public ResponseData listexamcourse(@RequestParam Long exam){
//        List<Long> examc= new ArrayList<Long>();
//        examc.add(examCourse.getCourse());
        List<ExamCourse> list = examService.getexamcourse(exam);
        if (list.size()>=0){
            return new ResponseData(ExceptionMsg.SUCCESS,list);
        }else {
            return new ResponseData(ExceptionMsg.FAILED,"考试暂无课程信息");
        }

    }
    @GetMapping("/getclazzs")
    @ApiOperation("获取该场考试登录教师负责班级")
    public ResponseData getclazzs(Long course){
        List<ClazzCourse> classList = stuClassService.listclassbycoursetea(course) ;
        return new ResponseData(ExceptionMsg.SUCCESS,classList);
    }
    @GetMapping("/getclazzlist")
    @ApiOperation("获取该场考试负责班级")
    public ResponseData getclazzlist(Long course,Long exam){
        Set<Long> scoreList = examService.getexamcoursescs(exam,course);
        if (scoreList.size()>=1){return new ResponseData(ExceptionMsg.SUCCESS,scoreList); }
        return new ResponseData(ExceptionMsg.FAILED,"名单获取失败");
    }
    @PutMapping("/asso")
    @ApiOperation("考试重置关联课程")
    public ResponseData reassocourse(){
        return new ResponseData(ExceptionMsg.SUCCESS,"菜单新增失败");
    }
    @PostMapping("/submitscore")
    @ApiOperation("成绩提交")
    public ResponseData submitscore(@RequestBody ExamScoreVO examScoreVO){
        int status = examService.scoresubmit(examScoreVO.getExam());
        if (status==1){
            return new ResponseData(ExceptionMsg.SUCCESS,"考试成绩已提交");
        }else {
            return new ResponseData(ExceptionMsg.SUCCESS,"成绩提交失败");
        }
    }
    @GetMapping("/pubscore")
    @ApiOperation("成绩发布")
    public ResponseData pubscore(@RequestParam Long exam){
        int status = examService.publish(exam);
        if (status==1){
            return new ResponseData(ExceptionMsg.SUCCESS,"考试成绩已发布");
        }else {
            return new ResponseData(ExceptionMsg.SUCCESS,"成绩发布失败");
        }
    }


}
