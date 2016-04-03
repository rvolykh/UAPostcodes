package com.pikaso.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.pikaso.entity.City;
import com.pikaso.pageable.PageHolder;
import com.pikaso.rest.dao.DaoConstants;
import com.pikaso.rest.service.ApiService;
import com.pikaso.rest.service.IApiService;
import com.pikaso.spring.service.ExampleService;
import com.pikaso.spring.service.IExampleService;

@Controller
public class ExampleController {
    @Autowired
    private IExampleService exampleService;
    
    @RequestMapping("/example")
    public String postalTable(@RequestParam("page")Integer page, Model model) {
        if(page==0){
            System.out.println("DEFAULT");
            PageHolder<City> cities = exampleService.getAllPageable(0);
            cities.setPage(0);
            model.addAttribute("cities", cities);
        }else{
            System.out.println("NOT DEFAULT"+page);
            PageHolder<City> cities = exampleService.getAllPageable(page);
            cities.setPage(page);
            model.addAttribute("cities", cities);
        }
        
        return "example";
    }
    
}
