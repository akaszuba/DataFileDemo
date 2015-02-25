package com.example.akaszuba.datafiledemo;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by akaszuba on 2/25/2015.
 */
public class DataAccess {
    private Context context;

    public DataAccess(Context ctx){
        context = ctx;
    }

    String FILENAME = "DataFile";

    public void SaveToFile(String text){

        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (Exception e) {

        } finally {

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String ReadFromFile(){
        StringBuffer buffer = new StringBuffer("");
        try {
            FileInputStream fIn = context.openFileInput(FILENAME) ;
            InputStreamReader isr = new InputStreamReader ( fIn ) ;
            BufferedReader buffreader = new BufferedReader ( isr ) ;

            String readString = buffreader.readLine ( ) ;
            while ( readString != null ) {
                buffer.append(readString);
                readString = buffreader.readLine ( ) ;
            }

            isr.close ( ) ;
        } catch ( IOException ioe ) {
            ioe.printStackTrace ( ) ;
        }
        return buffer.toString() ;
    }



    public DataObject getFromJson(){
        DataObject original = new DataObject();
        original.setName("something");
        original.setQuantity(250);



        Gson gs = new GsonBuilder().create();

        String jsonToParse = gs.toJson(original);

        DataObject dObj = gs.fromJson(jsonToParse , DataObject.class);

        return dObj;

    }
}
