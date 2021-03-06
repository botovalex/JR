package com.javarush.test.level25.lesson11.task02;

/* Первый закон Финэйгла: если эксперимент удался, что-то здесь не так...
Обеспечьте переуступку кванта времени (переход хода для текущей нити) для последовательных выводов текста в консоль
*/
public class Solution {
    public static class YieldRunnable implements Runnable {
        private int index;

        public YieldRunnable(int index) {
            this.index = index;
        }

        public void run() {
            System.out.println("begin-" + index);
            Thread.yield();
            System.out.println("end-" + index);
        }
    }

    public static void main(String[] args) {
        Thread tr1 = new Thread(new YieldRunnable(1));
        Thread tr2 = new Thread(new YieldRunnable(2));
        Thread tr3 = new Thread(new YieldRunnable(3));
        Thread tr4 = new Thread(new YieldRunnable(4));
        Thread tr5 = new Thread(new YieldRunnable(5));
        tr1.start();
        tr2.start();
        tr3.start();
        tr4.start();
        tr5.start();
    }
}
