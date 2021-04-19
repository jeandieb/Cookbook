package csulb.cecs323.model;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("FOODCRITIC")
public class FoodCritic extends User
{
    String currentPlatform;

    @OneToMany(mappedBy = "foodCritic", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Review> reviews = new HashSet<>();

    public FoodCritic() {};

    public FoodCritic(String currentPlatform) {
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
