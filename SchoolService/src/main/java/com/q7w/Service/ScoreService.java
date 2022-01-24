package com.q7w.Service;

import com.q7w.Entity.Score;
import com.q7w.Vo.ExamScoreVO;

import java.util.List;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/8/9 15:15
 **/
public interface ScoreService {
    public int resScore(Set<Score> stus);//考试注册
    public int addScore(ExamScoreVO scorelist);//教师打分
    public int modefyScore(Set<Score>  scorelist);//分数修改
    public List<Score> getscorebyclass(Long classid,Long exam,Long course);
    public List listall(Long exam);
    public List getScorebyCourse(Long course,Long exam);
    public List getScorebyuid(Long exam,Long stu);
    public List getScorebyuid(Long uid,boolean teacher);



}
