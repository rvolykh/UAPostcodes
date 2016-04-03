package com.pikaso.spring.dao;

import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;

public interface IExampleDao {
    public PageHolder<City> getAllPageable(int start, int count);
    public int getAllCount();
}
