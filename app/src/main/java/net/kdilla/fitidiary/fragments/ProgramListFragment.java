package net.kdilla.fitidiary.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.kdilla.fitidiary.R;
import net.kdilla.fitidiary.core.Program;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetc on 30.11.2017.
 */

public class ProgramListFragment extends Fragment implements View.OnClickListener{


    Button addNewProgramBtn;
    ArrayList<Program> programs;
    MyAdapter adapter;
    public RecyclerView getProgramSelectRecyclerView() {
        return programSelectRecyclerView;
    }

    RecyclerView programSelectRecyclerView;
    private FragmentListener mainActivity;

    Program program;

    @Override
    public void onAttach(Context context) {
        mainActivity = (FragmentListener) context;
        super.onAttach(context);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_program_list, container, false);
        addNewProgramBtn = rootView.findViewById(R.id.btn_new_program);
        addNewProgramBtn.setOnClickListener(this);
        programs = new ArrayList<>();
        adapter = new MyAdapter();
        program = new Program("Jay Cutler Program", 4, 10);


        programs.add(program);
        for (int i = 1; i < 7; i++) {
      //      programs.add(new ProgramRecycleItem("Workout program " + i, "3days"));
            programs.add( new Program("Jay Cutler Program "+i , 4, 10));
        }

        programSelectRecyclerView = rootView.findViewById(R.id.recycler_list_programs);
        registerForContextMenu(programSelectRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(PrefsID.VERTICAL);
        programSelectRecyclerView.setLayoutManager(layoutManager);
//        programSelectRecyclerView.setAdapter(new MyProgramListAdapter(programs, (ProgramListActivity)mainActivity ));
        programSelectRecyclerView.setAdapter(adapter);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        mainActivity.onFragmentButtonClick(view.getId());
    }

    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private TextView programNameTextView;
        private TextView txtOptionDigit;
        private TextView tvProgramDays;
        private TextView tvProgramWeeks;
        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item_program, parent, false));

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            txtOptionDigit = (TextView) itemView.findViewById(R.id.txtOptionDigit);
            programNameTextView = (TextView) itemView.findViewById(R.id.tv_program_name);
            tvProgramDays= itemView.findViewById(R.id.tv_program_days);
            tvProgramWeeks= itemView.findViewById(R.id.tv_program_weeks);
        }

        void bind(int position) {

            String category = programs.get(position).getName();
            programNameTextView.setText(category);
            tvProgramDays.setText(programs.get(position).getDaysCout()+" days traning");
            tvProgramWeeks.setText(programs.get(position).getWeeksCount()+" weeks");

        }

        @Override
        public void onClick(View view) {
            showNext(view, this.getLayoutPosition());
            //getActivity().onItemClick(this.getLayoutPosition());
        }


        @Override
        public boolean onLongClick(View view) {
            showPopup(view, this.getLayoutPosition());
            return true;
        }
    }
    public void showPopup(final View view, final int position) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), view);
        popupMenu.inflate(R.menu.context_menu);
        popupMenu.setOnMenuItemClickListener(new android.support.v7.widget.PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_edit:
                        Toast.makeText((Context) mainActivity, "Edited", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_select:
                        showNext(view, position);
                        Toast.makeText((Context) mainActivity, "Select", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.menu_delete:
                        //Delete item
                         programs.remove(position);
                        adapter.notifyDataSetChanged();
                        Toast.makeText((Context) mainActivity, "Deleted", Toast.LENGTH_LONG).show();
                        break;
                    default:

                        break;
                }
                return true;
            }
        });
        popupMenu.show();
    }
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{


        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(final MyViewHolder holder, final int position) {
            holder.programNameTextView.setText(programs.get(position).getName());
//            holder.txtOptionDigit.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //Display option menu
//
//                    android.support.v7.widget.PopupMenu popupMenu =
//                            new android.support.v7.widget.PopupMenu((Context) mainActivity, holder.txtOptionDigit);
//                    popupMenu.inflate(R.menu.context_menu);
//                    popupMenu.setOnMenuItemClickListener(new android.support.v7.widget.PopupMenu.OnMenuItemClickListener() {
//                        @Override
//                        public boolean onMenuItemClick(MenuItem item) {
//                            switch (item.getItemId()) {
//                                case R.id.menu_edit:
//                                    Toast.makeText((Context) mainActivity, "Edited", Toast.LENGTH_LONG).show();
//                                    break;
//                                case R.id.menu_select:
//                                    showWorkoutProgramMenu(position,  R.menu.context_menu);
//                                    break;
//                                case R.id.menu_delete:
//                                    //Delete item
//                                    programs.remove(position);
//                                    notifyDataSetChanged();
//                                    Toast.makeText((Context) mainActivity, "Deleted", Toast.LENGTH_LONG).show();
//                                    break;
//                                default:
//
//                                    break;
//                            }
//                            return true;
//                        }
//                    });
//                    popupMenu.show();
//                }
//            });
            holder.bind(position);
        }


        public int getItemCount() {

            return programs.size();
        }

    }


    private void showNext(View view, int id) {
        mainActivity.onFragmentRecycleElementClick(view, id);
    }

    private void showWorkoutProgramMenu(int pos, int id) {
        mainActivity.onFragmentMenuElementClick(pos, id);
    }

}
