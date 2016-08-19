package cookbook.displays;

import javax.swing.*;

abstract class CookBookDisplay extends JFrame {

    @Override
    public abstract void setVisible (boolean b);

    public abstract void nextPage ();

    public void previousPage () {
    }
}