package DTO;

public class Grade {
    public Student stu;
    public Subject sub;
    double lab, progressTest, finalExam;

    public Grade(Student stu, Subject sub, double lab, double progressTest, double finalExam) {
        this.stu = stu;
        this.sub = sub;
        this.lab = lab;
        this.progressTest = progressTest;
        this.finalExam = finalExam;
    }

    public double average() {
        return (0.3 * lab + 0.3 * progressTest + 0.4 * finalExam);
    }

    public Student getStu() {
        return stu;
    }

    public void setStu(Student stu) {
        this.stu = stu;
    }

    public Subject getSub() {
        return sub;
    }

    public void setSub(Subject sub) {
        this.sub = sub;
    }

    public double getLab() {
        return lab;
    }

    public void setLab(double lab) {
        this.lab = lab;
    }

    public double getProgressTest() {
        return progressTest;
    }

    public void setProgressTest(double progressTest) {
        this.progressTest = progressTest;
    }

    public double getFinalExam() {
        return finalExam;
    }

    public void setFinalExam(double finalExam) {
        this.finalExam = finalExam;
    }

}
