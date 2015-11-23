package com.example.imohomed.test1;

/**
 * Created by i.mohomed on 11/23/15.
 */
public class Item {
    public long id;
    public String text;
    public String priority;

    public Item(){
        id = -1;
        text = "";
        priority = "Normal";
    }

    @Override
    public String toString() {
        return text;
    }
}
