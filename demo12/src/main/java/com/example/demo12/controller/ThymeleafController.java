package com.example.demo12.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @author 孟凌涛
 * @create 2020-08-06 23:19
 */
@Controller
public class ThymeleafController {

    @RequestMapping("success")
    public ModelAndView successForhello(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("hello","你好");
        modelAndView.setViewName("success");
        return modelAndView;
    }
}
