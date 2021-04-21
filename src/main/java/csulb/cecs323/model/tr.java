
package csulb.cecs323.model;

public class tr {

    public static void main(String[] args){
        Recipe r = new Recipe("Mac n Cheese", "Cheesy Goodness", "30 Min", "25 Min", 5, 4); 
        Type dairy = new Type("Dairy");
        Ingredient cheese = new Ingredient("Cheese", dairy, "Basically Milk");
        r.addIngredient(cheese, 1, "Cup");


    
        System.out.println(r);

    }
}