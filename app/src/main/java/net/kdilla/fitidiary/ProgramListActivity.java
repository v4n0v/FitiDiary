package net.kdilla.fitidiary;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import net.kdilla.fitidiary.fragments.ExerciseFragment;
import net.kdilla.fitidiary.fragments.ProgramListFragment;
import net.kdilla.fitidiary.fragments.TrainingWeekFragment;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

public class ProgramListActivity extends AppCompatActivity implements ProgramListFragment.ProgramListListener {
    ProgramListFragment programListFragment;
    ExerciseFragment exerciseDetail;
    TrainingWeekFragment trainingWeekFragment;
    FragmentManager myFragmentManager;

    ListView listView;
    List<String> programs;
    ArrayAdapter<String> adapter;


    String programName;
    int programID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_list);
        initView();

        programListFragment = new ProgramListFragment();
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

        programs = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            programs.add("Workout program " + i);
        }

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
    public void onListItemClick(int id) {

        Intent intent = new Intent(ProgramListActivity.this, WorkoutProgramActivity.class);
        intent.putExtra(PrefsID.PROGRAM_ID, id);
        intent.putExtra(PrefsID.PROGRAM_NAME, programs.get(id));
        startActivity(intent);

    }


}
