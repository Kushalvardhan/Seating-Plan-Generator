package com.shuffle.Generator.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Entity
public class Student {

    @Id
    private String idNumber;
    private String branch;

    @Column(name = "room")
    private Integer examHall;

    @Column(name = "row_n")
    private Integer rowN;
    @Column(name = "column_n")
    private Integer columnN;

    @Column(name = "role_id")
    private Integer roles;

    @Override
    public String toString(){
        return "ExamHall : " + examHall + ", Row : " + rowN + ", Column : " + columnN;
    }
}
