package cookbook.displays;

import cookbook.model.Category;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cookbook.CookBook.*;
import static cookbook.displays.DisplayUtils.*;

public class CategoryDisplay extends CookBookDisplay {

    private static final Logger LOGGER = Logger.getLogger(CategoryDisplay.class.getName());
    private Category category;
    private JFrame categoryFrame;
    private JPanel recPanel;

    public CategoryDisplay(Category category) throws IOException {
        super();
        this.category = category;
        categoryFrame = new JFrame("CookBook");
        recPanel = new JPanel(new MigLayout());
    }

    @Override
    public void setVisible(boolean b) {
        categoryFrame.setVisible(b);
    }

    @Override
    void nextPage() {
        setCurrRecipe(0);
        showRecDisplay();
        hideCurrentDisplay();
    }

    @Override
    void previousPage() {
        if (getCurrCategory() == 0) {
            showMenuDisplay();
            hideCurrentDisplay();
        } else {
            setCurrCategory(getCurrCategory() - 1);
            setCurrRecipe(getCategories()
                    .get(getCurrCategory()).getRecipeList().size() - 1);
            showRecDisplay();
            hideCurrentDisplay();
        }
    }

    @Override
    void hideCurrentDisplay() {
        getCatDisplay().setVisible(false);
        getCatDisplay().removeAll();
    }

    void updateAllComponents() {
        this.category = getCategories().get(getCurrCategory());
        getCatDisplay().recPanel.removeAll();
        try {
            getCatDisplay().createAndShowGUI();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "An IOException has occurred", e);
        }
    }

    public void createAndShowGUI() throws IOException {

        JLabel picLabel = new JLabel(category.getCategoryImage());
        JLabel catTitle = new JLabel("Category:     " + category.getTitle());
        catTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        catTitle.setFont(new Font(null, Font.PLAIN, 18));

        recPanel.add(picLabel, "span 1 9");
        recPanel.add(catTitle, "wrap");
        recPanel.add(createButtonWithProperties("Home", 200, 50), "wrap");
        recPanel.add(createButtonWithProperties("Back", 98, 50), "split");
        recPanel.add(createButtonWithProperties("Next", 98, 50), "wrap");

        category.getRecipeList().forEach(e -> {
            JButton button = new JButton(e.getTitle());
            button.setPreferredSize(new Dimension(200, 50));
            button.addActionListener(a -> {
                setCurrRecipe(category.getRecipeList().indexOf(e));
                showRecDisplay();
                hideCurrentDisplay();
            });
            recPanel.add(button, "wrap");
        });
        setFrameProperties(categoryFrame, false);
        recPanel.setPreferredSize(new Dimension(1300, 900));
        categoryFrame.getContentPane().add(recPanel);
    }
}