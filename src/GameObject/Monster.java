package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class Monster extends GameObject{
    // x, y 변화
    private double vx, vy;
    // 속도
    private double speed;

    public Monster(int x, int y, int w, int h) {
        super(x, y, w, h);

        vx = 0.0;
        vy = 0.0;
        speed = 5.0;

        // 색 임의 설정 -> 빨간색
        color = Color.RED;
        borderColors = Color.BLACK;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getX(), getY(), getW(), getH());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(2));
        Rectangle2D rect = new Rectangle2D.Float(getX(), getY(), getW(), getH());
        g2d.draw(rect);
    }

    @Override
    public void update(double dt) {
        setX((int) (getX() + vx * dt));
        setY((int) (getY() + vy * dt));
    }

    @Override
    public boolean resolve(GameObject o) {
        if (o == null) return false;
        if (!o.isIn(this)) return false;

        if (getX() < 5) setX(10);
        if (getX() + getW() > 800) setX(800 - getW());
        if (getY() < 5) setY(10);
        if (getY() + getH() > 800) setY(800 - getH());

        return false;
    }

    // 키 입력 감지 -> vx 혹은 vy 값 수정
    public void keyPressed(int code) {
        if (code == KeyEvent.VK_LEFT) {
            vx = -speed;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            vx = speed;
        }

        if (code == KeyEvent.VK_UP) {
            vy = -speed;
        }
    }

    // 키 입력 종료 감지 -> vx 혹은 vy 값 복귀
    public void keyReleased(int code) {
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
            vx = 0.0;
        }
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
            vy = 0.0;
        }
    }
}
