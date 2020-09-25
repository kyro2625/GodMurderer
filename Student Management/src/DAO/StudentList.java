package DAO;

import DTO.Student;
import UTIL.MyValidation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class StudentList extends ArrayList<Student> {
    Scanner sc = new Scanner(System.in);
    ArrayList<Student> Stulist = new ArrayList<Student>();
    MyValidation myv = new MyValidation();

    public void addStudent() {
        String id, name, firstName, lastName, gender, DoB, email, password;
        boolean matched = true;
        while (true) {
            System.out.println("Enter student ID: ");
            do {

                System.out.print("   Code(format SE000000): ");
                id = sc.nextLine().toUpperCase();
                matched = id.matches("^SE\\d{6}$");// Pattern: SE and 6 digits
                if (!matched) {
                    System.err.print("   Wrong input format!");
                    System.out.println("   The code: SE and 6 digits.");
                }
            } while ((!matched));
            System.out.print("Enter student name: ");
            name = MyValidation.checkInputString();
            System.out.print("Enter student first name: ");
            firstName = MyValidation.checkInputString();
            System.out.print("Enter student last name: ");
            lastName = MyValidation.checkInputString();
            if (!MyValidation.checkIdExist(Stulist, id, name)) {
                System.err.println("Id has exist student. Pleas re-input.");
            }

            System.out.print("Enter student gender: ");
            gender = sc.nextLine();
            do {
                System.out.print("Enter student Date of Birth: ");
                DoB = sc.nextLine();
            } while (!MyValidation.checkDate(DoB));
            System.out.print("Enter student email: ");
            email = sc.nextLine();
            System.out.print("Enter student password: ");
            password = sc.nextLine();
            if (MyValidation.checkStudentExist(Stulist, id, name, lastName, firstName)) {
                Student newStu = new Student(id, name, firstName, lastName, gender, DoB, email, password);
                Stulist.add(newStu);
                System.out.println("Add new Student success!");
                return;
            }
            System.out.println("Duplicate! Please try again!");
        }
    }

    public void printStudent() {
        for (Student student : Stulist) {
            System.out.println(Stulist);
        }
    }

    //TO DO
//    //ham nay de them 1 SV ms vao list
//    public boolean addStudent() {
//        //tao 1 sv moi va nhap thong tin cho no
//        Student newStu = new Student();
//        newStu.input();
//
//        //them sv moi vao list
//        return list.add(newStu);
//    }
//    public void addNewStudent() {
//        String newCode, newName, newGender;
//        int age = 0, score = 0;
//        boolean duplicated = false, matched = true;
//        System.out.println("Enter new item details: ");
//        do {
//            System.out.print("   Code(format SE000000): ");
//            newCode = sc.nextLine().toUpperCase();
//            duplicated = !valid(newCode);
//            matched = newCode.matches("^SE\\d{6}$");// Pattern: SE and 6 digits
//            if (duplicated) {
//                System.out.println(" The code is duplicated.");
//            }
//            if (!matched) {
//                System.out.println("  The code: SE and 6 digits.");
//            }
//        } while (duplicated || (!matched));
//        System.out.print("    Name: ");
//        newName = sc.nextLine().toUpperCase();
//        System.out.print("   Gender: ");
//        newGender = sc.nextLine().toUpperCase();
//
//        while (true) {
//            try {
//
//                System.out.print("   Age: ");
//                age = Integer.parseInt(sc.nextLine());
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println("Wrong format");
//            }
//        }
//        while (true) {
//            try {
//                System.out.print("   Score: ");
//                score = Integer.parseInt(sc.nextLine());
//                break;
//            } catch (NumberFormatException e) {
//                System.out.println();
//            }
//        }
//        this.add(new Student150944(newCode, newName, newGender, age, score));
//        System.out.println("New item has been addded");
//
//    }
//
//    //remove an student from new student list
//    public void removeStudent() {
//        String code;
//        System.out.println("Enter the code of removed item: ");
//        code = sc.nextLine().toUpperCase();
//        int pos = find(code);
//        if (pos < 0) {
//            System.out.println("This code does not exist.");
//        } else {
//            this.remove(pos);
//            System.out.println("The item " + code + " has been removed.");
//
//        }
//    }
//
//    public void updateStudent() {
//        String code;
//        System.out.print("Enter the code of updated student: ");
//        code = sc.nextLine().toUpperCase();
//        int pos = find(code);
//        if (pos < 0) {
//            System.out.println("This code does not exist.");
//        } else {
//            int oldAge = this.get(pos).getAge();
//            int newAge;
//
//            System.out.print("Old age: " + oldAge + ", new age: ");
//            newAge = Integer.parseInt(sc.nextLine());
//
//            this.get(pos).setAge(newAge);
//
//            double oldScore = this.get(pos).getScore();
//            double newScore;
//            do {
//                System.out.print("Old scores: " + oldScore + ", new scores: ");
//                newScore = Double.parseDouble(sc.nextLine());
//
//            } while (newScore == oldScore);
//            this.get(pos).setScore(newScore);
//            System.out.println("The student " + code + " has been updated.");
//        }
//    }
}
