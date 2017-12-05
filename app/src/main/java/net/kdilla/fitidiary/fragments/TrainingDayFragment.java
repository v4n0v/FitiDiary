package net.kdilla.fitidiary.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.kdilla.fitidiary.R;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetc on 02.12.2017.
 */

public class TrainingDayFragment extends Fragment implements View.OnClickListener{

    List<String> exercise;
    FragmentListener mainActivity;
    Button startBtn;
    @Override
    public void onAttach(Context context) {
        mainActivity=(FragmentListener)context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_training_day, container, false);
        exercise= new ArrayList<>();
       exercise.add("Bench press");
        exercise.add("Incline barbell press");
        exercise.add("Flies");
        startBtn = rootView.findViewById(R.id.btn_start_workout);
        startBtn.setOnClickListener(this);

        RecyclerView programSelectRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_training_day);
        registerForContextMenu(programSelectRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(PrefsID.VERTICAL);
        programSelectRecyclerView.setLayoutManager(layoutManager);
        programSelectRecyclerView.setAdapter(new MyAdapter());

        return rootView;
    }

    @Override
    public void onClick(View view) {
        startWorkout(view);
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView exerciseTextView;
        private TextView muscleGroupTextView;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_training_day, parent, false));
            itemView.setOnClickListener(this);
            exerciseTextView = (TextView) itemView.findViewById(R.id.tv_exercise);
          //  muscleGroupTextView = itemView.findViewById(R.id.tv_muscle_group);
        }

        void bind(int position) {

            String exercise = TrainingDayFragment.this.exercise.get(position);
            String[] muscles = getResources().getStringArray(R.array.muscle_groups);
            exerciseTextView.setText(exercise);
       //     muscleGroupTextView.setText(muscles[position]);
        }

        @Override
        public void onClick(View view) {
         //startWorkout(view);

        }
    }

    private void startWorkout(View view) {
        mainActivity.onFragmentButtonClick(view.getId());
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(position);
        }


        public int getItemCount() {

            return exercise.size();
        }
    }


}
