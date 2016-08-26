package cookbook;

import cookbook.displays.CategoryDisplay;
import cookbook.displays.MenuDisplay;
import cookbook.displays.RecipeDisplay;
import cookbook.model.Category;
import cookbook.model.Ingredient;
import cookbook.model.Recipe;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static javax.swing.SwingUtilities.invokeLater;

public class CookBook {

    private static Integer currentCategory;
    private static Integer currentRecipe;
    private static List<Category> categories = new ArrayList<>();
    //Observer pattern- state is represented by these three objects
    private static RecipeDisplay recDisplay;
    private static CategoryDisplay catDisplay;
    private static MenuDisplay menuDisplay;

    private CookBook() {}

    public static void main(String[] args) throws IOException {

        Recipe recipe1 = new Recipe("Hamburger 1", "A hamburger 1 (or cheeseburger " +
                "when served with a slice of cheese) is a sandwich consisting of one or more\n" +
                " cooked patties of ground meat, usually beef, placed inside a sliced bread roll or bun.\n" +
                " Hamburgers may be cooked in a variety of ways, including pan-frying, barbecuing, and flame-broiling.\n" +
                "Hamburgers are often served with cheese, lettuce, tomato, bacon, onion, pickles, " +
                "and condiments\n such as mustard, mayonnaise, ketchup, relish, and chiles.",
                Arrays.asList(new Ingredient("Rolls", "", 2, 2), new Ingredient("Meat", "Ounces", 10, 2),
                        new Ingredient("Onion", "Grams", 30, 2), new Ingredient("Mustard", "Spoons", 2, 2)),
                ImageIO.read(new File("images/hamburger.jpg")), null, 2);

        Recipe recipe2 = new Recipe("Salad 2", "A salad is a dish consisting of small pieces of food, which" +
                " may be mixed with a sauce or salad dressing. They are typically served cold. \n" +
                "Salads can incorporate a variety of foods including vegetables, fruits, cheese, cooked meat, eggs, grains and nuts.\n" +
                "Garden salads use a base of leafy greens like lettuce, arugula, kale or spinach;\n" +
                " they are common enough that the word salad alone often refers specifically to garden salads.\n",
                Arrays.asList(new Ingredient("Salad", "Grams", 200, 1), new Ingredient("Chicken", "Grams", 100, 1),
                        new Ingredient("Feta cheese", "Grams", 50, 1), new Ingredient("Spices mix", "spoons", 2, 1)),
                ImageIO.read(new File("images/salad.jpg")), null, 1);

        Recipe recipe3 = new Recipe("Pizza 3", "“This classic American recipe with a killer crusty base and" +
                " beautiful meaty toppings makes the perfect pizza. ”",
                Arrays.asList(new Ingredient("Water", "Grams", 600, 4), new Ingredient("Yeast", "Grams", 16, 4),
                        new Ingredient("Mozzarella Cheese", "Grams", 400, 4), new Ingredient("Tomato Sauce", "Millilitres", 100, 4),
                        new Ingredient("Frech Chilies", "", 4, 4), new Ingredient("Pork sausages", "", 4, 4)),
                ImageIO.read(new File("images/pizza.jpg")), null, 4);

        Recipe recipe4 = new Recipe("Spaghetti 4", "A great introduction to pasta for kids – loads of fun to eat," +
                " and a brilliant base for adding all kinds of other fresh ingredients. ",
                Arrays.asList(new Ingredient("Tomatoes", "Grams", 1000, 5), new Ingredient("Spaghetti", "Grams", 480, 5),
                        new Ingredient("Parmesan Cheese", "Grams", 100, 5), new Ingredient("Garlic", "Cloves", 5, 5),
                        new Ingredient("Vinegar", "Teaspoons", 5, 5), new Ingredient("Basil", "Grams", 20, 5)),
                ImageIO.read(new File("images/spaghetti.jpeg")), null, 5);

        Category firstCategory = new Category("Dinners", Arrays.asList(recipe1, recipe2, recipe3, recipe4),
                ImageIO.read(new File("images/DinnerCategory.jpg")), ImageIO.read(new File("images/DinnerCategory.jpg")));

        Recipe dessertRecipe1 = new Recipe("Chocolate Cake 5", "The absolute best, richest, and easiest" +
                " one-bowl chocolate cake recipe ever! It's great topped with chocolate cream cheese frosting!",
                Arrays.asList(new Ingredient("Flour", "Cups", 2, 2), new Ingredient("Sugar", "Cups", 2, 2),
                        new Ingredient("Baking Soda", "Teaspoons", 2, 2), new Ingredient("Eggs", "", 2, 2),
                        new Ingredient("Milk", "Millilitres", 200, 2), new Ingredient("Vinegar", "Teaspoons", 2, 2)),
                ImageIO.read(new File("images/chocolate-cake.jpg")), null, 2);

        Recipe dessertRecipe2 = new Recipe("Lemon Cocoa Cake 6", "This simple recipe can be served as is in" +
                " a graham crustn\n or you can dress it up with a strawberry or peach glaze." +
                " \nEither way, a little Cool Whip or whipped cream on top is good.",
                Arrays.asList(new Ingredient("Lemon", "", 1, 1), new Ingredient("Cream Cheese", "Grams", 150, 1),
                        new Ingredient("Lemon Juice", "Millilitres", 40, 1),
                        new Ingredient("Milk", "Millilitres", 150, 1), new Ingredient("Sugar", "Teaspoons", 2, 1)
                        , new Ingredient("Cocoa", "Teaspoons", 8, 1)),
                ImageIO.read(new File("images/delicious-pie.jpeg")), null, 1);

        Recipe dessertRecipe3 = new Recipe("Sweet Cherry Pie 7", "You've never had a cherry pie this good—an incredible sour cherry filling,\n" +
                " a light and flaky crust, and vanilla ice cream to top it all off.\n " +
                "If you can't find sour cherries, use sweet cherries and a little extra lemon juice instead.\n",
                Arrays.asList(new Ingredient("Cherries", "", 20, 2), new Ingredient("Flour", "Cups", 2, 2),
                        new Ingredient("Sugar", "Tablespoons", 2, 2),
                        new Ingredient("Water", "Cups", 2, 2), new Ingredient("Sugar", "Teaspoons", 2, 2)
                        , new Ingredient("Cinnamon", "Teaspoons", 4, 2)),
                ImageIO.read(new File("images/cherry-pie-with-cream.jpeg")), null, 1);

        Recipe dessertRecipe4 = new Recipe("Bananas with Chocolate 8", "This delicous recipe is very easy to make as well as very tasty!\n" +
                "Peel and slice the banana. Scoop the ice cream into a bowl.\n " +
                "Top with the banana, chocolate sauce, and coconut.\n",
                Arrays.asList(new Ingredient("Bananas", "", 1, 1), new Ingredient("Chocolate Sauce", "Teaspoons", 3, 1),
                        new Ingredient("Coconut", "", 1, 1),
                        new Ingredient("Ice cream", "Scoops", 4, 1), new Ingredient("Sugar", "Teaspoons", 2, 1)),
                ImageIO.read(new File("images/bananaschocolate.jpg")), null, 1);

        Category secondCategory = new Category("Desserts", Arrays.asList(dessertRecipe1, dessertRecipe2, dessertRecipe3, dessertRecipe4),
                ImageIO.read(new File("images/DessertsCategory.jpg")), ImageIO.read(new File("images/DessertsCategory.jpg")));

        Recipe drinkRecipe1 = new Recipe("Mojito 9", "This is an authentic recipe for mojito.\n" +
                "I sized the recipe for one serving, but you can adjust it accordingly and make a pitcher full.\n" +
                "It's a very refreshing drink for hot summer days.\n",
                Arrays.asList(new Ingredient("Vodka", "ml", 50, 1), new Ingredient("Mint", "Leaves", 3, 1),
                        new Ingredient("Lime", "", 1, 1),
                        new Ingredient("Ice", "Cubes", 4, 1)),
                ImageIO.read(new File("images/drink1.jpg")), null, 1);

        Recipe drinkRecipe2 = new Recipe("Orange Cocktail 10", "A nice summer cocktail with a good mix of fruity flavors. ",
                Arrays.asList(new Ingredient("Vodka", "ml", 50, 1), new Ingredient("Orange Juice", "ml", 50, 1),
                        new Ingredient("Cranberry Juice", "ml", 50, 1),
                        new Ingredient("Ice", "Cubes", 1, 1)),
                ImageIO.read(new File("images/drink2.jpg")), null, 1);

        Recipe drinkRecipe3 = new Recipe("Summer Cocktail 11", "cookbook.model.Recipe courtesy Nico Szymanski, head mixologist, Gerber Group, New York City\n" +
                "This cocktail was created for Irvington, Gerber Group’s new restaurant across from NYC’s Union Square.\n" +
                "While Szymanski sources his produce from local farmers at the famed Union Square Greenmarket,\n" +
                "bonus points to you if you can pluck fresh mint and blackberries from your garden to make this tall, bubbly drink.\n",
                Arrays.asList(new Ingredient("Vodka", "ml", 100, 2), new Ingredient("Mint", "Leaves", 4, 2),
                        new Ingredient("Blackberries", "grams", 50, 2),
                        new Ingredient("Fruit syrup", "Teaspoons", 4, 2)),
                ImageIO.read(new File("images/drink3.jpg")), null, 2);

        Category thirdCategory = new Category("Drinks", Arrays.asList(drinkRecipe1, drinkRecipe2, drinkRecipe3),
                ImageIO.read(new File("images/DrinksCategory.jpg")), ImageIO.read(new File("images/DrinksCategory.jpg")));

        invokeLater(() -> {
            setCurrRecipe(0);
            setCurrCategory(0);
            getCategories().add(firstCategory);
            getCategories().add(secondCategory);
            getCategories().add(thirdCategory);
            try {
                setMenuDisplay(new MenuDisplay());
                setRecDisplay(new RecipeDisplay(recipe1));
                setCatDisplay(new CategoryDisplay(getCategories().get(0)));
                getCatDisplay().createAndShowGUI();
                getRecDisplay().createAndShowGUI();
                getMenuDisplay().createAndShowGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static Integer getCurrCategory() {
        return currentCategory;
    }

    public static void setCurrCategory(Integer currentCategory) {
        CookBook.currentCategory = currentCategory;
    }

    public static Integer getCurrRecipeIndex() {
        return currentRecipe;
    }

    public static void setCurrRecipe(Integer currentRecipe) {
        CookBook.currentRecipe = currentRecipe;
    }

    public static List<Category> getCategories() {
        return categories;
    }

    public static RecipeDisplay getRecDisplay() {
        return recDisplay;
    }

    private static void setRecDisplay(RecipeDisplay recDisplay) {
        CookBook.recDisplay = recDisplay;
    }

    public static CategoryDisplay getCatDisplay() {
        return catDisplay;
    }

    private static void setCatDisplay(CategoryDisplay catDisplay) {
        CookBook.catDisplay = catDisplay;
    }

    public static MenuDisplay getMenuDisplay() {
        return menuDisplay;
    }

    private static void setMenuDisplay(MenuDisplay menuDisplay) {
        CookBook.menuDisplay = menuDisplay;
    }
}
