package cookbook.displays;

import cookbook.CookBook;
import cookbook.model.Category;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static cookbook.displays.DisplayUtils.showMenuDisplay;
import static cookbook.displays.DisplayUtils.showRecDisplay;

public class CategoryDisplay extends CookBookDisplay {

    private static final Logger LOGGER = Logger.getLogger(CategoryDisplay.class.getName());
    private Category category;
    private JFrame categoryFrame;
    private JPanel recPanel;

    public CategoryDisplay (Category category) throws IOException {
        super();
        this.category = category;
        categoryFrame = new JFrame("CookBook");
        recPanel = new JPanel(new MigLayout());
    }

    @Override
    public void setVisible (boolean b) {
        categoryFrame.setVisible(b);
    }

    @Override
    void nextPage () {
        CookBook.setCurrRecipe(0);
        showRecDisplay();
        hideCurrentDisplay();
    }

    @Override
    void previousPage () {
        if (CookBook.getCurrCategory() == 0) {
            showMenuDisplay();
            hideCurrentDisplay();
        } else {
            CookBook.setCurrCategory(CookBook.getCurrCategory() - 1);
            CookBook.setCurrRecipe(CookBook.getCategories()
                    .get(CookBook.getCurrCategory()).getRecList().size() - 1);
            showRecDisplay();
            hideCurrentDisplay();
        }
    }

    @Override
    void hideCurrentDisplay () {
        CookBook.getCatDisplay().setVisible(false);
        CookBook.getCatDisplay().removeAll();
    }

    void updateAllComponents (){
        this.category = CookBook.getCategories().get(CookBook.getCurrCategory());
        CookBook.getCatDisplay().recPanel.removeAll();
        try {
            CookBook.getCatDisplay().createAndShowGUI();
        } catch(IOException e) {
            LOGGER.log(Level.SEVERE, "An IOException has occurred", e);
        }
    }

    public void createAndShowGUI () throws IOException {

        JLabel picLabel = new JLabel(new ImageIcon(category.getCatImage()));
        JLabel catTitle = new JLabel("Category:     " + category.getTitle());
        catTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        catTitle.setFont(new Font(null, Font.PLAIN, 18));

        recPanel.add(picLabel, "span 1 9");
        recPanel.add(catTitle, "wrap");
        recPanel.add(createButtonWithProperties("Home", 200, 50), "wrap");
        recPanel.add(createButtonWithProperties("Back", 98, 50), "split");
        recPanel.add(createButtonWithProperties("Next", 98, 50), "wrap");

        category.getRecList().forEach(e -> {
            JButton button = new JButton(e.getTitle());
            button.setPreferredSize(new Dimension(200, 50));
            button.addActionListener(a -> {
                CookBook.setCurrRecipe(category.getRecList().indexOf(e));
                showRecDisplay();
                hideCurrentDisplay();
            });
            recPanel.add(button, "wrap");
        });
        setFrameProperties();
        recPanel.setPreferredSize(new Dimension(1300, 900));
        categoryFrame.getContentPane().add(recPanel);
    }

    private void setFrameProperties () {
        categoryFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        categoryFrame.pack();
        categoryFrame.setResizable(false);
        categoryFrame.setLocationRelativeTo(null);
        categoryFrame.setVisible(false);
    }
}