package ru.job4j.pool;

import java.util.ArrayList;

public class AskingElement {

    private static ArrayList<Object> array;

    public AskingElement(ArrayList<Object> array) {
        AskingElement.array = array;
    }

    public static int ask(Object el) {
        int res = 0;
        for (Object o : array) {
            if (o.equals(el)) {
                res =  array.indexOf(o);
            }
        }
        return res;
    }
}
