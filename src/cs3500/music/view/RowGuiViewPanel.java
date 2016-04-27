package cs3500.music.view;

import javax.swing.*;
import java.awt.*;

/**
 * The panel for the pitches
 */
public class RowGuiViewPanel extends JPanel {
    ConcreteGuiViewPanel parent;
    RowGuiViewPanel(ConcreteGuiViewPanel parent) {
        this.parent = parent;
    }

    @Override
    public void paintComponent(Graphics g){
        // Handle the default painting
        super.paintComponent(g);

        this.parent.paintPitches(g);
    }
}
