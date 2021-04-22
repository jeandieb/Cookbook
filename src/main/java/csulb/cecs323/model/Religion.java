//this is a look-up table for cuisines' religions

package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "RELIGIONS")
public class Religion
{
    /** The name of a cuisine that pertains to a religion. */
    @Id
    private String name;

    /** The list of cuisines that pertains to a religion. */
    @OneToMany(mappedBy = "religion")
    private
    Set<Cuisine> cuisines;

    public Religion(){};

    public Religion(String na)
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
     * Adds an cuisine to a set of cuisines
     * @param cuisine   An cuisine object that pertains to a certain religion
     */
    public void addCuisine(Cuisine cuisine) {
        boolean added =  this.cuisines.add(cuisine);
        if (added)
            cuisine.setReligion(this);
    }

    @Override
    public String toString() {
        return "Religion{" +
                "name='" + name + '\'' +
                '}';
    }
}
