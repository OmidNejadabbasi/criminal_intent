package com.learn.android.omid.criminallintent;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by User on 8/27/2017.
 */
public class CrimeListFragment extends Fragment {

    private static final String TAG="ListFragment";

    private RecyclerView mCrimeRecyclerView;
    private CrimeAdapter mAdapter;




    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_crime_list,container,false);
        mCrimeRecyclerView= (RecyclerView) view.findViewById(R.id.crime_recycler_view);
        mCrimeRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        updateUI();

        return view;
    }

    private void updateUI(){
        CrimeLab crimeLab=CrimeLab.get(getActivity());
        List<Crime> crimeList=crimeLab.getCrimes();

        if(mAdapter==null) {
            mAdapter = new CrimeAdapter(crimeList);

        }else {
            mAdapter.notifyDataSetChanged();
            Log.d(TAG, "updateUI: mAdapter.notify...()");
        }
        mCrimeRecyclerView.setAdapter(mAdapter);
    }




    private class CrimeHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private CheckBox mSolvedCheckBox;
        private TextView mDateTextView;

        private Crime mCrime;


        public CrimeHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);


            mTitleTextView=(TextView) itemView.findViewById(R.id.list_item_crime_title_text_view);

            mDateTextView=(TextView) itemView.findViewById(R.id.list_item_crime_date_text_view);

            mSolvedCheckBox=(CheckBox) itemView.findViewById(R.id.list_item_crime_solved_check_box);



        }

        public void bindCrime(Crime crime){
            mCrime=crime;
            mTitleTextView.setText(crime.getTiltle());
            mSolvedCheckBox.setChecked(crime.isSolved());
            mDateTextView.setText(crime.getDate().toString());
        }

        @Override
        public void onClick(View v) {
            int id=v.getId();

//            Toast.makeText(getActivity(),mCrime.getTiltle()+ " clicked!",Toast.LENGTH_SHORT).show();
            Intent i=CrimePagerActivity.newIntent(getActivity(),mCrime.getId());

            startActivity(i);
        }
    }


    private class CrimeAdapter extends RecyclerView.Adapter<CrimeHolder>{
        private List<Crime> mCrimes;

        public CrimeAdapter(List<Crime> crimes){
            mCrimes=crimes;
        }

        @Override
        public CrimeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            Log.d(TAG,"onCreateViewHolder");

            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());

            View view=layoutInflater.inflate(R.layout.list_item_crime,parent,false);
            return new CrimeHolder(view);
        }

        @Override
        public void onBindViewHolder(CrimeHolder holder, int position) {

            Log.d(TAG,"onBindViewHolder  with position :"+position);

            Crime crime=mCrimes.get(position);
            holder.bindCrime(crime);
        }

        @Override
        public int getItemCount() {
            return mCrimes.size();
        }
    }
}
