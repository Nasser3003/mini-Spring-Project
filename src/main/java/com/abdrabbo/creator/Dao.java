package com.abdrabbo.creator;

import java.util.List;

public interface Dao<T> {
    List<T> createObjectsFromFile(String filePath);
}