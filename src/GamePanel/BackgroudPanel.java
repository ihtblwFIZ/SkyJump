package GamePanel;

import javax.swing.*;
import java.awt.*;

public class BackgroudPanel extends JPanel {

    boolean running = false;

    int width = 0, height = 0;
    static int highScore = 0;
    static int yourScore = 0;
    public BackgroudPanel(int w, int h) {
        setBackground(new Color(220, 240, 255));
        width = w;
        height = h;
    }

    public void treadState(boolean state) {
        running = state;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
