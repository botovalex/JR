package com.javarush.test.level25.lesson02.home01;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public enum Column implements Columnable {
    Customer("Customer"),
    BankName("Bank Name"),
    AccountNumber("Account Number"),
    Amount("Available Amount");

    private String columnName;

    private static int[] realOrder;

    private Column(String columnName) {
        this.columnName = columnName;
    }

    /**
     * Задает новый порядок отображения колонок, который хранится в массиве realOrder.
     * realOrder[индекс в энуме] = порядок отображения; -1, если колонка не отображается.
     *
     * @param newOrder новая последовательность колонок, в которой они будут отображаться в таблице
     * @throws IllegalArgumentException при дубликате колонки
     */
    public static void configureColumns(Column... newOrder) {
        realOrder = new int[values().length];
        for (Column column : values()) {
            realOrder[column.ordinal()] = -1;
            boolean isFound = false;

            for (int i = 0; i < newOrder.length; i++) {
                if (column == newOrder[i]) {
                    if (isFound) {
                        throw new IllegalArgumentException("Column '" + column.columnName + "' is already configured.");
                    }
                    realOrder[column.ordinal()] = i;
                    isFound = true;
                }
            }
        }
    }

    /**
     * Вычисляет и возвращает список отображаемых колонок в сконфигурированом порядке (см. метод configureColumns)
     * Используется поле realOrder.
     *
     * @return список колонок
     */
    public static List<Column> getVisibleColumns() {
        List<Column> result = new LinkedList<>();

        for (int i = 0; i < values().length; i++) {
            if (values()[i].isShown()) result.add(values()[i]);
        }
        Collections.sort(result, new Comparator<Column>() {
            @Override
            public int compare(Column o1, Column o2) {
                return realOrder[o1.ordinal()] - realOrder[o2.ordinal()];
            }
        });

        return result;
    }

    @Override
    public String getColumnName() {
        return columnName;
    }

    @Override
    public boolean isShown() {
        return realOrder[ordinal()] != -1;
    }

    @Override
    public void hide() {
        realOrder[ordinal()] = -1;
    }
}



/*
        int index = 0;
        for (int i = 0; i < values().length; i++) {
            for (int j = 0; j < realOrder.length; j++) {
                if (realOrder[j] == index) {
                    result.add(values()[j]);
                    break;
                }
            }
            index++;
        }
 */

/*
        for (int i = 0; i < values().length; i++) {
            if (values()[i].isShown()) result.add(null);
        }

        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] != -1) {
                result.set(realOrder[i], values()[i]);
            }
        }
 */

/*
        int ord = this.ordinal();
        if (realOrder[ord] == -1) {
            throw new IllegalArgumentException("Column '" + this.columnName + "' is already hidden.");
        }
        int current = realOrder[ord];
        realOrder[ord] = -1;
        for (int i = 0; i < realOrder.length; i++) {
            if (realOrder[i] > current) {
                realOrder[i]--;
            }
        }
 */