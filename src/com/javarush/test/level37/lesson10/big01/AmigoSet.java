package com.javarush.test.level37.lesson10.big01;

import java.io.*;
import java.util.*;

public class AmigoSet<E> extends AbstractSet<E> implements Cloneable, Serializable, Set<E> {
    private transient final static Object PRESENT = new Object();
    private transient HashMap<E, Object> map;

    public AmigoSet() {
        map = new HashMap<>();
    }

    public AmigoSet(Collection<? extends E> collection) {
        map = new HashMap<>((int) Math.round(Math.max(16, collection.size() / .75f)));
        addAll(collection);
    }

    @Override
    public boolean add(E e) {
        return null == map.put(e, PRESENT);
    }

    @Override
    public Iterator<E> iterator() {
        return map.keySet().iterator();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean remove(Object o) {
        return null != map.remove(o);
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    public boolean contains(Object o) {
        return map.containsKey(o);
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public Object clone() {
        AmigoSet<E> newAmigoSet = new AmigoSet<>();
        try {
            newAmigoSet.addAll(this);
            newAmigoSet.map.putAll(this.map);
        } catch (Exception e) {
            throw new InternalError();
        }
        return newAmigoSet;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
//        int capacity = HashMapReflectionHelper.callHiddenMethod(map, "capacity");
//        float loadFactor = HashMapReflectionHelper.callHiddenMethod(map, "loadFactor");

//        s.writeInt(capacity);
//        s.writeFloat(loadFactor);
        s.writeInt(map.size());
        for (E e : map.keySet()) {
            s.writeObject(e);
        }
    }


    private void readObject(ObjectInputStream s) throws IOException, ClassNotFoundException {
        int capacity = s.readInt();
        float loadFactor = s.readFloat();
        map = new HashMap<>(capacity, loadFactor);

        int size = s.readInt();
        for (int i = 0; i < size; i++) {
            map.put((E) s.readObject(), PRESENT);
        }

    }

}

/*
1. Реализуй свою логику сериализации и десериализации.
Вспоминай, какие именно приватные методы нужно добавить, чтоб сериализация пошла по твоему сценарию.
Для сериализации:
* сериализуй сет
* сериализуй capacity и loadFactor у объекта map, они понадобятся для десериализации.
 Т.к. эти данные ограничены пакетом, то воспользуйся утилитным классом HashMapReflectionHelper, чтобы достать их.

Для десериализации:
* вычитай все данные
* создай мапу используя конструктор с capacity и loadFactor

2. Помнишь, что такое transient?
 */