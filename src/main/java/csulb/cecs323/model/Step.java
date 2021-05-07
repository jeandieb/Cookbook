package csulb.cecs323.model;


import javax.persistence.*;

//@Embeddable
@Entity
@Table(
        name = "STEPS",
        uniqueConstraints = @UniqueConstraint(columnNames = {"orderNumber", "recipe"})
)
/**
 * Step is the direction that the recipe needs for it to be completed
 */
public class Step
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long stepId;

    private int orderNumber;

    private String description;

    private int time; // in minutes

    /**
     * Connects Steps to Recipe
     */
    @ManyToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(nullable = false)
    private Recipe recipe;

    /**
     * Empty constructor for Step
     */
    public Step(){};

    /**
     * Constructor for creating a Step
     * @param orderNumber step number
     * @param description what to do during step
     * @param time duration needed for step
     */
    public Step(int orderNumber, String description, int time)
    {
        this.setOrderNumber(orderNumber);
        this.setDescription(description);
        this.setTime(time);
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public Recipe getRecipe()
    {
        return this.recipe;
    }

    public void setRecipe(Recipe recipe)
    {
        this.recipe = recipe;
        recipe.addStep(this);
    }
}
