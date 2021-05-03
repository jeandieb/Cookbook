// this is a look-up table for ingredients' types
package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "TYPES")
public class Type
{
    /** The name of the type of ingredient that will be used for recipes. */
    @Id
    private String name;

    /** The name of the type of ingredient that will be used for recipes. */
    @OneToMany(mappedBy = "type", cascade = CascadeType.PERSIST)
    private Set<Ingredient> ingredients = new HashSet<>();

    public Type() {}

    public Type(String na)
    {
        setName(na);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Adds an ingredient to a set of ingredients
     * @param ingredient   An ingredient object that is used for recipes.
     */
    public void addIngredient(Ingredient ingredient) {
        boolean added = this.ingredients.add(ingredient);
        if (added)
            ingredient.setIngredientType(this);
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
