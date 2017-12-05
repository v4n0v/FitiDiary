package net.kdilla.fitidiary;

import android.app.Fragment;
import android.app.FragmentManager;
import   android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import net.kdilla.fitidiary.fragments.ExerciseFragment;
import net.kdilla.fitidiary.fragments.FragmentListener;
import net.kdilla.fitidiary.fragments.ProgramListFragment;
import net.kdilla.fitidiary.fragments.TrainingDayFragment;
import net.kdilla.fitidiary.fragments.TrainingWeekFragment;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.List;

/**
 * Created by avetc on 30.11.2017.
 */

public class WorkoutProgramActivity extends AppCompatActivity implements FragmentListener{
    private String programName;
    private int programId;
    private TextView programNameTextView;
    TrainingWeekFragment trainingWeekFragment;
    TrainingDayFragment trainingDayFragment;
    FragmentManager myFragmentManager;
    String[] muscles;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_program);

        Bundle bundle  = getIntent().getExtras();
        if (bundle!=null) {
            String programName = bundle.getString(PrefsID.PROGRAM_NAME);
            String title = getResources().getString(R.string.app_name) + ": "+programName;

            setTitle(title);
        }
        muscles = getResources().getStringArray(R.array.muscle_groups);
        trainingWeekFragment = new TrainingWeekFragment();
        myFragmentManager = getFragmentManager();
        if (savedInstanceState == null) {
            if (trainingWeekFragment != null) {// && changeFragment){
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.days_container, trainingWeekFragment);
                ft.commit();
            }
        }
//        if (savedInstanceState == null) {
//            // при первом запуске программы
//            FragmentTransaction fragmentTransaction = myFragmentManager
//                    .beginTransaction();
//
//            // добавляем в контейнер при помощи метода add()
//            fragmentTransaction.replace(R.id.days_container, trainingWeekFragment, PrefsID.TAG_1);
//            fragmentTransaction.commit();
//        }

//        programNameTextView=findViewById(R.id.text_view_program_name);
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle!=null){
//            programId=bundle.getInt(PrefsID.PROGRAM_ID);
//            programName=bundle.getString(PrefsID.PROGRAM_NAME);
//            programNameTextView.setText(programName);
//        }
    }
//
//    @Override
//    public void onTrainingDayClick(int id) {
//        Toast.makeText(this, (id+1)+", "+muscles[id], Toast.LENGTH_SHORT).show();
//
//    }

    @Override
    public void onFragmentButtonClick(int id) {
        android.support.v4.app.Fragment fragment = null;
        switch (id){
            case R.id.btn_next:
                Toast.makeText(this, "NextExercise", Toast.LENGTH_SHORT).show();
                break;
            case R.layout.item_training_week:
                int pos = trainingWeekFragment.getSelectedLayerPosition();
                Toast.makeText(this, "Selected day: "+pos, Toast.LENGTH_SHORT).show();
                break;
//
            case R.id.btn_start_workout:
                fragment = new ExerciseFragment();
                break;
        }
        if (fragment!=null){// && changeFragment){
            android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.days_container, fragment);
            ft.commit();
        }
//        FragmentTransaction fragmentTransaction = myFragmentManager
//                .beginTransaction();
//
//        // добавляем в контейнер при помощи метода add()
//        fragmentTransaction.replace(R.id.days_container, trainingDayFragment, PrefsID.TAG_2);
//        fragmentTransaction.commit();
      //  Toast.makeText(this, (id+1)+", "+muscles[id], Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onFragmentRecycleElementClick(View v, int id) {
        android.support.v4.app.Fragment fragment = null;
//        RecyclerView rv = (RecyclerView) v;
        fragment=new TrainingDayFragment();
//        FragmentTransaction fragmentTransaction = myFragmentManager
//                .beginTransaction();
//
//        // добавляем в контейнер при помощи метода add()
//        fragmentTransaction.replace(R.id.days_container, trainingDayFragment, PrefsID.TAG_2);
//        fragmentTransaction.commit();
        if (fragment!=null){// && changeFragment){
          FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.days_container, fragment);
            ft.commit();
        }
    }

    @Override
    public void onFragmentMenuElementClick(int viewId, int pos) {

    }
}
