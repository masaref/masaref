package com.masaref.bahez.masaref.MasarefFragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.masaref.bahez.masaref.MasarefCustomAdapters.CustomSpinnerAdapter;
import com.masaref.bahez.masaref.R;

/**
 * Fragment Name: TransferFragment
 * Created by: Mohammad Rafi Bahez
 * Last Edited: 02.05.2017
 * Purpose: Transfer provides users the ability to transfer money from one account to another.
 * Additionally, this Fragment is used by OperationsActivity.
 */

public class TransferFragment extends Fragment {

    // GUI References
    // Spinners
    Spinner spinnerOfTransferCardFrom;
    Spinner spinnerOfTransferCardTo;


    // Content Resolver
    ContentResolver resolver;

    // URI
    static final Uri URI_TABLE_CARD = Uri.parse("content://com.masaref.bahez.masaref.MasarefContentProvider.MasarefContentProvider/tableCard");



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
        // Create a view
        View inflatedView = inflater.inflate(R.layout.fragment_transfer, container, false);

        // GUI Spinner
        spinnerOfTransferCardFrom = (Spinner) inflatedView.findViewById(R.id.spn_fragment_transfer_card_type_from);
        spinnerOfTransferCardTo = (Spinner) inflatedView.findViewById(R.id.spn_fragment_transfer_card_type_to);

        // Initialized resolver
        resolver = getContext().getContentResolver();


        setViewForSpinnerTransferCardFrom();
        setViewForSpinnerTransferCardTo();


        // Inflate the layout for this fragment
        return inflatedView;

    }

    public void setViewForSpinnerTransferCardFrom() {

        // Get Card Value from DB.
        int size, counter = 0;
        String cardItemsName []={};
        Integer cardItemsIcon []={};

        try{
            String [] projectionFromCard = {"card_name" , "card_icon"};
            Cursor cursor = resolver.query(URI_TABLE_CARD, projectionFromCard, null, null, null);



            if(cursor.moveToFirst()) {
                size = cursor.getCount();
                cardItemsName = new String[size];
                cardItemsIcon = new Integer[size];

                do {
                    cardItemsName[counter] = cursor.getString(cursor.getColumnIndex("card_name"));
                    cardItemsIcon[counter] = cursor.getInt(cursor.getColumnIndex("card_icon"));
                    counter++;
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Database Record Missing" , Toast.LENGTH_SHORT).show();
        }

        // Create adapter for Card types
        CustomSpinnerAdapter adapterForCardType = new CustomSpinnerAdapter(getActivity(), cardItemsName, cardItemsIcon);
        spinnerOfTransferCardFrom.setAdapter(adapterForCardType);

        adapterForCardType.notifyDataSetChanged();

    }

    public void setViewForSpinnerTransferCardTo() {

        // Get Card Value from DB.
        int size, counter = 0;
        String cardItemsName []={};
        Integer cardItemsIcon []={};

        try{
            String [] projectionFromCard = {"card_name" , "card_icon"};
            Cursor cursor = resolver.query(URI_TABLE_CARD, projectionFromCard, null, null, null);



            if(cursor.moveToFirst()) {
                size = cursor.getCount();
                cardItemsName = new String[size];
                cardItemsIcon = new Integer[size];

                do {
                    cardItemsName[counter] = cursor.getString(cursor.getColumnIndex("card_name"));
                    cardItemsIcon[counter] = cursor.getInt(cursor.getColumnIndex("card_icon"));
                    counter++;
                } while (cursor.moveToNext());

            }
        }catch (Exception e){
            Toast.makeText(getContext(), "Database Record Missing" , Toast.LENGTH_SHORT).show();
        }

        // Create adapter for Card types
        CustomSpinnerAdapter adapterForCardType = new CustomSpinnerAdapter(getActivity(), cardItemsName, cardItemsIcon);
        spinnerOfTransferCardTo.setAdapter(adapterForCardType);

        adapterForCardType.notifyDataSetChanged();

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
