package com.eduhelper.supereditor;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OpenFileFragment.OnOpenFileClick{
    private EditText filenameEditText, userEditText;
    private static final String TAG = "SOMETAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        filenameEditText = (EditText) findViewById(R.id.filenameEditText);
        userEditText = (EditText) findViewById(R.id.userEditText);
    }

    public void onNewFile(View view){
        filenameEditText.setText("");
        userEditText.setText("");
        Log.d(TAG, "New File T_T");
    }

    public void onOpenFile(View view){
        DialogFragment dialogFragment = new OpenFileFragment();
        dialogFragment.show(getFragmentManager(), "OpenFile");
    }

    public static List<String> filenames = new ArrayList<>();

    public void onSaveFile(View view){
        String filename = String.valueOf(filenameEditText.getText());

        try{
            FileOutputStream fos = openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(String.valueOf(userEditText).getBytes());
            fos.close();
            filenames.add(filename);
            Log.d(TAG, "File saved! WOW");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListClick(DialogFragment dialogFragment, int i) {
        try {
            InputStream inputStream = openFileInput(filenames.get(i));

            if (inputStream != null){
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                StringBuilder stringBuilder = new StringBuilder();

                while((line = bufferedReader.readLine()) != null){
                    stringBuilder.append(line + "\n");
                }

                filenameEditText.setText(filenames.get(i));
                userEditText.setText(stringBuilder.toString());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
