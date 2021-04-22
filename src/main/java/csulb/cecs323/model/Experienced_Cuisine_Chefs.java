package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Experienced_Cuisine_Chefs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chef_id;

    private int cuisine_id;

    public long getChef_id() {
        return chef_id;
    }

    public int getCuisine_id() {
        return cuisine_id;
    }

    public int setCuisine_id(int cuisine_id){
        return this.cuisine_id = cuisine_id;
    }

    public Experienced_Cuisine_Chefs(int cuisine_id){
        this.setCuisine_id(cuisine_id);
    }


    public Experienced_Cuisine_Chefs(){}

    @Override
    public String toString(){
        return "Chef ID: " + this.getChef_id() + "Cuisine ID: " + this.getCuisine_id();
    }
}
