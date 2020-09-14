package com.example.practice;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.practice.Bean.Exercise;
import com.example.practice.Repository.ExerciseRepository;

import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private ExerciseRepository exerciseRepository;
    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        this.exerciseRepository=new ExerciseRepository(application);
    }
    public void insertExercise (Exercise...exercises){exerciseRepository.insertExercise(exercises);}
    public List<Exercise> getAllExercise(){
        return exerciseRepository.getAllExercise();
    }
    public List<Exercise> getExerciseWithPattern(String pattern){
        return exerciseRepository.getExerciseWithPattern(pattern);
    }
    // TODO: Implement the ViewModel

}
