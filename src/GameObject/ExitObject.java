package GameObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;


public class ExitObject extends GameObject {

    private boolean visible;

    public ExitObject(int x, int y, int w, int h) {
        super(x, y, w, h);
        visible = true;

        color = new Color(235, 100, 175);
        borderColors = Color.BLACK;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(this.getX(), this.getY(), this.getW(), this.getW());

        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        Rectangle2D rect = new Rectangle2D.Float(this.getX(), this.getY(), this.getW(), this.getW());
        g2d.draw(rect);
    }

    //깜빡이게 하고 싶어서 넣었는데 될지 모르겠음
    public void update() {
        visible = !visible;
        if (visible) {
            color = new Color(235, 100, 175);
        }
        else {
            color = new Color(255, 255, 181);
            borderColors = Color.WHITE;
        }
    }

    @Override
    public boolean isIn(GameObject o) {
        double xmin = this.getX();
        double ymin = this.getY();
        double xmax = this.getX() + this.getW();
        double ymax = this.getY() + this.getH();

        if (o.getX() + o.getW() > xmin && o.getX() < xmax &&
                o.getY() + o.getH() > ymin && o.getY() < ymax) {
            return true;
        }
        return false;
    }
}
