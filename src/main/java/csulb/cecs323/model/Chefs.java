package csulb.cecs323.model;

import javax.persistence.*;

@Entity
public class Chefs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long chef_id;

    @Column(nullable = false)
    private int years_of_experience;

    public Chefs(int years_of_experience){
        this.setYears_of_experience(years_of_experience);
    }

    public Chefs() {}

    public long getChef_id() {
        return chef_id;
    }

    public int getYears_of_experience(){return years_of_experience;}

    public void setYears_of_experience(int years_of_experience){this.years_of_experience = years_of_experience;}

    @Override
    public String toString(){
        return "Chef ID: " + this.getChef_id() + "Years of experience: " + this.getYears_of_experience();
    }
}

