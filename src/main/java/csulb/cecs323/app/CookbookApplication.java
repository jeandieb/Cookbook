package csulb.cecs323.app;

import csulb.cecs323.model.*;

import java.util.*;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.logging.Logger;

public class CookBookApplication
{
    private EntityManager entityManager;
    private static final Logger LOGGER = Logger.getLogger(CookBookApplication.class.getName());

    public CookBookApplication(EntityManager manager)
    {
        this.entityManager = manager;
    }

    public static void main(String [] args) {
        LOGGER.fine("Creating EntityManagerFactory and EntityManager");
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("cookbook_entry");
        EntityManager manager = factory.createEntityManager();
        CookBookApplication semesterProjectApplication = new CookBookApplication(manager);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();


        //create ingedients
        tx.begin();
        semesterProjectApplication.createIngredientEntity();
        tx.commit();

        //create cuisines
        tx.begin();
        semesterProjectApplication.createCuisineEntity();
        tx.commit();

        //create users
        tx.begin();
        semesterProjectApplication.createUserEntity();
        tx.commit();

        //create chefs and add  followers
        tx.begin();
        semesterProjectApplication.createChefEntity();
        tx.commit();

        //create a food critic and add followers
        tx.begin();
        semesterProjectApplication.createFoodCriticEntity();
        tx.commit();


        //create recipe
        tx.begin();
        semesterProjectApplication.createRecipeEntity();
        tx.commit();

        //create Steps
        tx.begin();
        semesterProjectApplication.createStepEntity();
        tx.commit();

        //create Review
        tx.begin();
        semesterProjectApplication.createReviewEntity();
        tx.commit();


        //simplified version made for debugging ..
//        System.out.println("trying to remove a Recipe...");
//        tx.begin();
//        manager.remove(manager.find(Recipe.class, (long)1));
//        tx.commit();

        semesterProjectApplication.runUserApplication();
    }

