package com.baf.data.repositories.list;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.baf.core.Repository;


public class RepositoryImplList<T> implements Repository<T>{

    protected List<T> data = new ArrayList<>();

    @Override
    public void insert(T data) {
        this.data.add(data);
    }

    @Override
    public List<T> selectAll() {
       return this.data;
    }

    @Override
    public T convertToObject(ResultSet rs) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'convertToObject'");
    }
    
}
