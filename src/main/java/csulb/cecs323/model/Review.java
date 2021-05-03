package csulb.cecs323.model;

import jdk.management.jfr.RecordingInfo;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;

@Entity
@Table(name = "REVIEWS")
/**
 * A User posts a Review, which is a post used to document a user's opinion of the recipe
 */
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private LocalDate dateCompleted;
    private float rating;
    private String description;

    /**
     * Connects Review to a Recipe
     */
    @ManyToOne
    @JoinColumn(nullable = false)
    private Recipe recipe;


    /**
     * Connects Review to a FoodCritic
     */
    @ManyToOne
    @JoinColumn (nullable = false)
    private FoodCritic foodCritic;

    /**
     * Connecting one Review to another Review (recursive)
     */
    @OneToOne
    @JoinColumn(name = "PREVIOUSREVIEW")
    private Review previousReview;

    /**
     * Connecting one Review to another Review (recursive)
     */
      @OneToOne(mappedBy = "previousReview")
    @JoinColumn(name = "RECENTREVIEW")
    private Review recentReview;

      /**
     * Default constructor for Review
     */
    public Review (){}

      /**
     * Constructor for creating a Review
     * @param critic person who is writing a review for the Recipe
     * @param dateCompleted date that Review is done
     * @param rating rating given to the food being reviewed
     * @param description description given to review
     */
    public Review(FoodCritic critic, LocalDate dateCompleted, float rating, String description)
    {
        this.foodCritic = critic;
        this.dateCompleted = dateCompleted;
        this.rating = rating;
        this.description = description;
    }


    public LocalDate getDateCompleted() { return dateCompleted; }

    public void setDateCompleted(LocalDate dateCompleted) { this.dateCompleted = dateCompleted; }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Recipe getRecipe() { return recipe; }

    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    public FoodCritic getFoodCritic()
    {
        return this.foodCritic;
    }

    public void setFoodCritic(FoodCritic foodCritic)
    {
        this.foodCritic = foodCritic;
        foodCritic.addReview(this);
    }

    public Review getPreviousReview()
    {
        return previousReview;
    }

    public void setPreviousReview(Review previousReview)
    {
        this.previousReview = previousReview;
        previousReview.setRecentReview(this);
    }

    public Review getRecentReview()
    {
        return recentReview;
    }

    public void setRecentReview(Review recentReview)
    {
        this.recentReview = recentReview;
        recentReview.setPreviousReview(this);
    }

    @Override
    public String toString() {
        return "Review{" +
                "Id=" + Id +
                ", dateCompleted=" + dateCompleted +
                ", rating=" + rating +
                ", description='" + description + '\'' +
                '}';
    }
}

