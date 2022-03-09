package com.codegym.dao;

import java.util.List;

public interface IGeneralDao <T>{
    List <T> findAll ();

    T findById (int id);

    boolean create (T t);

    boolean updateById (int id, T t);

    boolean deleteById (int id);
}
