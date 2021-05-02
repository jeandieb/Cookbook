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

    /** The first name of the user. */
    private String firstName;

    /** The last name of the user. */
    private String lastName;

    /** The username that a user wants to go by. */
    private String userName;

    /** The the password a user registered with. */
    private String password;

    /** The the email of the user which can be used to contact. */
    @Column(unique = true)
    private String email;

    /** The timestamp of when a user registered. */
    private LocalDateTime dateRegistered;

    /** A set of followers the current user has. */
    @ManyToMany
    @JoinTable(
            name = "FOLLOWER_FOLLOWING"
    )
    private Set<User> followers = new HashSet<>();

    /** A set of users the current user is following. */
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

    /**
     * Adds a user to a list of followers for a specific user
     * @param follower   A user the follows another user
     */
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


    /**
     * Adds a user that the current user wants to follow
     * @param following   The user that the current wants to follow
     */
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
