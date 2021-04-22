package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RECIPES")
public class Recipe
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;

    private String description;

    private String prepTime;

    private String cookTime;

    private int difficultyRating;

    private int numberOfServings;

    @OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Step> steps = new HashSet<>();

    @ManyToOne
    private Cuisine cuisine;

    @OneToMany (mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Review> reviews = new HashSet<>();

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (nullable = false)
    private Chef chef;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    public Recipe(){}

    public Recipe(String na, String desc, String prepT, String cookT, int difficultyRating, int numberOfServings)
    {
        this.setName(na);
        this.setDescription(desc);
        this.setPrepTime(prepT);
        this.setCookTime(cookT);
        this.setDifficultyRating(difficultyRating);
        this.setNumberOfServings(numberOfServings);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrepTime() {
        return prepTime;
    }

    public void setPrepTime(String prepTime) {
        this.prepTime = prepTime;
    }

    public String getCookTime() {
        return cookTime;
    }

    public void setCookTime(String cookTime) {
        this.cookTime = cookTime;
    }

    public int getDifficultyRating() {
        return difficultyRating;
    }

    public void setDifficultyRating(int difficultyRating) {
        this.difficultyRating = difficultyRating;
    }

    public int getNumberOfServings() {
        return numberOfServings;
    }

    public void setNumberOfServings(int numberOfServings) {
        this.numberOfServings = numberOfServings;
    }

    public Set<Step> getSteps()
    {
        return this.steps;
    }

    public void addStep(Step step)
    {
        boolean added = this.steps.add(step);
        if(added)
        {
            step.setRecipe(this);
        }
    }

    public Cuisine getCuisine()
    {
        return this.cuisine;
    }

    public void setCuisine(Cuisine cuisine)
    {
        this.cuisine = cuisine;
        cuisine.addRecipe(this);
    }

    public Set<Review> getReviews() {
        return this.reviews;
    }

    public void addReview (Review review)
    {
        boolean added = this.reviews.add(review);
        if (added)
        {
            review.setRecipe(this);
        }
    }

    public Chef getChef() {
        return chef;
    }

    public void setChef(Chef chef)
    {
        this.chef = chef;
        chef.addRecipe(this);
    }

    public void addIngredient(Ingredient ingredient, float amount, String units)
    {
        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setAmount(amount);
        recipeIngredient.setUnits(units);
        recipeIngredient.setRecipe(this);
        recipeIngredient.setIngredientId(ingredient.getId());
        recipeIngredient.setRecipeId(this.Id);

        this.ingredients.add(recipeIngredient);
        ingredient.getRecipes().add(recipeIngredient);
    }

}
