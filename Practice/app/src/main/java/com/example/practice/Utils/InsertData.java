package com.example.practice.Utils;

import android.app.Application;
import android.util.Log;

import com.example.practice.Bean.Exercise;
import com.example.practice.Repository.ExerciseRepository;

public class InsertData {
    private ExerciseRepository exerciseRepository;
    public InsertData(Application application){
        exerciseRepository=new ExerciseRepository(application);
    }
    public void InsertExercise(){
        String[] s1={
                "第一单元练习题",
                "第二单元练习题",
                "第三单元练习题",
                "第四单元练习题",
                "第五单元练习题",
                "第六单元练习题",
                "第七单元练习题",
                "第八单元练习题",
                "第九单元练习题",
                "第十单元练习题"
        };
        for (int i=0;i<s1.length;i++){
            Exercise exercise=new Exercise(s1[i]);
            exerciseRepository.insertExercise(exercise);
            Log.d("mylog",i+" 号成功了");
        }
    }
}
