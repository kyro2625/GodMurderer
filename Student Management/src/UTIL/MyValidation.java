package UTIL;

import DTO.Student;
import DTO.Subject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class MyValidation extends ArrayList<Student> {
    public MyValidation() {
        super();
    }
    private final static Scanner sc = new Scanner(System.in);

    public static String checkInputString() {
        //loop until user input correct
        while (true) {
            String result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Field Should Not Empty");
                System.out.print("\nEnter again: ");
            } else {
                return result;
            }
        }
    }

    public static String checkGender() {
        String gender = null;
        boolean valid = true;
        while (valid){
            gender = sc.nextLine();
            if (gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")) {
                valid = false;

            } else System.out.print("Wrong input format! Please try again: ");
        }
        return gender.toUpperCase();
    }


    public static Integer checkInputScore() {
        while (true) {
            int result = Integer.parseInt(sc.nextLine());
            if (result<=0 || result>10) {
                System.err.println("Field Should Not Exceed Below 0 and Above 10");
                System.out.print("\nEnter again: ");
            } else return result;
        }
    }

    public static int checkInputNumber() {
        while (true) {
            int result = Integer.parseInt(sc.nextLine());
            if (result<=0 || result >30) {
                System.err.println("Field Should Not Exceed Below 0 and Above 30");
                System.out.print("\nEnter again: ");
            } else {
                return result;
            }
        }
    }

    public static String checkPhoneNumber() {
       while (true) {
           String result = sc.nextLine().trim();
           boolean matched = result.matches("^\\d{10,12}$");

           if(result.isEmpty()) {
               System.err.println("Field Should Not Empty");
               System.out.print("\nEnter again: ");
           } else if (!matched) {
               System.out.print("Wrong format. Please enter again: ");
           } else {
               return result;
           }
       }
    }

    public static String checkEmail() {
        while (true) {
            String result = sc.nextLine().trim();
            boolean matched = result.matches("^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$");

            if(result.isEmpty()) {
                System.err.println("Field Should Not Empty");
                System.out.print("\nEnter again: ");
            } else if (!matched) {
                System.out.print("Wrong format. Please enter again: ");
            } else {
                return result;
            }
        }
    }
    public static boolean checkDate(String DoB) {
        final String DATE_FORMAT = "dd/MM/yyyy";
        try {
            SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,...
            df.parse(DoB); // parse dateString thành kiểu Date
        } catch (Exception e) { // quăng lỗi nếu dateString ko hợp lệ
            System.out.println("Invalid date");
            return true;
        }
        return false;
    }

    //check student exist
    public static boolean checkStudentExist(ArrayList<Student> ls, String id,
                                            String lastName, String firstName) {
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && lastName.equalsIgnoreCase(student.getLastName())
                    && firstName.equalsIgnoreCase(student.getFirstName())) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSubjectExist(ArrayList<Subject> ls, String id,
                                            String name, int credit) {
        for (Subject subject : ls) {
            if (id.equalsIgnoreCase(subject.getId())
                    && name.equalsIgnoreCase(subject.getName())
                    && (credit == subject.getCredit())) {
                return false;
            }
        }
        return true;
    }
}
