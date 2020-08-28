package com.webapp.app.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.webapp.app.database.StudentRepository;
import com.webapp.app.model.ErrorMessage;
import com.webapp.app.model.Student;
import com.webapp.app.model.StudentData;
import com.webapp.app.services.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {

    ApplicationService applicationService;

    @Autowired
    StudentRepository studentRepository;

    public Controller(ApplicationService service){
        this.applicationService=service;
    }

    @GetMapping(value = "/test")
    public String getTest(){

        applicationService.run();
        return "RUNNING....";
    }


    @GetMapping(value = "/findAll")
    public String getAll(){

        List<Student> students = studentRepository.findAll();

        if(students.size()!= 0){
            List<StudentData> studentData = new ArrayList<>();

            for(Student student : students) {
                studentData.add(new StudentData(student.getId(), student.getFirstName(), student.getContact()));
            }
            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.toJson(studentData);
        }else{

            List<ErrorMessage> errorMessages = new ArrayList<>();

            errorMessages.add(new ErrorMessage(false,"Data not available..."));

            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.toJson(errorMessages);
        }
    }

    @RequestMapping("/getByName/{name}")
    public String  fetchDataByName(@PathVariable String name){
        Student students = studentRepository.findByFirstName(name);


        if(students!=null){

            List<StudentData> studentData = new ArrayList<>();
            studentData.add(new StudentData(students.getId(),students.getFirstName(),students.getContact()));

            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.toJson(studentData);
        }else{

            List<ErrorMessage> errorMessages = new ArrayList<>();

            errorMessages.add(new ErrorMessage(true,"Invalid search..."));

            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.toJson(errorMessages);
        }





    }

    @RequestMapping("/getByID/{id}")
    public String fetchDataById(@PathVariable int id){
        Student students = studentRepository.findById(id);
        if(students!=null){

            List<StudentData> studentData = new ArrayList<>();
            studentData.add(new StudentData(students.getId(),students.getFirstName(),students.getContact()));

            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.toJson(studentData);
        }else{

            List<ErrorMessage> errorMessages = new ArrayList<>();

            errorMessages.add(new ErrorMessage(true,"Invalid search..."));

            Gson gson = new GsonBuilder().serializeNulls().create();
            return gson.toJson(errorMessages);
        }
    }

    @RequestMapping("/create")
    public String saveStudent(@RequestParam (value = "name") String name, @RequestParam (value = "contact") String contact) {

        Student students= studentRepository.save(new Student(name,contact));

        List<StudentData> studentData = new ArrayList<>();
        studentData.add(new StudentData(students.getId(),students.getFirstName(),students.getContact()));

        Gson gson = new GsonBuilder().serializeNulls().create();
        return gson.toJson(studentData);
    }

    @RequestMapping("/delete/{s_id}")
    public String deleteStudent(@PathVariable int s_id){

        String result = "";
        try{

            int status = studentRepository.deleteById(s_id);
            if(status==1){
                result = "Success...";
            }else{
                result = "not exits...";
                throw new Exception(" CUSTOM EXCEPTION!!!");
            }

            return  "Student deleted having id "+s_id + " is "+result;
        }catch (Exception e){
            return "Student delete having id "+s_id+" is throw error:\n"+e.getMessage();
        }

    }

    @RequestMapping("/update")
    public String deleteStudent(@RequestParam int s_id,@RequestParam String name , @RequestParam String contact) {

        String result = "";
        try {

            int status = studentRepository.updateStudentInfo(s_id,name,contact);
            if(status==1){
                result = "Success...";
            }else{
                result = "not exits...";
            }

            return  "Student update having id "+s_id + " is "+result;

        } catch (Exception e) {
            e.printStackTrace();
            return  "Student update having id "+s_id + " is generate error \n"+e.getMessage();
        }

    }
}
