package com.learn.android.omid.criminallintent;

import android.support.v4.app.Fragment;

/**
 * Created by User on 8/27/2017.
 */
public class CrimeListActivity extends SingleFragmentActivity{
    @Override
    protected Fragment createFragment() {
        return new CrimeListFragment();
    }
}
