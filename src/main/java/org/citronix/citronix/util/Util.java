package org.citronix.citronix.util;

import org.citronix.citronix.domain.eum.Season;

import java.time.LocalDateTime;
import java.time.Month;

import static java.time.Month.*;

public class Util {
    public static Season getSeason(LocalDateTime dateTime) {
        Month month = dateTime.getMonth();

        return switch (month) {
            case DECEMBER, JANUARY, FEBRUARY -> Season.WINTER;
            case MARCH, APRIL, MAY -> Season.SPRING;
            case JUNE, JULY, AUGUST -> Season.SUMMER;
            case SEPTEMBER, OCTOBER, NOVEMBER -> Season.AUTUMN;
            default -> throw new IllegalArgumentException("Invalid month: " + month);
        };
    }
}
