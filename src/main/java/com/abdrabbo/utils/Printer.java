package com.abdrabbo.utils;

import java.util.List;

public class Printer {

    public static <T> void printsIterable(List<T> iterable) {
        iterable.forEach(System.out::println);
    }

}
