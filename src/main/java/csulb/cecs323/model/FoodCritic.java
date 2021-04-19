package csulb.cecs323.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FoodCritic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int criticID;

    private String currentPlatform;

    @OneToMany
    private List<Review> review = new ArrayList<>();

    public FoodCritic() {};

    public FoodCritic(int criticID, String currentPlatform, List<Review> review) {
        this.setCriticID(criticID);
        this.setCurrentPlatform(currentPlatform);
        this.setReview(review);
    }

    public int getCriticID() { return criticID; }

    public void setCriticID(int criticID) { this.criticID = criticID; }

    public String getCurrentPlatform() { return currentPlatform; }

    public void setCurrentPlatform(String currentPlatform) { this.currentPlatform = currentPlatform; }

    public List<Review> getReview() { return review; }

    public void setReview(List<Review> review) { this.review = review; }

    @Override
    public String toString() {
        return "FoodCritic{" +
                "criticID=" + criticID +
                ", currentPlatform='" + currentPlatform + '\'' +
                ", review=" + review +
                '}';
    }
}
