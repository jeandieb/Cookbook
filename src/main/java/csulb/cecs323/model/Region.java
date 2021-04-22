//this is a look-up table for cuisines' regions
package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "REGIONS")
public class Region
{
    /** The name of a region for a specific cuisine. */
    @Id
    private String name;

    /** The list of cuisines that pertains to a region. */
    @OneToMany(mappedBy = "region")
    private Set<Cuisine> cuisines = new HashSet<>();

    public Region(){};

    public Region(String na)
    {
        this.name = na;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Cuisine> getCuisines() {
        return cuisines;
    }

    /**
     * Adds an cuisine to a set of cuisines that pertains to a specific region
     * @param cuisine   An cuisine object that pertains to a certain region
     */
    public void addCuisine(Cuisine cuisine) {
        boolean added = this.cuisines.add(cuisine);
        if (added)
            cuisine.setRegion(this);
    }

    @Override
    public String toString() {
        return "Region{" +
                "name='" + name + '\'' +
                '}';
    }
}
