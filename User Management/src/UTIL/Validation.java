package UTIL;

import DTO.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Scanner;

public class Validation {
    private static final String USERNAME_VALID = "^[a-zA-Z0-9]+$";
    private static final String PHONE_VALID = "^\\d{10,12}$";
    private static final String EMAIL_VALID = "^[\\w-_.+]*[\\w-_.]@([\\w]+\\.)+[\\w]+[\\w]$";
    private static final String PASSWORD_VALID = "^[a-zA-Z0-9!~@#$%&*()<>-]+$";
    // private static final String GENDER_VALID = "^male$|^female$";

    //check user input string
    public static String checkInputUserName() {
        Scanner in = new Scanner(System.in);
        //loop until user input correct
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else if (result.length() >= 5 && !result.contains(" ") && result.matches(USERNAME_VALID)) {
                return result;
            } else {
                System.out.println("Wrong input format! The username must be at least 5 characters and no spaces");
                System.out.print("Please enter username again: ");
            }
        }
    }

    public static String checkInputString() {
        Scanner in = new Scanner(System.in);
        //loop until user input correct
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Not empty");
                System.out.print("Enter again: ");
            } else return result;
        }
    }

    //check phone is number with minimum 9 to max 12 characters
    public static String checkInputPhone() {
        while (true) {
            String result = checkInputString();
            //check user input phone valid
            if (result.matches(PHONE_VALID)) {
                return result;
            } else {
                System.out.print("Invalid phone format. Phone number must be between 10 and 12 numbers. Enter again: ");
            }
        }
    }

    public static String checkInputPhone2() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String result = in.nextLine();
            if (result.isEmpty()) {
                return null;
            }
            //check user input phone valid
            if (result.matches(PHONE_VALID)) {
                return result;
            } else
                System.out.print("Invalid phone format. Phone number must be between 10 and 12 numbers. Enter again: ");
        }
    }

    //check email
    public static String checkInputEmail() {
        //loop until user input correct
        while (true) {
            String result = checkInputString();
            //check user input email valid
            if (result.matches(EMAIL_VALID)) {
                return result;
            } else {
                System.out.println("Email with format <account name>@<domain>");
                System.out.print("Enter again: ");
            }
        }
    }

    public static String checkInputEmail2() {
        Scanner in = new Scanner(System.in);
        while (true) {
            String result = in.nextLine();
            if (result.isEmpty()) {
                return null;
            }
            //check user input phone valid
            if (result.matches(EMAIL_VALID)) {
                return result;
            } else
                System.out.print("Wrong format. Email must be with format <account name>@<domain>. Please enter again: ");

        }
    }

    // check password
    public static String checkInputPass() {
        // loop until user input correct
        while (true) {
            String result = checkInputString();
            // check user input password valid
            if (result.length() >= 6 && result.matches(PASSWORD_VALID) && !result.contains(" "))
                return result;
            else {
                System.out.println("Password is invalid (password must be at least 6 characters and must not have space between: ");
                System.out.print("Enter password again: ");
            }
        }
    }

    public static String checkInputPass2() {
        // loop until user input correct
        while (true) {
            String result = checkInputString();
            // check user input password valid
            if (result.length() >= 6 && result.matches(PASSWORD_VALID) && !result.contains(" "))
                return result;
            else {
                System.out.println("Comfirm Password is invalid (password must be at least 6 characters and must not have space between: ");
                System.out.print("Enter confirm password again: ");
            }
        }
    }

    // check user input yes/no
    public static boolean checkInputYN() {
        //System.out.print("Do you want to continue? (Y/N): ");
        while (true) {
            String result = checkInputString();
            if (result.equalsIgnoreCase("Y"))
                break;
            if (result.equalsIgnoreCase("N"))
                return false;
            System.err.println("Please input y/Y or n/N");
            System.out.print("Enter again: ");
        }
        return true;
    }

    // check user existed
    public static boolean checkUserExisted(ArrayList<User> list, String username) {
        for (User user : list) {
            if (username.equals(user.getUsername())) {
                return false;
            }
        }

        return true;
    }

    // check password existed
    public static int checkPassExisted2(ArrayList<User> list, String password) {
        for (User user : list) {
            if (password.equals(user.getPassword())) {
                return list.indexOf(user);
            }
        }
        return -1;
    }

    // check user existed
    public static int checkUserExisted2(ArrayList<User> list, String username) {
        for (User user : list) {
            if (username.equals(user.getUsername())) {
                return list.indexOf(user);
            }
        }

        return -1;
    }

    public static String sha256(String password) {
        try {
            // Static getInstance method is called with hashing SHA
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            //return array of byte of the password
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            // Convert array of byte digest into hex value
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                // Convert message digest into hex value
                String hex = Integer.toHexString(0xff & b);
                // Pad with leading zeros
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}



