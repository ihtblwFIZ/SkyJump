package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

public class PCObject extends GameObject {

    private double vx = 0.0, vy = 0.0; //PC 위치 업데이트에 쓰일 변수
    private double pcSpeed = 5.0; //

    public PCObject(int x, int y, int w, int h) {
        super(x, y, w, h);
        color = Color.CYAN; //pc 색은 임의로 해놨음 변경o
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

    public void keyPressed(int code) {
        if (code == KeyEvent.VK_LEFT) {
            vx = -pcSpeed;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            vx = pcSpeed;
        }
        if (code == KeyEvent.VK_UP) {
            vy = -pcSpeed;
        }
        else if (code == KeyEvent.VK_DOWN) {
            vy = pcSpeed;
        }
    }

    public void keyReleased(int code) {
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
            vx = 0.0;
        }
        if (code == KeyEvent.VK_UP || code == KeyEvent.VK_DOWN) {
            vy = 0.0;
        }
    }

    @Override
    public void update(double dt) {
        int x = getX();
        int y = getY();
        x += vx*dt;
        y += vy*dt;
        setX(x);
        setY(y);
    }

    @Override
    public boolean resolve(GameObject o) {

        if (!(o instanceof GameObject)) {
            return false;
        }
        if (!o.isIn(this))  //벽, npc, 코인, 출구 오브젝트에 다 있어야함
            return false;

        //이 아래는 벽이랑 충돌했을 때임
        //npc, 코인, 출구랑 충돌했을 때 위치 다시 설정해야함
        if (getX() < 5)
            setX(10);
        if (getX() + this.getW() > 800)
            setX(800 - this.getW());
        if (getY() < 5)
            setY(10);
        if (getY() + this.getH() > 800)
            setY(800 - this.getH());
        return false;
    }

}

