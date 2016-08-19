package cookbook.displays;

import cookbook.CookBook;
import net.miginfocom.swing.MigLayout;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class MenuDisplay extends CookBookDisplay {

    private JFrame menuFrame;
    private JPanel recPanel;
    private JLabel picLabel;


    public MenuDisplay () throws IOException {
        picLabel = new JLabel();
        picLabel.setIcon(new ImageIcon(ImageIO.read(new File("images/StartPage.jpeg"))));
    }

    public void setVisible (boolean b) {
        menuFrame.setVisible(b);
    }

    private void nextPageFunction () {
        menuFrame.setVisible(false);
        CookBook.setCurrentCategory(0);

        CookBook.getCatDisplay().updateAllComponents();
        CookBook.getCatDisplay().setVisible(true);
        CookBook.getCatDisplay().setLocationRelativeTo(null);
    }


    public void createAndShowGUI () throws IOException {

        menuFrame = new JFrame("CookBook- Welcome!");
        recPanel = new JPanel(new MigLayout());
        recPanel.add(picLabel, "span 1 5");
        JButton nextPage = new JButton("Next");
        nextPage.setPreferredSize(new Dimension(150, 100));
        nextPage.setFont(new Font(null, Font.PLAIN, 18));
        nextPage.addActionListener(e -> nextPageFunction());
        recPanel.add(nextPage, "wrap");
        CookBook.getCategories().forEach(c -> {
            JButton b = new JButton(c.getTitle());
            b.setPreferredSize(new Dimension(150, 100));
            b.setFont(new Font(null, Font.PLAIN, 18));
            b.addActionListener(e -> {
                CookBook.setCurrentCategory(CookBook.getCategories().indexOf(c));
                CookBook.getCatDisplay().updateAllComponents();
                CookBook.getCatDisplay().setVisible(true);
                CookBook.getMenuDisplay().setVisible(false);
            });
            recPanel.add(b, "wrap");
        });
        JTextArea welcome = new JTextArea("Welcome to the CookBook Application \n" +
                "Created by:\n" +
                "Marceli Baczewski\n");
        recPanel.add(welcome, "dock south");

        recPanel.setPreferredSize(new Dimension(1300, 900));
        CookBook.getRecDisplay().setVisible(false);
        menuFrame.getContentPane().add(recPanel);
        menuFrame.pack();
        menuFrame.setResizable(false);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        menuFrame.setVisible(true);
    }
}
