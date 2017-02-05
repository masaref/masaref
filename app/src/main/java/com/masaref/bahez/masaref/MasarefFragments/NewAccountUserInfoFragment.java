package com.masaref.bahez.masaref.MasarefFragments;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.masaref.bahez.masaref.R;

/**
 * Fragment Name: NewAccountUserInfoFragment
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 02.05.2017
 * Purpose: This Fragment collects user's bio information.
 * Additionally, this Fragment is used by SignUpActivity.
 */

public class NewAccountUserInfoFragment extends Fragment {

    public NewAccountUserInfoFragment() {
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
        return inflater.inflate(R.layout.fragment_new_account_user_info, container, false);
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
