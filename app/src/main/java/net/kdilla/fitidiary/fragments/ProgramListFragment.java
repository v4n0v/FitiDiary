package net.kdilla.fitidiary.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import net.kdilla.fitidiary.R;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetc on 30.11.2017.
 */

public class ProgramListFragment extends Fragment {
    ListView listView;
    List<String> programs;
    ArrayAdapter<String> adapter;

    public RecyclerView getProgramSelectRecyclerView() {
        return programSelectRecyclerView;
    }

    RecyclerView programSelectRecyclerView;
    private ProgramListListener mainActivity;

    public interface ProgramListListener {
        void onListItemClick(int id);
    }

    @Override
    public void onAttach(Context context) {
        mainActivity = (ProgramListListener) context;
        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program_list, container, false);

        programs = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            programs.add("Workout program " + i);
        }

        programSelectRecyclerView = rootView.findViewById(R.id.recycler_list_programs);
        registerForContextMenu(programSelectRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(PrefsID.VERTICAL);
        programSelectRecyclerView.setLayoutManager(layoutManager);
        programSelectRecyclerView.setAdapter(new MyAdapter());
        return rootView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView programNameTextView;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_program, parent, false));
            itemView.setOnClickListener(this);
            programNameTextView = (TextView) itemView.findViewById(R.id.tv_program_name);
        }

        void bind(int position) {

            String category = programs.get(position);
            programNameTextView.setText(category);

        }

        @Override
        public void onClick(View view) {
            showWorkoutProgramScreen(this.getLayoutPosition());
            //getActivity().onItemClick(this.getLayoutPosition());
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
            //   return City.cities.length;
            //  return GetWeatherFromRes.getWeatherList(getActivity()).length;
            return programs.size();
        }
    }

//    public void initContent(ViewGroup content) {
//
//    }
//    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        getActivity().getMenuInflater().inflate(R.menu.context_menu, menu);
//
//    }

    private void showWorkoutProgramScreen(int id) {
        mainActivity.onListItemClick(id);
    }

}
