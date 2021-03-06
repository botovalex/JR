package com.javarush.test.level18.lesson10.bonus03;

/* Прайсы 2
CrUD для таблицы внутри файла
Считать с консоли имя файла для операций CrUD
Программа запускается с одним из следующих наборов параметров:
-u id productName price quantity
-d id
Значения параметров:
где id - 8 символов
productName - название товара, 30 chars (60 bytes)
price - цена, 8 символов
quantity - количество, 4 символа
-u  - обновляет данные товара с заданным id
-d  - производит физическое удаление товара с заданным id (все данные, которые относятся к переданному id)

В файле данные хранятся в следующей последовательности (без разделяющих пробелов):
id productName price quantity
Данные дополнены пробелами до их длины

Пример:
19846   Шорты пляжные синие           159.00  12
198478  Шорты пляжные черные с рисунко173.00  17
19847983Куртка для сноубордистов, разм10173.991234
*/

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Solution {
    public static void main(String[] args) throws IOException {
        try {
            if (args[0] == null) return;
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите полное имя файла: ");
        String fileName = br.readLine();
        br.close();
        Path originFile = Paths.get(fileName);
        Path tempFile = Paths.get(originFile.getParent().toString(), "tempFile.txt");

        if (Files.exists(tempFile)) Files.delete(tempFile);
        Files.createFile(tempFile);
        BufferedReader reader = new BufferedReader(new FileReader(originFile.toFile()));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(tempFile.toFile()), Charset.forName("UTF-8")));

        switch (args[0]) {
            case "-u":
                while (reader.ready()) {
                    String line = reader.readLine();
                    if (line.substring(0, 8).trim().equals(args[1])) {
                        String id = args[1];
                        String productName = args[2].codePointCount(0, args[2].length()) > 30 ? args[2].substring(0, args[2].offsetByCodePoints(0, 30)) : args[2];
                        String price = args[3].codePointCount(0, args[3].length()) > 8 ? args[3].substring(0, args[3].offsetByCodePoints(0, 8)) : args[3];
                        String quantity = args[4].codePointCount(0, args[4].length()) > 4 ? args[4].substring(0, args[4].offsetByCodePoints(0, 4)) : args[4];
                        writer.write(String.format("%-8s%-30s%-8s%-4s", id, productName, price, quantity));
                        writer.newLine();
                    } else {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                reader.close();
                writer.close();
                Files.move(tempFile, originFile, REPLACE_EXISTING);
                break;
            case "-d":
                while (reader.ready()) {
                    String line = reader.readLine();
                    if (!line.substring(0, 8).trim().equals(args[1])) {
                        writer.write(line);
                        writer.newLine();
                    }
                }
                reader.close();
                writer.close();
                Files.move(tempFile, originFile, REPLACE_EXISTING);
                break;
        }
    }
}
