package com.webapp.app.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class StudentData {


    int id;
    String name,contact;



    public StudentData(String name,String contact){
        this.name=name;
        this.contact=contact;
    }

    public StudentData(int id,String name,String contact){
        this.id=id;
        this.name=name;
        this.contact=contact;
    }


}
