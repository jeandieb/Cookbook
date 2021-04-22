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
    @Id
    private String name;

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
