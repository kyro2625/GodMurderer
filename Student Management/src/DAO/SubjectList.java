package DAO;

import DTO.Subject;
import UTIL.MyValidation;

import java.util.ArrayList;
import java.util.Scanner;

public class SubjectList extends ArrayList<Subject> {

    transient Scanner sc = new Scanner(System.in);
    private boolean confirmation(String c) {
        boolean t = true;
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

    public boolean validID(String id) {
        for (Subject subject : this) {
            if (id.equals(subject.getId())) {
                return false;
            }
        }
        return true;
    }

    public void addSubject() {
        String id, name, confirm = null;
        int credit;
        boolean duplicated, confirmed, valid = true;
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
                System.out.print("    Enter subject ID: ");
                do {
                    do {
                        id = sc.nextLine().toUpperCase();
                        duplicated = validID(id);
                        if (!duplicated) {
                            System.out.println("        Subject ID is duplicated. Pleas re-input.");
                        }
                    } while ((!duplicated));
                } while (id.equals(""));

                System.out.print("      Enter subject name: ");
                name = MyValidation.checkInputString();

                while (true) {
                    try {
                        System.out.print("      Enter subject credit: ");
                        credit = MyValidation.checkInputNumber();
                        break;
                    } catch (Exception e) {
                        System.out.println("    Wrong input format, please try again!");
                    }
                }
                if (MyValidation.checkSubjectExist(this, id, name, credit)) {
                    this.add(new Subject(id, name, credit));
                    System.out.println("    Add new Subject success!");
                    return;
                }
                System.out.println("    Duplicate! Please try again!");
            }
        } else {
            System.out.println("    Confirmed cancel!");
        }
    }

    public void updateSubject() {
        String id, confirm = null;
        int pos;
        boolean confirmed, valid = true;
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
                System.out.print("Enter the ID of subject you want to update: ");
                id = sc.nextLine().toUpperCase();

                pos = findSubjectIO(id);
                if (pos < 0) {
                    System.out.print("This code does not exist! Do you want to try again? (Y/N): ");
                    confirm = sc.nextLine();
                    confirmed = confirmation(confirm);
                    if (!confirmed) {
                        return;
                    }
                }
                if (pos >= 0) {
                    String oldName = this.get(pos).getName();
                    System.out.print("Old subject name: " + oldName + ", new subject name: ");
                    String newName = sc.nextLine();
                    if (!newName.equals("")) this.get(pos).setName(newName);

                    int oldCredit = this.get(pos).getCredit();
                    System.out.print("Old credit: " + oldCredit + ", new: ");
                    int newCredit = MyValidation.checkInputNumber();
                    this.get(pos).setCredit(newCredit);

                    System.out.println("The subject ID: " + id + " has been updated!");
                }
            } while ((pos < 0));
        } else {
            System.out.println("Confirmed cancel!");
        }
    }

    public void removeSubject() {
        int pos;
        String id, confirm = null, confirm1;
        boolean confirmed, valid = true;
        System.out.println("Do you want to continue? (Y/N): ");
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
                System.out.print("Enter the ID of subject you want to remove: ");
                id = sc.nextLine().toUpperCase();

                pos = findSubjectIO(id);
                if (pos < 0) {
                    System.out.print("This code does not exist! Do you want to try again? (Y/N): ");
                    confirm1 = sc.nextLine();
                    boolean confirmed1 = confirmation(confirm1);
                    if (!confirmed1) {
                        return;
                    }
                }
            } while ((pos < 0));

            if (pos >= 0) {
                System.out.print("Are you sure want to remove this subject? (Y/N): ");
                confirm1 = MyValidation.checkInputString();
                if (confirm1.equals("Y")) {
                    this.remove(pos);
                    System.out.println("This subject with ID " + id + " has been removed!");
                }
                if (confirm1.equals("N")) {
                    System.out.println("The command has been cancelled!");
                }
            } else {
                System.out.println("Confirmed cancel!");
            }
        }
    }
}
