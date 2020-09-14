package com.example.practice;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practice.Bean.Exercise;

public class ExerciseAdapter extends ListAdapter<Exercise,ExerciseAdapter.MyViewHolder> {
    private ExerciseViewModel exerciseViewModel;
    ExerciseAdapter(ExerciseViewModel exerciseViewModel) {
        super(new DiffUtil.ItemCallback<Exercise>() {
            @Override
            public boolean areItemsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
                return oldItem.getEid() == newItem.getEid();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Exercise oldItem, @NonNull Exercise newItem) {
                return (oldItem.getName().equals(newItem.getName()));
            }
        });
        this.exerciseViewModel = exerciseViewModel;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.exercise,parent,false);
        final MyViewHolder holder = new MyViewHolder(itemView);
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavController navController = Navigation.findNavController(v);
                holder.textViewName.setTag(R.id.exercise_name_for_task,holder.textViewName.getText());
                navController.navigate(R.id.action_exerciseFragment_to_practiceFragment);
            }
        });
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        final Exercise exercise = getItem(position);
        holder.itemView.setTag(R.id.exercise_name_for_task,exercise);
        holder.textViewName.setText(exercise.getName());
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName;
        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.exerciseName);

        }
    }
}
