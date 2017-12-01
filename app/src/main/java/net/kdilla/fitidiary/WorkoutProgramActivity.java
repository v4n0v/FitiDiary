package net.kdilla.fitidiary;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import net.kdilla.fitidiary.fragments.ProgramListFragment;
import net.kdilla.fitidiary.fragments.TrainingWeekFragment;
import net.kdilla.fitidiary.utils.PrefsID;

import java.util.List;

/**
 * Created by avetc on 30.11.2017.
 */

public class WorkoutProgramActivity extends AppCompatActivity implements TrainingWeekFragment.TrainingWeekListener{
    private String programName;
    private int programId;
    private TextView programNameTextView;
    TrainingWeekFragment trainingWeekFragment;
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
            // при первом запуске программы
            FragmentTransaction fragmentTransaction = myFragmentManager
                    .beginTransaction();

            // добавляем в контейнер при помощи метода add()
            fragmentTransaction.replace(R.id.days_container, trainingWeekFragment, PrefsID.TAG_1);
            fragmentTransaction.commit();
        }

//        programNameTextView=findViewById(R.id.text_view_program_name);
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle!=null){
//            programId=bundle.getInt(PrefsID.PROGRAM_ID);
//            programName=bundle.getString(PrefsID.PROGRAM_NAME);
//            programNameTextView.setText(programName);
//        }
    }

    @Override
    public void onTrainingDayClick(int id) {
        Toast.makeText(this, (id+1)+", "+muscles[id], Toast.LENGTH_SHORT).show();

    }
}
