package com.javarush.test.level19.lesson10.bonus01;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* Отслеживаем изменения
Считать в консоли 2 имени файла - file1, file2.
Файлы содержат строки, file2 является обновленной версией file1, часть строк совпадают.
Нужно создать объединенную версию строк, записать их в список lines
Операции ADDED и REMOVED не могут идти подряд, они всегда разделены SAME
Пример:
оригинальный   редактированный    общий
file1:         file2:             результат:(lines)

строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
строка4                           REMOVED строка4
строка5        строка5            SAME строка5
               строка0            ADDED строка0
строка1        строка1            SAME строка1
строка2                           REMOVED строка2
строка3        строка3            SAME строка3
               строка5            ADDED строка5
строка4        строка4            SAME строка4
строка5                           REMOVED строка5
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите полное имя первого файла:");
        String file1 = br.readLine();
        System.out.println("Введите полное имя второго файла:");
        String file2 = br.readLine();

        ArrayList<String> file1List = new ArrayList<>();
        ArrayList<String> file2List = new ArrayList<>();

        BufferedReader reader = new BufferedReader(new FileReader(file1));
        String line;
        while (reader.ready()) {
            if ((line = reader.readLine()) != null) file1List.add(line);
        }
        reader.close();

        reader = new BufferedReader(new FileReader(file2));
        while (reader.ready()) {
            if ((line = reader.readLine()) != null) file2List.add(line);
        }
        reader.close();

        for (int i = 0; i < file1List.size(); i++) {
            if (file2List.size() == 0) {
                lines.add(new LineItem(Type.REMOVED, file1List.get(i)));
                break;
            }
            if (file1List.get(i).equals(file2List.get(0))) {
                lines.add(new LineItem(Type.SAME, file1List.get(i)));
                file2List.remove(0);
            } else {
                if (file1List.get(i).equals(file2List.get(1))) {
                    lines.add(new LineItem(Type.ADDED, file2List.get(0)));
                    i--;
                    file2List.remove(0);
                } else {
                    lines.add(new LineItem(Type.REMOVED, file1List.get(i)));
                }
            }
        }
        if (file2List.size() != 0) {
            lines.add(new LineItem(Type.ADDED, file2List.get(0)));
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}


/*
        Iterator<String> it1 = file1List.iterator();
        Iterator<String> it2 = file2List.iterator();

        String lineFile1 = it1.hasNext() ? it1.next() : null;
        String lineFile2 = it2.hasNext() ? it2.next() : null;
        String temp1;
        String temp2;

        while (true) {
            if (lineFile2 != null && (lineFile1 != null ? lineFile1 : "").equals(lineFile2)) {
                LineItem item = new LineItem(Type.SAME, lineFile1);
                lines.add(item);
                lineFile1 = it1.hasNext() ? it1.next() : null;
                lineFile2 = it2.hasNext() ? it2.next() : null;
            } else {
//                temp1 = it1.hasNext() ? it1.next() : null;
                temp2 = it2.hasNext() ? it2.next() : null;
                if (lineFile2 != null && (temp2 != null ? temp2 : "").equals(lineFile1)) {
                    LineItem item = new LineItem(Type.REMOVED, lineFile1);
                    lines.add(item);
                    System.out.println(item.type + " " + item.line);
                    lineFile1 = temp1;
                } else {
                    LineItem item = new LineItem(Type.ADDED, lineFile2);
                    lines.add(item);
                    System.out.println(item.type + " " + item.line);
                    LineItem item1 = new LineItem(Type.SAME, lineFile1);
                    if (temp1 != null) lines.add(item1);
                    System.out.println(item1.type + " " + item1.line);
                    lineFile1 = temp1;
                    if (it2.hasNext()) it2.next();
                    lineFile2 = it2.hasNext() ? it2.next() : null;
                }

            }

            if (lineFile1 == null && lineFile2 == null) break;
        }
 */

