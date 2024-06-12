package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class PCObject extends GameObject {
    public PCObject(int x, int y, int w, int h) {
        super(x, y, w, h);
        color = Color.CYAN; //pc 색은 임의로 해놨음 변경o
        borderColors = Color.BLACK;
    }

    // draw는 추후 디자인 변경 사항이 생기면 다시 수정

    public void keyPressed(int code) {
        int nX = getX();
        if (code == KeyEvent.VK_LEFT) {
            nX -= SPEED*2;
        }
        else if (code == KeyEvent.VK_RIGHT) {
            nX += SPEED*2;
        }
        if (code == KeyEvent.VK_UP /*&& isOnGround*/) {
            vy = -JUMP_SPEED;
            isOnGround = false; // 점프 중에 또 다시 점프 불가
        }
        setX(nX);
    }

    public void keyReleased(int code) {
        if (code == KeyEvent.VK_LEFT || code == KeyEvent.VK_RIGHT) {
            vx = 0.0;
        }
    }

    @Override
    public void update(double dt) {
        int nX = getX();
        int nY = getY();

        // 중력 적용
        vy += GRAVITY * dt;

        nY += vy * dt;

        setX(nX);
        setY(nY);
    }
}

