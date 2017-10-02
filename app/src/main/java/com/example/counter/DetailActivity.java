package com.example.counter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private EditText name,currentValue,initValue,comment;


    /**
     *  Called when the activity is created
     *  Bind the values got from intent to the views
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // find views
        name = (EditText) findViewById(R.id.detail_edit_name);
        currentValue = (EditText) findViewById(R.id.detail_edit_current_value);
        initValue= (EditText) findViewById(R.id.detail_edit_init_value);
        comment = (EditText) findViewById(R.id.detail_edit_comment);

        // bind values from intent to views
        Intent intent = getIntent();
        name.setText(intent.getStringExtra("name"));
        currentValue.setText(intent.getStringExtra("currentValue"));
        initValue.setText(intent.getStringExtra("initialValue"));
        comment.setText(intent.getStringExtra("comment"));
        ((TextView)findViewById(R.id.detail_date)).setText(intent.getStringExtra("date"));

    }

    /**
     * Called when the confirm button is pressed
     * finish the current activity, put the changed data from views into intent
     * MainActivity would receive these data at method onActivityResult
     * @param v
     */
    public void onConfirmClick(View v){
        Intent intent = new Intent();
        intent.putExtra("name",name.getText().toString());
        intent.putExtra("currentValue",currentValue.getText().toString());
        intent.putExtra("initialValue",initValue.getText().toString());
        intent.putExtra("comment",comment.getText().toString());
        setResult(Activity.RESULT_OK,intent);
        finish();;
    }
}
