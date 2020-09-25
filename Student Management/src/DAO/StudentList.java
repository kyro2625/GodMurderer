package DAO;

import DTO.Student;
import UTIL.MyValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentList extends ArrayList<Student> {
    public StudentList() {
        super();
    }

    Scanner sc = new Scanner(System.in);
    ArrayList<Student> Stulist = new ArrayList<Student>();

    public int findCode(String code) {
        for (int i = 0; i < this.size(); i++) {
            if (code.equals(this.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public void addStudent() {
        String id, name, firstName, lastName, gender, DoB, email, phoneNumber;
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
            System.out.print("Enter student phone number: ");
            phoneNumber = sc.nextLine();
            if (MyValidation.checkStudentExist(Stulist, id, name, lastName, firstName)) {
                Student newStu = new Student(id, name, firstName, lastName, gender, DoB, email, phoneNumber);
                Stulist.add(newStu);
                System.out.println("Add new Student success!");
                return;
            }
            System.out.println("Duplicate! Please try again!");
        }
    }

    public void updateStudent() {
        String id;
        System.out.print("Enter the ID of student you want to update: ");
        id = sc.nextLine().toUpperCase();
        int pos = findCode(id);
        if (pos < 0) {
            System.out.println("This code does not exist!");
        } else {
            String oldName = this.get(pos).getName();

            System.out.print("Old name: " + oldName + ", new Name: ");
            String newName = sc.nextLine();
            System.out.println("The student ID: " + id + " has been updated!");
        }
    }

    public void printStudent() {
        for (Student student : Stulist) {
            System.out.println(Stulist);
            System.out.println(Stulist.size());
            System.out.println(Stulist.indexOf(contains("SE150944")));
        }
    }


}
