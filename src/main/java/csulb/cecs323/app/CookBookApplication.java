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
        CookBookApplication cookBookApp = new CookBookApplication(manager);

        LOGGER.fine("Begin of Transaction");
        EntityTransaction tx = manager.getTransaction();


        //create ingredients, cuisines, and users
        tx.begin();
        cookBookApp.createIngredientEntity();
        cookBookApp.createCuisineEntity();
        cookBookApp.createUserEntity();
        tx.commit();




        //create chefs, food critics and add  followers
        tx.begin();
        cookBookApp.createChefEntity();
        cookBookApp.createFoodCriticEntity();
        tx.commit();



        //create recipe
        tx.begin();
        cookBookApp.createRecipeEntity();
        tx.commit();

        //create Steps and reviews
        tx.begin();
        cookBookApp.createStepEntity();
        cookBookApp.createReviewEntity();
        tx.commit();



        cookBookApp.runUserApplication();
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

        Recipe recipe3 = new Recipe("Orange chicken", "fried chicken with orange sauce", Duration.ofMinutes(60).toString(), Duration.ofMinutes(60).toString(), 2, 4);
        recipe3.addStep(new Step(1, "fry the chicken", 3));
        recipe3.setChef(this.entityManager.find(Chef.class, (long) 5));
        recipe3.setCuisine(this.entityManager.find(Cuisine.class, (long) 2));
        recipe3.addIngredient(this.entityManager.find(Ingredient.class, (long)3), (float) 2.5, "Tbs");

        this.entityManager.persist(recipe);
        this.entityManager.persist(recipe2);
        this.entityManager.persist(recipe3);
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
                    runQueries();
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
                "4) Remove a Chef(Warning: using this method will cause a violation of FK and stop the program!)\n" +
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

        System.out.print("Select the Chef who created this recipe using his ID: ");
        Query query = this.entityManager.createNativeQuery("SELECT * FROM USERS WHERE USER_TYPE = 'Chef'", User.class);
        System.out.println(query.getResultList());
        recipe.setChef(this.entityManager.find(Chef.class, (long) keyboard.nextInt()));

        System.out.print("Select the Cuisine this recipe belongs to using its ID: ");
        Query query2 = this.entityManager.createNativeQuery("SELECT * FROM CUISINES", Cuisine.class);
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
        Query query = this.entityManager.createNativeQuery("SELECT * FROM RECIPES ", Recipe.class);
       //query.setParameter(1, recipe_name);
        System.out.println(query.getResultList());
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
        Scanner keyboard = new Scanner(System.in);
        System.out.println("1) Print first name and last name of chefs in the database and the cuisines" +
                " they experts in. " +
                "\n2) List the recipes, the last names of the chefs who created them, and the number of steps for each recipe " +
                "\n3) List the pair of users and their followers" +
                "\n4) Retrieves all recipes that take more than 1 step" +
                "\n5) Find recipes rated 8 and higher of the chef who has created the most recipes");
        int userChoice = keyboard.nextInt();
        if(userChoice == 1)
        {
            Query query = this.entityManager.createNativeQuery("SELECT FIRSTNAME AS chef_first_name, LASTNAME AS chef_last_name, C.NAME AS cuisine_name\n" +
                    "FROM CUISINES C INNER JOIN CHEF_CUISINE CC ON C.ID = CC.CUISINES_ID\n" +
                    "                INNER JOIN USERS U ON CC.CHEFS_ID = U.ID\n" +
                    "WHERE USER_TYPE = 'Chef'");

            List<String[]> queryRows = query.getResultList();
            System.out.println("chef first name     chef last name     cuisine name");
            for (int i = 0; i < queryRows.size(); i++)
            {
                Object arr[] = queryRows.get(i);
                for (int j = 0; j < arr.length; j++)
                {
                    System.out.print(arr[j].toString() + "               ");
                }
                System.out.println();
            }
        }

        else if (userChoice == 2)
        {
            Query query = this.entityManager.createNativeQuery("SELECT R.NAME, C.LASTNAME, COUNT(S.ORDERNUMBER) AS number_of_steps\n" +
                    "FROM USERS C INNER JOIN RECIPES R ON C.ID = R.CHEF_ID\n" +
                    "            INNER JOIN STEPS S ON R.RECIPEID = S.RECIPE_RECIPEID\n" +
                    "GROUP BY R.NAME, C.LASTNAME");
            List<String[]> queryRows = query.getResultList();
            System.out.println("Recipe Name           chef last name     number of steps");
            for (int i = 0; i < queryRows.size(); i++)
            {
                Object arr[] = queryRows.get(i);
                for (int j = 0; j < arr.length; j++)
                {
                    System.out.format("%15s", arr[j].toString());
                }
                System.out.println();
            }
        }

        else if(userChoice == 3)
        {
            Query query = this.entityManager.createNativeQuery("SELECT u.FIRSTNAME AS Followed_first_name, u.LASTNAME  AS Followed_last_name, u1.FIRSTNAME AS follower_first_name, u1.LASTNAME AS follower_last_name\n" +
                    "FROM USERS u1 RIGHT OUTER JOIN FOLLOWER_FOLLOWING FF ON u1.ID = FF.FOLLOWERS_ID\n" +
                    "            RIGHT OUTER JOIN USERS u ON u.ID = FF.FOLLOWINGS_ID");
            List<String[]> queryRows = query.getResultList();
            System.out.format("%15s%15s%22s%22s", "User First Name", "User last name", "Follower first name", "Follower last name");
            System.out.println();
            for (int i = 0; i < queryRows.size(); i++)
            {
                Object arr[] = queryRows.get(i);
                for (int j = 0; j < arr.length; j++)
                {
                    if (arr[j] == null) System.out.format("%20s", "No Followers");
                    else
                        System.out.format("%15s", arr[j].toString());
                }
                System.out.println();
            }

        }

        else if (userChoice == 4)
        {
            Query query = this.entityManager.createNativeQuery("SELECT recipeid, cooktime, difficultyrating, name, numberofservings, preptime, chef_id, cuisine_id\n" +
                    "FROM RECIPES INNER JOIN USERS U ON RECIPES.CHEF_ID = U.ID\n" +
                    "INNER JOIN CHEF_CUISINE CC ON U.ID = CC.CHEFS_ID\n" +
                    "WHERE RECIPEID = ANY(\n" +
                    "SELECT recipe\n" +
                    "FROM (SELECT RECIPE_RECIPEID AS recipe, COUNT(RECIPEID) as StepCount\n" +
                    "FROM STEPS INNER JOIN RECIPES R ON R.RECIPEID = STEPS.RECIPE_RECIPEID\n" +
                    "GROUP BY RECIPE_RECIPEID\n" +
                    "HAVING COUNT(RECIPE_RECIPEID) > 1) AS RecipeSteps) AND RECIPES.CUISINE_ID = CC.CUISINES_ID");
            List<String[]> queryRows = query.getResultList();
            System.out.format("%18s%18s%18s%18s%18s%18s%18s%18s", "Recipe Id", "Cook Time", "Difficulty", "Recipe Name", "# of Servings", "Prep Time", "Chef Id", "Cuisine Id");
            System.out.println();
            for (int i = 0; i < queryRows.size(); i++)
            {
                Object arr[] = queryRows.get(i);
                for (int j = 0; j < arr.length; j++)
                {
                        System.out.format("%17s", arr[j].toString());
                }
                System.out.println();
            }
        }
        else if (userChoice == 5)
        {
            Query query = this.entityManager.createNativeQuery(
                    "SELECT R.RECIPEID, R.COOKTIME, R.DESCRIPTION, R.DIFFICULTYRATING, R.NAME, R.NUMBEROFSERVINGS, R.PREPTIME, R.CHEF_ID, R.CUISINE_ID, R2.RATING\n" +
                            "FROM RECIPES R INNER JOIN REVIEWS R2 ON R.RECIPEID = R2.RECIPE_RECIPEID\n" +
                            "WHERE CHEF_ID = (\n" +
                            "SELECT CHEFID\n" +
                            "FROM (\n" +
                            "SELECT USERS.ID AS CHEFID, COUNT(R.RECIPEID)\n" +
                            "FROM USERS INNER JOIN RECIPES R ON USERS.ID = R.CHEF_ID\n" +
                            "group by USERS.ID\n" +
                            "HAVING COUNT(R.RECIPEID) = (\n" +
                            "SELECT MAX(numRecipes)\n" +
                            "FROM (\n" +
                            "SELECT USERS.ID, COUNT(R.RECIPEID) numRecipes\n" +
                            "FROM USERS INNER JOIN RECIPES R ON USERS.ID = R.CHEF_ID\n"  +
                            "group by users.id\n" +
                            ") MAXRECIPES)) CHEFWITHMAXRECIPES) AND R2.RATING > 8");
            List<String[]> queryRows = query.getResultList();
            System.out.format("Recipe Id", "Cook Time", "Description", "Difficulty Rating", "Name", "Number of Servings", "Chef Id", "Cuisine Id", "Recipe Rating");
            System.out.println();
            for (int i = 0; i < queryRows.size(); i++)
            {
                Object arr[] = queryRows.get(i);
                for (int j = 0; j < arr.length; j++)
                {
                    System.out.format("%15s", arr[j].toString());
                }
                System.out.println();
            }
        }
        else if (userChoice == 6)
        {
            Query query = this.entityManager.createNativeQuery("SELECT u1.FIRSTNAME, u1.LASTNAME, u1.USER_TYPE, COUNT(FF.Followers_ID) AS number_of_followers\n" +
                    "FROM USERS u1 INNER JOIN FOLLOWER_FOLLOWING FF ON u1.ID = FF.FOLLOWINGS_ID\n" +
                    "GROUP BY u1.FIRSTNAME, u1.LASTNAME, u1.USER_TYPE\n" +
                    "HAVING COUNT(FF.Followers_ID) > 1");
            List<String[]> queryRows = query.getResultList();
            System.out.format("%15s%15s%15s%22s", "User First Name", "User last name", "User Type", "Number of followers");
            System.out.println();
            for (int i = 0; i < queryRows.size(); i++)
            {
                Object arr[] = queryRows.get(i);
                for (int j = 0; j < arr.length; j++)
                {
                    System.out.format("%15s", arr[j].toString());
                }
                System.out.println();
            }
        }



    }

}
