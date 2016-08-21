package cookbook.displays;

import cookbook.CookBook;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static cookbook.displays.DisplayUtils.showCatDisplay;


public class MenuDisplay extends CookBookDisplay {

    private JFrame menuFrame;
    private JLabel picLabel;

    public MenuDisplay () throws IOException {
        picLabel = new JLabel();
        picLabel.setIcon(new ImageIcon(ImageIO.read(new File("images/StartPage.jpeg"))));
    }

    @Override
    public void setVisible (boolean b) {
        menuFrame.setVisible(b);
    }

    @Override
    void hideCurrentDisplay () {
        menuFrame.setVisible(false);
    }

    @Override
    void nextPage () {
        CookBook.setCurrCategory(0);
        showCatDisplay();
        hideCurrentDisplay();
    }

    public void createAndShowGUI () throws IOException {

        menuFrame = new JFrame("CookBook- Welcome!");
        JPanel recPanel = new JPanel(new MigLayout());

        recPanel.add(picLabel, "span 1 5");
        recPanel.add(createButtonWithProperties("Next", 150, 100), "wrap");

        CookBook.getCategories().forEach(c -> {
            JButton b = new JButton(c.getTitle());
            b.setPreferredSize(new Dimension(150, 100));
            b.setFont(new Font(null, Font.PLAIN, 18));
            b.addActionListener(e -> {
                CookBook.setCurrCategory(CookBook.getCategories().indexOf(c));
                showCatDisplay();
                hideCurrentDisplay();
            });
            recPanel.add(b, "wrap");
        });

        recPanel.add(new JTextArea("Welcome to the CookBook Application \n" +
                "Created by:\n" +
                "Marceli Baczewski\n"), "dock south");

        recPanel.setPreferredSize(new Dimension(1300, 900));
        menuFrame.getContentPane().add(recPanel);
        setFrameProperties();
    }

    private void setFrameProperties () {
        menuFrame.pack();
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }
}
