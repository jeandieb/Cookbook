package csulb.cecs323.model;


import javax.persistence.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "REVIEWS",
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"recipe", "foodCritic", "description"})
)
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

    @ManyToOne
    @JoinColumn(nullable = false)
    /**
     * Connects Review to a Recipe
     */
    private Recipe recipe;


    @ManyToOne
    @JoinColumn (nullable = false)
      /**
     * Connects Review to a FoodCritic
     */
    private FoodCritic foodCritic;

    @OneToOne
    @JoinColumn
      /**
     * Connecting one Review to another Review (recursive)
     */
    private Review previousReview;


      /**
     * Default constructor for Review
     */
    public Review (){}

      /**
     * Constructor for creating a Review
     * @param Id id of a FoodCritic
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