    private void createIngredientEntity()
    {
        List<Ingredient> ingredients = new ArrayList<>();
        Ingredient ingredient = new Ingredient("Salt", entityManager.find(IngredientType.class, "Spice"), "A mineral composed primarily of sodium chloride (NaCl), " +
                " and one of the oldest and most ubiquitous food seasonings," +
                " and salting is an important method of food preservation.");
        ingredients.add(ingredient);

        Ingredient ingredient2 = new Ingredient("Black Pepper", entityManager.find(IngredientType.class, "Spice"), "was historically both a seasoning and a traditional medicine. " +
                " Pepper appears in the Buddhist Samaññaphala Sutta, chapter five, as one of the few medicines" +
                " a monk is allowed to carry.");
        ingredients.add(ingredient2);

        Ingredient ingredient3 = new Ingredient("Garlic", entityManager.find(IngredientType.class, "Flavor Agent"), " is most often used as a flavoring agent but can also. " +
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
        recipe.addIngredient(this.entityManager.find(Ingredient.class, (long)1), (float) 1.0, "Tbs");

        Recipe recipe2 = new Recipe("Chicken bok choy", "cooked chicken with bok choy", Duration.ofMinutes(55).toString(), Duration.ofMinutes(55).toString(), 1, 4);
        recipe2.addStep(new Step(1, "cook the chicken", 4));
        recipe2.setChef(this.entityManager.find(Chef.class, (long) 5));
        recipe2.setCuisine(this.entityManager.find(Cuisine.class, (long) 2));
        recipe2.addIngredient(this.entityManager.find(Ingredient.class, (long)2), (float) 1.5, "Tbs");

        this.entityManager.persist(recipe);
        this.entityManager.persist(recipe2);
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
        List<IngredientType> types = new ArrayList<>();

        for(IngredientType temp : types)
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
        rev.setRecipe(entityManager.find(Recipe.class,(long)1));
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

    public void runUserApplication()
    {
        EntityTransaction tx = entityManager.getTransaction();

        int userChoice = 0;
        do
        {
            printMenu();
            userChoice = getUserInput();
            switch (userChoice)
            {
                case 1:
                    tx.begin();
                    this.addRecipe();
                    tx.commit();
                    System.out.println("created User's Recipe");
                    break;
                case 2:
                    tx.begin();
                    this.updateRecipe();
                    tx.commit();
                    System.out.println("User update a recipe");
                    break;
                case 3:
                    int toBeRemoved = removeFoodCritic();
                    tx.begin();
                    FoodCritic toBeRemoved_foodCritic = this.entityManager.find(FoodCritic.class, (long)toBeRemoved);
                    this.entityManager.remove(toBeRemoved_foodCritic);
                    tx.commit();
                    System.out.println("user removed a food critic");
                    break;
                case 4:
                    int toBeRemoved_int = removeChef();
                    tx.begin();
                    Chef toBeRemoved_chef = this.entityManager.find(Chef.class, (long)toBeRemoved_int);
                    this.entityManager.remove(toBeRemoved_chef);
                    tx.commit();
                    System.out.println("User removed a Chef");
                    break;
                case 5:
                    //runQueries();
                    System.out.println("User executed SQL queries");
                    break;
                case 0:
                    System.out.println("you have chosen to quit the program...\nHave a good day!");
                default:
                    break;
            }

        }while (userChoice != 0);


    }

    public static void printMenu()
    {
        System.out.println("Welcome to Cookbook!\n" +
                "1) Create a new recipe\n" +
                "2) Update the information in an existing recipe\n" +
                "3) Delete a food critic\n" +
                "4) Remove an entity\n" +
                "5) Execute some SQL queries on the database\n" +
                "0) Quit the program");
    }

    public static int getUserInput()
    {
        int userChoice = 0;
        Scanner keyboard = new Scanner(System.in);
        try {

            System.out.print("make a selection by typing the appropriate number:");
            userChoice = keyboard.nextInt();
            if(userChoice < 0 || userChoice > 5)
            {
                System.out.println("invalid option, try again");
                userChoice = getUserInput();
            }
        }catch (InputMismatchException e)
        {
            System.out.println("input has to be an integer!");
            userChoice = getUserInput();
        }catch (Exception e)
        {
            System.out.println("something went wrong, please try again...");
            e.printStackTrace();
        }
        return userChoice;
    }


    //printing Cuisines list from DB and adding ingredients do not work ...
    public void addRecipe()
    {
        Scanner keyboard = new Scanner(System.in);
        Recipe recipe = new Recipe();
        System.out.print("enter the name of your recipe: ");
        recipe.setName(keyboard.nextLine());
        System.out.print("enter the description of your recipe: ");
        recipe.setDescription(keyboard.nextLine());
        System.out.print("enter preparation time (in minutes): ");
        recipe.setPrepTime(keyboard.nextLine());
        System.out.print("enter cook time (in minutes): ");
        recipe.setCookTime(keyboard.nextLine());
        System.out.print("enter difficulty rating of you recipe:");
        recipe.setDifficultyRating(keyboard.nextInt());
        System.out.print("enter the number of servings of your recipe:");
        recipe.setNumberOfServings(keyboard.nextInt());
        char continueWithSteps = ' ';
        do
        {
            System.out.println("Adding a step...");
            Step addToRecipe = new Step();
            System.out.print("Enter the step order number: ");
            addToRecipe.setOrderNumber(keyboard.nextInt());
            keyboard.nextLine();
            System.out.println("Enter the step description: ");
            addToRecipe.setDescription(keyboard.nextLine());
            System.out.print("How long does the step take? (in minutes): ");
            addToRecipe.setTime(keyboard.nextInt());
            recipe.addStep(addToRecipe);
            System.out.print("add another step? (y/n): ");
            continueWithSteps = keyboard.next().charAt(0);
            if(!(continueWithSteps == 'n' || continueWithSteps == 'N' ||continueWithSteps == 'y' ||continueWithSteps == 'Y'))
            {
                System.out.println("invalid selection... can't add more steps ... ");
                continueWithSteps = 'n';
            }
        }while(!(continueWithSteps == 'n' || continueWithSteps == 'N'));
        //recipe.addStep(new Step(1, "paste some onion paste on a pita", 2));

        System.out.print("Select the Chef who created this recipe using his ID: ");
        Query query = this.entityManager.createNativeQuery("SELECT * FROM USERS WHERE USER_TYPE = 'Chef'", User.class);
        System.out.println(query.getResultList());
        recipe.setChef(this.entityManager.find(Chef.class, (long) keyboard.nextInt()));

        System.out.print("Select the Cuisine this recipe belongs to using its ID: ");
        Query query2 = this.entityManager.createNativeQuery("SELECT * FROM CUISINES", Cuisine.class);
        //System.out.println(query2.getResultList());
        recipe.setCuisine(this.entityManager.find(Cuisine.class, (long) keyboard.nextInt()));

       // recipe.addIngredient(this.entityManager.find(Ingredient.class, (long)1), (float) 2.0, "Tbs");

        this.entityManager.persist(recipe);
        this.entityManager.flush();
        LOGGER.info("Persisted Object after flush (non-null id): " + recipe);

    }

    public int removeFoodCritic()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Select the critic you want to remove using his/her ID: ");
        Query query = this.entityManager.createNativeQuery("SELECT * FROM USERS WHERE USER_TYPE = 'FoodCritic'", User.class);
        System.out.println(query.getResultList());
        System.out.println("removing a chef will remove all the recipes he created as well as all the steps associated with the recipes!");
        return keyboard.nextInt();
    }

    public void updateRecipe(){
        // User enters a recipe that they want to update currently, does not support substrings, and is
        // ***case sensitive**
//        Scanner in = new Scanner(System.in);
//        System.out.println("Select a recipe by name: ");
//        String recipe_name = in.nextLine();
        // Prints out what the current recipe contains
//        Query query = this.entityManager.createNativeQuery("SELECT * FROM RECIPES WHERE NAME = ?1", Recipe.class);
//        query.setParameter(1, recipe_name);
//        System.out.println(query.getResultList());
        Scanner in = new Scanner(System.in);
        System.out.println("Select a recipe by id: ");
        Recipe recipe = this.entityManager.find(Recipe.class, (long) in.nextInt());
        System.out.println(recipe);
        int userChoice = 0;
        do{
            System.out.println("Choose what you want to edit: ");
            System.out.println("1. Enter new description: ");
            System.out.println("2. Enter new preparation time (in minutes): ");
            System.out.println("3. Enter new cook time (in minutes): ");
            System.out.println("4. Enter new difficulty rating of you recipe:");
            System.out.println("5. Enter new the number of servings of your recipe:");
            System.out.println("6. Done");
            userChoice = in.nextInt();
            switch(userChoice){
                case 1:
                    String desc = newDescription();
                    recipe.setDescription(desc);
                    break;
                case 2:
                    String prep = newPrepTime();
                    recipe.setDescription(prep);
                    break;
                case 3:
                    String cookTime = newCookTime();
                    recipe.setDescription(cookTime);
                    break;
                case 4:
                    System.out.println("Enter new difficulty rating of you recipe:");
                    int newDifficulty = in.nextInt();
                    recipe.setDifficultyRating(newDifficulty);
                    break;
                case 5:
                    System.out.println("Enter new the number of servings of your recipe:");
                    int newServings = in.nextInt();
                    recipe.setNumberOfServings(newServings);
                    break;
                case 6:
                    userChoice = 6;
                    break;
                default:
                    break;

            }
        } while (userChoice != 6);
    }

    public String newDescription(){
        Scanner in = new Scanner(System.in);
        String desc = "";
        System.out.print("Enter new description: ");
        desc = in.nextLine();
        return desc;
    }

    public String newPrepTime(){
        Scanner in = new Scanner(System.in);
        String prep = "";
        System.out.print("Enter new prep time: ");
        prep = in.nextLine();
        return prep;
    }

    public String newCookTime(){
        Scanner in = new Scanner(System.in);
        String cookTime = "";
        System.out.print("Enter new cook time: ");
        cookTime = in.nextLine();
        return cookTime;
    }

    //does not work yet, throws an exception...
    public int removeChef()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Select the Chef you want to remove using his/her ID: ");
        Query query = this.entityManager.createNativeQuery("SELECT * FROM USERS WHERE USER_TYPE = 'Chef'", User.class);
        System.out.println(query.getResultList());
        return keyboard.nextInt();

    }


    public void runQueries()
    {

    }

}
