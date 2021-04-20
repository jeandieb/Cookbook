package csulb.cecs323.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING) //used for SINGLE_TABLE
//@Table(name = "USER") //USER is a SQL KEYWORD!!
@Entity
public class User1 //implements Serializable
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String firstName;

    private String lastName;

    private String userName;

    private String password;

    private String email;

    private LocalDateTime dateRegistered;

    public User1() {}

    public User1 (String fn, String ls, String userName, String pw, String email, LocalDateTime dateReg)
    {
        setFirstName(fn);
        setLastName(ls);
        setUserName(userName);
        setPassword(pw);
        setEmail(email);
        setDateRegistered(dateReg);
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(LocalDateTime dateRegistered) {
        this.dateRegistered = dateRegistered;
    }

    public long getId() {
        return Id;
    }
}
