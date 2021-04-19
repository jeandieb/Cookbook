package csulb.cecs323.model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;
import java.util.Date;
import java.text.DateFormat;

@Entity
public class Review {
    @Id @GeneratedValue
    private int recipeID;
    @Id @GeneratedValue
    private int criticID;
    private Date dateCompleted;
    private float rating;
    private String description;

    @ManyToOne
    @JoinColumn
    private Review review;


    @OneToMany(mappedBy = "review")
    private Set<Review> reviews;


    public Review() {
        reviews = new HashSet<>();
    }

    public Review(int recipeID, int criticID, Date dateCompleted, float rating, String description) {
        this.recipeID = recipeID;
        this.criticID = criticID;
        this.dateCompleted = dateCompleted;
        this.rating = rating;
        this.description = description;
        reviews = new HashSet<>();
    }

    public int getRecipeID() { return recipeID; }

    public void setRecipeID(int recipeID) { this.recipeID = recipeID; }

    public int getCriticID() { return criticID; }

    public void setCriticID(int criticID) { this.criticID = criticID; }

    public Date getDateCompleted() { return dateCompleted; }

    public void setDateCompleted(Date dateCompleted) { this.dateCompleted = dateCompleted; }

    public float getRating() { return rating; }

    public void setRating(float rating) { this.rating = rating; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Review getReview() { return review; }

    public void setReview(Review review) { this.review = review; }

    public Set<Review> getReviews() { return reviews; }

    public void setReviews(Set<Review> reviews) { this.reviews = reviews; }

    @Override
    public String toString() {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        String strDate = df.format(dateCompleted);
        return String.format("Review[recipeID = %d, criticID= %d, dateCompleted = %s, rating = %s, description = %s]",
                recipeID, criticID, strDate, rating, description);
    }
}

