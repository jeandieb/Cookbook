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


-- Find Users who have more than one follower, their types and how many followers they have
SELECT u1.FIRSTNAME, u1.LASTNAME, u1.USER_TYPE, COUNT(FF.Followers_ID) AS number_of_followers
       FROM USERS u1 INNER JOIN FOLLOWER_FOLLOWING FF ON u1.ID = FF.FOLLOWINGS_ID
GROUP BY u1.FIRSTNAME, u1.LASTNAME, u1.USER_TYPE
HAVING COUNT(FF.Followers_ID) > 1;


-- QUERY 5 DESCRIPTION



-- QUERY 6 DESCRIPTION

