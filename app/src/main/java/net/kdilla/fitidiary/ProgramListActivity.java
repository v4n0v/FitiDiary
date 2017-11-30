package net.kdilla.fitidiary;

import android.app.FragmentManager;
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

import net.kdilla.fitidiary.fragments.ExerciseFragment;
import net.kdilla.fitidiary.fragments.ProgramListFragment;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.ArrayList;
import java.util.List;

public class ProgramListActivity extends AppCompatActivity  {
    ProgramListFragment programList;
    public ExerciseFragment exerciseDetail;
    ProgramListFragment programListFragment;
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

        programs = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            programs.add("Workout program " + i);
        }


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_activated_1, programs);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);
        //final Intent intent =  new Intent(this, WorkoutProgramActivity.class);
      //  final Intent intent = getIntent();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position,
                                    long id) {
                Intent intent =  new Intent(getApplication(), WorkoutProgramActivity.class);
                intent.putExtra(PrefsID.PROGRAM_ID, position);
                intent.putExtra(PrefsID.PROGRAM_NAME, programs.get(position));
                startActivity(intent);
            }
        });



    }


    private void initView() {
        listView = findViewById(R.id.list_programs);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//    public boolean onContextItemSelected(MenuItem item) {
        //return super.onContextItemSelected(item);
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.menu_edit:
                   editElement(info.position);
                return true;
            case R.id.menu_delete:
                    deleteElement();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void editElement(int position) {

    }

    private void deleteElement() {

    }

}
