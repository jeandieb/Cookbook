package csulb.cecs323.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "ingredients",
        uniqueConstraints =
            @UniqueConstraint(columnNames = {"name", "type"})
)
public class Ingredient
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ingredientId;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    IngredientType type;

    private String description;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable
    private Set<Cuisine> cuisines = new HashSet<>();

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private Set<RecipeIngredient> recipeIngredient = new HashSet<>();

    public Ingredient() {}

    public Ingredient(String name, IngredientType type, String description)
    {
        this.setName(name);
        this.setIngredientType(type);
        this.setDescription(description);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public IngredientType getIngredientType() {
        return type;
    }

    public void setIngredientType(IngredientType type)
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

    public long getId() {return this.ingredientId;}


    public Set<RecipeIngredient> getRecipes()
    {
        return this.recipeIngredient;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "ID=" + ingredientId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", description='" + description + '\'' +
                '}';
    }
}
