package example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class HelloWorld {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        long numrepeat;
        int i=1;
        while (true) {
            try {
                System.out.print("Enter the period time between two execution (seconds): ");
                numrepeat = Long.parseLong(sc.nextLine());
                System.out.println("Input success! Please wait!\n");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Wrong input format! Try again!");
            }
        }
        Object obj;
        while (i!=-1) {
            Thread.sleep(numrepeat * 1000); //Suspend 5 seconds
            try {
                obj = new JSONParser().parse(new FileReader("C:\\Users\\Kyro\\IdeaProjects\\HelloTest\\src\\main\\java\\example\\name.json"));
                JSONObject jsonObject = (JSONObject) obj;

                // read firstName và lastName
                String firstName = (String) jsonObject.get("firstName");
                String lastName = (String) jsonObject.get("lastName");
                long age = (Long) jsonObject.get("age");
                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println("age: " + age);
                System.out.println("This is round: " + i++ + "\n");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }
}
