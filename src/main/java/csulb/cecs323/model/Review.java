package csulb.cecs323.model;

import jdk.management.jfr.RecordingInfo;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private Date dateCompleted;
    private float rating;
    private String description;

    @ManyToOne
    private Recipe recipe;


    @ManyToOne
    private FoodCritic foodCritic;


    public Review (){};

    public Review(FoodCritic criticID, Date dateCompleted, float rating, String description)
    {
        this.foodCritic = criticID;
        this.dateCompleted = dateCompleted;
        this.rating = rating;
        this.description = description;
    }


    public Date getDateCompleted() { return dateCompleted; }

    public void setDateCompleted(Date dateCompleted) { this.dateCompleted = dateCompleted; }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Recipe getReview() { return recipe; }

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

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = df.format(dateCompleted);
        return String.format(" dateCompleted = %s, rating = %s, description = %s]",
               strDate, rating, description);
    }
}

