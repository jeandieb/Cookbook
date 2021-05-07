package csulb.cecs323.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CHEFS")
public class Chef extends User
{
    private int yearsOfExperience;

    @OneToMany(mappedBy = "chef", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private Set<Recipe> recipesCreated = new HashSet<>();

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
        return recipesCreated;
    }

    public void addRecipe(Recipe recipe)
    {
        boolean added = this.recipesCreated.add(recipe);
        if (added){
            recipe.setChef(this);
        }
    }

    public Set<Cuisine> getCuisines() {return cuisines;}

    public void addCuisine(Cuisine cuisine)
    {
        boolean added = cuisines.add(cuisine);
        if(added) {
            cuisine.addChef(this);
        }
    }

    @Override
    public String toString() {
        return  this.getFirstName() + " " + this.getLastName() + " ID: " + this.getId();
    }
}
