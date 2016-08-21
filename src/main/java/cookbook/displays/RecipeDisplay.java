package cookbook.displays;

import cookbook.CookBook;
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

import static cookbook.displays.DisplayUtils.showCatDisplay;

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

    public RecipeDisplay(Recipe recipe) {
        this.recipe = recipe;

        recipeFrame = new JFrame("CookBook");
        recPanel = new JPanel(new MigLayout());
        recipePicture = new ImageIcon(recipe.getImage());
        picLabel = new JLabel(recipePicture);
        currentNumberOfPeople = recipe.getNumberOfPeople();
        /*button construction and configuration*/
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
    public void setVisible(boolean b) {
        recipeFrame.setVisible(b);
    }

    @Override
    void nextPage() {
        if (CookBook.getCategories().get(CookBook.getCurrCategory()).getRecList().size() > CookBook.getCurrRecipeIndex() + 1) {
            CookBook.setCurrRecipe(CookBook.getCurrRecipeIndex() + 1);
            updateAllComponents();
        } else if (CookBook.getCategories().size() > CookBook.getCurrCategory() + 1) {
            CookBook.setCurrCategory(CookBook.getCurrCategory() + 1);
            CookBook.setCurrRecipe(0);
            showCatDisplay();
        }
    }

    @Override
    void previousPage() {
        if (CookBook.getCurrRecipeIndex() > 0) {
            CookBook.setCurrRecipe(CookBook.getCurrRecipeIndex() - 1);
            updateAllComponents();
        } else {
            hideCurrentDisplay();
            showCatDisplay();
        }
    }

    @Override
    void hideCurrentDisplay() {
        CookBook.getRecDisplay().setVisible(false);
    }

    void updateAllComponents() {
        this.recipe = CookBook.getCategories().get(CookBook.getCurrCategory())
                .getRecList().get(CookBook.getCurrRecipeIndex());
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

    public void createAndShowGUI() throws IOException {

        /* text field construction and property setting- recipe title */
        recipeTitle = new JLabel("Recipe:     " + recipe.getTitle());
        recipeTitle.setPreferredSize(new Dimension(200, 50));
        recipeTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        recipeTitle.setFont(new Font(null, Font.PLAIN, 18));
        /* recipe description */
        recipeDescription = new JTextArea(recipe.getDescription());
        recipeDescription.setPreferredSize(new Dimension(400, 100));
        recipeDescription.setFont(new Font(null, Font.PLAIN, 18));
        recipeDescription.setEditable(false);
        /* current number of people */
        numberOfPeople = new JLabel(PEOPLE + currentNumberOfPeople);
        numberOfPeople.setPreferredSize(new Dimension(100, 50));
        numberOfPeople.setFont(new Font(null, Font.PLAIN, 18));
        /* ingredient list construction */
        DefaultListModel<Object> listModel = new DefaultListModel<>();
        recipe.getIngredientList().forEach(i -> i.reScale(currentNumberOfPeople));
        recipe.getIngredientList().forEach(listModel::addElement);
        ingredientStringsJList = new JList<>(listModel);
        ingredientStringsJList.setFont(new Font(null, Font.PLAIN, 18));
        ingredientStringsJList.setPreferredSize(new Dimension(200, 500));
        /*adding objects to the JPanel*/
        pairs.addAll(Arrays.asList(new Pair<>(picLabel, "span 1 9"), new Pair<>(numberOfPeople, SPLIT),
                new Pair<>(downScale, SPLIT), new Pair<>(upScale, WRAP), new Pair<>(ingredientStringsJList, WRAP),
                new Pair<>(recipeDescription, "dock south"), new Pair<>(recipeTitle, WRAP), new Pair<>(home, WRAP),
                new Pair<>(previousPage, SPLIT), new Pair<>(nextPage, WRAP)));
        addElementsToLabel(pairs);
        recPanel.setPreferredSize(new Dimension(1300, 900));
        setFrameProperties();
    }

    private void addElementsToLabel(ArrayList<Pair<JComponent, String>> pairs) {
        pairs.forEach(e -> recPanel.add(e.getKey(), e.getValue()));
    }

    private void upScale() {
        currentNumberOfPeople++;
        updateList();
    }

    private void downScale() {
        if (currentNumberOfPeople > 1) {
            currentNumberOfPeople--;
            updateList();
        }
    }

    private void updateList() {
        numberOfPeople.setText(PEOPLE + currentNumberOfPeople);
        recipe.getIngredientList().forEach(i -> i.reScale(currentNumberOfPeople));
        ingredientStringsJList.setListData(recipe.getIngredientList()
                .stream().map(Ingredient::toString).collect(Collectors.toList()).toArray());
    }

    private void setFrameProperties() {
        recipeFrame.getContentPane().add(recPanel);
        recipeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        recipeFrame.pack();
        recipeFrame.setResizable(false);
        recipeFrame.setLocationRelativeTo(null);
        recipeFrame.setVisible(false);
    }
}
