package net.kdilla.fitidiary;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.kdilla.fitidiary.core.Program;
import net.kdilla.fitidiary.fragments.ExerciseFragment;
import net.kdilla.fitidiary.fragments.FragmentListener;
import net.kdilla.fitidiary.fragments.ProgramListFragment;
import net.kdilla.fitidiary.fragments.TrainingWeekFragment;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

public class ProgramListActivity extends AppCompatActivity implements FragmentListener{
    ProgramListFragment programListFragment;
    ExerciseFragment exerciseDetail;
    TrainingWeekFragment trainingWeekFragment;
    FragmentManager myFragmentManager;

    ListView listView;

    ArrayList<Program> programs;
    ArrayAdapter<String> adapter;
    Program program;

    String programName;
    int programID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);
        initView();
        programs = new ArrayList<>();
        program = new Program("Jay Cutler Program", 4, 10);


        programs.add(program);
        for (int i = 1; i < 7; i++) {
            //      programs.add(new ProgramRecycleItem("Workout program " + i, "3days"));
            programs.add( new Program("Jay Cutler Program "+i , 4, 10));
        }



        programListFragment = new ProgramListFragment();
        programListFragment.initPrograms(programs);
        //registerForContextMenu(programListFragment.getProgramSelectRecyclerView());
        myFragmentManager = getFragmentManager();
        if (savedInstanceState == null) {
            // при первом запуске программы
            FragmentTransaction fragmentTransaction = myFragmentManager
                    .beginTransaction();

            // добавляем в контейнер при помощи метода add()
            fragmentTransaction.replace(R.id.container, programListFragment, PrefsID.TAG_1);
            fragmentTransaction.commit();
        }

//        programs = new ArrayList<>();
//        for (int i = 1; i < 6; i++) {
//            //programs.add("Workout program " + i);
//            programs.add( "Workout program " + i );
//        }

    }


    private void initView() {
        //    listView = findViewById(R.id.list_programs);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void editElement(int position) {
        Toast.makeText(ProgramListActivity.this, "edit", Toast.LENGTH_SHORT).show();
    }

    private void deleteElement() {
        Toast.makeText(ProgramListActivity.this, "delete", Toast.LENGTH_SHORT).show();
    }



    @Override
    public void onFragmentButtonClick(int id) {
        switch (id) {
            case R.id.btn_new_program:
                Toast.makeText(ProgramListActivity.this, "Create new", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFragmentRecycleElementClick( View v,int id) {
        Intent intent = new Intent(ProgramListActivity.this, WorkoutProgramActivity.class);
        intent.putExtra(PrefsID.PROGRAM_ID, id);
        intent.putExtra(PrefsID.PROGRAM_NAME, programs.get(id).getName());
        startActivity(intent);
    }

    @Override
    public void onFragmentMenuElementClick(int viewId, int pos) {
        if (viewId == R.menu.context_menu) {
            Intent intent = new Intent(ProgramListActivity.this, WorkoutProgramActivity.class);
            intent.putExtra(PrefsID.PROGRAM_ID, pos);
            intent.putExtra(PrefsID.PROGRAM_NAME, programs.get(pos).getName());
            startActivity(intent);
        }
    }
}
