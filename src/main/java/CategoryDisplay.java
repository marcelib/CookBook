import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;


class CategoryDisplay extends JFrame {

    private Category category;
    private JFrame catDisplay;
    private JPanel recPanel;
    private ArrayList<JButton> buttonList;

    CategoryDisplay (Category category) throws IOException {
        this.category = category;

        catDisplay = new JFrame("CookBook");
        recPanel = new JPanel(new MigLayout());
        catDisplay.pack();
    }


    private void previousPage () {
        if (CookBook.getCurrentCategory() == 0) {
            CookBook.getCatDisplay().setVisible(false);
            CookBook.getCatDisplay().removeAll();
            CookBook.getCatDisplay().dispose();
            CookBook.getMenuDisplay().setVisible(true);
        } else {
            CookBook.setCurrentCategory(CookBook.getCurrentCategory() - 1);
            CookBook.setCurrentRecipe(CookBook.getCategories().get(CookBook.getCurrentCategory()).getRecipeList().size() - 1);
            CookBook.getCatDisplay().setVisible(false);
            CookBook.getCatDisplay().removeAll();
            CookBook.getRecDisplay().updateAllComponents();
            CookBook.getRecDisplay().setVisible(true);
        }
    }

    private void nextPage () {
        CookBook.getCatDisplay().setVisible(false);
        CookBook.getCatDisplay().dispose();
        CookBook.setCurrentRecipe(0);
        CookBook.getRecDisplay().updateAllComponents();
        CookBook.getRecDisplay().setVisible(true);

    }

    public void updateAllComponents () {
        this.category = CookBook.getCategories().get(CookBook.getCurrentCategory());

        CookBook.getCatDisplay().recPanel.removeAll();
        try {
            CookBook.getCatDisplay().createAndShowGUI();
        } catch(IOException e) {
            e.printStackTrace();
        }

    }

    public void setVisible (boolean b) {
        catDisplay.setVisible(b);
    }

    void createAndShowGUI () throws IOException {

        ImageIcon categoryPicture = new ImageIcon(category.getCategoryImage());
        JLabel picLabel = new JLabel(categoryPicture);
        catDisplay.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buttonList = new ArrayList<>();
        JLabel catTitle = new JLabel("Category:     " + category.getTitle());
        catTitle.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        catTitle.setFont(new Font(null, Font.PLAIN, 18));


        JButton previousPage = new JButton("Back");
        previousPage.addActionListener(e -> previousPage());
        JButton nextPage = new JButton("Next");
        nextPage.addActionListener(e -> nextPage());
        JButton home = new JButton("Home");


        previousPage.setPreferredSize(new Dimension(100, 50));
        nextPage.setPreferredSize(new Dimension(100, 50));
        home.setPreferredSize(new Dimension(200, 50));
        home.addActionListener(e -> {
            CookBook.getCatDisplay().setVisible(false);
            recPanel.removeAll();
            CookBook.setCurrentCategory(0);
            CookBook.setCurrentRecipe(0);
            CookBook.getMenuDisplay().setVisible(true);
        });
        recPanel.add(picLabel, "span 1 9");
        recPanel.add(catTitle, "wrap");
        recPanel.add(home, "wrap");
        recPanel.add(previousPage, "split");
        recPanel.add(nextPage, "wrap");
        category.getRecipeList().forEach(e -> {
            JButton b = new JButton(e.getTitle());
            b.setPreferredSize(new Dimension(200, 50));
            b.addActionListener(a -> {
                buttonList.add(b);
                CookBook.setCurrentRecipe(category.getRecipeList().indexOf(e));
                CookBook.getRecDisplay().updateAllComponents();
                CookBook.getRecDisplay().setVisible(true);
                CookBook.getCatDisplay().setVisible(false);
                catDisplay.dispose();
            });
            recPanel.add(b, "wrap");
        });
        recPanel.setPreferredSize(new Dimension(1300, 900));
        catDisplay.getContentPane().add(recPanel);
        catDisplay.pack();
        catDisplay.setResizable(false);
        catDisplay.setLocationRelativeTo(null);
        catDisplay.setVisible(false);
    }
}
