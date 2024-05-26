package GameObject;

import java.awt.*;

public class WallObject extends GameObject {
    /*
    x,y : 좌상단 모서리 좌표
    w : 벽의 가로 길이(너비)
    h : 벽의 세로 길이(높이)
     */
    public WallObject(int x,int y, int w, int h){
        super(x,y,w,h);
        color= Color.orange;    //벽 내부 채울 색 설정
        borderColors=Color.DARK_GRAY;   //벽 테두리 색 설정
    }

    // draw는 추후 디자인 변경 사항이 생기면 다시 수정
}
