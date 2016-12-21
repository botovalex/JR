package com.javarush.test.level33.lesson15.big01.strategies;

import com.javarush.test.level33.lesson15.big01.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            path = Files.createTempFile(null, null);
            Files.deleteIfExists(path);
            Files.createFile(path);
            path.toFile().deleteOnExit();
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }

    public long getFileSize() {
        long size = 0L;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }

        return size;
    }

    public void putEntry(Entry entry) {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path.toFile()))) {
            oos.writeObject(entry);
        } catch (FileNotFoundException e) {
            ExceptionHandler.log(e);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }

    }

    public Entry getEntry() {
        if (getFileSize() == 0) return null;
        Entry entry = null;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path.toFile()))) {
            entry = (Entry) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            ExceptionHandler.log(e);
        }

        return entry;
    }

    public void remove() {
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}

/*
9.4.	Добавь в класс методы:
9.4.1.	long getFileSize(), он должен возвращать размер файла на который
указывает path.
9.4.2.	void putEntry(Entry entry) - должен сериализовывать переданный entry в
файл. Учти, каждый entry может содержать еще один entry.
9.4.3.	Entry getEntry() - должен забирать entry из файла. Если файл имеет нулевой
размер, вернуть null.
9.4.4.	void remove() – удалять файл на который указывает path.
Конструктор и методы не должны кидать исключения.
 */