package cookbook.displays;

import javax.swing.*;
import java.awt.*;

import static cookbook.CookBook.*;

class DisplayUtils {

    private DisplayUtils () {}

    /* methods responsible for showing a specific display type*/
    static void showRecDisplay () {
        getRecDisplay().updateAllComponents();
        getRecDisplay().setVisible(true);
        getCatDisplay().setLocationRelativeTo(null);
    }

    static void showMenuDisplay () {
        getMenuDisplay().setVisible(true);
        getCatDisplay().setLocationRelativeTo(null);
    }

    static void showCatDisplay () {
        getCatDisplay().updateAllComponents();
        getCatDisplay().setVisible(true);
        getCatDisplay().setLocationRelativeTo(null);
    }

    static JComponent createJComponent (String type, String title, int width, int height) {
        JComponent component;
        if ("JLabel".equals(type)) {
            component = new JLabel(title);
        } else if ("JList".equals(type)) {
            component = new JList<>();
        } else {
            component = new JTextArea(title);
        }
        setComponentProperties(component, width, height);
        return component;
    }

    static void setComponentProperties (JComponent component, int width, int height) {
        component.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        component.setPreferredSize(new Dimension(width, height));
        component.setFont(new Font(null, Font.PLAIN, 18));
    }

    static void setFrameProperties (JFrame frame, boolean visibility) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(visibility);
    }
}
