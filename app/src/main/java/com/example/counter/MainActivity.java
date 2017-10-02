package com.example.counter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView viewList;
    private static final String FILENAME = "save.sav";
    private ArrayList<Counter> itemList;
    private ArrayAdapter<Counter> adapter;
    public Button add_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadFromFile();

        viewList = (ListView) findViewById(R.id.viewList);
        add_button = (Button) findViewById(R.id.add_button);
        adapter = new ArrayAdapter<Counter>(this,
                R.layout.list_item, itemList);
        viewList.setAdapter(adapter);

        //add button, open new window.//
        add_button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                Intent toy = new Intent(MainActivity.this, new_window.class);
                startActivityForResult(toy, 1);
            }
      });

    }





    /**
     * Loads tweets from file.
     * @throws RuntimeException if IOException e happens
     * @exception FileNotFoundException if the file is not created
     */
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();
            // Taken from https://stackoverflow.com/question/12384064/gson-convert-from-json-into java
            // 2017 01-26 17:53:59
            itemList = gson.fromJson(in, new TypeToken<ArrayList<Counter>>(){}.getType());
            fis.close();
        } catch (FileNotFoundException e) {
            itemList = new ArrayList<Counter>();
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Saves tweets in file in JSON format.
     * @throws FileNotFoundException if folder not exists
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(itemList, out);
            out.flush();
            fos.close();

        } catch (FileNotFoundException e) {
            throw new RuntimeException();
        } catch (IOException e) {
            throw new RuntimeException();
        }

    }

    //@Override
    //protected void onStart() {
        //super.onStart();

        //adapter = new ArrayAdapter<Counter>(this,
                //R.layout.list_item, itemAdapter);
        //oldItemAdapter.setAdapter(adapter);
}
