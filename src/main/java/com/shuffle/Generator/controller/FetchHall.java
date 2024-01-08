package com.shuffle.Generator.controller;

import com.shuffle.Generator.model.Student;
import com.shuffle.Generator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class FetchHall {
    @Autowired
    StudentRepository studentRepository;
    @RequestMapping(value={"/hallDetails"}, method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView viewHall(@RequestParam(value = "classNo", required = true) int classNo){

        ModelAndView modelAndView = new ModelAndView("hall.html");

        Sort sort = Sort.by("rowN", "columnN").ascending();
        List<Student> students = studentRepository.findByExamHall(classNo, sort);

        modelAndView.addObject("students", students);
        modelAndView.addObject("ExamHall", classNo);
        return modelAndView;
    }
}
