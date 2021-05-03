package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "RECIPES")
/**
  * A recipe is a food created by following a step-by-step instruction with its ingredients.
  */
public class Recipe
{
    /** id used to identify recipe */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    /** recipe name */
    private String name;
    /** recipe description */
    private String description;
    /** prep time needed for recipe  */
    private String prepTime;
    /** cook time needed for recipe */
    private String cookTime;
    /** recipe difficulty rating */
    private int difficultyRating;
    /** number of servings recipe makes */
    private int numberOfServings;

    /** Connects recipe to its steps */
    @OneToMany(mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Step> steps = new HashSet<>();

    /** Connects recipe to its type of cuisine */
    @ManyToOne
    private Cuisine cuisine;

    /** Connects recipe to its reviews */
    @OneToMany (mappedBy = "recipe", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Review> reviews = new HashSet<>();

    /** Connects recipe to its chef */
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn (nullable = false)
    private Chef chef;

    /** Connects recipe to its ingredients */
    @OneToMany(mappedBy = "recipe", cascade = CascadeType.PERSIST)
    private Set<RecipeIngredient> ingredients = new HashSet<>();

    /** Empty constructor for recipe */
    public Recipe(){}
    
    /**
     * Constructor for creating a Recipe
     * @param na name of recipe
     * @param desc recipe desciption
     * @param prepT recipe prep time
     * @param cookT recipe cook time
     * @param difficultyRating recipe rating of difficulty
     * @param numberOfServings # of servings recipe makes
     */
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
