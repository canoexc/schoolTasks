package com.example.practice;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Bean.Exercise;

import java.util.List;

public class ExerciseFragment extends Fragment {

    private ExerciseViewModel exerciseViewModel;
    private List<Exercise> findExercise;
    private RecyclerView recyclerView;
    private ExerciseAdapter exerciseAdapter;
    private DividerItemDecoration dividerItemDecoration;
    public ExerciseFragment(){
        setHasOptionsMenu(true);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.exercise_fragment, container, false);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);
        //inflater.inflate(R.menu.main, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.exercise_search).getActionView();
        searchView.setMaxWidth(1000);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String pattern = newText.trim();
                findExercise = exerciseViewModel.getExerciseWithPattern(pattern);
                exerciseAdapter.submitList(findExercise);
                return true;
            }
        });
        ;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        exerciseViewModel = ViewModelProviders.of(this).get(ExerciseViewModel.class);
        // TODO: Use the ViewModel
        recyclerView = requireActivity().findViewById(R.id.exercise_recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity()));
        exerciseAdapter = new ExerciseAdapter(exerciseViewModel);
        recyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public void onAnimationFinished(@NonNull RecyclerView.ViewHolder viewHolder) {
                super.onAnimationFinished(viewHolder);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                if (linearLayoutManager != null) {
                    int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    int lastPosition = linearLayoutManager.findLastVisibleItemPosition();
                    for (int i = firstPosition; i <= lastPosition; i++) {
                        ExerciseAdapter.MyViewHolder holder = (ExerciseAdapter.MyViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                    }
                }

            }
        });
        dividerItemDecoration = new DividerItemDecoration(requireActivity(),DividerItemDecoration.VERTICAL);
        recyclerView.setAdapter(exerciseAdapter);
        recyclerView.addItemDecoration(dividerItemDecoration);
        exerciseAdapter.submitList(exerciseViewModel.getAllExercise());
    }

}
