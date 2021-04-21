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
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private LocalDate dateCompleted;
    private float rating;
    private String description;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Recipe recipe;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (nullable = false)
    private FoodCritic foodCritic;

    @OneToOne
    @JoinColumn(name = "PREVIOUSREVIEW")
    private Review previousReview;

    @OneToOne(mappedBy = "previousReview")
    @JoinColumn(name = "RECENTREVIEW")
    private Review recentReview;

    public Review (){}

    public Review(FoodCritic criticID, LocalDate dateCompleted, float rating, String description)
    {
        this.foodCritic = criticID;
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

