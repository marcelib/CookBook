package cookbook.displays;

import cookbook.CookBook;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static cookbook.displays.DisplayUtils.showMenuDisplay;

abstract class CookBookDisplay extends JFrame {

    private Map<String, Runnable> actions;

    CookBookDisplay() {
        actions = new HashMap<>();
        actions.put("Back", this::previousPage);
        actions.put("Next", this::nextPage);
        actions.put("Home", this::returnToHome);
    }

    /* present in child classes */
    @Override
    public abstract void setVisible(boolean b);

    abstract void hideCurrentDisplay();

    abstract void nextPage();

    /* not necessarily in child classes */
    void previousPage() {
    }

    /* used in button generation */
    JButton createButtonWithProperties(String title, int width, int height) {
        JButton button = new JButton(title);
        if (actions.containsKey(title)) {
            button.addActionListener(e -> actions.get(title).run());
        }
        button.setPreferredSize(new Dimension(width, height));
        return button;
    }

    private void returnToHome() {
        CookBook.setCurrCategory(0);
        CookBook.setCurrRecipe(0);
        showMenuDisplay();
        hideCurrentDisplay();
    }
}