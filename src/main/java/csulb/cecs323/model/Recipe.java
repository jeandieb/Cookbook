package csulb.cecs323.model;

import javax.persistence.*;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

@Entity
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

    @OneToMany(mappedBy = "recipe")//, orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Step> steps = new HashSet<>();

    private Set<IngredientAmount> ingredients = new HashSet<>();

    @ManyToOne
    private Cuisine cuisine;

    public Recipe(){};

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

    public void addIngredient(Ingredient ingredient, int amount, String units){
        IngredientAmount newIngr = new IngredientAmount(this, ingredient, amount, units);
        this.ingredients.add(newIngr);
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

    @Override
    public String toString(){
        String nameTimeDescr = String.format("Recipe: %s \nPrep Time: %s \nCook Time: %s \nDescription: %s", this.name, this.prepTime, this.cookTime, this.description); 
        String allIngr = "\nIngredients:  ";
        for(IngredientAmount ingr: this.ingredients){
            allIngr += ingr.toString();
        }
        nameTimeDescr += allIngr;
        String allSteps = "\nSteps:\n"; 
        for(Step step : this.steps){
            allSteps += step.toString();
        }
        nameTimeDescr += allSteps; 
        return nameTimeDescr;
        
    }
}
