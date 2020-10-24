package DAO;


import DTO.User;
import UTIL.FileManagement;
import UTIL.Validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UserList {
    private ArrayList<User> list = new ArrayList<>();

//    public UserList() {
//        super();
//    }

    // create user account
    public void createAccount() {
        list = FileManagement.loadUser();
        while (true) {
            String username;
            while (true) {
                System.out.print("Enter username: ");
                username = Validation.checkInputUserName();

                // check exist username
                if (!Validation.checkUserExisted(list, username)) {
                    System.out.println("Username existed! Enter again");
                } else {
                    break;
                }
            }
            // enter password
            System.out.print("Enter password: ");
            String password = Validation.checkInputPass();

            // check password again
            while (true) {
                System.out.print("Enter confirm password: ");
                String password_again = Validation.checkInputPass2();
                if (password_again.equals(password)) {
                    break;
                } else System.out.println("Confirm password not matches password. Please enter again: ");
            }
            String enpassword = Validation.sha256(password);
            // enter other in4
            System.out.print("Enter first name: ");
            String firstname = Validation.checkInputString();
            System.out.print("Enter last name: ");
            String lastname = Validation.checkInputString();
            System.out.print("Enter phone number: ");
            String phoneNumber = Validation.checkInputPhone();
            System.out.print("Enter email: ");
            String email = Validation.checkInputEmail();
            list.add(new User(username, enpassword, firstname, lastname, phoneNumber, email));
            FileManagement.writeUser(list);
            System.out.println("Added");
            System.out.println("New data shoud be saved first!");
            System.out.print("Continue to create user account? (y/n)");
            if (!Validation.checkInputYN()) {
                return;
            }
            System.out.println();
        }
    }

    // check exist user
    public void checkExistedUser() {
        list = FileManagement.loadUser();
        while (true) {
            System.out.print("Enter username: ");
            String username = Validation.checkInputString();

            if (!Validation.checkUserExisted(list, username)) {
                System.out.println("This username is existed!");
            } else {
                System.out.println("This username isn't existed!");
            }

            System.out.print("Continue to check existed user? (y/n): ");
            if (!Validation.checkInputYN()) {
                return;
            }
            System.out.println();
        }
    }

    // search user in4 by name
    public void searchUser() {
        list = FileManagement.loadUser();

        // check if list is empty
        if (list.isEmpty()) {
            System.err.println("The file is empty! Nothing to search");
            return;
        }
        while (true) {
            int count = -1;
            System.out.print("Enter a search string (a part of first name or last name) for search: ");
            String searchString = Validation.checkInputString();
            for (User user : list) {
                if (user.getFirstName().toLowerCase().contains(searchString.toLowerCase()) || user.getLastName().toLowerCase().contains(searchString.toLowerCase())) {
                    count++;
                }
            }
            if (count > -1) {
                try {
                    int result = count + 1;
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.println("Search user with keyword " + searchString.toUpperCase() + " return " + result + " result(s)");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.printf("%5s  | %15s     | %70.65s     | %15s     | %15s     | %15s     | %20s     |\n", "No.", "USERNAME", "Encrypted Password (SHA-256)", "First Name", "Last Name", "Phone Number", "Email");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

                    for (User user : list) {
                        if (user.getFirstName().toLowerCase().contains(searchString.toLowerCase()) || user.getLastName().toLowerCase().contains(searchString.toLowerCase())) {
                            System.out.printf("%5d  | %15s     | %70.65s     | %15s     | %15s     | %15s     | %20s     |\n", list.indexOf(user), user.getUsername(), user.getPassword(), user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getPhone(), user.getEmail());
                            // System.out.print("User info: " + user);
                            //return;
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Found error while loading file");
                }
            } else {
                System.out.println("Can not find user with keyword " + searchString.toUpperCase() + "!");
//                System.out.print("Do you want to continue search user? (y/n): ");
//                if (!Validation.checkInputYN()) {
//                    return;
//                } else break;
            }
            System.out.print("Do you want to continue search user? (y/n): ");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
    }


    // search user
    // update user
    public void updateUser() {
        Scanner sc = new Scanner(System.in);
        list = FileManagement.loadUser();
        boolean confirmed;
        int pos = -1;
        do {
            // login
            System.out.println("Please login first ");
            System.out.print("Username: ");
            String username = Validation.checkInputString();
            System.out.print("Password: ");
            String password = Validation.checkInputPass();
            pos = Validation.checkUserExisted2(list, username);
            // check existed
            String enpassword = Validation.sha256(password);
            if (pos < 0 || Validation.checkPassExisted2(list, enpassword) < 0) {
                System.out.println("Username is not existed or password is wrong!");
//                System.out.print(" Do you want to try again? (y/n): ");
//                if (!Validation.checkInputYN()) {
//                    return;
//                }
            }
            if (pos >= 0 && Validation.checkPassExisted2(list, enpassword) >-1) {
                System.out.print("Enter new first name: ");
                String firstname = sc.nextLine();
                if (!firstname.isEmpty())
                    list.get(pos).setFirstName(firstname);

                System.out.print("Enter new last name: ");
                String lastname = sc.nextLine();
                if (!lastname.isEmpty())
                    list.get(pos).setLastName(lastname);

                System.out.print("Enter new phone: ");
                String phone = Validation.checkInputPhone2();
                if (phone != null) list.get(pos).setPhone(phone);

                System.out.print("Enter new email: ");
                String mail = Validation.checkInputEmail2();
                if (mail != null) list.get(pos).setEmail(mail);
                System.out.println("Update successful");
                System.out.println("Update data should be saved!");
                FileManagement.writeUser(list);
            }
            System.out.print("Continue to update a user? (y/n): ");
            confirmed = Validation.checkInputYN();
            if (!confirmed) {
                return;
            }
        } while (true);
    }


    // delete user
    public void deleteUser() {
        list = FileManagement.loadUser();

        while (true) {
            // login
            System.out.println("Please login first");
            System.out.print("Username: ");
            String username = Validation.checkInputString();
            System.out.print("Password: ");
            String password = Validation.checkInputPass();
            int pos = Validation.checkUserExisted2(list, username);
            // check username or password is correct
            String enpassword = Validation.sha256(password);
            if (pos < 0 || Validation.checkPassExisted2(list, enpassword) < 0) {
                System.out.println("Username is not existed or password is wrong!");
//                System.out.print("Do you want to try again? (y/n): ");
//                if (!Validation.checkInputYN()) {
//                    return;
//                }
            } else {
                // remove user
                System.out.println("User " + list.get(pos).getUsername() + " has been removed!");
                list.remove(pos);
                FileManagement.writeUser(list);
            }

            System.out.print("Continue to remove a user? (y/n): ");
            if (!Validation.checkInputYN()) {
                return;
            }
        }
    }

    // save to file
    public void saveFile() {
        if (list.isEmpty()) {
            System.out.println("Nothing to save!");
            return;
        }
        FileManagement.writeUser(list);
        System.out.println("Saved!");
    }

    // print all list from file
    public void printFile() {
        int count = -1;
        list = FileManagement.loadUser();
        if (list.isEmpty()) {
            System.err.println("The file is empty, nothing to show!");
            return;
        }
        try {
            Collections.sort(list, User.FirstNameComparator);
            // System.out.println("The list show in order: Username, Encrypted Password, First Name, Last Name, Phone Number, Email");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%5s  | %15s     | %70.65s     | %15s     | %15s     | %15s     | %20s     |\n", "No.", "USERNAME", "Encrypted Password (SHA-256)", "First Name", "Last Name", "Phone Number", "Email");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
            for (User user : list) {
                count++;
                System.out.printf("%5d  | %15s     | %70.65s     | %15s     | %15s     | %15s     | %20s     |\n", count, user.getUsername(), user.getPassword(), user.getFirstName().toUpperCase(), user.getLastName().toUpperCase(), user.getPhone(), user.getEmail());
            }
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        } catch (Exception e) {
            System.out.println("Found error while loading file");
        }
    }
}
