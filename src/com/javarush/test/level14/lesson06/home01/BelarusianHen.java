package com.javarush.test.level14.lesson06.home01;

/**
 * Created by barbudos on 07.06.2016.
 */
public class BelarusianHen extends Hen {
    public int getCountOfEggsPerMonth() {
        return 10;
    }

    public String getDescription() {
        return new StringBuilder().append(super.getDescription()).append(" Моя страна - ").append(Country.BELARUS).
                append(". Я несу ").append(this.getCountOfEggsPerMonth()).append(" яиц в месяц.").toString();
    }
}
