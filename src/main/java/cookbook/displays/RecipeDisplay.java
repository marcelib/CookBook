package cookbook.displays;

import cookbook.model.Ingredient;
import cookbook.model.Recipe;
import javafx.util.Pair;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

import static cookbook.CookBook.*;
import static cookbook.displays.DisplayUtils.*;

public class RecipeDisplay extends CookBookDisplay {

    private static final String PEOPLE = "People:";
    private static final String SPLIT = "split";
    private static final String WRAP = "wrap";
    private ArrayList<Pair<JComponent, String>> pairs;
    private int currentNumberOfPeople;
    private ImageIcon recipePicture;
    private JFrame recipeFrame;
    private JPanel recPanel;
    private JLabel picLabel;
    private JLabel recipeTitle;
    private JLabel numberOfPeople;
    private JTextArea recipeDescription;
    private JList<Object> ingredientStringsJList;
    private Recipe recipe;
    private JButton previousPage;
    private JButton nextPage;
    private JButton home;
    private JButton upScale;
    private JButton downScale;

    public RecipeDisplay (Recipe recipe) {
        this.recipe = recipe;
        recipeFrame = new JFrame("CookBook");
        recPanel = new JPanel(new MigLayout());
        recipePicture = new ImageIcon(recipe.getImage());
        picLabel = new JLabel(recipePicture);
        currentNumberOfPeople = recipe.getPeople();
        previousPage = createButtonWithProperties("Back", 98, 50);
        nextPage = createButtonWithProperties("Next", 98, 50);
        home = createButtonWithProperties("Home", 200, 50);
        upScale = createButtonWithProperties("+", 48, 50);
        upScale.addActionListener(e -> upScale());
        downScale = createButtonWithProperties("-", 48, 50);
        downScale.addActionListener(e -> downScale());
        pairs = new ArrayList<>();
    }

    @Override
    public void setVisible (boolean b) {
        recipeFrame.setVisible(b);
    }

    @Override
    void nextPage () {
        if (getCategories().get(getCurrCategory()).getRecipeList().size() > getCurrRecipeIndex() + 1) {
            setCurrRecipe(getCurrRecipeIndex() + 1);
            updateAllComponents();
        } else if (getCategories().size() > getCurrCategory() + 1) {
            setCurrCategory(getCurrCategory() + 1);
            setCurrRecipe(0);
            hideCurrentDisplay();
            showCatDisplay();
        }
    }

    @Override
    void previousPage () {
        if (getCurrRecipeIndex() > 0) {
            setCurrRecipe(getCurrRecipeIndex() - 1);
            updateAllComponents();
        } else {
            hideCurrentDisplay();
            showCatDisplay();
        }
    }

    @Override
    void hideCurrentDisplay () {
        getRecDisplay().setVisible(false);
    }

    void updateAllComponents () {
        this.recipe = getCategories().get(getCurrCategory())
                .getRecipeList().get(getCurrRecipeIndex());
        currentNumberOfPeople = recipe.getPeople();
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
        numberOfPeople = (JLabel) createJComponent("JLabel", PEOPLE + currentNumberOfPeople, 100, 50);
        recipeTitle = (JLabel) createJComponent("JLabel", "Recipe:     " + recipe.getTitle(), 300, 50);
        recipeDescription = (JTextArea) createJComponent("JTextArea", recipe.getDescription(), 400, 100);
        recipeDescription.setEditable(false);
        DefaultListModel<Object> listModel = new DefaultListModel<>();
        recipe.getIngredientList().forEach(i -> {
            i.reScale(currentNumberOfPeople);
            listModel.addElement(i);
        });
        ingredientStringsJList = new JList<>(listModel);
        setComponentProperties(ingredientStringsJList, 300, 500);
        pairs.addAll(Arrays.asList(new Pair<>(picLabel, "span 1 9"), new Pair<>(numberOfPeople, SPLIT),
                new Pair<>(downScale, SPLIT), new Pair<>(upScale, WRAP), new Pair<>(ingredientStringsJList, WRAP),
                new Pair<>(recipeDescription, "dock south"), new Pair<>(recipeTitle, WRAP), new Pair<>(home, WRAP),
                new Pair<>(previousPage, SPLIT), new Pair<>(nextPage, WRAP)));
        pairs.forEach(e -> recPanel.add(e.getKey(), e.getValue()));
        recipeFrame.getContentPane().add(recPanel);
        recPanel.setPreferredSize(new Dimension(1300, 900));
        setFrameProperties(recipeFrame, false);
    }

    private void upScale () {
        currentNumberOfPeople++;
        updateList();
    }

    private void downScale () {
        if (currentNumberOfPeople > 1) {
            currentNumberOfPeople--;
            updateList();
        }
    }

    private void updateList () {
        numberOfPeople.setText(PEOPLE + currentNumberOfPeople);
        ingredientStringsJList.setListData(recipe.getIngredientList()
                .stream().map(i -> i.reScale(currentNumberOfPeople)).collect(Collectors.toList()).toArray());
    }
}
