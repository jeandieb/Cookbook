package csulb.cecs323.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Inheritance
@DiscriminatorColumn(name = "USER_TYPE")
@Table(name = "USER")
public class User implements Serializable
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

    public User() {};

    public User (String fn, String ls, String userName, String pw, String email, LocalDateTime dateReg)
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
}
