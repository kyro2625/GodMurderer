package DAO;

import DTO.Subject;

import java.util.ArrayList;

public class SubjectList {
    public SubjectList(ArrayList<Subject> list) {
        this.list = list;
    }

    private ArrayList<Subject> list;

    public ArrayList<Subject> getList() {
        return list;
    }

    public void setList(ArrayList<Subject> list) {
        this.list = list;
    }
}
