package csulb.cecs323.app;

import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.Date;

public class StarterApplication
{
    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(StarterApplication.class.getName());

    public StarterApplication(EntityManager manager)
    {
        this.entityManager = manager;
    }

    public static void main(String [] args)
    {
        LOGGER.fine("Creating EntityManagerFactory and EntityManager");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("starter_unit");
        EntityManager manager = factory.createEntityManager();
        StarterApplication semesterProjectApplication = new StarterApplication(manager);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        semesterProjectApplication.createRegionEntity();
        semesterProjectApplication.creatReligionEntity();
        semesterProjectApplication.createTypeEntity();
        semesterProjectApplication.createRecipeEntity();
        tx.commit();

        tx.begin();
        semesterProjectApplication.createCuisineEntity();
        tx.commit();

        tx.begin();
        semesterProjectApplication.createIngredientEntity();
        tx.commit();

        tx.begin();
        Cuisine temp = manager.find(Cuisine.class, (long) 1.0);
        System.out.println("here " + temp);
        temp.addIngredients(manager.find(Ingredient.class, (long) 1.0));
        manager.persist(temp);
        tx.commit();

        tx.begin();
        manager.remove(manager.find(Recipe.class, (long)1));
        LOGGER.info("removing object from DB: ");
        tx.commit();


    }

    //populate Region
    private void createRegionEntity() {
        Set<Region> regions = new HashSet<>();
        regions.add(new Region("African"));
        regions.add(new Region("American"));
        regions.add(new Region("European"));
        regions.add(new Region("Asian"));
        regions.add(new Region("Oceanic"));
        for(Region temp : regions)
        {
            this.entityManager.persist(temp);
            LOGGER.info("Persisted to DB: " + temp);
        }

    }
    //populate Religion //not sure what cuisine religion is
    private void creatReligionEntity()
    {
        Set<Religion> religions = new HashSet<>();
        religions.add(new Religion("Christianity"));
        religions.add(new Religion("Judaism"));
        religions.add(new Religion("Islam"));
        religions.add(new Religion("Hinduism"));
        religions.add(new Religion("Buddhism"));
        for(Religion temp : religions)
        {
            this.entityManager.persist(temp);
            LOGGER.info("Persisted to DB: " + temp);
        }
    }
    //populate Type
    private void createTypeEntity()
    {
        List<Type> types = new ArrayList<>();
        types.add(new Type("Acid Regulator"));
        types.add(new Type("Anticaking Agent"));
        types.add(new Type("Antifoaming Agent"));
        types.add(new Type("Antioxidant"));
        types.add(new Type("Carrier"));
        types.add(new Type("Color"));
        types.add(new Type("Emulsifiers"));
        types.add(new Type("Firming Agent"));
        types.add(new Type("Flavor Enhancer"));
        types.add(new Type("Foaming Agent"));
        types.add(new Type("Gelling Agent"));
        types.add(new Type("Glazing Agent"));
        types.add(new Type("Humectant"));
        types.add(new Type("Preservative"));
        types.add(new Type("Raising Agent"));
        types.add(new Type("Sequestrant"));
        types.add(new Type("Stabilizer"));
        types.add(new Type("Sweetener"));
        types.add(new Type("Thickener"));

        for(Type temp : types)
        {
            this.entityManager.persist(temp);
            LOGGER.info("Persisted into DB: " + temp);
        }
    }

    private void createIngredientEntity()
    {
        Ingredient ingredient = new Ingredient();
        ingredient.setName("Salt");
        entityManager.find(Type.class, "Flavor Enhancer ").addIngredient(ingredient);
        ingredient.setDescription("A mineral composed primarily of sodium chloride (NaCl), " +
                                    " and one of the oldest and most ubiquitous food seasonings," +
                                    " and salting is an important method of food preservation.");
        this.entityManager.persist(ingredient);
        LOGGER.info("Persisting Into DB: " + ingredient);
        this.entityManager.flush();
        LOGGER.info("Persisted object after flush(non-null id): " + ingredient);

    }

    private void createRecipeEntity()
    {

        Recipe recipe = new Recipe("Shawerma", "cooked chicken breast slices wrapped with pita bread", Duration.ofMinutes(60).toString(), Duration.ofMinutes(30).toString(), 3, 4);
        recipe.addStep(new Step(1, "paste some onion paste on a pita", 2));
        this.entityManager.persist(recipe);
        this.entityManager.flush();
        LOGGER.info("Persisted Object after flush (non-null id): " + recipe);

        Recipe recipe2 = new Recipe("hard boiled egg", "steamed eggs", Duration.ofMinutes(8).toString(), Duration.ofMinutes(5).toString(), 1, 1);
        recipe2.addStep(new Step(1, "sink eggs in water and boil them for 8 minutes", 8));
        this.entityManager.persist(recipe2);
        this.entityManager.flush();
        LOGGER.info("Persisted Object after flush (non-null id):  recipe2 " + recipe2);
    }

    private void createStepEntity()
    {
        Step step = new Step(2, "add 1tbs of salt", 1);
        Recipe recipeFrench = new Recipe("french fries", "good old French Fries", Duration.ofMinutes(30).toString(), Duration.ofMinutes(15).toString(),2,2);
        step.setRecipe(recipeFrench);
        this.entityManager.persist(recipeFrench);
        this.entityManager.persist(step);
        this.entityManager.flush();
        LOGGER.info("persisted object after flush(non-null id): " + step);
    }

    private void createCuisineEntity()
    {
        Cuisine cuisine = new Cuisine();
        cuisine.setName("Johnny's");
       // cuisine.setRegion(new Region("American"));
       // cuisine.setReligion(new Religion("Christianity"));
        entityManager.find(Region.class, "American").addCuisine(cuisine);
        entityManager.find(Religion.class, "Christianity").addCuisine(cuisine);
        entityManager.find(Recipe.class, (long)1).setCuisine(cuisine);
        this.entityManager.persist(cuisine);
        LOGGER.info("Persisted to DB: " + cuisine);
        this.entityManager.flush();
        LOGGER.info("Persisted object after flush(non-null id): " + cuisine);


    }

    private void createReviewEntity()
    {
        Review rev = new Review();
        rev.setDescription("Amazing. Very tasty!");
        rev.setRecipeID(entityManager.find(Recipe.class,"Shawerma").);
        Date date = new SimpleDateFormat("yyyy-mm-dd").parse("2021-04-19");
        rev.setDateCompleted((date);


        this.entityManager.persist(rev);
        LOGGER.info("Persisted to DB: " + rev);
        this.entityManager.flush();
        LOGGER.info("Persisted object after flush(non-null id): " + rev);
    }
}

