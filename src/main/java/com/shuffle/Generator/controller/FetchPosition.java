package com.shuffle.Generator.controller;

import com.shuffle.Generator.model.Student;
import com.shuffle.Generator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FetchPosition {

    @Autowired
    StudentRepository studentRepository;

    @RequestMapping(value={"/position"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewHome(@RequestParam(value = "studentId", required = true) String studentId){

        ModelAndView modelAndView = new ModelAndView("show.html");

        Student student = studentRepository.findByIdNumber(studentId);

        modelAndView.addObject("student", student);
        return modelAndView;
    }
}
