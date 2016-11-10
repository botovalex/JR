package com.javarush.test.level37.lesson04.big01.male;

import com.javarush.test.level37.lesson04.big01.AbstractFactory;
import com.javarush.test.level37.lesson04.big01.Human;

public class MaleFactory implements AbstractFactory{
    @Override
    public Human getPerson(int age) {
        if (age <= KidBoy.MAX_AGE) return new KidBoy();
        else if (age <= TeenBoy.MAX_AGE) return new TeenBoy();
        else return new Man();
    }
}

/*
Задание 2

1. Создай публичный класс MaleFactory с публичным методом getPerson, который принимает один параметр int age (возраст) и
возвращает нужный объект одного из классов, реализующих интерфейс Human.

2. Логика метода getPerson: используя константу MAX_AGE определи, какой класс соответствует переданному возрасту.
Например, age=4 соответствует мальчику (KidBoy), age=15 - подростку (TeenBoy), остальной возраст - взрослому мужчине.

3. Создай свой класс Solution, и добавь в метод main создание фабрики и вызови у нее метод getPerson три раза с такими параметрами: 99, 4, 15.
Выведи результат в консоль.
Вывод должен быть следующий:
Man{}
KidBoy{}
TeenBoy{}

Молодец, Фабрика готова!  Это паттерн Factory Method.
 */