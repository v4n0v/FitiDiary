package net.kdilla.fitidiary.fragments;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import net.kdilla.fitidiary.R;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

public class TrainingWeekFragment extends Fragment{
    public List<String> getDays() {
        return days;
    }

    List<String> days;
    private TrainingWeekListener mainActivity;

    public interface TrainingWeekListener {
        void onTrainingDayClick(int id);
    }
    @Override
    public void onAttach(Context context) {
        mainActivity = (TrainingWeekListener) context;
        super.onAttach(context);
    }
    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_training_week, container, false);
        days= new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            days.add("Day "+(i+1));
        }

        RecyclerView programSelectRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_training_week);
        registerForContextMenu(programSelectRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(PrefsID.VERTICAL);
        programSelectRecyclerView.setLayoutManager(layoutManager);
        programSelectRecyclerView.setAdapter(new MyAdapter());

        return rootView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView daysTextView;
        private TextView muscleGroupTextView;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_training_week, parent, false));
            itemView.setOnClickListener(this);
            daysTextView = (TextView) itemView.findViewById(R.id.tv_days);
            muscleGroupTextView = itemView.findViewById(R.id.tv_muscle_group);
        }

        void bind(int position) {

            String day = days.get(position);
            String[] muscles = getResources().getStringArray(R.array.muscle_groups);
            daysTextView.setText(day);
            muscleGroupTextView.setText(muscles[position]);
        }

        @Override
        public void onClick(View view) {
            showTrainingDay(this.getLayoutPosition());

        }
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

            return days.size();
        }
    }

    private void showTrainingDay(int id){
        mainActivity.onTrainingDayClick(id);
    }

}
