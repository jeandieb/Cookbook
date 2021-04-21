package csulb.cecs323.model;

import javax.persistence.Basic;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class IngredientAmountPK implements Serializable {
    // @Basic
    // private Recipe recipe;

    // @Basic
    // private Ingredient ingredient;

    // public IngredientAmountPK(Recipe recipe, Ingredient ingredient){
    //     this.recipe = recipe;
    //     this.ingredient = ingredient;
    // }

    // public IngredientAmountPK(){}

    // public Recipe getRecipe() {
    //     return recipe;
    // }

    // public void setRecipeId(Recipe recipe) {
    //     this.recipe = recipe;
    // }

    // public Ingredient getIngredient() {
    //     return this.ingredient;
    // }

    // public void setIngredient(Ingredient ingredient) {
    //     this.ingredient = ingredient;
    // }
    // @Override
    // public boolean equals(Object object){
    //     if (this == object) { return true; }
    //     if(!(object instanceof IngredientAmountPK)) { return false;}
    //     IngredientAmountPK otherPK = (IngredientAmountPK) object;
    //     return Objects.equals(getRecipe(), otherPK.getRecipe()) && Objects.equals(getIngredient(), otherPK.getIngredient());
    // }

    // @Override
    // public int hashCode(){
    //     return Objects.hash(getIngredient(), getRecipe());
    // }

    @Basic 
    private long recipeId; 

    @Basic 
    private long ingredientId; 

    public IngredientAmountPK(Recipe recipe, Ingredient ingredient){
        this.recipeId = recipe.getId(); 
        this.ingredientId = ingredient.getId(); 
    
    }

    public long getIngredientId() {
        return ingredientId;
    }

    public long getRecipeId() {
        return recipeId;
    }

    public void setIngredientId(long ingredientId) {
        this.ingredientId = ingredientId;
    }

    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
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
