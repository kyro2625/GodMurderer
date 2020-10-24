package GUI;

import java.util.Scanner;
import java.util.Vector;

public class Menu extends Vector<String> {
    public Menu(){
        super();
    }
    int getUserChoice(){
        int choice = 0;
        Scanner sc= new Scanner(System.in);
        boolean valid;
        do{
            System.out.print("Choose: ");
            valid=true;
            try{
                choice= Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                valid= false;
            }
            if(choice<0) valid= false;
            if(!valid) System.out.print("Choose again! ");
        } while(!valid);
        return choice;
    }
    int getUserSubChoice(){
        int choice = 0;
        Scanner sc= new Scanner(System.in);
        boolean valid;
        do{
            System.out.print("      Choose: ");
            valid=true;
            try{
                choice= Integer.parseInt(sc.nextLine());
            }catch(NumberFormatException e){
                valid= false;
            }
            if(choice<0) valid= false;
            if(!valid) System.out.print("       Choose again! ");
        } while(!valid);
        return choice;
    }
}
