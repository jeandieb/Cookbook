//this is a look-up table for cuisines' religions

package csulb.cecs323.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
public class Religion
{
    @Id
    private String name;

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
