package csulb.cecs323.model;
import javax.persistence.*;
import java.io.Serializable;

//@TODO have to update javadoc
//https://en.wikibooks.org/wiki/Java_Persistence/Identity_and_Sequencing#Id_Class
//https://dwuysan.wordpress.com/2012/02/22/joincolumn-is-part-of-the-composite-primary-keys/

@Entity
@IdClass(IngredientAmountPK.class)
public class IngredientAmount{

    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "recipe_id", referencedColumnName = "Id")
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @Id
    @JoinColumn(name = "ingredient_id", referencedColumnName = "ID")
    private Ingredient ingredient;

    private float amount;
    private String units;

    public IngredientAmount(long ingredientId, long recipeId, float amount, String units){
        //this.ingredientId = ingredientId;
        //this.recipeId = recipeId;
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

}
