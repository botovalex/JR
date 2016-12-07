package test;

import java.util.ArrayList;
import java.util.Collection;

public class C {
    public static void main(String[] args) {
        ArrayList lst = new ArrayList();
        Collection col = lst;
        lst.add("one");
        lst.add("two");
        lst.add("three");
        System.out.println(lst.remove(0));;
        System.out.println(col.remove(0));;
        System.out.println("N = " + lst.size());
        System.out.println("N = " + col.size());
    }
}
