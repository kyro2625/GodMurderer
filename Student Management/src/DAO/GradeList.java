package DAO;

import DTO.Grade;
import DTO.Student;
import DTO.Subject;
import UTIL.MyValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeList extends ArrayList<Grade> {

    transient Scanner sc = new Scanner(System.in);
    StudentList StuList;
    SubjectList SubList;

    public GradeList(StudentList stuList, SubjectList subList) {
        this.StuList = stuList;
        this.SubList = subList;
    }

    private boolean confirmation(String c) {
        boolean t = true;
        if (c.equalsIgnoreCase("N")) {
            t = false;
        }
        return t;
    }

    public int search(String stuID, String subID) {
        for (Grade grade : this) {
            if (grade.stu.getId().equalsIgnoreCase(stuID) && grade.sub.getId().equalsIgnoreCase(subID)) {
                return indexOf(grade);
            }
        }
        return -1;
    }

    public int searchStudent(String stuID) {
        for (Student student : StuList) {
            if (stuID.equalsIgnoreCase(student.getId())) {
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
        String stuID, subID, confirm, confirm1;
        double lab, progressTest, finalExam;
        boolean t, confirmed, confirmed1;
        int stuPos, subPos, gradePos;
        do {
            System.out.print("Do you want to continue? (Y/N): ");
            confirm = sc.nextLine();
            confirmed = confirmation(confirm);
        } while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N"));
        if (confirmed) {
            do {
                do {
                    System.out.print("Enter student ID: ");
                    stuID = sc.nextLine().toUpperCase();
                    stuPos = searchStudent(stuID);
                    if (stuPos < 0) {
                        System.out.println("Enter again: ");
                    }
                } while (stuPos < 0);
                do {
                    System.out.print("Enter subject ID: ");
                    subID = sc.nextLine().toUpperCase();
                    subPos = searchSub(subID);
                    if (subPos < 0) {
                        System.out.println("Enter again: ");
                    }
                } while (subPos < 0);
                gradePos = search(stuID, subID);
                if (gradePos >= 0) {
                    System.out.println("This grade existed!");
                    do {
                        System.out.print("Do you want to update grade of this subject for this student? (Y/N): ");
                        confirm1 = sc.nextLine();
                        confirmed1 = confirmation(confirm1);
                    } while (!confirm1.equalsIgnoreCase("Y") && !confirm1.equalsIgnoreCase("N"));
                    if (confirmed1) {
                        double oldLab = this.get(gradePos).getLab();
                        System.out.print("Old lab score: " + oldLab + ", new lab score: ");
                        double newLab = MyValidation.checkInputScore();
                        this.get(gradePos).setLab(newLab);

                        double oldProgressTest = this.get(gradePos).getProgressTest();
                        System.out.print("Old progress test score: " + oldProgressTest + ", new progress test score: ");
                        double newProgressTest = MyValidation.checkInputScore();
                        this.get(gradePos).setProgressTest(newProgressTest);

                        double oldFinalExam = this.get(gradePos).getFinalExam();
                        System.out.print("Old final exam score: " + oldFinalExam + ", new final exam score: ");
                        double newFinalExam = MyValidation.checkInputScore();
                        this.get(gradePos).setFinalExam(newFinalExam);

                        System.out.println("Update grade completed! ");
                        return;
                    }
                }
            } while (gradePos >= 0);
            do {
                try {
                    t = true;
                    System.out.print("Enter Lab Score: ");
                    lab = MyValidation.checkInputScore();

                    System.out.print("Enter Progress Test Score: ");
                    progressTest = MyValidation.checkInputScore();

                    System.out.print("Enter Final Exam Score: ");
                    finalExam = MyValidation.checkInputScore();

                    this.add(new Grade(StuList.get(stuPos), SubList.get(subPos), lab, progressTest, finalExam));
                } catch (Exception e) {
                    t = false;
                    System.out.println("Please enter again");
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
        } while (!confirm.equalsIgnoreCase("Y") && !confirm.equalsIgnoreCase("N"));
        if (confirmed) {
            System.out.print("Enter student ID: ");
            stuID = sc.nextLine().toUpperCase();
            int gradePos = searchStudent(stuID);
            if (this.size() == 0) System.out.println("No grade to display!");
            else if (gradePos < 0) {
                System.out.println("No report can be supported!");
            } else {
                System.out.printf("\n%50s\n", "Student ID: " + stuID.toUpperCase());
                Student student = this.get(gradePos).stu;
                System.out.printf("%50s\n", "Student Name: " + student.getFirstName() + " " + student.getLastName());
                System.out.printf("%20s %20s %20s %20s\n", "No", "Subject", "Average", "Status");
                int count = 1;

                for (Grade grade : this) {
                    if (stuID.equalsIgnoreCase(grade.getStu().getId())) {
                        if (grade.average() > 5) {
                            System.out.printf("%20d %20s %20f %20s\n", count, grade.getSub().getName(), grade.average(), "Passed");
                            count++;
                        }
                        if (grade.average() <= 5) {
                            System.out.printf("%20d %20s %20f %20s\n", count, grade.getSub().getName(), grade.average(), "Failed");
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
            System.out.print("Do you want to continue? (Y/N): ");
            confirm = sc.nextLine();
            confirmed = confirmation(confirm);
        } while (!confirm.equalsIgnoreCase("Y") && confirm.equalsIgnoreCase("N"));
        if (confirmed) {
            System.out.print("Enter subject ID: ");
            subID = sc.nextLine();
            int gradePos = searchSub(subID);

            if (gradePos < 0) {
                System.out.println("    No Report For This Subject!");
            } else {
                while (true) {
                    try {
                        System.out.printf("\n%50s\n", "Subject ID: " + subID.toUpperCase());
                        Subject subject = this.get(gradePos).sub;
                        System.out.printf("%50s\n", "Subject name: " + subject.getName());
                        System.out.printf("%20s %20s %20s  %20s %20s\n", "No", "Student ID", "Student Name", "Average", "Status");
                        int count = 1;

                        for (Grade grade : this) {
                            if (subID.equalsIgnoreCase(grade.getSub().getId())) {
                                if (grade.average() > 5) {
                                    System.out.printf("%20d %20s %20s %20f %20s\n", count, grade.getStu().getId(), grade.getStu().getFirstName() + " " + grade.getStu().getLastName(), grade.average(), "Passed");
                                    count++;
                                }
                                if (grade.average() <= 5) {
                                    System.out.printf("%20d %20s %20s %20f %20s\n", count, grade.getStu().getId(), grade.getStu().getFirstName() + " " + grade.getStu().getLastName(), grade.average(), "Failed");
                                    count++;
                                }

                            }
                        }
                        break;
                    } catch (Exception e) {
                        System.out.printf("%50s","No Student to report! ");
                        return;
                    }
                }
            }
        }
    }
}
