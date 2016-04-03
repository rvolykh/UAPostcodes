package com.pikaso.spring.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;
import com.pikaso.rest.dao.CityDao;
import com.pikaso.rest.dao.DaoConstants;
import com.pikaso.spring.dao.IExampleDao;

public class ExampleService implements IExampleService{
    @Autowired
    private IExampleDao exampleRepository;
    
    @Override
    public PageHolder<City> getAllPageable(int page) {
        PageHolder<City> pageHolder = exampleRepository.getAllPageable(page*DaoConstants.PAGEABLE_PAGE_SIZE, DaoConstants.PAGEABLE_PAGE_SIZE);
        int count = exampleRepository.getAllCount();
        int lastpage = count % DaoConstants.PAGEABLE_PAGE_SIZE != 0 ? count/DaoConstants.PAGEABLE_PAGE_SIZE: count/DaoConstants.PAGEABLE_PAGE_SIZE-1;
        pageHolder.setLastPage(lastpage);
        return pageHolder;
    }

}
