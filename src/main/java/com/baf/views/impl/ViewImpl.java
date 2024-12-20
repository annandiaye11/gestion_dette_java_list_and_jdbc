package com.baf.views.impl;

import java.util.List;
import java.util.Scanner;

import com.baf.core.View;

public abstract  class ViewImpl<T> implements View<T> {
    protected Scanner scanner = new Scanner(System.in);

    @Override
    public void liste(List<T> datas) {
        if (datas == null || datas.isEmpty()) {
            System.out.println("Aucune donnée trouvée.");
            return;
        }
        for (T t : datas) {
            System.out.println(t.toString());
        }
    }

    @Override
    public void show(T data) {
        System.out.println(data.toString());
    }
    
}
