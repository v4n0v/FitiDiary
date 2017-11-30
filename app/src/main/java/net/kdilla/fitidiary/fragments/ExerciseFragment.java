package net.kdilla.fitidiary.fragments;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.kdilla.fitidiary.R;
import net.kdilla.fitidiary.utils.PrefsID;


public class ExerciseFragment extends Fragment {

    public ExerciseFragment() {
        // Required empty public constructor
    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_exercise, container, false);


        FragmentManager fragmentManager = getChildFragmentManager();//Внимание! ChildFragmentManager!
        //Ищем Фрагмент по тегу и если его нет, то создаем Фрагмент с таким тегом
        StopWatchFragment stopwatchFragment = (StopWatchFragment) fragmentManager.findFragmentByTag(PrefsID.STOPWATCH_FRAGMENT_TAG);
        if (stopwatchFragment == null) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            stopwatchFragment = new StopWatchFragment();
            fragmentTransaction.replace(R.id.stopwatch_container, stopwatchFragment, PrefsID.STOPWATCH_FRAGMENT_TAG);
            fragmentTransaction.commit();
        }

        // Inflate the layout for this fragment
        return rootView;
    }

}
