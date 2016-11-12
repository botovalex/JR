package com.javarush.test.level18.lesson10.bonus03;

public class test {
    public static void main(String[] args) {
        String line = "19846   Шорты пляжные синие           159.00  12";
        String line2 = "19847983Куртка для сноубордистов, разм10173.991234";
        String result = String.format("%-8s%-30s%-8s%-4s", "19846", "Шорты пляжные синие", "159.00", "12");
        System.out.println(result);
    }
}
