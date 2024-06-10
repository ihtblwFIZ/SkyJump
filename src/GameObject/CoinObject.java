package GameObject;

import java.awt.*;

public class CoinObject extends GameObject {
    int centerX, centerY;

    public CoinObject(int x, int y, int w, int h) {
        super(x, y, w, h);
        color = Color.PINK; // 코인 color
        borderColors = Color.darkGray;  // 코인 테두리 color

        centerX = x + w / 2;
        centerY = y + h / 2;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillOval(x, y, w, h);

        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));
        // g2.setPaint(gradientPaint);
        g.setColor(Color.black);
        g.drawOval(x, y, w, h);
    }
}