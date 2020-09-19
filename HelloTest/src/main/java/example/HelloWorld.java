package example;

import java.awt.*;
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
        System.out.print("Enter the period time between two execution (seconds): ");
        Long numrepeat = sc.nextLong();
        Object obj;
        for (int i = 1; i < 10; i++) {
            Thread.sleep(numrepeat*1000); //Suspend 5 seconds
            try {
                obj = new JSONParser().parse(new FileReader("C:\\Users\\Kyro\\IdeaProjects\\HelloTest\\src\\main\\java\\example\\name.json"));
                JSONObject jsonObject = (JSONObject) obj;

                // read firstName và lastName
                String firstName = (String) jsonObject.get("firstName");
                String lastName = (String) jsonObject.get("lastName");
                long age = (Long) jsonObject.get("age");
                System.out.println("firstName: " + firstName);
                System.out.println("lastName: " + lastName);
                System.out.println("age: "+ age);
//                TestSleepMethod1 t1 = new TestSleepMethod1();
//                TestSleepMethod1 t2 = new TestSleepMethod1();
//
//                t1.start();
//                t2.start();
                System.out.println("This is round: " + i + "\n");
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
