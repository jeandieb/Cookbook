package csulb.cecs323.model;


import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.Objects;

/**
* Recipe Ingredient Id class represents the composite key for the Recipe Ingredient class. 
* @author Jean
*/

public class RecipeIngredientId implements Serializable
{
    private long recipeId;
    private long ingredientId;


    public RecipeIngredientId(long recipeId, long ingredientId)
    {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public RecipeIngredientId() {}

    public long getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    /**
    * Checks if object passed is equal to this Recipe Ingredient 
    * @return true if object is equal to current Recipe Ingredient Object
    */
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof RecipeIngredientId)
        {
            RecipeIngredientId id = (RecipeIngredientId)object;
            return recipeId == id.recipeId && ingredientId == id.ingredientId;
        }
        else return false;
    }

    /**
    * Creates a hashcode from recipeId and ingredientId
    * @return hash of recipeId with ingredientId
    */
    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }

    //the version that addresses the feedback... does not work yet
/*
    private Recipe recipe;
    private Ingredient ingredient;


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

    public RecipeIngredientId(Recipe recipe, Ingredient ingredient) {
        this.recipe = recipe;
        this.ingredient = ingredient;
    }

    public RecipeIngredientId() {
    }


    *//**
     * Checks if object passed is equal to this Recipe Ingredient
     * @return true if object is equal to current Recipe Ingredient Object
     *//*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientId id = (RecipeIngredientId) o;
        return recipe.equals(id.recipe) && ingredient.equals(id.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recipe, ingredient);
    }
     */
}
