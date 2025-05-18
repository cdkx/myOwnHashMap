package ru.eremin;


public class Main {
    public static void main(String[] args) {
        MyOwnHashMap<String, String> map = new MyOwnHashMap<>();

        var key1 = "Hello1";
        var key2 = "Hello2";
        var key3 = "Hello3";
        var key4 = "Hello4";


        map.put(key1, "vasya");
        map.put(key2, "petya");
        map.put(key3, "grisha");
        map.put(key4, "serega");


        System.out.println(map.get(key1));
        System.out.println(map.get(key2));
        System.out.println(map.get(key3));
        System.out.println(map.get(key4));

        map.remove(key3);
        System.out.println(map.get(key3));

    }
}
