package com.abdrabbo.utils;

public class Contains {
    public static <T> boolean contains(T[] array, T element) {
        for (T item : array) {
            if (item.equals(element))
                return true;
        }
        return false;
    }
}
