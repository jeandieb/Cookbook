package csulb.cecs323.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "USER_TYPE", discriminatorType = DiscriminatorType.STRING) //used for SINGLE_TABLE
@Table(name = "USERS") //USER is a SQL KEYWORD!!
@Entity
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

    @ManyToMany
    @JoinTable(
            name = "FOLLOWER_FOLLOWING"
    )
    private Set<User> followers = new HashSet<>();

    @ManyToMany(mappedBy = "followers")
    private Set<User> followings = new HashSet<>();

    public User() {}

    public User(String fn, String ls, String userName, String pw, String email, LocalDateTime dateReg)
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

    public Set<User> getFollowers() {
        return followers;
    }

    public void addFollower(User follower)
    {
        boolean added = this.followers.add(follower);
        if (added)
        {
            follower.addFollowing(this);
        }
    }

    public Set<User> getFollowings() {
        return followings;
    }

    //follows
    public void addFollowing(User following) {
        boolean added = this.followings.add(following);
        if(added)
        {
            following.addFollower(this);
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", dateRegistered=" + dateRegistered +
                '}';
    }
}
