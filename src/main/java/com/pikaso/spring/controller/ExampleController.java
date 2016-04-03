package com.pikaso.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;
import com.pikaso.spring.service.IExampleService;

@Controller
public class ExampleController {
    @Autowired
    private IExampleService exampleService;
    
    @RequestMapping("/example")
    public String postalTable(@RequestParam(value="page",required=false)Integer page, Model model) {
        if(page==null || page==0){
            PageHolder<City> cities = exampleService.getAllPageable(0);
            cities.setPage(0);
            model.addAttribute("cities", cities);
        }else{
            PageHolder<City> cities = exampleService.getAllPageable(page);
            cities.setPage(page);
            model.addAttribute("cities", cities);
        }
        return "example";
    }
    
}
