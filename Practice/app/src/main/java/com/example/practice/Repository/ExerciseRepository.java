package com.example.practice.Repository;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.practice.Bean.Exercise;
import com.example.practice.Dao.ExerciseDao;
import com.example.practice.PracticeDatabase;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ExerciseRepository {
    private ExerciseDao exerciseDao;
    public ExerciseRepository(Context context){
        PracticeDatabase practiceDatabase=PracticeDatabase.getDatabase(context.getApplicationContext());
        exerciseDao = practiceDatabase.getExerciseDao();
    }
    public void insertExercise(Exercise... exercises){new InsertAsyncTask(exerciseDao).execute(exercises);
    }
    public List<Exercise>getAllExercise(){
        try {
            return new getAllExerciseAsyncTask(exerciseDao).execute().get();
        }catch (ExecutionException e){
            Log.d("mylog","执行异常了");
            return null;
        }catch (InterruptedException e){
            Log.d("mylog","中断异常了");
            return null;
        }
    }
    public List<Exercise>getExerciseWithPattern(String pattern){
        try {
            return new getExerciseWithPatternAsyncTask(exerciseDao).execute(pattern).get();
        }catch (ExecutionException e){
            Log.d("mylog","执行异常了");
            return null;
        }catch (InterruptedException e){
            Log.d("mylog","中断异常了");
            return null;
        }
    }
    static class InsertAsyncTask extends AsyncTask<Exercise,Void,Void>{
        private ExerciseDao exerciseDao;
        InsertAsyncTask(ExerciseDao exerciseDao) {
            this.exerciseDao=exerciseDao;
        }
        @Override
        protected Void doInBackground(Exercise... exercises) {
            exerciseDao.InsertExercise(exercises);
            return null;
        }
    }
    static class getAllExerciseAsyncTask extends AsyncTask<Void,Void,List<Exercise>>{
        private ExerciseDao exerciseDao;
        getAllExerciseAsyncTask(ExerciseDao exerciseDao){this.exerciseDao=exerciseDao;}
        @Override
        protected List<Exercise> doInBackground(Void...voids) {
            return exerciseDao.getAllExercise();
        }
    }
    static class getExerciseWithPatternAsyncTask extends AsyncTask<String,Void,List<Exercise>>{
        private ExerciseDao exerciseDao;
        getExerciseWithPatternAsyncTask(ExerciseDao exerciseDao){this.exerciseDao=exerciseDao;}
        @Override
        protected List<Exercise> doInBackground(String...strings) {
            return exerciseDao.getExerciseWithPattern(strings[0]);
        }
    }
}
