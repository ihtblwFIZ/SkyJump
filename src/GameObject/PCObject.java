package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PCObject extends GameObject {

    private double vx = 0.0, vy = 0.0; //PC 위치 업데이트에 쓰일 변수
    private double pcSpeed = 5.0; //

    public PCObject(int x, int y, int w, int h) {
        super(x, y, w, h);
        color = Color.CYAN; //pc 색은 임의로 해놨음 변경o
        borderColors = Color.BLACK;
    }

    // draw는 추후 디자인 변경 사항이 생기면 다시 수정

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
        x += (int) (vx * dt);
        y += (int) (vy * dt);
        setX(x);
        setY(y);
    }

    @Override
    public boolean resolve(GameObject o) {

        if (o == null) {
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

