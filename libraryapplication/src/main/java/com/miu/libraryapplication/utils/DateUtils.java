package com.miu.libraryapplication.utils;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateUtils {
    public static String changeLocalDateToString(LocalDate date) {
        return date.toString();
    }

    public static LocalDate changeStringToLocalDate(String dateString) {
        return LocalDate.parse(LocalDate.now().toString());
    }

    public static long findDaysDifference(LocalDate borrowDate, LocalDate submissionDate) {
        return ChronoUnit.DAYS.between(borrowDate, submissionDate);

    }
}
