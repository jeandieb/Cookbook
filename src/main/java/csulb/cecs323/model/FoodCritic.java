package csulb.cecs323.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
//@DiscriminatorValue(value = "FOODCRITIC")//used with SINGLE_TABLE
public class FoodCritic extends User
{
    private String currentPlatform;

    //private int numOfReview;

    @OneToMany(mappedBy = "foodCritic", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Review> reviews = new HashSet<>();

    public FoodCritic() {}

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
