package com.example.counter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;
import java.util.ArrayList;

class CounterArrayAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<Counter> counters;
    private Context context;
    private TextView sizeView;

    /**
     * Constructor
     * @param context is the MainActivity
     * @param counters is the array list of counters
     * @param sizeView is the view records the size of array list
     */
    CounterArrayAdapter(Context context,ArrayList<Counter> counters,TextView sizeView) {
        this.context = context;
        this.counters = counters;
        this.sizeView = sizeView;
    }

    /**
     * Get the item at position i in array list
     * @param i
     * @return counter
     */
    @Override
    public Counter getItem(int i) {
        return counters.get(i);
    }

    /**
     *  Get ID, return 0 since counters have no ID
     *  Just for implement interface
     * @param i
     * @return 0
     */
    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Get the size of array list
     * @return size
     */
    @Override
    public int getCount() {
        return counters.size();
    }

    /**
     * Get the rendered view of list
     * @param i
     * @param view
     * @param parent
     * @return view
     */
    @Override
    public View getView(final int i, View view, ViewGroup parent) {
        // if view is null, inflate it from layout
        if (view == null){
            view = LayoutInflater.from(this.context).inflate(R.layout.list_item, parent, false);
        }

        // Get the counter at position, bind its data on views
        final Counter counter = getItem(i);
        ((TextView)view.findViewById(R.id.list_name)).setText(counter.getCounterName());
        ((TextView)view.findViewById(R.id.list_date)).setText(counter.getDate());
        final TextView currentValue = view.findViewById(R.id.list_value);
        currentValue.setText(counter.getCurrentValue());
        String size = ""+getCount();
        sizeView.setText(size);

        // onclick for the whole view, goto DetailActivity for edit
        // put all data of current counter into intent
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailActivity.class);
                intent.putExtra("name",counter.getCounterName());
                intent.putExtra("currentValue",counter.getCurrentValue());
                intent.putExtra("initialValue",counter.getInitValue());
                intent.putExtra("date",counter.getDate());
                intent.putExtra("comment",counter.getComment());
                ((Activity)context).startActivityForResult(intent,i);
            }
        });

        // onclick for increment button, call corresponding method of Counter class
        view.findViewById(R.id.btn_increment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.increment();
                notifyDataSetChanged();
            }
        });

        // onclick for decrement button, call corresponding method of Counter class
        view.findViewById(R.id.btn_decrement).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.decrement();
                notifyDataSetChanged();
            }
        });

        // onclick for reset button, call corresponding method of Counter class
        view.findViewById(R.id.btn_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter.reset();
                notifyDataSetChanged();
            }
        });

        // onclick for delete button, remove the current Counter form array list
        view.findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counters.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    /**
     *  Update the view records array list size at the same time as the list view
     */
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        String size = ""+getCount();
        sizeView.setText(size);
    }
}
