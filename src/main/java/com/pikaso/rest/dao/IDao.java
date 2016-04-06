package com.pikaso.rest.dao;

import java.util.List;

public interface IDao<TEntity> {
    TEntity getById(String id);
    List<TEntity> getByFieldName(String fieldName, String text);
    List<TEntity> getAll();
}
