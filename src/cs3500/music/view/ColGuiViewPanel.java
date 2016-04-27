package cs3500.music.view;

import javax.swing.*;
import java.awt.*;

/**
 * The panel for the beats
 */
public class ColGuiViewPanel extends JPanel {
    ConcreteGuiViewPanel parent;
    ColGuiViewPanel(ConcreteGuiViewPanel parent) {
        this.parent = parent;
    }

    @Override
    public void paintComponent(Graphics g){
        // Handle the default painting
        super.paintComponent(g);
        this.parent.paintLength(g);
    }
}
