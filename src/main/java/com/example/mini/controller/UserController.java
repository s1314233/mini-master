package com.example.mini.controller;

import com.example.mini.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/users")
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(@RequestParam(value = "username") String username) {
        return userService.create(username);
    }

    @RequestMapping("/getString")
    public ModelAndView getString(HttpServletRequest request){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
