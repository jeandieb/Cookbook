package csulb.cecs323.app;

import csulb.cecs323.model.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

public class CookbookApplication
{
    private EntityManager entityManager;

    private static final Logger LOGGER = Logger.getLogger(CookbookApplication.class.getName());

    public CookbookApplication(EntityManager manager)
    {
        this.entityManager = manager;
    }

    public static void main(String [] args) {
        LOGGER.fine("Creating EntityManagerFactory and EntityManager");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("starter_unit");
        EntityManager manager = factory.createEntityManager();
        CookbookApplication cookbookApp = new CookbookApplication(manager);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();

        //create ingedients
        tx.begin();
        cookbookApp.createIngredientEntity();
        tx.commit();

        //create cuisines
        tx.begin();
        cookbookApp.createCuisineEntity();
        tx.commit();

        //create users
        tx.begin();
        cookbookApp.createUserEntity();
        tx.commit();

        //create chefs and add  followers
        tx.begin();
        cookbookApp.createChefEntity();
        tx.commit();

        //create a food critic and add followers
        tx.begin();
        cookbookApp.createFoodCriticEntity();
        tx.commit();


        //create recipe
        tx.begin();
        cookbookApp.createRecipeEntity();
        tx.commit();

        //create Steps
        tx.begin();
        cookbookApp.createStepEntity();
        tx.commit();

    }

    private void createIngredientEntity()
    {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("Salt", entityManager.find(Type.class, "Spice"), "A mineral composed primarily of sodium chloride (NaCl), " +
                " and one of the oldest and most ubiquitous food seasonings," +
                " and salting is an important method of food preservation.");
        ingredients.add(ingredient);

        Ingredient ingredient2 = new Ingredient("Black Pepper", entityManager.find(Type.class, "Spice"), "was historically both a seasoning and a traditional medicine. " +
                " Pepper appears in the Buddhist Samaññaphala Sutta, chapter five, as one of the few medicines" +
                " a monk is allowed to carry.");
        ingredients.add(ingredient2);

        Ingredient ingredient3 = new Ingredient("Garlic", entityManager.find(Type.class, "Flavor Agent"), " is most often used as a flavoring agent but can also. " +
                " be eaten as a vegetable. It is used to flavor many foods, " +
                " such as salad dressings, vinaigrettes, marinades, sauces, vegetables, meats, soups, and stews.");
        ingredients.add(ingredient3);

        for(Ingredient loopIngredient : ingredients) {
            this.entityManager.persist(loopIngredient);
            LOGGER.info("Persisting Into DB: " + loopIngredient);
            this.entityManager.flush();
            LOGGER.info("Persisted object after flush(non-null id): " + loopIngredient);
        }
    }

    private void createCuisineEntity()
    {
        List<Cuisine> cuisines = new ArrayList<>();
        Cuisine cuisine = new Cuisine("Italian", entityManager.find(Region.class, "European"), entityManager.find(Religion.class, "None"), entityManager.find(Ingredient.class, (long) 3));
        cuisines.add(cuisine);

        Cuisine cuisine2 = new Cuisine("Middle Eastern", entityManager.find(Region.class, "Asian"), entityManager.find(Religion.class, "None"), entityManager.find(Ingredient.class, (long) 1));
        cuisines.add(cuisine2);

        for(Cuisine loopCuisine : cuisines)
        {
            this.entityManager.persist(loopCuisine);
            LOGGER.info("Persisted to DB: " + loopCuisine);
            this.entityManager.flush();
            LOGGER.info("Persisted object after flush(non-null id): " + loopCuisine);
        }

    }

    private void createUserEntity()
    {
        List<User> users = new ArrayList<>();
        User user = new User("Jean", "Dib", "JeanD", "1111", "jeanD@csulb.com", LocalDateTime.now());
        users.add(user);
        User user1 = new User("John", "Quach", "JohnQ", "2222", "JohnQ@csulb.com", LocalDateTime.now());
        users.add(user1);
        User user2 = new User("Kearne", "Permalino", "KearneP", "3333", "KearneP@csulb.com", LocalDateTime.now());
        users.add(user2);
        User user3 = new User("Nikki", "Benitez", "NikkiB", "4444", "NikkiB@csulb.com", LocalDateTime.now());
        users.add(user3);

        for(User loopUser : users)
        {
            this.entityManager.persist(loopUser);
            LOGGER.info("Persisting object to DB: " + loopUser);
            this.entityManager.flush();
            LOGGER.info("Persisting Object after flush (non- null id) " + loopUser);
        }

    }

