package net.kdilla.fitidiary;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import net.kdilla.fitidiary.utils.PrefsID;

/**
 * Created by avetc on 30.11.2017.
 */

public class WorkoutProgramActivity extends AppCompatActivity {
    private String programName;
    private int programId;
    private TextView programNameTextView;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_program);

//        programNameTextView=findViewById(R.id.text_view_program_name);
//
//        Bundle bundle = getIntent().getExtras();
//        if (bundle!=null){
//            programId=bundle.getInt(PrefsID.PROGRAM_ID);
//            programName=bundle.getString(PrefsID.PROGRAM_NAME);
//            programNameTextView.setText(programName);
//        }
    }
}
