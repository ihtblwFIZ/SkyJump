package GameObject;

import java.awt.*;


public class ExitObject extends GameObject {

    private boolean visible;

    public ExitObject(int x, int y, int w, int h) {
        super(x, y, w, h);
        visible = true;

        color = new Color(235, 100, 175);
        borderColors = Color.BLACK;
    }

    // draw는 추후 디자인 변경 사항이 생기면 다시 수정

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
        double xMin = this.getX();
        double yMin = this.getY();
        double xMax = this.getX() + this.getW();
        double yMax = this.getY() + this.getH();

        return o.getX() + o.getW() > xMin && o.getX() < xMax &&
                o.getY() + o.getH() > yMin && o.getY() < yMax;
    }
}
