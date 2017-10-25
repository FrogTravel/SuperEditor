package com.eduhelper.supereditor;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

/**
 * Created by ekaterina on 10/25/17.
 */

public class OpenFileFragment extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        super.onCreateDialog(savedInstanceState);

        String[] filenames = new String[MainActivity.filenames.size()];
        filenames = MainActivity.filenames.toArray(filenames);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(filenames, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                mListener.onListClick(OpenFileFragment.this, i);
            }

        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        try{
            mListener = (OnOpenFileClick) context;
        } catch (ClassCastException e){
            e.printStackTrace();
        }
    }

    OnOpenFileClick mListener;

    public interface OnOpenFileClick{
        void onListClick(DialogFragment dialogFragment, int i);
    }
}
