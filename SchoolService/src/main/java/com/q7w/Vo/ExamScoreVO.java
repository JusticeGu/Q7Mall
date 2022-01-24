package com.q7w.Vo;

import com.q7w.Entity.Score;
import lombok.Data;

import java.util.Set;

/**
 * @author xiaogu
 * @date 2021/8/9 16:09
 **/
@Data
public class ExamScoreVO {
    private Long exam;
    private Long course;
    private Long kid;
    private Set<Score> scoreList;
}
