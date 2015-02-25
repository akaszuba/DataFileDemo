package com.example.akaszuba.datafiledemo;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class MainActivity extends Activity {

    private DataAccess dataAccess;
    private TextView txtFileContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        txtFileContent = (TextView)findViewById(R.id.txtFileContent);
        dataAccess = new DataAccess(this);
    }

    public void onSaveClicked(View view){
        String textToSave = txtFileContent.getText().toString();

        dataAccess.SaveToFile(textToSave);
    }

    public void onReadClicked(View view){

        txtFileContent.setText(dataAccess.ReadFromFile());
    }

    public void onClearClicked(View view) {
        txtFileContent.setText("");
    }

    public void onTestJson(View view){

        DataObject dataObj  = dataAccess.getFromJson();

        if(dataObj != null){
            txtFileContent.setText(String.format("%s - %d",dataObj.getName(),dataObj.getQuantity()));
        }
    }

}
