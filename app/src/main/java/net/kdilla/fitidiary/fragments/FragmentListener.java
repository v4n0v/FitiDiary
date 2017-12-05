package net.kdilla.fitidiary.fragments;

import android.view.View;

/**
 * Created by avetc on 02.12.2017.
 */

public interface FragmentListener {
    void onFragmentButtonClick(int id);
    void onFragmentRecycleElementClick(View viewId, int pos);
    void onFragmentMenuElementClick(int viewId, int pos);
}
