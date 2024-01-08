package com.shuffle.Generator.controller;


import com.shuffle.Generator.model.Student;
import com.shuffle.Generator.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("admin")
public class CoreController {

    @Autowired
    StudentRepository studentRepository;


    @RequestMapping(value = {"/shuffle"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String shuffle() {
        List<Student> cseStudents = studentRepository.findByBranch("CSE");
        List<Student> eceStudents = studentRepository.findByBranch("ECE");
        List<Student> civilStudents = studentRepository.findByBranch("CIVIL");
        List<Student> mechStudents = studentRepository.findByBranch("MECH");
        List<Student> eeeStudents = studentRepository.findByBranch("EEE");

        Random random = new Random();
        //round -1 (cse and ece)
        int iterations = 14;

        for (int room = 1; room <= iterations; room++) {

            for (int row = 1; row <= 5; row++) {

                for (int col = 1; col <= 6; col++) {
                    if (col % 2 == 0) {
                        int cseIndex = random.nextInt(cseStudents.size());
                        Student student = cseStudents.get(cseIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        cseStudents.remove(cseIndex);
                        studentRepository.save(student);

                    } else {
                        int eceIndex = random.nextInt(eceStudents.size());
                        Student student = eceStudents.get(eceIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        eceStudents.remove(eceIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    }
                }
            }
        }//420 people are shuffled

        //Round - 2 (cse vs civil vs mech)
        iterations = 23;

        for (int room = 15; room <= iterations; room++) {

            for (int row = 1; row <= 5; row++) {

                for (int col = 1; col <= 6; col++) {
                    if (col % 3 == 0) {
                        int cseIndex = random.nextInt(cseStudents.size());
                        Student student = cseStudents.get(cseIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        cseStudents.remove(cseIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    } else if (col % 2 == 0) {
                        int civilIndex = random.nextInt(civilStudents.size());
                        Student student = civilStudents.get(civilIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        civilStudents.remove(civilIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    } else {
                        int mechIndex = random.nextInt(mechStudents.size());
                        Student student = mechStudents.get(mechIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        mechStudents.remove(mechIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    }
                }
            }
        }


        //round - 3 (civil vs eee)
        iterations = 25;

        for (int room = 24; room <= iterations; room++) {

            for (int row = 1; row <= 5; row++) {

                for (int col = 1; col <= 6; col++) {
                    if (col % 2 == 0) {
                        int civilIndex = random.nextInt(civilStudents.size());
                        Student student = civilStudents.get(civilIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        civilStudents.remove(civilIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    } else {
                        int eeeIndex = random.nextInt(eeeStudents.size());
                        Student student = eeeStudents.get(eeeIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        eeeStudents.remove(eeeIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    }
                }
            }
        }

        cseStudents.addAll(mechStudents);
        cseStudents.addAll(eeeStudents);



        //round - 3 (cse + mech + eee) vs (ece)
        iterations = 27;
        boolean stop = false;

        for (int room = 26; room <= iterations; room++) {

            for (int row = 1; row <= 5; row++) {

                for (int col = 1; col <= 6; col++) {
                    if (col % 2 == 0) {
                        if(cseStudents.size() == 0){
                            stop = true;
                            break;
                        }

                        int cseIndex = random.nextInt(cseStudents.size());
                        Student student = cseStudents.get(cseIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        cseStudents.remove(cseIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    } else {
                        if(eceStudents.size() == 0){
                            stop = true;
                            break;
                        }

                        int eceIndex = random.nextInt(eceStudents.size());
                        Student student = eceStudents.get(eceIndex);

                        student.setExamHall(room);
                        student.setRowN(row);
                        student.setColumnN(col);

                        eceStudents.remove(eceIndex);
                        //saving student into database using student repository
                        studentRepository.save(student);
                    }
                }if(stop){
                    break;
                }

            }if(stop) {
                break;
            }
        }

        return "redirect:/home";
    }
}
