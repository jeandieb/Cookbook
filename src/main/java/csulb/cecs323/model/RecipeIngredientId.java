package csulb.cecs323.model;


import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.io.Serializable;
import java.util.Objects;

public class RecipeIngredientId implements Serializable
{
    private long recipeId;
    private long ingredientId;


    public RecipeIngredientId(long recipeId, long ingredientId)
    {
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(recipeId, ingredientId);
    }
}
