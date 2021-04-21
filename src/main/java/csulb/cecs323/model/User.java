package csulb.cecs323.model;
import javax.persistence.*;

@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "USERS")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long user_id;

    @Column(length=20, nullable = false)
    private String first_name;

    @Column(length=20, nullable = false)
    private String last_name;

    @Column(length=30,nullable = false)
    private String user_name;

    @Column(length=30,nullable = false)
    private String password;

    @Column(length=30,nullable = false)
    private String email;

    @Column
    private java.sql.Date date_registered;

    public User(String first_name, String last_name, String user_name, String password,
                String email, java.sql.Date date_registered){
        this.setFirst_name(first_name);
        this.setLast_name(last_name);
        this.setUser_name(user_name);
        this.setPassword(password);
        this.setEmail(email);
        this.setDate(date_registered);
    }

    public User() {}

    public long getUser_id() {return user_id;}

    public String getFirst_name() {return first_name;}

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {return last_name;}

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUser_name() {return user_name;}

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {return password;}

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {return email;}

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDate(java.sql.Date date_registered) { this.date_registered = date_registered; }

    public java.sql.Date getDate(){ return this.date_registered; }

    @Override
    public String toString(){
        return "User ID: " + this.getUser_id() + " First Name: " + this.first_name + " Last Name: " + this.last_name
                + " User Name: " + this.user_name + "Password: " + this.password + " Email: " + this.email
                + " Date Registered: " + this.date_registered;
    }

}




