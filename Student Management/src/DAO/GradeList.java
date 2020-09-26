package DAO;

import DTO.Grade;
import DTO.Student;

import java.util.ArrayList;
import java.util.Scanner;

public class GradeList extends ArrayList<Grade>  {
    public GradeList() {

    }
    Scanner sc = new Scanner(System.in);
    StudentList StuList = new StudentList();
    SubjectList SubList = new SubjectList();

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
    public int findSubjectIO(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (id.equals(this.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }
    public void addNewGrade() {
        StuList.findStudentIO()
    }
}
