package com.learn.android.omid.criminallintent;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;
import java.util.UUID;

/**
 * Created by User on 8/28/2017.
 */
public class CrimePagerActivity extends FragmentActivity {

    private ViewPager mViewPager;
    private List<Crime> mCrimes;


    public static final String EXTRA_CRIME_ID = "com.learn.android.omid.criminallintent.crim_id";


    public static Intent newIntent(Context packageContext, UUID crimeId) {
        Intent intent = new Intent(packageContext, CrimePagerActivity.class);
        intent.putExtra(EXTRA_CRIME_ID, crimeId);
        return intent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crime_pager);

        mViewPager = (ViewPager) findViewById(R.id.activity_crime_pager_view_pager);
        mCrimes = CrimeLab.get(this).getCrimes();

        FragmentManager fragmentManager = getSupportFragmentManager();
        mViewPager.setAdapter(new
                                      FragmentStatePagerAdapter(fragmentManager) {
                                          @Override
                                          public int getCount() {
                                              return mCrimes.size();
                                          }

                                          @Override
                                          public Fragment getItem(int position) {
                                              Crime crime = mCrimes.get(position);
                                              return CrimeFragment.newInstance(crime.getId());
                                          }
                                      });
    }
}
