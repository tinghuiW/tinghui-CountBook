package com.example.counter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;
import java.util.Date;

public class new_window extends MainActivity {
    private EditText itemText;
    private EditText commentText;
    private EditText valueText;
    private EditText dateText;
    private Date Dated;

    @Override
    /**
     * Creates the Activity when called from MainActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_window);
        final Calendar c = Calendar.getInstance();

        Button saveButton = (Button) findViewById(R.id.button3);


        itemText = (EditText) findViewById(R.id.editText);
        valueText = (EditText) findViewById(R.id.editText2);
        commentText = (EditText) findViewById(R.id.comment);


        saveButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);

                String name_text = itemText.getText().toString();
                String date_text = dateText.getText().toString();
                String comment_text = commentText.getText().toString();
                String value_text = valueText.getText().toString();

                Counter info = new Counter(name_text, comment_text, value_text);

            }
        });

    }

}


