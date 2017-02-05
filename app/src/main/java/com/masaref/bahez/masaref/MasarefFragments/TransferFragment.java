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
 * Fragment Name: TransferFragment
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 02.05.2017
 * Purpose: Transfer provides users the ability to transfer money from one account to another.
 * Additionally, this Fragment is used by OperationsActivity.
 */

public class TransferFragment extends Fragment {

    public TransferFragment() {
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
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    public void onButtonPressed(Uri uri) {

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
