package ru.eremin;

import java.util.Arrays;


public class Main {
    public static void main(String[] args) {
        MyOwnHashMap<String, String> map = new MyOwnHashMap<>();

        String[] keys = new String[55];
        for (int i = 0; i < keys.length; i++) {
            keys[i] = "Hello" + i;
        }
        for (int i = 0; i < keys.length; i++) {
            map.put(keys[i], "value" + i);
        }
        System.out.println(map);

        String[] keysForRemove = Arrays.copyOfRange(keys, 10, 30);
        for (String s : keysForRemove) {
            map.remove(s);
        }
        System.out.println(map);

        for (String key : keys) {
            System.out.println(map.get(key));
        }
    }
}
