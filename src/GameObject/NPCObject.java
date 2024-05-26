package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NPCObject extends GameObject{
    // x, y 변화
    private double vx, vy;
    // 속도
    private final double speed;

    public NPCObject(int x, int y, int w, int h) {
        super(x, y, w, h);

        vx = 0.0;
        vy = 0.0;
        speed = 5.0;

        // 색 임의 설정 -> 빨간색
        color = Color.RED;
        borderColors = Color.BLACK;
    }

    // draw는 추후 디자인 변경 사항이 생기면 다시 수정

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
