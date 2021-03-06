/*package com.javarush.test.level33.lesson05.home03;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
*/
/* Десериализация JSON объекта
НЕОБХОДИМО: подключенные библиотеки Jackson Core, Bind и Annotation версии 2.6.1

В метод convertFromJsonToNormal первым параметром приходит имя файла, который содержит один ДЖЕЙСОН объект.
Вторым параметром приходит имя класса, объект которого находится в файле.
Метод convertFromJsonToNormal должен вычитать объект из файла, преобразовать его из JSON и вернуть его.
*/
/*
public class Solution {

    public static <T> T convertFromJsonToNormal(String fileName, Class<T> clazz) throws IOException {
        InputStream reader = new FileInputStream(fileName);
        ObjectMapper mapper = new ObjectMapper();
        return (T) mapper.readValue(reader, clazz);
    }

    public static void main(String[] args) throws IOException
    {
        Cat cat = convertFromJsonToNormal("d:\\1\\cat.txt", Cat.class);
        System.out.println(cat);
    }

    @JsonAutoDetect
    public static class Cat{
        public String name;
        public int age;

        Cat(){}

        @Override
        public String toString()
        {
            return "Cat{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
*/