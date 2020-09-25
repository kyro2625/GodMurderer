package List1;

import com.Employee;

import java.util.Scanner;

public class LL_EmpList extends SLL {
    Scanner sc = null;

    public LL_EmpList() {
        super();
        sc = new Scanner(System.in);
    }

    //Find an employee with a known code-Linear search - Validation
    private SLLNode<Employee> find(String code) {
        SLLNode<Employee> ref;
        for (ref = this.getHead(); ref != null; ref = ref.next)
            if (ref.info.getCode().equals(code)) return ref;
        return null;
    }
    //Add a new employee to the end of the list
    public void add() {
        String code = "", name="";
        int salary =0;
        System.out.println("Add new employee: ");
        boolean proceed=false;
        //Enter employee's code
        do{
            System.out.print("Enter employee's code: ");
            code= sc.nextLine().toUpperCase();
            proceed = find(code)!=null;
            if (proceed==true) System.out.println("This code is duplicated!");
        }
        while (proceed);
        //Enter employee's name
        do {
            System.out.print("Enter employee's name: ");
            name = sc.nextLine().trim();
            proceed= (name.length()==0);
            if (proceed==true) System.out.println(("Employee's name must be inputted!"));
        }
        while (proceed);
        //Enter employee's salary
        do{
            System.out.print("Enter employee's salary: ");
            salary = Integer.parseInt(sc.nextLine());
            if (salary<=0) System.out.println("Employee's salary must be greater than 0!");
        }
        while (salary<=0);
        //Add this employee to the end of the list
        Employee emp = new Employee(code,name, salary);
        this.addToTail(emp);
        System.out.println("This employee is added");
    }
    //Remove an employee from the list
    public void remove() {
        if (this.isEmpty()) System.out.println("The list is empty!");
        else {
            String code="";
            System.out.print("Enter the code of employee who will be removed: ");
            code = sc.nextLine().toUpperCase();
            SLLNode<Employee> ref = find(code);
            if (ref==null) System.out.println("He/She does not exist!");
            else {
                this.delete((ref.info));
                System.out.println("This employee has been removed!");
            }
        }
    }
    //Increasing the salary of an employee
    public void increaseSalary() {
        if (this.isEmpty()) System.out.println("THe list is empty!");
        else {
            String code="";
            System.out.print("Enter the code of employee who will be promoted: ");
            code =sc.nextLine().toUpperCase();
            SLLNode<Employee> ref = find(code);
            if (ref ==null) System.out.println("He/She does not exist!");
            else {
                int oldSal = ref.info.getSalary();
                int newSal;
                do {
                    System.out.print("Old salary: "+ oldSal + ", new salary: ");
                    newSal = Integer.parseInt(sc.nextLine());
                }
                while(newSal<= oldSal);
                ref.info.setSalary(newSal);
                System.out.println("His/Her salary has been updated!");
            }
        }
    }
    //Print the list
    public void print() {
        if (this.isEmpty()) System.out.println("The list is empty!");
        else {
            System.out.println("EMPLOYEE LIST");
            this.printAll();
        }
    }
}
