package List1;

import com.Menu;

public class LL_Employee_Program {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.add("Add new employee");
        menu.add("Remove an employee");
        menu.add("Increase salary of an employee");
        menu.add("Print employee list");
        menu.add("Quit");
        LL_EmpList empList = new LL_EmpList();
        int userChoice;
        do {
            userChoice = menu.getUserChoice();
            switch (userChoice) {
                case 1 -> empList.add();
                case 2 -> empList.remove();
                case 3 -> empList.increaseSalary();
                case 4 -> empList.print();
            }
        }
        while (userChoice>0 && userChoice<5);
    }
}
