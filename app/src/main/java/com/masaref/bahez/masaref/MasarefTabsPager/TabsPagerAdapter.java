package com.masaref.bahez.masaref.MasarefTabsPager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.masaref.bahez.masaref.MasarefFragments.ExpenseFragment;
import com.masaref.bahez.masaref.MasarefFragments.IncomeFragment;
import com.masaref.bahez.masaref.MasarefFragments.TransferFragment;

/**
 * Class Name: TabsPagerAdapter
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 2.2.2017
 * Purpose: TabsPagerAdapter class is to define three fragments for the tabs activity
 * Additionally, this class uses Fragments to display in three tabs.
 */
public class TabsPagerAdapter extends FragmentPagerAdapter{
    public TabsPagerAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new ExpenseFragment();
            case 1:
                return new IncomeFragment();
            case 2:
                return new TransferFragment();
        }
        return null;
    }

    @Override
    public int getCount()
    {
        return 3;
    }

    public CharSequence getPageTitle(int position){
        switch (position){
            case 0:
                return "expense";
            case 1:
                return "income";
            case 2:
                return "Transfer";
            default:
                return null;
        }
    }
}
