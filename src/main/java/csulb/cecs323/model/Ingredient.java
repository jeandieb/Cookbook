package csulb.cecs323.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
public class Ingredient implements Serializable
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
    private List<Cuisine> cuisines;


    //added by Nikki for debugging 
    public Ingredient(String name, Type type, String description){
        this.name = name; 
        this.type = type; 
        this.description = description; 
    }
    public Ingredient() {};
    //got rid of the constructor to make the user use the .addIngredient method from type
    //this way we make sure that the List of Ingredients in type is modified everytime we add a new Ingredient


    public long getId(){
        return this.ID;
    }
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

    public List<Cuisine> getCuisines()
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
