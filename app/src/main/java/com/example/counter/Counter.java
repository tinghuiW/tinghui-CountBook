package com.example.counter;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by WTH on 2017-10-01.
 */

public class Counter {
    private Date dateTime;
    private String countThing;
    private String countSum;
    private int initValue;
    private int currentValue;

    public Counter(Date dateTime, String countThing, String countSum, int initValue, int currentValue) {
        this.dateTime = dateTime;
        this.countThing = countThing;
        this.countSum = countSum;
        this.initValue = initValue;
        this.currentValue = currentValue;
    }

    /**
     * getter
     */

    public Date getDateTime() {
        return dateTime;
    }

    public String getCountThing() {
        return countThing;
    }

    public String getCountSum() {
        return countSum;
    }

    public Integer getInitValue() {
        return initValue;
    }

    public Integer getCurrentValue() {
        return currentValue;
    }
    /**
     * setter
     */

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public void setCountThing(String countThing) {
        this.countThing = countThing;
    }

    public void setCountSum(String countSum) {
        this.countSum = countSum;
    }

    public void setInitValue(Integer initValue) {
        this.initValue = initValue;
    }

    public void setCurrentValue(Integer currentValue) {
        this.currentValue = currentValue;
    }
    /**
     * update time
     */

    public void increment(){
        this.currentValue += 1;
        Calendar cal = Calendar.getInstance();
        this.dateTime = cal.getTime();
    }

    public void decrement(){
        if(this.currentValue <= 0){
            initValue = 0;
        }
        else{
            this.currentValue -= 1;
            Calendar cal = Calendar.getInstance();
            this.dateTime = cal.getTime();
        }
    }

    public void reset(){
        this.currentValue = this.initValue;
        Calendar cal = Calendar.getInstance();
        this.dateTime = cal.getTime();
    }

    public String toString(){
        //SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return   this.countThing + this.currentValue + this.countSum;
    }
}
