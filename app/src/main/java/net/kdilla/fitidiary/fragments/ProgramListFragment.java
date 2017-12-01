package net.kdilla.fitidiary.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.kdilla.fitidiary.R;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetc on 30.11.2017.
 */

public class ProgramListFragment extends Fragment {
    ListView listView;
//    List<ProgramRecycleItem> programs;
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
//            programs.add(new ProgramRecycleItem("Workout program " + i, "3days"));
            programs.add("Workout program " + i);
        }

        programSelectRecyclerView = rootView.findViewById(R.id.recycler_list_programs);
        registerForContextMenu(programSelectRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(PrefsID.VERTICAL);
        programSelectRecyclerView.setLayoutManager(layoutManager);
//        programSelectRecyclerView.setAdapter(new MyProgramListAdapter(programs, (ProgramListActivity)mainActivity ));
        programSelectRecyclerView.setAdapter(new MyAdapter());
        return rootView;
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView programNameTextView;
        TextView txtOptionDigit;
        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_program, parent, false));

            //    itemView.setOnClickListener(this);

            txtOptionDigit = (TextView) itemView.findViewById(R.id.txtOptionDigit);
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
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.programNameTextView.setText(programs.get(position));
            holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Display option menu

                    android.support.v7.widget.PopupMenu popupMenu =
                            new android.support.v7.widget.PopupMenu((Context) mainActivity, holder.txtOptionDigit);
                    popupMenu.inflate(R.menu.context_menu);
                    popupMenu.setOnMenuItemClickListener(new android.support.v7.widget.PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {

                            switch (item.getItemId()) {
                                case R.id.menu_edit:
                                    Toast.makeText((Context) mainActivity, "Edited", Toast.LENGTH_LONG).show();
                                    break;
                                case R.id.menu_delete:
                                    //Delete item
                                    programs.remove(position);
                                    notifyDataSetChanged();
                                    Toast.makeText((Context) mainActivity, "Deleted", Toast.LENGTH_LONG).show();
                                    break;
                                default:
                                    break;
                            }
                            return false;
                        }
                    });
                    popupMenu.show();
                }
            });
           holder.bind(position);
        }


        public int getItemCount() {

            return programs.size();
        }
    }

    public void initContent(ViewGroup content) {

    }


    private void showWorkoutProgramScreen(int id) {
        mainActivity.onListItemClick(id);
    }

}
