package DAO;

import DTO.Grade;
import DTO.Student;
import DTO.Subject;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeList extends ArrayList<Grade> {
    public GradeList() {

    }

    Scanner sc = new Scanner(System.in);
    StudentList StuList;
    SubjectList SubList;

    public GradeList(StudentList stuList, SubjectList subList) {
        this.StuList = stuList;
        this.SubList = subList;
    }

    private boolean confirmation(String c) {
        boolean t = true;
        if (c.equalsIgnoreCase("Y")) {
            t = true;
        }
        if (c.equalsIgnoreCase("N")) {
            t = false;
        }
        return t;
    }

    public int search(String stuID, String subID) {
        for (int i = 0; i < this.size(); i++) {
            if (stuID.equals(StuList.get(i).getId()) && subID.equals(SubList.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public int searchStudent(String stdID) {
        for (Student student : StuList) {
            if (stdID.equalsIgnoreCase(student.getId())) {
                return StuList.indexOf(student);
            }
        }
        return -1;
    }

    public int searchSub(String subID) {
        for (Subject subject : SubList) {
            if (subID.equalsIgnoreCase(subject.getId())) {
                return SubList.indexOf(subject);
            }
        }
        return -1;
    }

    public void addNewGrade() {
        String stuID, subID, confirm;
        double lab, progressTest, finalExam;
        boolean t, confirmed;
        int stuPos, subPos, gradePos;
        do {
            System.out.print("Do you want to continue? (Y/N): ");
            confirm = sc.nextLine();
            confirmed = confirmation(confirm);
        } while (confirm.equalsIgnoreCase("Y") != true && confirm.equalsIgnoreCase("N") != true);
        if (confirmed) {
            do {
                do {
                    System.out.print("Enter student ID: ");
                    stuID = sc.nextLine().toUpperCase();
                    stuPos = searchStudent(stuID);
                    if (stuPos < 0) {
                        System.out.println("Enter again: ");
                        System.out.println(stuPos);
                    }
                } while (stuPos < 0);
                do {
                    System.out.print("Enter subject ID: ");
                    subID = sc.nextLine().toUpperCase();
                    subPos = searchSub(subID);
                } while (subPos < 0);
                gradePos = search(stuID, subID);
                if (gradePos >= 0) System.out.println("This grade existed!");
            } while (gradePos >= 0);
            do {
                try {
                    t = true;
                    System.out.print("Enter Lab Score: ");
                    lab = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Final Exam Score: ");
                    finalExam = Integer.parseInt(sc.nextLine());
                    System.out.print("Enter Progress Test Score: ");
                    progressTest = Integer.parseInt(sc.nextLine());
                    this.add(new Grade(StuList.get(stuPos), SubList.get(subPos), lab, progressTest, finalExam));
                } catch (Exception e) {
                    t = false;
                    System.out.println("Mời bạn nhập lại điểm");
                }
            } while (!t);
            System.out.println("Input Successfully!");
        }
    }

    public void printStudentReport() {
        String stuID, confirm;
        boolean confirmed;
        do {
            System.out.print("Do you want to continue? (Y/N): ");
            confirm = sc.nextLine();
            confirmed = confirmation(confirm);
        } while (confirm.equalsIgnoreCase("Y") != true && confirm.equalsIgnoreCase("N") != true);
        if (confirmed) {
            System.out.print("Enter student ID: ");
            stuID = sc.nextLine().toUpperCase();
            int gradePos = searchStudent(stuID);

            if (gradePos < 0) {
                System.out.println("No report can be supported!");
            } else {
                System.out.println("Student ID: " + stuID.toUpperCase());
                Student student = this.get(gradePos).stu;
                System.out.println("Student Name: " + student.getFirstName() + " " + student.getLastName());
                System.out.printf("%20s %20s %20s %20s\n", "No", "Subject", "Average", "Status");
                int count = 1;

                for (int o = 0; o < this.size(); o++) {
                    if (stuID.equalsIgnoreCase(this.get(o).getStu().getId())) {
                        if (this.get(o).average() > 5) {
                            System.out.printf("%20d %20s %20f %20s\n", count, this.get(o).getSub().getName(), this.get(o).average(), "Passed");
                            count++;
                        }
                        if (this.get(o).average() <= 5) {
                            System.out.printf("%20d %20s %20f %20s\n", count, this.get(o).getSub().getName(), this.get(o).average(), "Failed");
                            count++;
                        }
                    }
                }
            }
        }
    }
    public void printSubjectReport() {
        String subID, confirm;
        boolean confirmed;
        do {
            System.out.print("Do you want to continue? (Y/N)");
            confirm = sc.nextLine();
            confirmed = confirmation(confirm);
        }while (confirm.equalsIgnoreCase("Y") !=true && confirm.equalsIgnoreCase("N"));
        if (confirmed) {
            System.out.print("Enter subject ID: ");
            subID = sc.nextLine();
            int gradePos = searchSub(subID);

            if (gradePos<0) {
                System.out.println("No Report For This Subject!");
            } else {
                System.out.println("Subject ID: "+ subID.toUpperCase());
                Subject subject = this.get(gradePos).sub;
                System.out.println("Subject name: " + subject.getName());
                System.out.printf("%20s %20s %20s  %20s %20s\n", "No", "Student ID", "Student Name", "Average", "Status");
                int count = 1;

                for (int o = 0; o < this.size(); o++) {
                    if (subID.equalsIgnoreCase(this.get(o).getSub().getId())) {
                        if (this.get(o).average() > 5) {
                            System.out.printf("%20d %20s %20s %20f %20s\n", count, this.get(o).getStu().getId(), this.get(o).getStu().getFirstName() + " " +this.get(o).getStu().getLastName(), this.get(o).average(), "Passed");
                            count++;
                        }
                        if (this.get(o).average() <= 5) {
                            System.out.printf("%20d %20s %20s %20f %20s\n", count, this.get(o).getStu().getId(), this.get(o).getStu().getFirstName() + " " +this.get(o).getStu().getLastName(), this.get(o).average(), "Failed");
                            count++;
                        }

                    }
                }
            }
        }
    }

}
