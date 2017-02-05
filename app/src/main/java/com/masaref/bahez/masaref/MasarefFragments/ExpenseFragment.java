package com.masaref.bahez.masaref.MasarefFragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masaref.bahez.masaref.R;

/**
 * Fragment Name: ExpenseFragment
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 02.05.2017
 * Purpose: ExpenseFragment provides users the Expense category and user can withdraw from an account.
 * Additionally, this Fragment is used by OperationsActivity.
 */

public class ExpenseFragment extends Fragment {

    public ExpenseFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_expense, container, false);
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}
