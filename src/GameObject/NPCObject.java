package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

public class NPCObject extends GameObject{
    boolean direction;
    boolean jump;

    public NPCObject(int x, int y, int w, int h) {
        super(x, y, w, h);

        // 색 임의 설정 -> 빨간색
        color = Color.RED;
        borderColors = Color.BLACK;
    }

    // draw는 추후 디자인 변경 사항이 생기면 다시 수정

    public void setDirection() {
        Random random = new Random();
        int d = random.nextInt(10);

        if (d < 5) direction = true;
        if (d > 5) direction = false;
        if (d < 4) jump = true;
    }

    @Override
    public void update(double dt) {
        vy +=GRAVITY * dt;

        if (direction) {
            x++;
        }
        if (!direction) {
            x--;
        }
        if (jump) {
            vy = -JUMP_SPEED;
            jump = false;
        }

        y += (int) (vy * dt);
    }
}
