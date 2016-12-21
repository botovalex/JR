package com.javarush.test.level33.lesson15.big01;

import com.javarush.test.level33.lesson15.big01.strategies.HashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.OurHashMapStorageStrategy;
import com.javarush.test.level33.lesson15.big01.strategies.StorageStrategy;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Solution {
    public static Set<Long> getIds(Shortener shortener, Set<String> strings) {
        Set<Long> ids = new HashSet<>();
        for (String s : strings) {
            ids.add(shortener.getId(s));
        }
        return ids;
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys) {
        Set<String> strings = new HashSet<>();
        for (Long key : keys) {
            strings.add(shortener.getString(key));
        }
        return strings;
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber) {
        Helper.printMessage(strategy.getClass().getSimpleName());
        Set<String> testStrings = new HashSet<>();
        for (int i = 0; i < elementsNumber; i++) {
            testStrings.add(Helper.generateRandomString());
        }
        Shortener shortener = new Shortener(strategy);

        Date beforeTestTime = new Date();
        Set<Long> ids = getIds(shortener, testStrings);
        Date afterTestTime = new Date();
        Helper.printMessage("Время, необходимое для отработки метода getIds = " + (afterTestTime.getTime() - beforeTestTime.getTime()));

        beforeTestTime = new Date();
        Set<String> strings = getStrings(shortener, ids);
        afterTestTime = new Date();
        Helper.printMessage("Время, необходимое для отработки метода getStrings = " + (afterTestTime.getTime() - beforeTestTime.getTime()));

        if (strings.retainAll(testStrings)) {
            Helper.printMessage("Тест не пройден.");
        } else {
            Helper.printMessage("Тест пройден.");
        }
    }

    public static void main(String[] args) {
        StorageStrategy hashMapStorageStrategy = new HashMapStorageStrategy();
        testStrategy(hashMapStorageStrategy, 10000);

        StorageStrategy ourHashMapStorageStrategy = new OurHashMapStorageStrategy();
        testStrategy(ourHashMapStorageStrategy, 10000);
    }
}

/*
6.2.3.1.	Выводить имя класса стратегии. Имя не должно включать имя пакета.
6.2.3.2.	Генерировать тестовое множество строк, используя Helper и заданное
количество элементов elementsNumber.
6.2.3.3.	Создавать объект типа Shortener, используя переданную стратегию.
6.2.3.4.	Замерять и выводить время необходимое для отработки метода getIds
для заданной стратегии и заданного множества элементов. Время
вывести в миллисекундах. При замере времени работы метода можно
пренебречь переключением процессора на другие потоки, временем,
которое тратится на сам вызов, возврат значений и вызов методов
получения времени (даты). Замер времени произведи с
использованием объектов типа Date.
6.2.3.5.	Замерять и выводить время необходимое для отработки метода
getStrings для заданной стратегии и полученного в предыдущем пункте
множества идентификаторов.
6.2.3.6.	Сравнивать одинаковое ли содержимое множества строк, которое было
сгенерировано и множества, которое было возвращено методом
getStrings. Если множества одинаковы, то выведи "Тест пройден.",
иначе "Тест не пройден.".
 */