package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue(value = "FOODCRITIC")
@Table(name = "FOODCRITICS")
/**
 * A FoodCritic is a type of User who writes Reviews to food recipes
 */
public class FoodCritic extends User
{
    private String currentPlatform;


    /**
     * Connects FoodCritic to all reviews
     */
    @OneToMany(mappedBy = "foodCritic", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Review> reviews = new HashSet<>();
    
    /**
     * Subtotal for number of reviews done by FoodCritic
     */
    private int numberOfReview = reviews.size();

    /**
     * Empty Constructor for FoodCritic
     */
    public FoodCritic() {}

    /**
     * Constructor for creating a FoodCritic
     * @param fn first name of food critic
     * @param ls last name of food critic
     * @param userName username of account
     * @param pw password to access account
     * @param email email used to register account
     * @param dateReg date when food critic first registered
     * @param currentPlatform platform used
     */
    public FoodCritic(String fn, String ls, String userName, String pw, String email, LocalDateTime dateReg, String currentPlatform)
    {
        super(fn, ls, userName, pw, email, dateReg);
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
        return numberOfReview;
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
    }


    @Override
    public String toString() {
        return "FoodCritic{" +
                ", currentPlatform='" + currentPlatform + '\'' +
                ", review=" + reviews +
                '}';

    }
}
