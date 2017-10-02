package com.example.counter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by WTH on 2017-10-01.
 */

class Counter {
    private Date dateTime;
    private String counterName,comment;
    private int initValue,currentValue;


    /**
     * Constructor
     * @param counterName
     * @param comment
     * @param initValue
     */
    Counter(String counterName, String comment, int initValue) {
        this.counterName = counterName;
        this.comment = comment;
        this.initValue = initValue;
        this.currentValue = initValue;
        this.dateTime = new Date();
    }

    /**
     * Getter of counterName
     * @return String counterName
     */
    String getCounterName(){
        return counterName;
    }

    /**
     * Getter of currentValue, converted to string
     * @return String currentValue
     */
    String getCurrentValue(){
        return ""+currentValue;
    }

    /**
     * Getter of dateTime, converted to string
     * @return String date in form "yyyy-MM-dd"
     */
    String getDate(){
        return new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA).format(this.dateTime);
    }

    /**
     * Getter of initValue, converted to string
     * @return String initValue
     */
    String getInitValue(){
        return ""+initValue;
    }

    /**
     * Getter of comment
     * @return String comment
     */
    String getComment(){
        return  comment;
    }

    /**
     * Increment current value and update date
     */
    void increment(){
        this.currentValue += 1;
        this.dateTime = new Date();
    }

    /**
     * Decrement current value and update date
     */
    void decrement(){
        if(this.currentValue > 0){
            this.currentValue -= 1;
            this.dateTime = new Date();
        }
    }

    /**
     *  Reset current value to initial value, and update date
     */
    void reset(){
        this.currentValue = this.initValue;
        this.dateTime = new Date();
    }

    /**
     * Edit name,current value,initial value and comment at once
     * Update date if current value has changed
     * All arguments in string form
     * @param name
     * @param currentValueString
     * @param initialString
     * @param comment
     */
    void edit(String name,String currentValueString,String initialString,String comment){
        this.counterName = name;
        int value = Integer.parseInt(currentValueString);
        if(value != this.currentValue){
            this.currentValue = value;
            this.dateTime = new Date();
        }
        this.initValue = Integer.parseInt(initialString);
        this.comment = comment;
    }

}
