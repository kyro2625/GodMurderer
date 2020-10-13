package GUI;

import DAO.UserList;
import UTIL.FileManagement;

public class UserManager {
    public static void main(String[] args) {
        UserList list = new UserList();
        FileManagement fm = new FileManagement();
        Menu menu = new Menu();
        menu.add("1. Create user account");
        menu.add("2. Check exist user");
        menu.add("3. Search user information by name");
        menu.add("4. Update user");
        menu.add("5. Delete user");
        menu.add("6. Save account to file");
        menu.add("7. Print list user from file");
        menu.add("8. Quit");
        menu.add("Enter your choice : ");
        int userChoice, userChoice2;
        do {
            System.out.println("\nSTUDENT MANAGEMENT");
            for (Object str : menu) {
                System.out.println(str);
            }
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1 -> {
                    list.createAccount();
                    System.out.println();
                }
                case 2 -> {
                    list.checkExistedUser();
                    System.out.println();
                }
                case 3 -> {
                    list.searchUser();
                    System.out.println();
                }
                case 4 -> {
                    System.out.println("    ");
                    list.updateUser();
                    System.out.println();
                }
                case 5 -> {
                    list.deleteUser();
                    System.out.println();
                }
                case 6 -> {
                    list.saveFile();
                    System.out.println();
                }
                case 7 -> {
                    list.printFile();
                    System.out.println();
                }
                case 8 -> {
                    list.saveFile();
                    System.out.println("Thank you for using our service!");
                    return;
                }
                default -> System.out.println("No supported. Enter choice again");
            }
        } while (userChoice > 0 && userChoice != menu.size());
    }
}
