package com.pikaso.spring.service;


import org.springframework.beans.factory.annotation.Autowired;

import com.pikaso.constants.Constants;
import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;
import com.pikaso.spring.dao.IExampleDao;

public class ExampleService implements IExampleService{
    @Autowired
    private IExampleDao exampleRepository;
    
    @Override
    public PageHolder<City> getAllPageable(int page) {
        PageHolder<City> pageHolder = exampleRepository.getAllPageable(page*Constants.PAGEABLE_PAGE_SIZE, Constants.PAGEABLE_PAGE_SIZE);
        int count = exampleRepository.getAllCount();
        int lastpage = count % Constants.PAGEABLE_PAGE_SIZE != 0 ? count/Constants.PAGEABLE_PAGE_SIZE: count/Constants.PAGEABLE_PAGE_SIZE-1;
        pageHolder.setLastPage(lastpage);
        return pageHolder;
    }

}
