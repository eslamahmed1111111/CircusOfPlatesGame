package eg.edu.alexu.csd.oop.game;

import javax.swing.*;

public class MenuItem implements State {
    private JMenuItem menuItem;

    public MenuItem(JMenuItem menuItem) {
        this.menuItem = menuItem;
    }

    @Override
    public void update() {
        if(menuItem.isEnabled()){
            menuItem.setEnabled(false);
        }else {
            menuItem.setEnabled(true);
        }
    }
}

