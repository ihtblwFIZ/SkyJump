package GameObject;

import java.awt.*;
import java.awt.geom.Rectangle2D;

// 추상 클래스(구현된 코드는 자식 클래스에서 사용 가능, abstract 메서드 구현은 필수)
public abstract class GameObject {
    // 객체의 좌표와 관련된 공통 변수 네 개
    // 변수에 직접 접근하지 못하도록 private으로 설정함
    // get, set으로 접근하게 함
    protected Integer x, y;
    protected Integer w, h;

    // x, y 속도
    protected double vx = 0.0, vy = 0.0;
    // 땅에 있는지 (떨어지는 위치에 있지 않은지 확인)
    protected boolean isOnGround = true;

    protected static final double SPEED = 5.0;
    protected static final double JUMP_SPEED = 5.0;
    protected static final double GRAVITY = 0.05;

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
    // 이번 커밋까지는 draw 내용이 다 동일해서 그냥 여기에 구현해놓음
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(getX(), getY(), getW(), getH());

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4));
        Rectangle2D rect = new Rectangle2D.Float(getX(), getY(), getW(), getH());
        g.setColor(borderColors);
        g2d.draw(rect);
    }

    // update는 움직이는 객체만 사용 (위치 정보를 업데이트)
    public void update(double dt) { }

    // resolve는 움직이는 객체만 사용
    // 움직이는 객체를 중심으로 다른 객체(o)와의 충돌 상황을 확인하고 위치 및 이동 방향 변경
    // 충돌이 있어서 변경 사항이 있으면 true, 없으면 false 반환
    public boolean resolve(GameObject o) {
        if (o == null) return false;
        //if (!o.isIn(this)) return false;

        //if (o instanceof CoinObject) {
            // isIn으로 판단
        //    return isIn(o);
        //}

        //else {
            // 1. 위에서 접근하는 경우 - isOnGround 설정 필요
            if (y < o.getY() && (y + h) > o.getY()) {
                if ((x > o.getX() && x < (o.getX() + o.getW()))
                        || ((x + w) > o.getX() && (x + w) < (o.getX() + o.getW()))) {
                    y =  o.getY() - h;
                    return true;
                }
            }

            // 2. 밑에서 접근하는 경우
            if (y < (o.getY() + o.getH()) && (y + h) > (o.getY() + o.getH())) {
                if ((x > o.getX() && x < (o.getX() + o.getW()))
                        || ((x + w) > o.getX() && (x + w) < (o.getX() + o.getW()))) {
                    y = o.getY() + o.getH();
                    vy =0;
                    return true;
                }
            }

            // 3. 벽의 왼쪽으로 접근하는 경우
            if (x < o.getX() && (x + w) > o.getX()) {
                if ((y > o.getY() && y < (o.getY()+o.getH()))
                        || ((y + h) > o.getY() && (y + h) < (o.getY() + o.getH()))) {
                    x = o.getX() - w;
                    return true;
                }
            }

            // 4. 벽의 오른쪽으로 접근하는 경우
            if (x < (o.getX() + o.getW()) && (x + w) > (o.getX() + o.getW())) {
                if ((y > o.getY() && y < (o.getY()+o.getH()))
                        || ((y + h) > o.getY() && (y + h) < (o.getY() + o.getH()))) {
                    x = o.getX() + o.getW();
                    return true;
                }
            }
        //}

        return false;
    }

    // isIn는 움직이는 o 내부에 this의 좌표가 있는지 확인
    // 좌표를 바탕으로 계산함
    // 충돌했으면 true, 하지 않았으면 false 반환
    public boolean isIn(GameObject o) {
        if (this instanceof CoinObject) {
            if (y > o.getY() && (y + h) < o.getY()) {
                return ((x > o.getX() && x < (o.getX() + o.getW()))
                    || ((x + w) > o.getX() && (x + w) < (o.getX() + o.getW())));
            }
        }

        return false;
    }

}