package com.pikaso.spring.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pikaso.entity.City;
import com.pikaso.exceptions.ExampleException;
import com.pikaso.pageable.PageHolder;
import com.pikaso.spring.service.IExampleService;

@Controller
public class ExampleController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleController.class);

    @Autowired
    private IExampleService exampleService;

    @RequestMapping("/example")
    public String postalTable(@RequestParam(value = "page", required = false) Integer page, Model model) {

        if (page == null) {
            page = 0;
        }
        int lastPage = -1;//TODO: receive this value from Client
        PageHolder<City> cities = exampleService.getAllPageable(page, lastPage);
        cities.setPage(page);
        model.addAttribute("cities", cities);
        
       

        LOGGER.info("User ask for page = {}",page);
        return "example";
    }
    
    @ExceptionHandler(ExampleException.class)
    public String errorPage(){
        return "redirect:/error.html";
    }


}
