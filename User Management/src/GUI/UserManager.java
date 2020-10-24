package GUI;

import DAO.UserList;
import UTIL.Validation;

public class UserManager {
    public static void main(String[] args) {
        UserList list = new UserList();
        Menu menu = new Menu();
        menu.add("1. Create user account");
        menu.add("2. Check exist user");
        menu.add("3. Search user information by name");
        menu.add("4. Update user");
        menu.add("5. Save account to file");
        menu.add("6. Print list user from file");
        menu.add("7. Quit program");
        int userChoice;
        do {
            System.out.println("\n-----STUDENT MANAGEMENT-----");
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
                    Menu menu1 = new Menu();
                    int userChoice2;
                    menu1.add("     1 - Update user's information.");
                    menu1.add("     2 - Delete user");
                    menu1.add("     3-  Return to main menu");
                    do {
                        System.out.println("     --Update User--");
                        for (Object sub: menu1) System.out.println(sub);
                        userChoice2 = menu1.getUserSubChoice();
                        switch (userChoice2) {
                            case 1 -> {
                                list.updateUser();
                                System.out.println();
                            }
                            case 2 -> {
                                list.deleteUser();
                                System.out.println();
                            }
                            case 3 -> {
                                break;
                            }
                            default -> System.out.println("     No supported function. Enter choice again");
                        }
                    } while (userChoice2 >= 0 && userChoice2 != menu1.size());
                    System.out.println();
                }
                case 5 -> {
                    list.saveFile();
                    System.out.println();
                }
                case 6 -> {
                    list.printFile();
                    System.out.println();
                }
                case 7 -> {
                    System.out.print("Do you want to save file before exit program? (y/n): ");
                    if (Validation.checkInputYN()) {
                        list.saveFile();
                    }
                    System.out.println("Thank you for using our service!");
                    return;
                }
                default -> System.out.println("No supported. Enter choice again");
            }
        } while (userChoice >= 0 && userChoice != menu.size());
    }
}
