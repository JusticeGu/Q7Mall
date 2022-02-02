package com.q7w.Service.impl;

import com.q7w.Dao.ScoreDao;
import com.q7w.Entity.ExamCourse;
import com.q7w.Entity.Score;
import com.q7w.Service.ExamService;
import com.q7w.Service.ScoreService;
import com.q7w.Service.StuClassService;
import com.q7w.Service.UserFeign;
import com.q7w.Vo.ClszzStuVO;
import com.q7w.Vo.ExamScoreVO;
import com.q7w.common.exception.GlobalException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/8/9 15:29
 **/
@Service
public class ScoreServiceimpl implements ScoreService {
    @Autowired
    ScoreDao scoreDao;
    @Autowired
    ExamService examService;
    @Autowired
    StuClassService stuClassService;
    @Autowired
    UserFeign userFeign;
    @Override
    public int resScore(Set<Score> stus) {
        List errs = new ArrayList();
        stus.forEach(stu -> {
          try {
              exist(stu.getExam(), stu.getCourse(),stu.getUid());
              stu.setStatus(0);//待考试
              stu.setType((byte) 0);
              scoreDao.save(stu);
          }catch (Exception e){
              errs.add(stu.getUid());
          }
        });
        return errs.size();
    }
    private boolean exist(Long exam,Long course,Long uid){
        List<Score> score = scoreDao.findAllByCourseAndExamAndUid(course,exam,uid);
        if (score.size()!=0){
            throw new GlobalException("1005X01","考试记录已存在");
        }else {
           return false;
        }
    }

    @Override
    public int addScore(ExamScoreVO scorelist) {
        List errs = new ArrayList();
        ExamCourse examCourse = examService.getinfobyexamcourse(scorelist.getExam(),scorelist.getCourse());
        if (examCourse.isSubmit()){
            throw new GlobalException("403002","该考试成绩已经提交，修改成绩请通过成绩修改端口进行操作");
        }
       // Long teacher = (Long)userFeign.getcurrentuid().getData();
   //     Long teacher = (Long)userFeign.getcurrentuid();
//        if (!(stuClassService.getteacher(examCourse.getCourse())
//                .contains(teacher)))
//        {
//            throw new GlobalException("403001","您没有该考试登分权限，请联系负责人");
//        }
        scorelist.getScoreList().forEach(stu -> {
            try {
                //Score scoredb = scoreDao.findAllByUid(stu.getId()).get(0);
                //Score scoredb = scoreDao.findByCourseAndExamAndUid(scorelist.getCourse(),scorelist.getExam(),stu.getUid());
                Score scoredb = new Score();
                scoredb.setExam(scorelist.getExam());
                scoredb.setUid(stu.getUid());
                scoredb.setCid(stu.getCid());
                scoredb.setName(stu.getName());
                scoredb.setCourse(scorelist.getCourse());
                scoredb.setScore(stu.getScore());
                scoredb.setExscore(stu.getExscore());
                scoredb.setFinalscore(stu.getScore()*examCourse.getNorpercent()
                        +stu.getExscore()*(100-examCourse.getNorpercent()));
                scoredb.setContent("【正考成绩】"+stu.getContent());
                scoredb.setStatus(2);
                scoredb.setType((byte)1);
                scoreDao.save(scoredb);
            }catch (Exception e){
                errs.add(stu.getUid());
            }
        });
        return errs.size();
    }

    @Override
    public int modefyScore(Set<Score> scorelist) {
        scorelist.forEach(score -> {
            if ((Long)userFeign.getcurrentuid()!=
                    examService.manageuid(score.getExam())){
                throw new GlobalException("403-1002","您暂无修改"+score.getUid()+"该次成绩的权限");
            }
            Score scoredb = scoreDao.findById(score.getId()).get();
            scoredb.setScore(score.getScore());
            scoredb.setFinalscore(score.getFinalscore());
            scoredb.setExscore(score.getExscore());
            scoredb.setContent(score.getContent()+"-"+scoredb.getContent());
            scoreDao.save(scoredb);
        });
        return 1;
    }

    @Override
    public List getScorebyuid(Long exam, Long stu) {
        List<Score> scoreList = scoreDao.findAllByExamAndUid(exam,stu);
        List<Score> newscoreList = new ArrayList<>();
        Set<Long> allowexam = new HashSet<>();
        scoreList.forEach(exs -> {
            if (allowexam.contains(exs.getExam())||examService.examstatus(exs.getExam())){
                allowexam.add(exs.getExam());
                exs.setContent("【已审核】"+exs.getContent());
                newscoreList.add(exs);
            }else {
                //scoreList.remove(exs);
                exs.setScore(-1);
                exs.setExscore(-1);
                exs.setFinalscore(-1);
                exs.setContent("【成绩暂未发布】");
                newscoreList.add(exs);
            }
        });
        return newscoreList;
    }

    @Override
    public List getScorebyuid(Long uid,boolean teacher) {
        List<Score> scoreList = scoreDao.findAllByUid(uid);
        List<Score> newscoreList = new ArrayList<>();
        Set<Long> allowexam = new HashSet<>();
        scoreList.forEach(exs -> {
           if (allowexam.contains(exs.getExam())||examService.examstatus(exs.getExam())){
               allowexam.add(exs.getExam());
               exs.setContent("【已审核】"+exs.getContent());
               newscoreList.add(exs);
           }else if(teacher){
               allowexam.add(exs.getExam());
               exs.setContent("【待审核】"+exs.getContent());
               newscoreList.add(exs);
           } else {
               //scoreList.remove(exs);
               exs.setScore(-1);
               exs.setExscore(-1);
               exs.setFinalscore(-1);
               exs.setContent("【成绩暂未发布】");
               newscoreList.add(exs);
           }
        });
        return newscoreList;
    }

    @Override
    public List<Score> getscorebyclass(Long classid, Long exam,Long course) {
//        List<Score> scoreList = new ArrayList<>();
//        List<ClszzStuVO> stulist=stuClassService.listallstubyclass(classid);
//        stulist.forEach(stu -> {
//            scoreList.addAll(scoreDao.findAllByCourseAndExamAndUid(course,exam,stu.getUid()));
//        });
        List<Score> scoreList = scoreDao.findAllByCourseAndExamAndCid(course, exam, classid);
        return scoreList;
    }

    @Override
    public List listall(Long exam) {
        return null;
    }

    @Override
    public List getScorebyCourse(Long course, Long exam) {
        return scoreDao.findAllByCourseAndExam(course, exam);
    }


}
