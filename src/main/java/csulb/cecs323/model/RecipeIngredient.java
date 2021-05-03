package csulb.cecs323.model;

import org.eclipse.persistence.annotations.PrimaryKey;

import javax.persistence.*;

@Entity
@Table(name = "INGREDIENT_AMOUNT")
@IdClass(RecipeIngredientId.class)
public class RecipeIngredient
{
    @Id
    private long recipeId;

    @Id
    private long ingredientId;

    private float amount;

    private String units;

 //   @Id
    @ManyToOne
    private Recipe recipe;

 //   @Id
    @ManyToOne
    private Ingredient ingredient;

    public RecipeIngredient() {}

    public long getRecipeId() { return recipeId; }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
