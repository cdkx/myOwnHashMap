package ru.eremin;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        MyOwnHashMap map = new MyOwnHashMap();
        map.put(1,"vasya");

        System.out.println(map.get(1));
    }
}
