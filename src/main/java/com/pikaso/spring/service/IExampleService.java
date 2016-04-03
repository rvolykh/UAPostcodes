package com.pikaso.spring.service;

import org.springframework.beans.support.PagedListHolder;

import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;

public interface IExampleService {
    PageHolder<City> getAllPageable(int page);
}
