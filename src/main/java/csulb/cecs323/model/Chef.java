package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@DiscriminatorValue("CHEF")
@Table(name = "CHEFS")
public class Chef extends User
{
    /** A measure of a chefs experience. */
    private int yearsOfExperience;

    /** A list of recipes. */
    @OneToMany(mappedBy = "chef")
    private Set<Recipe> recipes = new HashSet<>();

    @ManyToMany
    @JoinColumn(nullable = false)
    @JoinTable(
            name = "CHEF_CUISINE"
    )
    private Set<Cuisine> cuisines = new HashSet<>();

    public Chef() {}

    public Chef(String fn, String ls, String userName, String pw, String email, LocalDateTime dateReg, int yearsOfExperience)
    {
        super(fn, ls, userName, pw, email, dateReg);
        setYearsOfExperience(yearsOfExperience);
    }


    public int getYearsOfExperience() {
        return yearsOfExperience;
    }

    public void setYearsOfExperience(int yearsOfExperience) {
        this.yearsOfExperience = yearsOfExperience;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Adds a recipe to a list of recipes for a chef
     * @param recipe   A recipe that belong to a chef
     */
    public void addRecipe(Recipe recipe)
    {
        boolean added = this.recipes.add(recipe);
        if (added)
        {
            recipe.setChef(this);
        }
    }

    public Set<Cuisine> getCuisines() {return cuisines;}

    /**
     * Adds a cuisine to a list of cuisines for a chef
     * @param cuisine   A cuisine a chef is known for
     */
    public void addCuisine(Cuisine cuisine)
    {
        boolean added = cuisines.add(cuisine);
        if(added)
            cuisine.addChef(this);
    }

    @Override
    public String toString() {
        return "Chef{" + "name=" + this.getFirstName() + " " + this.getLastName() +
                " yearsOfExperience=" + yearsOfExperience +
                '}';
    }
}
