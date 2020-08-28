package com.inymbus.app.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "student")
@Getter @Setter
@SequenceGenerator(name="student_s_id_seq", initialValue=1, allocationSize=1)
public class Student implements Serializable {

    @Id
    @Column(name ="s_id")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="student_s_id_seq")
    int id;

    @Column(name = "s_name")
    String firstName;

    @Column(name = "contact")
    String contact;

    public Student(String name,String contact){
        this.firstName=name;
        this.contact=contact;

    }

    public Student(){}
}
