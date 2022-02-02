package com.q7w.Controller;

import com.q7w.Entity.ClazzCourse;
import com.q7w.Entity.Score;
import com.q7w.Service.ScoreService;
import com.q7w.Vo.ExamScoreVO;
import com.q7w.Vo.StuExamVO;
import com.q7w.common.result.ExceptionMsg;
import com.q7w.common.result.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/8/9 15:30
 **/
@RestController
@Api(tags = "成绩接口")
@RequestMapping("/v1/school/score")
public class ScoreController {
    @Autowired
    ScoreService scoreService;
    @GetMapping("/getscore")
    @ApiOperation("考试成绩列表获取")
    public ResponseData getexamscore(){
        int status = 1;
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单新增失败");
    }
    @GetMapping("/examcourse")
    @ApiOperation("考试成绩查询")
    public ResponseData examcourse(@RequestParam Long exam,@RequestParam Long course){
        List<Score> infos = scoreService.getScorebyCourse(course,exam);
        return new ResponseData(ExceptionMsg.SUCCESS,infos);
    }
    @GetMapping("/examcourseclass")
    @ApiOperation("班级考试成绩查询")
    public ResponseData examcourseclass(@RequestParam Long exam,@RequestParam Long course,@RequestParam Long clazz){
        List<Score> infos = scoreService.getscorebyclass(clazz,exam,course);
        return new ResponseData(ExceptionMsg.SUCCESS,infos);
    }
    @GetMapping("/stugetscore")
    @ApiOperation("考生单场成绩获取")
    public ResponseData stugetscore(Long examid,Long uid){
        List scorelist = scoreService.getScorebyuid( examid, uid);
        if (scorelist.size()!=0){return new ResponseData(ExceptionMsg.SUCCESS,scorelist); }
        return new ResponseData(ExceptionMsg.FAILED,"成绩暂不可查询，请等待成绩发布后再查询");
    }
    @GetMapping("/stugetscorelist")
    @ApiOperation("考生所有成绩列表")
    public ResponseData stuscorelist(@RequestParam Long uid){
        List<Score> scoreList = scoreService.getScorebyuid(uid,false);
        if (scoreList.size()!=0){return new ResponseData(ExceptionMsg.SUCCESS,scoreList); }
        return new ResponseData(ExceptionMsg.FAILED,"暂无可查询成绩");
    }

    @PostMapping("/initscores")
    @ApiOperation("班级考试报名")
    public ResponseData initscores(@RequestBody Set<Score> stuExamVO){
        int status = scoreService.resScore(stuExamVO);
        if (status>=1){return new ResponseData(ExceptionMsg.SUCCESS,status+"失败,关联成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"关联失败");
    }
    @PostMapping("/initscore")
    @ApiOperation("个人考试报名")
    public ResponseData initscore(@RequestBody StuExamVO stuExamVO){
        Set stu = new HashSet<Score>();
        stuExamVO.getCourses().forEach(course ->{
            Score stuscore = new Score();
            stuscore.setExam(stuExamVO.getExam());
            stuscore.setUid(stuExamVO.getUid());
            stuscore.setCourse(course);
            stu.add(stuscore);
        });
        int status = scoreService.resScore(stu);
        if (status>=1){return new ResponseData(ExceptionMsg.SUCCESS,status+"条考生记录失败"); }
        return new ResponseData(ExceptionMsg.SUCCESS,"新增成功");
    }
    @GetMapping("/getscoreclass")
    @ApiOperation("获取可操作班级")
    public ResponseData getclassscore(){
        int status = 1;
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"关联成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"关联失败");
    }
    @GetMapping("/addscore")
    @ApiOperation("获取成绩录入列表")
    public ResponseData getaddscore(Long classid,Long course,Long exam){
        List<Score> scoreList = scoreService.getscorebyclass(classid, exam,course);
        if (scoreList.size()>=1){return new ResponseData(ExceptionMsg.SUCCESS,scoreList); }
        return new ResponseData(ExceptionMsg.FAILED,"名单获取失败");
    }

    @PostMapping("/scoreadd")
    @ApiOperation("成绩录入")
    public ResponseData addscore(@RequestBody ExamScoreVO examScoreVO){
        int status = scoreService.addScore(examScoreVO);
        if (status>=1){return new ResponseData(ExceptionMsg.SUCCESS,status+"条成绩录入有误,其余已录入"); }
        return new ResponseData(ExceptionMsg.SUCCESS,"录入成功");
    }
    @PostMapping("/{classcourse}/submit/")
    @ApiOperation("成绩提交")
    public ResponseData submitscore(@RequestBody ExamScoreVO examScoreVO){
        int status = scoreService.addScore(examScoreVO);
        if (status>=1){return new ResponseData(ExceptionMsg.SUCCESS,status+"条记录失败"); }
        return new ResponseData(ExceptionMsg.FAILED,"录入成功");
    }
    @GetMapping("/{exam}/publish")
    @ApiOperation("成绩提交")
    public ResponseData pubscore(){
        int status = 1;
        if (status==1){return new ResponseData(ExceptionMsg.SUCCESS,"新增菜单成功"); }
        return new ResponseData(ExceptionMsg.FAILED,"菜单新增失败");
    }
}

