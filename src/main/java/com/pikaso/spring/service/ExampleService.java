package com.pikaso.spring.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.pikaso.constants.Constants;
import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;
import com.pikaso.spring.dao.IExampleDao;

public class ExampleService implements IExampleService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleService.class);
    
    @Autowired
    private IExampleDao exampleRepository;
    
    @Override
    public PageHolder<City> getAllPageable(int page, int lastPage) {
        PageHolder<City> pageHolder = exampleRepository.getAllPageable(page*Constants.PAGEABLE_PAGE_SIZE, Constants.PAGEABLE_PAGE_SIZE);
        if(lastPage<0){
            int count = exampleRepository.getAllCount();
            lastPage = count % Constants.PAGEABLE_PAGE_SIZE != 0 ? count/Constants.PAGEABLE_PAGE_SIZE: count/Constants.PAGEABLE_PAGE_SIZE-1;
            LOGGER.info("Calculate last page from database = "+lastPage);
        }
        pageHolder.setLastPage(lastPage);
        return pageHolder;
    }

}
