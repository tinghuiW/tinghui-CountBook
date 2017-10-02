package com.example.counter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;


public class MainActivity extends Activity {


    private ArrayList<Counter> counters;
    private static final String FILENAME = "countBook.sav";
    private CounterArrayAdapter adapter;
    /**
     * Called when the activity is first created.
     * load the data from files
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counterlist);
        loadFromFile();

    }

    /**
     * Called when the activity is started
     * bind the adapter to the list view
     */
    @Override
    protected void onStart() {
        super.onStart();

        adapter = new CounterArrayAdapter(this,counters,(TextView) findViewById(R.id.counters_size));
        ((ListView)findViewById(R.id.listview_counters)).setAdapter(adapter);
    }

    /**
     *  Called when the activity is paused
     *  save data into the file
     */
    @Override
    protected void onPause() {
        super.onPause();
        saveInFile();
    }

    /**
     * Called when back from DetailActivity
     * Receive the result and call Counter.edit to change data
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK){
            Counter counter = counters.get(requestCode);
            counter.edit(data.getStringExtra("name"),data.getStringExtra("currentValue"),
                    data.getStringExtra("initialValue"),data.getStringExtra("comment"));
        }
    }

    /**
     * Load the file that storing the counters with Gson
     */
    private void loadFromFile() {
        try {

            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();
            Type listType = new TypeToken<ArrayList<Counter>>() {}.getType();
            counters = gson.fromJson(in, listType);
        } catch (FileNotFoundException e) {
            counters = new ArrayList<Counter>();
        }
    }

    /**
     *  Save the counters in Gson format into the file
     */
    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    Context.MODE_PRIVATE);

            OutputStreamWriter writer = new OutputStreamWriter(fos);
            Gson gson = new Gson();
            gson.toJson(counters, writer);
            writer.flush();
            fos.close();
        }  catch (IOException e) {
            throw new RuntimeException();
        }
    }

    /**
     * Called when the add new button is pressed
     * Collected data from views and create a new counter
     * @param v
     */
    public void onAddClick(View v) {
        // find views
        EditText editName = findViewById(R.id.edit_name);
        EditText editInitial = findViewById(R.id.edit_intial);
        EditText editComment = findViewById(R.id.edit_comment);

        // collect data from name view, default is "Counter{size of array list}"
        String name = editName.getText().toString();
        if (name.equals("")){
            name = "Counter" + counters.size();
        } else {
            editName.setText("");
        }

        // collect data from init value view, default is 0
        String stringInitial = editInitial.getText().toString();
        int initialValue;
        if (stringInitial.equals("")){
            initialValue = 0;
        } else {
            initialValue = Integer.parseInt(stringInitial);
            editInitial.setText("");
        }

        // collect data from comment view, default is ""
        String comment = editComment.getText().toString();
        editComment.setText("");

        // create new counter with data, and add into array list
        counters.add(new Counter(name,comment,initialValue));
        adapter.notifyDataSetChanged();
    }

}