    private void createChefEntity()
    {
        List<Chef> chefs = new ArrayList<>();
        Chef chef = new Chef("Gordon", "Ramsay", "Gramsay", "AnIdiotSandwich", "GoldRam@xyz.com", LocalDateTime.now(), 30);
        chef.addCuisine(this.entityManager.find(Cuisine.class, (long) 1));
        chefs.add(chef);
        chef.addFollower(this.entityManager.find(User.class, (long) 1));
        chef.addFollower(this.entityManager.find(User.class, (long) 2));

        Chef chef1 = new Chef("Ramzi", "Choueiri", "Rchoueiri", "Kebab!", "Rchoueiri@xyz.com", LocalDateTime.now(), 25);
        chef1.addCuisine(this.entityManager.find(Cuisine.class, (long) 2));
        chefs.add(chef1);
        chef1.addFollower(this.entityManager.find(User.class, (long) 1));
        chef1.addFollower(this.entityManager.find(User.class, (long) 2));
        for (Chef loopChef : chefs)
        {
            this.entityManager.persist(chef);
            LOGGER.info("Persisting object to DB: " + loopChef);
            this.entityManager.flush();
            LOGGER.info("Persisting Object after flush (non- null id) " + loopChef);

        }

    }

    private void createFoodCriticEntity()
    {
        List<FoodCritic> foodCritics = new ArrayList<>();
        FoodCritic foodCritic = new FoodCritic("Frank", "Bruni", "Fbruni", "1234", "Fbruni@critic.com", LocalDateTime.now(),"The New York Times");
        foodCritic.addFollower(this.entityManager.find(User.class, (long) 3));
        foodCritic.addFollower(this.entityManager.find(User.class, (long) 4));
        foodCritics.add(foodCritic);

        FoodCritic foodCritic1 = new FoodCritic("Katie", "Lee", "Klee", "5678", "Klee@critic.com", LocalDateTime.now(),"Bravo's Top Chef");
        foodCritics.add(foodCritic1);
        for(FoodCritic loopFoodCritic : foodCritics)
        {
            this.entityManager.persist(loopFoodCritic);
            LOGGER.info("Persisting object to DB: " + loopFoodCritic);
            this.entityManager.flush();
            LOGGER.info("persisting object after flush (non-null id)" + loopFoodCritic);
        }
    }

    private void createRecipeEntity()
    {
        Recipe recipe = new Recipe("Shawerma", "cooked chicken breast slices wrapped with pita bread", Duration.ofMinutes(60).toString(), Duration.ofMinutes(30).toString(), 3, 4);
        recipe.addStep(new Step(1, "paste some onion paste on a pita", 2));
        recipe.setChef(this.entityManager.find(Chef.class, (long) 6));
        recipe.setCuisine(this.entityManager.find(Cuisine.class, (long) 2));
        //recipe.addIngredient(this.entityManager.find(Ingredient.class, (long)1), (float) 1.0, "Tbs");
        this.entityManager.persist(recipe);
        this.entityManager.flush();
        LOGGER.info("Persisted Object after flush (non-null id): " + recipe);

    }


    private void createRegionEntity() {
        Set<Region> regions = new HashSet<>();
        for(Region temp : regions)
        {
            this.entityManager.persist(temp);
            LOGGER.info("Persisted to DB: " + temp);
        }

    }
    private void creatReligionEntity()
    {
        Set<Religion> religions = new HashSet<>();
        for(Religion temp : religions)
        {
            this.entityManager.persist(temp);
            LOGGER.info("Persisted to DB: " + temp);
        }
    }
    private void createTypeEntity()
    {
        List<Type> types = new ArrayList<>();

        for(Type temp : types)
        {
            this.entityManager.persist(temp);
            LOGGER.info("Persisted into DB: " + temp);
        }
    }


    private void createStepEntity()
    {
        Step step = new Step(1, "add 1tbs of salt", 1);
        //Recipe recipeFrench = new Recipe("french fries", "good old French Fries", Duration.ofMinutes(30).toString(), Duration.ofMinutes(15).toString(),2,2);
        step.setRecipe(this.entityManager.find(Recipe.class, (long) 1));
        //this.entityManager.persist(recipeFrench);
        this.entityManager.persist(step);
        this.entityManager.flush();
        LOGGER.info("persisted object after flush(non-null id): " + step);
    }



    private void createReviewEntity()
    {
        Review rev = new Review();
        rev.setDescription("Amazing. Very tasty!");
        rev.setRating(10);
        rev.setRecipe(entityManager.find(Recipe.class,(long)2));
        LocalDate date = LocalDate.of(2020, 5, 19);
        rev.setDateCompleted(date);
        FoodCritic temp = new FoodCritic("Foo", "Bar", "Fbar", "56789", "fbar@foob.com", LocalDateTime.now(), "Facebook");
        this.entityManager.persist(temp);
        rev.setFoodCritic(temp);

        this.entityManager.persist(rev);
        LOGGER.info("Persisted to DB: " + rev);
        this.entityManager.flush();
        LOGGER.info("Persisted object after flush(non-null id): " + rev);
    }


}
