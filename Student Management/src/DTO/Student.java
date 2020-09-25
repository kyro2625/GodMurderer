package DTO;

import DAO.StudentList;
import UTIL.MyValidation;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Student {
    private String id;
    private String name;
    private String firstName;
    private String lastName;
    private String gender;
    private String DoB;
    private String email;
    private String phoneNumber;
    //   private boolean canDelete = true;
    transient int i;
    //    private StudentList stulist;
    //private ArrayList<Subject> selectedList;

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", DoB=" + DoB +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public Student(String id, String name, String firstName, String lastName, String gender, String doB, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.DoB = doB;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public void print() {

    }

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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDoB() {
        return DoB;
    }

    public void setDoB(String doB) {
        DoB = doB;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void input() {
        //to do
        Scanner sc = new Scanner(System.in);
        //do {

         //   while (!MyValidation.checkString(id, "") || (!MyValidation.checkID(id, Stulist.)))
        }

        //nhap name

    }
