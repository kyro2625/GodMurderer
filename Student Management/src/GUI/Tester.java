package GUI;

import DAO.GradeList;
import DAO.StudentList;
import DAO.SubjectList;

public class Tester {
    public static void main(String[] args) throws InterruptedException {
        StudentList stu = new StudentList();
        SubjectList sub = new SubjectList();
        GradeList gra = new GradeList(stu, sub);
        Menu menu = new Menu();
        menu.add("1. Add New Student");
        menu.add("2. Update Student Information");
        menu.add("3. Remove Student Information");
        menu.add("4. Add New Subject");
        menu.add("5. Update Subject Information");
        menu.add("6. Remove Subject Information");
        menu.add("7. Input Grade");
        menu.add("8. Display Student Grade Report");
        menu.add("9. Display Subject Grade Report");
        menu.add("10. Quit!");
        int userChoice;
        do {
            System.out.println("\nSTUDENT MANAGEMENT");
            for (Object str : menu) {
                System.out.println(str);
            }
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1 -> stu.addStudent();
                case 2 -> stu.updateStudent();
                case 3 -> stu.removeStudent();
                case 4 -> sub.addSubject();
                case 5 -> sub.updateSubject();
                case 6 -> sub.removeSubject();
                case 7 -> gra.addNewGrade();
                case 8 -> gra.printStudentReport();
                case 9 -> gra.printSubjectReport();
                case 10 -> System.out.println("Thank you! See you again (￣y▽,￣)╭ ");
                default -> System.out.println("Try again!");
            }
        } while (userChoice > 0 && userChoice != menu.size());
    }
}
