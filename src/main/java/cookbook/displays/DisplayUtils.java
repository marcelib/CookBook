package cookbook.displays;

import cookbook.CookBook;

class DisplayUtils {

    /* methods responsible for showing a specific display type*/
    static void showRecDisplay() {
        CookBook.getRecDisplay().updateAllComponents();
        CookBook.getRecDisplay().setVisible(true);
        CookBook.getCatDisplay().setLocationRelativeTo(null);
    }

    static void showMenuDisplay() {
        CookBook.getMenuDisplay().setVisible(true);
        CookBook.getCatDisplay().setLocationRelativeTo(null);
    }

    static void showCatDisplay() {
        CookBook.getCatDisplay().updateAllComponents();
        CookBook.getCatDisplay().setVisible(true);
        CookBook.getCatDisplay().setLocationRelativeTo(null);
    }
}
