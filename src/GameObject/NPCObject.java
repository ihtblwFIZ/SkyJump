package GameObject;

import java.awt.*;
import java.awt.event.KeyEvent;

public class NPCObject extends GameObject{
    // x, y 변화
    private final double vx;
    private final double vy;
    // 속도
    private final double speed;

    public NPCObject(int x, int y, int w, int h) {
        super(x, y, w, h);

        speed = 5.0;
        vx = 1.0;
        vy = 1.0;

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
}
