package com.javarush.test.level18.lesson10.bonus03;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class test {
    public static void main(String[] args) throws FileNotFoundException {
        String line = "19846   Шорты пляжные синие           159.00  12";
        String line2 = "19847983Куртка для сноубордистов, разм10173.991234";
        String result = String.format("%-8s%-30s%-8s%-4s", "19846", "красивое розовое платице для любимой Лисеньки", "159.00", "12");
        System.out.println(result);
        System.out.println();

        Path originFile = Paths.get("d:\\1\\1.txt");
        System.out.println();
    }
}


/*
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите полное имя файла: ");
        String fileName = br.readLine();
        br.close();
        Path originFile = Paths.get(fileName);
        Path tempFile = Paths.get(originFile.getParent().toString(), "tempFile.txt");

        if (Files.exists(tempFile)) Files.delete(tempFile);
        Files.createFile(tempFile);
        BufferedReader reader = new BufferedReader(new FileReader(originFile.toFile()));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile.toFile()));
 */