-- Print the first and last name of chefs in the database and the cuisines that they experts in.
SELECT FIRSTNAME AS chef_first_name, LASTNAME AS chef_last_name, C.NAME AS cuisine_name
       FROM CUISINES C INNER JOIN CHEF_CUISINE CC ON C.ID = CC.CUISINES_ID
                       INNER JOIN USERS U ON CC.CHEFS_ID = U.ID
WHERE USER_TYPE = 'Chef';


-- List the recipes, the last names of the chefs who created them, and the number of steps for each recipe.
SELECT R.NAME, C.LASTNAME, COUNT(S.ORDERNUMBER) AS number_of_steps
       FROM USERS C INNER JOIN RECIPES R ON C.ID = R.CHEF_ID
                    INNER JOIN STEPS S on R.RECIPEID = S.RECIPE_RECIPEID
GROUP BY R.NAME, C.LASTNAME;


-- List the pair of users and their followers
SELECT u.FIRSTNAME AS Followed_first_name, u.LASTNAME AS Followed_last_name, u1.FIRSTNAME AS follower_first_name, u1.LASTNAME AS follower_last_name
       FROM USERS u1 RIGHT OUTER JOIN FOLLOWER_FOLLOWING FF ON u1.ID = FF.FOLLOWERS_ID
                     RIGHT OUTER JOIN USERS u ON u.ID = FF.FOLLOWINGS_ID;


-- Retrieve recipes with more than 1 step
SELECT recipeid, cooktime, description, difficultyrating, name, numberofservings, preptime, chef_id, cuisine_id
    FROM RECIPES INNER JOIN USERS U ON RECIPES.CHEF_ID = U.ID
                 INNER JOIN CHEF_CUISINE CC ON U.ID = CC.CHEFS_ID
WHERE RECIPEID = ANY (SELECT recipe
                        FROM (SELECT RECIPE_RECIPEID AS recipe, COUNT(RECIPEID) as StepCount
                                FROM STEPS INNER JOIN RECIPES R ON R.RECIPEID = STEPS.RECIPE_RECIPEID
                        GROUP BY RECIPE_RECIPEID
                        HAVING COUNT(RECIPE_RECIPEID) > 1) AS RecipeSteps
                     ) AND RECIPES.CUISINE_ID = CC.CUISINES_ID;


-- Find recipes rated 8 and higher of the chef who has created the most recipes
SELECT R.RECIPEID, R.COOKTIME, R.DESCRIPTION, R.DIFFICULTYRATING, R.NAME, R.NUMBEROFSERVINGS, R.PREPTIME, R.CHEF_ID, R.CUISINE_ID, R2.RATING
    FROM RECIPES R INNER JOIN REVIEWS R2 ON R.RECIPEID = R2.RECIPE_RECIPEID
WHERE CHEF_ID = (SELECT CHEFID
                     FROM (SELECT USERS.ID AS CHEFID, COUNT(R.RECIPEID)
                                FROM USERS INNER JOIN RECIPES R ON USERS.ID = R.CHEF_ID
                           GROUP BY USERS.ID
                           HAVING COUNT(R.RECIPEID) = (SELECT MAX(numRecipes)
                                                            FROM (SELECT USERS.ID, COUNT(R.RECIPEID) numRecipes
                                                                    FROM USERS INNER JOIN RECIPES R ON USERS.ID = R.CHEF_ID
                                                                  GROUP BY users.id
                                                                 ) MAXRECIPES
                                                      )

                           ) CHEFWITHMAXRECIPES
                ) AND R2.RATING > 8;

-- QUERY 6???
-- Find the recipe with the minimum number of steps and the chef who created it
SELECT R.NAME, C.LASTNAME, COUNT(S.ORDERNUMBER) AS number_of_steps
FROM USERS C INNER JOIN RECIPES R ON C.ID = R.CHEF_ID
             INNER JOIN STEPS S on R.RECIPEID = S.RECIPE_RECIPEID
GROUP BY R.NAME, C.LASTNAME
HAVING COUNT(S.ORDERNUMBER) = (
    SELECT MIN(NUMSTEPS)
    FROM
        (SELECT COUNT(S2.ORDERNUMBER) NUMSTEPS
         FROM RECIPES RS INNER JOIN STEPS S2 on RS.RECIPEID = S2.RECIPE_RECIPEID
         group by RS.NAME) MINSTEPS);

