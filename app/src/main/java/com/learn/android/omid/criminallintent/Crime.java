package com.learn.android.omid.criminallintent;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;

/**
 * Created by User on 8/13/2017.
 */
public class Crime {
    private UUID mId;
    private String mTiltle;
    private Date mDate;
    private boolean mIsSolved;


    public Crime(){
        mId=UUID.randomUUID();
        mDate=new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTiltle() {
        return mTiltle;
    }

    public void setTiltle(String tiltle) {
        mTiltle = tiltle;
    }


    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isSolved() {
        return mIsSolved;
    }

    public void setSolved(boolean solved) {
        mIsSolved = solved;
    }
}
