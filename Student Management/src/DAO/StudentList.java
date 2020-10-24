package DAO;

import DTO.Student;
import UTIL.MyValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentList extends ArrayList<Student> {


    transient Scanner sc = new Scanner(System.in);

    private boolean confirmation(String c) {
        boolean t = true;   
        if (c.equalsIgnoreCase("N")) {
            t = false;
        }
        return t;
    }

    public int findStudentIO(String id) {
        for (int i = 0; i < this.size(); i++) {
            if (id.equals(this.get(i).getId())) {
                return i;
            }
        }
        return -1;
    }

    public boolean validID(String id) {
        for (Student student : this) {
            if (id.equals(student.getId())) {
                return false;
            }
        }
        return true;
    }


    public void addStudent() {
        String id, firstName, lastName, gender, DoB, email, phoneNumber, confirm = null;
        boolean matched, duplicated, confirmed, valid = true;
        System.out.print("Do you want to continue? (Y/N): ");
        while (valid) {
            confirm = MyValidation.checkInputString();
            if ((confirm.equalsIgnoreCase("Y")) || (confirm.equalsIgnoreCase("N"))) {
                valid = false;
            } else {
                System.out.print("Wrong input format! Please try again! (Y/N): ");
            }
        }
        confirmed = confirmation(confirm);
        if (confirmed) {
            while (true) {
                System.out.println("    Enter student ID: ");
                do {
                    do {

                        System.out.print("      Code(format SE000000): ");
                        id = sc.nextLine().toUpperCase();
                        matched = id.matches("^SE\\d{6}$");// Pattern: SE and 6 digits
                        duplicated = validID(id);
                        if (!matched) {
                            System.err.print("      Wrong input format!");
                            System.out.println("        The code: SE and 6 digits.");
                        }
                        if (!duplicated) {
                            System.out.println("        Student ID is duplicated. Pleas re-input.");
                        }
                    } while ((!matched) || (!duplicated));
                } while (id.equals(""));

                System.out.print("      Enter student first name: ");
                firstName = MyValidation.checkInputString();

                System.out.print("      Enter student last name: ");
                lastName = MyValidation.checkInputString();

                System.out.print("      Enter student gender (Male/Female): ");
                gender = MyValidation.checkGender();

                do {
                    System.out.print("      Enter student Date of Birth (DD/MM/YYYY): ");
                    DoB = sc.nextLine();
                } while (MyValidation.checkDate(DoB));

                System.out.print("      Enter student email: ");
                email = MyValidation.checkEmail();

                System.out.print("      Enter student phone number: ");
                phoneNumber = MyValidation.checkPhoneNumber();

                if (MyValidation.checkStudentExist(this, id, lastName, firstName)) {
                    this.add(new Student(id, firstName, lastName, gender, DoB, email, phoneNumber));
                    System.out.println("    Add new Student success!");
                    return;
                }
                System.out.println("    Duplicate! Please try again!");
            }
        } else {
            System.out.println("    Confirmed cancel!");
        }
    }

    public void updateStudent() {
        String id, confirm = null;
        boolean matched, valid = true;
        int pos;
        System.out.print("Do you want to continue? (Y/N): ");
        while (valid) {
            confirm = MyValidation.checkInputString();
            if ((confirm.equalsIgnoreCase("Y")) || (confirm.equalsIgnoreCase("N"))) {
                valid = false;
            } else {
                System.out.print("Wrong input format! Please try again! (Y/N): ");
            }
        }
        boolean confirmed = confirmation(confirm);
        if (confirmed) {
            do {
                System.out.print("Enter the ID of student you want to update: ");
                id = sc.nextLine().toUpperCase();

                pos = findStudentIO(id);
                matched = id.matches("^SE\\d{6}$");
                if (!matched) {
                    System.err.print("      Wrong input format! ");
                    System.out.println("        The code: SE and 6 digits.");
                }
                if (pos < 0) {
                    System.out.print("This code does not exist! Do you want to try again? (Y/N): ");
                    confirm = sc.nextLine();
                    confirmed = confirmation(confirm);
                    if (!confirmed) {
                        return;
                    }
                }
                if (pos >= 0) {
                    String oldFirstName = this.get(pos).getFirstName();
                    System.out.print("Old first name: " + oldFirstName + ", new first name: ");
                    String newFirstName = sc.nextLine();
                    if (!newFirstName.equals("")) {
                        this.get(pos).setFirstName(newFirstName);
                    }

                    String oldLastName = this.get(pos).getLastName();
                    System.out.print("Old last name: " + oldLastName + ", new last name: ");
                    String newLastName = sc.nextLine();
                    if (!newLastName.equals("")) {
                        this.get(pos).setLastName(newLastName);
                    }

                    String oldGender = this.get(pos).getGender();
                    System.out.print("Old gender: " + oldGender + ", new gender (Male/Female): ");
                    String newGender = sc.nextLine().toUpperCase();
                    if (!newGender.equals("")) {
                        this.get(pos).setGender(newGender);
                    }

                    String oldDoB = this.get(pos).getDoB();
                    String newDoB;
                    System.out.print("Old Date of Birth: " + oldDoB + ", new Date of Birth (DD/MM/YYYY): ");
                    newDoB = sc.nextLine();
                    if (!newDoB.isEmpty()) {
                        while (MyValidation.checkDate(newDoB)) {
                            System.out.print("Wrong input format! Please enter again: ");
                            newDoB = sc.nextLine();
                        }
                        this.get(pos).setDoB(newDoB);
                    }

                    String oldEmail = this.get(pos).getEmail();
                    System.out.print("Old email: " + oldEmail + ", new email: ");
                    String newEmail = MyValidation.checkEmail();
                    this.get(pos).setEmail(newEmail);

                    String oldPhoneNumber = this.get(pos).getPhoneNumber();
                    System.out.print("Old phone number: " + oldPhoneNumber + ", new phone number: ");
                    String newPhoneNumber = MyValidation.checkPhoneNumber();
                    this.get(pos).setPhoneNumber(newPhoneNumber);

                    System.out.println("The student ID: " + id + " has been updated!");
                }
            } while ((!matched) || (pos < 0));
        } else {
            System.out.println("Confirmed cancel!");
        }
    }

    public void removeStudent() {
        int pos;
        boolean matched, confirmed, confirmed1, valid = true;
        String id, confirm = null, confirm1, confirm2;
        System.out.print("Do you want to continue? (Y/N): ");
        while (valid) {
            confirm = MyValidation.checkInputString();
            if ((confirm.equalsIgnoreCase("Y")) || (confirm.equalsIgnoreCase("N"))) {
                valid = false;
            } else {
                System.out.print("Wrong input format! Please try again! (Y/N): ");
            }
        }
        confirmed = confirmation(confirm);
        if (confirmed) {
            do {
                System.out.print("Enter the ID of student you want to remove: ");
                id = sc.nextLine().toUpperCase();
                matched = id.matches("^SE\\d{6}$");
                if (!matched) {
                    System.err.print("      Wrong input format! ");
                    System.out.println("        The code: SE and 6 digits.");
                }
                pos = findStudentIO(id);
                if (pos < 0) {
                    System.out.print("This code does not exist! Do you want to try again? (Y/N): ");
                    confirm1 = sc.nextLine();
                    confirmed1 = confirmation(confirm1);
                    if (!confirmed1) {
                        return;
                    }
                }
            } while ((!matched) || (pos < 0));

            if (pos >= 0) {
                System.out.print("Are you sure want to remove this student? (Y/N): ");
                confirm2 = MyValidation.checkInputString();
                if (confirm2.equals("Y")) {
                    this.remove(pos);
                    System.out.println("This student with ID " + id + " has been removed!");
                }
                if (confirm2.equals("N")) {
                    System.out.println("The command has been cancelled!");
                }
            } else {
                System.out.println("Confirmed cancel!");
            }
        }
    }
}
