package cookbook.displays;

import cookbook.CookBook;
import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.stream.Collectors;


public class RecipeDisplay extends CookBookDisplay {

    private static final String PEOPLE = "People:";
    private static final String SPLIT = "split";
    private static final String WRAP = "wrap";
    private Recipe recipe;
    private JFrame recipeFrame;
    private JPanel recPanel;
    private JLabel picLabel;
    private JLabel numberOfPeople;
    private int currentNumberOfPeople;
    private JList<Object> ingredientStringsJList;
    private ImageIcon recipePicture;
    private JTextArea recipeDescription;
    private JLabel recipeTitle;

    public RecipeDisplay (Recipe recipe) throws IOException {
        this.recipe = recipe;

        recipeFrame = new JFrame("CookBook");
        recPanel = new JPanel(new MigLayout());
        recipePicture = new ImageIcon(recipe.getImage());
        picLabel = new JLabel(recipePicture);
        recipeFrame.pack();
        currentNumberOfPeople = recipe.getNumberOfPeople();
    }

    @Override
    public void setVisible (boolean b) {
        recipeFrame.setVisible(b);
    }

    @Override
    public void nextPage () {
        if (CookBook.getCategories().get(CookBook.getCurrentCategory()).getRecipeList().size() > CookBook.getCurrentRecipe() + 1) {
            CookBook.setCurrentRecipe(CookBook.getCurrentRecipe() + 1);
            updateAllComponents();
        } else if (CookBook.getCategories().size() > CookBook.getCurrentCategory() + 1) {
            CookBook.setCurrentCategory(CookBook.getCurrentCategory() + 1);
            CookBook.setCurrentRecipe(0);
            CookBook.getCatDisplay().updateAllComponents();
            CookBook.getCatDisplay().setVisible(true);
            CookBook.getRecDisplay().setVisible(false);
        }
    }

    @Override
    public void previousPage () {
        if (CookBook.getCurrentRecipe() > 0) {
            CookBook.setCurrentRecipe(CookBook.getCurrentRecipe() - 1);
            updateAllComponents();
        } else {
            CookBook.getRecDisplay().setVisible(false);
            CookBook.getCatDisplay().updateAllComponents();
            CookBook.getCatDisplay().setVisible(true);
            CookBook.getCatDisplay().setLocationRelativeTo(null);

        }
    }

    void updateAllComponents () {
        this.recipe = CookBook.getCategories().get(CookBook.getCurrentCategory())
                .getRecipeList().get(CookBook.getCurrentRecipe());
        currentNumberOfPeople = recipe.getNumberOfPeople();
        numberOfPeople.setText(PEOPLE + currentNumberOfPeople);
        recipe.getIngredientList().forEach(i -> i.reScale(currentNumberOfPeople));
        ingredientStringsJList.setListData(recipe.getIngredientList()
                .stream().map(Ingredient::toString).collect(Collectors.toList()).toArray());
        recipeDescription.setText(recipe.getDescription());
        recipeTitle.setText("Recipe:     " + recipe.getTitle());
        recipePicture.setImage(recipe.getImage());
        picLabel.setIcon(recipePicture);
        picLabel.repaint();
    }

    public void createAndShowGUI () throws IOException {

        recipeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        recipeTitle = new JLabel("Recipe:     " + recipe.getTitle());
        recipeTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

        recipeDescription = new JTextArea(recipe.getDescription());


        recipeDescription.setEditable(false);
        recipeDescription.setPreferredSize(new Dimension(400, 100));
        numberOfPeople = new JLabel(PEOPLE + currentNumberOfPeople);
        numberOfPeople.setPreferredSize(new Dimension(100, 50));

        JButton previousPage = new JButton("Back");
        previousPage.addActionListener(e -> previousPage());
        JButton nextPage = new JButton("Next");
        nextPage.addActionListener(e -> nextPage());
        JButton home = new JButton("Home");
        home.addActionListener(e -> {
            CookBook.getRecDisplay().setVisible(false);
            recipeFrame.dispose();
            CookBook.setCurrentCategory(0);
            CookBook.setCurrentRecipe(0);
            updateAllComponents();
            CookBook.getMenuDisplay().setVisible(true);
        });
        JButton upScale = new JButton("+");

        upScale.addActionListener(e -> {
            currentNumberOfPeople++;
            numberOfPeople.setText(PEOPLE + currentNumberOfPeople);
            recipe.getIngredientList().forEach(i -> i.reScale(currentNumberOfPeople));
            ingredientStringsJList.setListData(recipe.getIngredientList()
                    .stream().map(Ingredient::toString).collect(Collectors.toList()).toArray());
        });
        JButton downScale = new JButton("-");
        downScale.addActionListener(e -> {
            if (currentNumberOfPeople > 1) {
                currentNumberOfPeople--;
                numberOfPeople.setText(PEOPLE + currentNumberOfPeople);
                recipe.getIngredientList().forEach(i -> i.reScale(currentNumberOfPeople));
                ingredientStringsJList.setListData(recipe.getIngredientList()
                        .stream().map(Ingredient::toString).collect(Collectors.toList()).toArray());
            }
        });

        upScale.setPreferredSize(new Dimension(50, 50));
        downScale.setPreferredSize(new Dimension(50, 50));
        previousPage.setPreferredSize(new Dimension(100, 50));
        nextPage.setPreferredSize(new Dimension(100, 50));
        home.setPreferredSize(new Dimension(200, 50));

        DefaultListModel<Object> listModel = new DefaultListModel<>();
        recipe.getIngredientList().forEach(i -> i.reScale(currentNumberOfPeople));
        recipe.getIngredientList().forEach(listModel::addElement);

        ingredientStringsJList = new JList<>(listModel);
        ingredientStringsJList.setPreferredSize(new Dimension(200, 500));

        recipeTitle.setFont(new Font(null, Font.PLAIN, 18));
        recipeTitle.setPreferredSize(new Dimension(200, 50));
        recipeDescription.setFont(new Font(null, Font.PLAIN, 18));
        ingredientStringsJList.setFont(new Font(null, Font.PLAIN, 18));
        numberOfPeople.setFont(new Font(null, Font.PLAIN, 18));

        recPanel.add(picLabel, "span 1 9");
        recPanel.add(numberOfPeople, SPLIT);
        recPanel.add(downScale, SPLIT);
        recPanel.add(upScale, WRAP);
        recPanel.add(ingredientStringsJList, WRAP);
        recPanel.add(recipeDescription, "dock south");
        recPanel.add(recipeTitle, WRAP);
        recPanel.add(home, WRAP);
        recPanel.add(previousPage, SPLIT);
        recPanel.add(nextPage, WRAP);
        recPanel.setPreferredSize(new Dimension(1300, 900));
        recipeFrame.getContentPane().add(recPanel);

        recipeFrame.pack();
        recipeFrame.setResizable(false);
        recipeFrame.setLocationRelativeTo(null);
        recipeFrame.setVisible(false);
    }
}
