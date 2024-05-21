package GameObject;

import java.awt.*;

// 추상 클래스(구현된 코드는 자식 클래스에서 사용 가능, abstract 메서드 구현은 필수)
public abstract class GameObject {
    // 객체의 좌표와 관련된 공통 변수 네 개
    // 변수에 직접 접근하지 못하도록 private으로 설정함
    // get, set으로 접근하게 함
    private Integer x, y;
    private Integer w, h;

    // 물체를 그리는 데에 필요한 변수들 (draw에서 사용)
    Color color; // 물체의 색
    Color borderColors; // 물체 테두리 색

    // x, y, w, h는 자식 클래스의 객체를 생성할 때 꼭 필요함
    // x, y, w, h 값 할당은 super(x, y, w, h); 로 가능
    // 즉, 자식 클래스의 생성자 코드는 super(x, y, w, h); 를 작성 후 필요한 코드를 쓰면 됨
    public GameObject(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }

    public Integer getX() {
        return x;
    }

    public Integer getY() {
        return y;
    }

    public Integer getW() {
        return w;
    }

    public Integer getH() {
        return h;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setH(int h) {
        this.h = h;
    }

    // 객체를 그리는 메서드
    // 자식 클래스에서 구현 필수
    public abstract void draw(Graphics g);

    // update는 움직이는 객체만 사용 (위치 정보를 업데이트)
    public void update(double dt) {};

    // resolve는 움직이는 객체만 사용
    // 움직이는 객체를 중심으로 다른 객체(o)와의 충돌 상황을 확인하고 위치 및 이동 방향 변경
    // 충돌이 있어서 변경 사항이 있으면 true, 없으면 false 반환
    public boolean resolve(GameObject o) {
        return false;
    };

    // isIn는 움직이는 객체가 다른 객체(o)에 충돌했는지 확인
    // 좌표를 바탕으로 계산함
    // 충돌했으면 true, 하지 않았으면 false 반환
    public boolean isIn(GameObject o) {
        return false;
    }
}