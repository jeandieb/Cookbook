package csulb.cecs323.model;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IngredientAmountPK implements Serializable {
    @Basic
    private long recipeId;

    @Basic
    private long ingredientId;

    public IngredientAmountPK(long recipeId, long ingredientId){
        this.recipeId = recipeId;
        this.ingredientId = ingredientId;
    }

    public IngredientAmountPK(){}

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


    @Override
    public boolean equals(Object object){
        if (this == object) { return true; }
        if(!(object instanceof IngredientAmountPK)) { return false;}
        IngredientAmountPK otherPK = (IngredientAmountPK) object;
        return Objects.equals(getRecipeId(), otherPK.getRecipeId()) && Objects.equals(getIngredientId(), otherPK.getIngredientId());
    }

    @Override
    public int hashCode(){
        return Objects.hash(getIngredientId(), getRecipeId());
    }

}
