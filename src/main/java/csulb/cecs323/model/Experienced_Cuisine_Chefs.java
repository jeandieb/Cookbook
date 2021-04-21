package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Experienced_Cuisine_Chefs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chef_id;

    private long cuisine_id;

    public long getChef_id() {
        return chef_id;
    }

    public long getCuisine_id() {
        return cuisine_id;
    }

    public Experienced_Cuisine_Chefs(){}

    @Override
    public String toString(){
        return "Chef ID: " + this.getChef_id() + "Cuisine ID: " + this.getCuisine_id();
    }
}
