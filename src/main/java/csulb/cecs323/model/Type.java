// this is a look-up table for ingredients' types
package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Type
{
    @Id
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Ingredient> ingredients;

    public Type() {};

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

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        boolean added = this.ingredients.add(ingredient);
        if (added)
            ingredient.setType(this);
    }

    @Override
    public String toString() {
        return "Type{" +
                "name='" + name + '\'' +
                '}';
    }
}
