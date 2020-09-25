package GUI;

import DAO.StudentList;
import DTO.Student;

import java.util.Scanner;

public class Tester {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList stu = new StudentList();
        Menu menu = new Menu();
        menu.add("1. Add New Student");
        menu.add("2. Print out Student List");
        menu.add("3. Quit!");
        int userChoice;
        do {
            System.out.println("STUDENT MANAGEMENT");
            for (Object str : menu) {
                System.out.println(str);
            }
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1 -> stu.addStudent();
                case 2 -> stu.printStudent();
                case 3 -> System.out.println("Thank you!");
                default -> System.out.println("Try again!");
            }
        } while (userChoice > 0 && userChoice != menu.size());
    }
}
