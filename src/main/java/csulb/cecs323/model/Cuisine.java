package csulb.cecs323.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(
        name = "CUISINES",
        uniqueConstraints =
                @UniqueConstraint(columnNames = {"name", "region", "religion"})
)
public class Cuisine
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    private String name;

    @ManyToOne
    Region region;

    @ManyToOne
    Religion religion;

    @ManyToMany(mappedBy = "cuisines")
    @JoinColumn(nullable = false)
    private Set<Ingredient> ingredients = new HashSet<>();

    @ManyToMany(mappedBy = "cuisines", cascade = CascadeType.PERSIST)
    private Set<Chef> chefs = new HashSet<>();

    @OneToMany(mappedBy = "cuisine", orphanRemoval = false)
    private Set<Recipe> recipes = new HashSet<>();

    public Cuisine(){}

    public Cuisine(String name, Region region ,Religion religion, Ingredient ingredient)
    {
        setName(name);
        setRegion(region);
        setReligion(religion);
        addIngredients(ingredient);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }

    public Set<Ingredient> getIngredients() {
        return ingredients;
    }

    public void addIngredients(Ingredient ingredient)
    {
        boolean added = this.ingredients.add(ingredient);
        if(added)
            ingredient.getCuisines().add(this);

    }

    public Set<Recipe> getRecipes()
    {
        return this.recipes;
    }

    public void addRecipe(Recipe recipe) {
        boolean added = this.recipes.add(recipe);
        if (added)
        {
            recipe.setCuisine(this);
        }
    }


    public Set<Chef> getChefs() {return chefs;}


    public void addChef(Chef chef)
    {
        boolean added = chefs.add(chef);
        if (added)
            chef.addCuisine(this);
    }


    @Override
    public String toString()
    {
        return name + " ID: " + Id;
    }

    public String toStringDetailed() {
        return "Cuisine{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", region=" + region +
                ", religion=" + religion +
                '}';
    }
}
