package com.baf.core;

import java.sql.ResultSet;
import java.util.List;

public interface Repository<T> {
    void insert(T data);
    List<T> selectAll();
    public T convertToObject(ResultSet rs);
}
