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
    * @return true if object is equal to current Recipe Iggredient Object
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
}
