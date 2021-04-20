package csulb.cecs323.model;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
public class Ingredient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;

    @ManyToOne
    Type type;

    private String description;

    @ManyToMany
    @JoinTable(
            name = "INGREDIENT_CUISINE",
            joinColumns = @JoinColumn(name = "ingredientID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "cuisineID", referencedColumnName = "Id"))
    private Set<Cuisine> cuisines;


    public Ingredient() {};
    //got rid of the constructor to make the user use the .addIngredient method from type
    //this way we make sure that the List of Ingredients in type is modified everytime we add a new Ingredient

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Cuisine> getCuisines()
    {
        return cuisines;
    }

    public void addCuisine(Cuisine cuisine)
    {
        boolean added = this.cuisines.add(cuisine);
        if(added)
            cuisine.getIngredients().add(this);
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
