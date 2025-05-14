package ru.eremin;

import java.util.ArrayList;
import java.util.List;


public class MyOwnHashMap {
    private final String[] array = new String[16];

    public String get(Integer key) {
        return array[key];
    }


    public void put(Integer key, String value) {
        array[key] = value;
    }


    public void remove(Integer key) {
        array[key] = null;

    }
}
