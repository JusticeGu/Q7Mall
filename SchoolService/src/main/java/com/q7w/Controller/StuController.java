package com.q7w.Controller;

import com.q7w.Entity.Exam;
import com.q7w.Entity.Score;
import com.q7w.Entity.Student;
import com.q7w.Service.ScoreService;
import com.q7w.Service.StuService;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xiaogu
 * @date 2021/8/9 15:03
 **/
@RestController
@Api(tags = "学生接口")
@RequestMapping("/v1/school/stu")
public class StuController {
    @Autowired
    StuService stuService;
    @Autowired
    ScoreService scoreService;
    @PostMapping("")
    @ApiOperation("学生新增")
    public ResponseData adduser(@RequestBody Student stu){
        int status = stuService.add(stu);
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"学生新增成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"学生新增失败");
    }
    @GetMapping("")
    @ApiOperation("学生列表")
    public ResponseData listuser(){
        return new ResponseData(ExceptionMsg.SUCCESS,stuService.liatall());
    }
    @GetMapping("/exam/list")
    @ApiOperation("学生参加考试列表")
    public ResponseData liststuexam(){
        return new ResponseData(ExceptionMsg.SUCCESS,"list");
    }
    @GetMapping("/exam/score")
    @ApiOperation("学生成绩查询")
    public ResponseData liststuscore(Long uid,Long eid){
        return new ResponseData(ExceptionMsg.SUCCESS,"list");
    }
    @GetMapping("/getscorelist")
    @ApiOperation("考生所有成绩列表")
    public ResponseData stuscorelist(@RequestParam Long uid){
        List<Score> scoreList = scoreService.getScorebyuid(uid,false);
        if (scoreList.size()!=0){return new ResponseData(ExceptionMsg.SUCCESS,scoreList); }
        return new ResponseData(ExceptionMsg.FAILED,"暂无可查询成绩");
    }
}
