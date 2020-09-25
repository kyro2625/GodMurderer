package UTIL;

import DAO.StudentList;
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

    public static boolean checkNumber(int number, int min, int max) {
        if ((number >= min) && (number <= max)) return true;
        return false;
    }

    public static boolean checkDate(String DoB) {
        final String DATE_FORMAT = "dd/MM/yyyy";
        try {
            SimpleDateFormat df = new SimpleDateFormat(DATE_FORMAT);
            df.setLenient(false); // set false để kiểm tra tính hợp lệ của date. VD: tháng 2 phải có 28-29 ngày, năm có 12 tháng,...
            df.parse(DoB); // parse dateString thành kiểu Date
        } catch (Exception e) { // quăng lỗi nếu dateString ko hợp lệ
            System.out.println("Invalid date");
            return false;
        }
        return true;
    }

    public static boolean checkIdExist(ArrayList<Student> ls, String id, String name) {
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && !name.equalsIgnoreCase(student.getName())) {
                return false;
            }
        }
        return true;
    }

    //check student exist
    public static boolean checkStudentExist(ArrayList<Student> ls, String id,
                                            String studentName, String lastName, String firstName) {
        int size = ls.size();
        for (Student student : ls) {
            if (id.equalsIgnoreCase(student.getId())
                    && studentName.equalsIgnoreCase(student.getName())
                    && lastName.equalsIgnoreCase(student.getLastName())
                    && firstName.equalsIgnoreCase(student.getFirstName())) {
                return false;
            }
        }
        return true;
    }

    public static int findStudent(ArrayList<Student> ls, String id) {

        for (int i = 0; i <= ls.size(); i++) {
            if (ls.get(i).getId().contains(id)) {
                return i;
            }
        }
        return -1;
    }
}
