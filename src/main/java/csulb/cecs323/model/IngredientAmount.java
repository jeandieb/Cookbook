package csulb.cecs323.model;
import javax.persistence.*;
import java.io.Serializable; 

//@TODO have to update javadoc
//https://en.wikibooks.org/wiki/Java_Persistence/Identity_and_Sequencing#Id_Class
//https://dwuysan.wordpress.com/2012/02/22/joincolumn-is-part-of-the-composite-primary-keys/

@IdClass(IngredientAmountPK.class)
@Entity
public class IngredientAmount implements Serializable {


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id", referencedColumnName = "Id")
    private Recipe recipe;


    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ID")
    private Ingredient ingredient;

    @Id
    private long recipeId; 

    @Id
    private long ingredientId; 

    private float amount;
    private String units;

    public IngredientAmount(Recipe recipe, Ingredient ingredient, float amount, String units){
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.recipeId = recipe.getId();
        this.ingredientId = ingredient.getId(); 
        this.amount = amount;
        this.units = units;
    }

  

    public IngredientAmount(){}


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

    public void setPK(Recipe recipe, Ingredient ingredient){
        this.recipe = recipe; 
        this.ingredient = ingredient; 
    }

    @Override
    public String toString(){
        String str = String.format("%s %f %s", this.getIngredient().getName(), this.getAmount(), this.getUnits());
        return str;
    }
    
}
