package net.kdilla.fitidiary.fragments;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import net.kdilla.fitidiary.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by avetc on 30.11.2017.
 */

public class ProgramListFragment extends Fragment {
    ListView listView;
    List<String> programs;
    ArrayAdapter<String> adapter;
    private ProgramListListener mainActivity;

    public interface ProgramListListener {
        void onListItemClick(int id);
    }
//
//    @Override
//    public void onAttach(Context context) {
//        mainActivity = (ProgramListListener) context;
//        super.onAttach(context);
//    }

//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_program_list, container, false);
////        initView(container);
//        listView = rootView.findViewById(R.id.list_programs);
//        programs = new ArrayList<>();
//        for (int i = 1; i < 6; i++) {
//            programs.add("Workout program " + i);
//        }
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_activated_1, programs);
//        listView.setAdapter(adapter);
//        registerForContextMenu(listView);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
//                                    long id) {
//                MyFragment2 fragment = (MyFragment2) myFragmentManager
//                        .findFragmentByTag(TAG_2);
//
//                if (fragment == null) {
//
//                    Bundle bundle = new Bundle();
//                    bundle.putString(KEY_MSG_2, "Заменили на второй фрагмент");
//                    myFragment2.setArguments(bundle);
//
//                    FragmentTransaction fragmentTransaction = myFragmentManager
//                            .beginTransaction();
//                    fragmentTransaction.replace(R.id.container, myFragment2,
//                            TAG_2);
//                    fragmentTransaction.commit();
//
//                } else {
//                    fragment.setMsg("Второй фрагмент уже загружен");
//                }
//            }
//        });
//
//        return rootView;
//    }

    public void initContent(ViewGroup content) {

    }

    //    @Override
//    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        super.onCreateContextMenu(menu, v, menuInfo);
//        getMenuInflater().inflate(R.menu.context_menu, menu);
//
//    }
//    private void initView(ViewGroup container) {
//
//        listView = rootView.findViewById(R.id.list_programs);
//
//    }
}
