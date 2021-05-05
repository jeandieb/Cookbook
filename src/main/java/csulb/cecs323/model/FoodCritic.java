package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "FOODCRITICS")
/**
 * A FoodCritic is a type of User who writes Reviews to food recipes
 */
public class FoodCritic extends User
{
    private String currentPlatform;


    @OneToMany(mappedBy = "foodCritic", orphanRemoval = true, cascade = CascadeType.PERSIST)
    /**
     * Connects FoodCritic to all reviews
     */
    private Set<Review> reviews = new HashSet<>();
    /**
     * Subtotal for number of reviews done by FoodCritic
     */
    private int numberOfReview = 0;

    /**
     * Empty Constructor for FoodCritic
     */
    public FoodCritic() {}

    /**
     * Constructor for creating a FoodCritic
     * @param firstName first name of food critic
     * @param lastName last name of food critic
     * @param userName username of account
     * @param password password to access account
     * @param email email used to register account
     * @param dateRegistered date when food critic first registered
     * @param currentPlatform platform used
     */
    public FoodCritic(String firstName, String lastName, String userName, String password, String email, LocalDateTime dateRegistered, String currentPlatform)
    {
        super(firstName, lastName, userName, password, email, dateRegistered);
        this.setCurrentPlatform(currentPlatform);
    }

    public String getCurrentPlatform() {
        return currentPlatform;
    }

    public void setCurrentPlatform(String currentPlatform) {
        this.currentPlatform = currentPlatform;
    }

    public Set<Review> getReview() {
        return reviews;
    }


    public int getNumberOfReview()
    {
        return reviews.size();
    }

    /**
     * Adds a Review to a FoodCritic
     * @param review review done by food critic
     */
    public void addReview(Review review) {
        boolean added = this.reviews.add(review);
        if (added) {
            review.setFoodCritic(this);
        }
        numberOfReview++;
    }


    @Override
    public String toString() {
        return this.getFirstName() + " " + this.getLastName() + " ID: " + this.getId();
    }
}
