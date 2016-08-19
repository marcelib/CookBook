package cookbook.displays;

import cookbook.CookBook;
import cookbook.model.Category;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CategoryDisplay extends CookBookDisplay {

    private static final Logger LOGGER = Logger.getLogger(CategoryDisplay.class.getName());
    private Category category;
    private JFrame categoryFrame;
    private JPanel recPanel;
    private Map<String, Runnable> actions;

    public CategoryDisplay (Category category) throws IOException {
        this.category = category;

        categoryFrame = new JFrame("CookBook");
        recPanel = new JPanel(new MigLayout());
        categoryFrame.pack();
        actions = new HashMap<>();
        actions.put("Back", this::previousPage);
        actions.put("Next", this::nextPage);
        actions.put("Home", this::returnToHome);
    }

    @Override
    public void setVisible (boolean b) {
        categoryFrame.setVisible(b);
    }

    @Override
    public void nextPage () {
        hideCatDisplay();
        CookBook.setCurrentRecipe(0);
        CookBook.getRecDisplay().updateAllComponents();
        CookBook.getRecDisplay().setVisible(true);
    }

    @Override
    public void previousPage () {
        if (CookBook.getCurrentCategory() == 0) {
            hideCatDisplay();
            CookBook.getMenuDisplay().setVisible(true);
        } else {
            CookBook.setCurrentCategory(CookBook.getCurrentCategory() - 1);
            CookBook.setCurrentRecipe(CookBook.getCategories()
                    .get(CookBook.getCurrentCategory()).getRecipeList().size() - 1);
            hideCatDisplay();
            CookBook.getRecDisplay().updateAllComponents();
            CookBook.getRecDisplay().setVisible(true);
        }
    }

    private void hideCatDisplay () {
        CookBook.getCatDisplay().setVisible(false);
        CookBook.getCatDisplay().removeAll();
    }

    private void returnToHome () {
        hideCatDisplay();
        CookBook.setCurrentCategory(0);
        CookBook.setCurrentRecipe(0);
        CookBook.getMenuDisplay().setVisible(true);
    }

    private JButton createButtonWithProperties (String title, int width, int height) {
        JButton button = new JButton(title);
        button.addActionListener(e -> actions.get(title).run());
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    void updateAllComponents () {
        this.category = CookBook.getCategories().get(CookBook.getCurrentCategory());
        CookBook.getCatDisplay().recPanel.removeAll();
        try {
            CookBook.getCatDisplay().createAndShowGUI();
        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, "An IOException has occured", e);
        }
    }

    public void createAndShowGUI () throws IOException {

        JLabel picLabel = new JLabel(new ImageIcon(category.getCategoryImage()));
        categoryFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JLabel catTitle = new JLabel("Category:     " + category.getTitle());
        catTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        catTitle.setFont(new Font(null, Font.PLAIN, 18));

        JButton previousPage = createButtonWithProperties("Back", 98, 50);
        JButton nextPage = createButtonWithProperties("Next", 98, 50);
        JButton home = createButtonWithProperties("Home", 200, 50);

        recPanel.add(picLabel, "span 1 9");
        recPanel.add(catTitle, "wrap");
        recPanel.add(home, "wrap");
        recPanel.add(previousPage, "split");
        recPanel.add(nextPage, "wrap");

        category.getRecipeList().forEach(e -> {
            JButton button = new JButton(e.getTitle());
            button.setPreferredSize(new Dimension(200, 50));
            button.addActionListener(a -> {
                CookBook.setCurrentRecipe(category.getRecipeList().indexOf(e));
                CookBook.getRecDisplay().updateAllComponents();
                CookBook.getRecDisplay().setVisible(true);
                CookBook.getCatDisplay().setVisible(false);
            });
            recPanel.add(button, "wrap");
        });
        recPanel.setPreferredSize(new Dimension(1300, 900));
        categoryFrame.getContentPane().add(recPanel);
        categoryFrame.pack();
        categoryFrame.setResizable(false);
        categoryFrame.setLocationRelativeTo(null);
        categoryFrame.setVisible(false);
    }
}
