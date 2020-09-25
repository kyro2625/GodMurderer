package GUI;
    import java.util.ArrayList;
    import java.util.Scanner;
    import java.util.Vector;

public class Menu extends Vector {
    public Menu(){
        super();
    }
    void addMenuItem(String S){
        this.add(S);
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
            if(valid== false) System.out.print("Choose again! ");
        } while(valid==false);
        return choice;
    }
}
