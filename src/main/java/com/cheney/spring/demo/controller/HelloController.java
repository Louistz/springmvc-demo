package com.cheney.spring.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.Map;
import java.util.HashMap;

@Controller
@EnableWebMvc
public class HelloController {

    //返回字符串
    @RequestMapping("/index")
    @ResponseBody
    public String index(){
        return "welcome, springMVC.";
    }

    //返回模板(jsp等等)
    @RequestMapping("/hello")
    public ModelAndView hello(){
        ModelAndView mv = new ModelAndView();
        //模板附带的属性，可以直接取出来
        mv.addObject("attributeName","Hello World!");

        //index.jsp, index.html等等
        mv.setViewName("hello_template");

        return mv;
    }

    //动态url, 返回json .通常用于ajax请求
    @RequestMapping(value = "/user/{userId}",method = RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Map<String,Object> getUser(@PathVariable String userId){
        User user = new User();
        user.setId(userId);
        user.setName("username");

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("user",user);
        return result;
    }


    private class User{
        private String id;
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
