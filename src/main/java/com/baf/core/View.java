package com.baf.core;

import java.util.List;

public interface View<T> {
    T saisie();
    void liste(List<T> datas);
    void show(T data);


}
