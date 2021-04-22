package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "INGREDIENTS")
public class Ingredient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    Type type;

    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "INGREDIENT_CUISINE",
            joinColumns = @JoinColumn(name = "ingredientID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "cuisineID", referencedColumnName = "Id"))
    private Set<Cuisine> cuisines = new HashSet<>();

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.PERSIST)
    private Set<RecipeIngredient> recipes = new HashSet<>();

    public Ingredient() {}

    public Ingredient(String name, Type type, String description)
    {
        this.setName(name);
        this.setType(type);
        this.setDescription(description);
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

    public void setType(Type type)
    {
        this.type = type;
        type.addIngredient(this);
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

    public long getId() {return this.ID;}


    public Set<RecipeIngredient> getRecipes()
    {
        return this.recipes;
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
