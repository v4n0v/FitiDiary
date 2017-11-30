package net.kdilla.fitidiary.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import net.kdilla.fitidiary.R;

import java.util.Locale;
import java.util.Timer;

/**
 * Created by avetc on 30.11.2017.
 */

public class StopWatchFragment extends Fragment implements View.OnClickListener {

    private static final int MILLS_IN_SECOND = 3600;
    private static final int SECONDS_IN_MUNUTE = 60;
    private static final int DELAY = 1000;
    private static final String KEY_SECONDS = "seconds";
    private static final String KEY_RUNNING = "running";
    private static final String KEY_WAS_RUNNING = "wasRunning";
    private int seconds;
    private boolean running;
    private boolean wasRunning;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("StopwatchFragment", "savedInstanceState " + savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt(KEY_SECONDS);
            running = savedInstanceState.getBoolean(KEY_RUNNING);
            wasRunning = savedInstanceState.getBoolean(KEY_WAS_RUNNING);
        }
        View rootView = inflater.inflate(R.layout.fragment_stopwatch, container, false);

        Button resetButton = (Button) rootView.findViewById(R.id.reset_button);
        Button startButton = (Button) rootView.findViewById(R.id.start_button);
        Button stopButton = (Button) rootView.findViewById(R.id.stop_button);
        resetButton.setOnClickListener(this);
        startButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);

        runTimer(rootView);
        return rootView;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.reset_button) {
            running = false;
            seconds = 0;
        }
        if (view.getId() == R.id.start_button) {
            running = true;
        }
        if (view.getId() == R.id.stop_button) {
            running = false;
        }
    }

    private void runTimer(View rootView) {
        final TextView timeView = (TextView) rootView.findViewById(R.id.text_view);
        final Handler handler = new Handler();


        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = seconds / MILLS_IN_SECOND;
                int minutes = (seconds % MILLS_IN_SECOND) / SECONDS_IN_MUNUTE;
                int secs = seconds % 60;
                String time = String.format(Locale.US, "%d:%02d:%02d", hours, minutes, secs);
                timeView.setText(time);
                if (running) {
                    seconds++;
                }
                handler.postDelayed(this, DELAY);
            }
        });
    }

    @Override
    public void onStart() {
        if (wasRunning) {
            running = true;
        }
        super.onStart();
    }

    @Override
    public void onStop() {
        wasRunning = running;
        running = false;
        super.onStop();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(KEY_SECONDS, seconds);
        savedInstanceState.putBoolean(KEY_RUNNING, running);
        savedInstanceState.putBoolean(KEY_WAS_RUNNING, wasRunning);
    }
}
