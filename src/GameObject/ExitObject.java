package GameObject;

import java.awt.*;


public class ExitObject extends GameObject {

    public ExitObject(int x, int y, int w, int h) {
        super(x, y, w, h);

        color = new Color(235, 100, 175);
        borderColors = Color.BLACK;
    }

    // draw는 추후 디자인 변경 사항이 생기면 다시 수정
}
