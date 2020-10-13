package DTO;

import java.util.Comparator;

public class User {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String phone;
    private String email;

    public User(String username, String password, String firstName, String lastName,
                String phone, String email) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.phone = phone;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return username + "," + password + "," + firstName + "," + lastName + "," + phone + "," + email;
    }

    public static Comparator<User> FirstNameComparator = new Comparator<User>() {

        public int compare(User s1, User s2) {
            String FirstName1 = s1.getFirstName().toUpperCase();
            String FirstName2 = s2.getFirstName().toUpperCase();

            //ascending order
            return FirstName1.compareTo(FirstName2);

            //descending order
            //return StudentName2.compareTo(StudentName1);
        }
    };

}

