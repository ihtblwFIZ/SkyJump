package GamePanel;

import GameObject.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.List;

public class PlayPanel extends BackgroudPanel implements Runnable {
    // n번째 stage
    int stage = 0;

    // 스테이지 모양과 관련된 변수들
    // 전체 스테이지 디자인
    List<List<Point>> mapDesign = new ArrayList<>();
    // 스테이지에서 사용할 디자인 (mapDesign에서 랜덤 3개 + 바닥이 될 7번 디자인)
    List<Integer> stageMap = new ArrayList<>();

    // GameObject와 관련된 변수들
    // 화면 내에서 밟을 수 있는 블록, 스테이지마다 초기화
    List<WallObject> block = new ArrayList<>();
    // 플레이 화면 범위를 설정하는 벽
    List<WallObject> wall = new ArrayList<>();
    // 블록의 위치
    List<Integer> blockY = new ArrayList<>();
    // 코인 개수
    int coinNum = 0;
    // 코인
    List<CoinObject> coin = new ArrayList<>();
    // NPC 수
    int npcNum = 0;
    // NPC
    final List<NPCObject> npc = new ArrayList<>();
    // PC
    PCObject pc = null;
    // Exit
    ExitObject exit = null;

    public PlayPanel(int w, int h) {
        super(w, h);

        setLayout(null);

        // 전체 맵 디자인 (쓰일거 안 쓰일거 전부)
        setMapDesign();
        // 천장, 좌우벽
        setWall();

        // 스레드
        Thread thread = new Thread(this);
        thread.start();
    }

    public void keyPressed(KeyEvent e) {
        // 키 종류는 KeyReleased와 동일
        // 스페이스, 좌우 방향키는 PC 움직임에 필요
        // 엔터로 게임 진행 (이 키는 프레임 단에서 변경해도 상관 없음)
        pc.keyPressed(e.getKeyCode());
    }

    public void keyReleased(KeyEvent e) {
        pc.keyReleased(e.getKeyCode());
    }

    private void nextStage() {
        stage++;

        // 맵 디자인 설정
        block.clear();
        setStageMap();

        // PC 관련 설정
        setPc();

        // NPC 관련 설정
        npcNum = stage;
        npc.clear();
        setNpc();

        // coin 관련 설정
        coinNum = stage * 2;
        coin.clear();
        setCoin();

        // Exit 관련 설정
        exit = null;

        // 스테이지 넘어가는 텀
        if (stage > 1) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored) {
            }
        }
    }

    private List<Integer> randomX(int num, boolean isPc) {
        Random random = new Random();

        List<Integer> rx = new ArrayList<>();

        while (rx.size() < num) {
            int x = random.nextInt(width - 100) +15;
            boolean isVaild = true;

            for (int r : rx) {
                if (Math.abs(r - x) <= 40) {
                    isVaild = false;
                    break;
                }
                if (!isPc && Math.abs(pc.getX() - x) <= 40) {
                    isVaild = false;
                    break;
                }
            }

            if (isVaild) {
                rx.add(x);
            }
        }

        return rx;
    }

    private void setPc() {
        int pcX = randomX(1, true).get(0);

        int y = blockY.get(1);
        pc = new PCObject(pcX, y - 70, 30, 70);
    }

    private void setNpc() {
        Random random = new Random();
        List<Integer> npcX = randomX(npcNum, false);

        for (int i =0; i<npcNum; i++) {
            int y = blockY.get(random.nextInt(4));
            npc.add(new NPCObject(npcX.get(i), y - 70, 30, 70));
        }
    }

    private void setCoin() {
        Random random = new Random();
        List<Integer> coinX = randomX(coinNum, false);

        for (int i=0; i<coinNum; i++) {
            int y = blockY.get(random.nextInt(4));
            coin.add(new CoinObject(coinX.get(i), y - 70, 30, 30));
        }
    }

    private void setWall() {
        // 천장
        wall.add(new WallObject(1, 1, width - 15, 10));
        // 왼쪽 벽
        wall.add(new WallObject(1, 11, 10, height - 49));
        // 오른쪽 벽
        wall.add(new WallObject(width - 32, 11, 11, height - 49));
        // 바닥
        wall.add(new WallObject(1, height - 105, width - 15, 50));
    }

    // 전체 블록 디자인 틀
    private void setMapDesign() {
        for (int i=0; i<8; i++) {
            mapDesign.add(new ArrayList<>());
        }

        // x = x, y = width
        mapDesign.get(0).add(new Point(0, 200));
        mapDesign.get(0).add(new Point(300, 200));
        mapDesign.get(0).add(new Point(600, 200));

        mapDesign.get(1).add(new Point(200, 300));
        mapDesign.get(1).add(new Point(700, 200));

        mapDesign.get(2).add(new Point(0, 200));
        mapDesign.get(2).add(new Point(500, 300));

        mapDesign.get(3).add(new Point(0, 300));
        mapDesign.get(3).add(new Point(500, 200));

        mapDesign.get(4).add(new Point(100, 200));
        mapDesign.get(4).add(new Point(400, 300));

        mapDesign.get(5).add(new Point(0, 600));
        mapDesign.get(5).add(new Point(700, 100));

        mapDesign.get(6).add(new Point(0, 100));
        mapDesign.get(6).add(new Point(200, 500));

        blockY.add(130);
        blockY.add(315);
        blockY.add(510);
        blockY.add(695);
    }

    private void setExit() {
        Random random = new Random();
        int exitX = randomX(1, false).get(0);

        int y = blockY.get(random.nextInt(4));
        exit = new ExitObject(exitX, y - 70, 30, 70);
    }

    // mapDesign에서 3개 선택, 7번째는 바닥.
    private void setStageMap() {
        Random random = new Random();

        Set<Integer> stageMapSet = new HashSet<>();
        while (stageMapSet.size() < 3) {
            stageMapSet.add(random.nextInt(7));
        }

        stageMap.addAll(stageMapSet);
        Collections.shuffle(stageMap);

        for (int i=0; i<3; i++) {
            for (Point p : mapDesign.get(stageMap.get(i))) {
                if (blockY.isEmpty()) break;
                block.add(new WallObject(p.x, blockY.get(i), p.y, 50));
            }
        }
    }

    private void resetStage() {
        block.clear();
        coinNum = 0;
        coin.clear();
        npcNum = 0;
        npc.clear();
        exit = null;
    }

    private void gameOver() {
        stage = 0;
        Component targetComponent = KeyboardFocusManager.getCurrentKeyboardFocusManager().getFocusOwner();
        GameFrame gameFrame = (GameFrame) targetComponent;
        gameFrame.display = 2;
    }

    public void setScore() {
        yourScore = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (WallObject b : block) {
            b.draw(g);
        }

        for (CoinObject c : coin) {
            c.draw(g);
        }

        for (NPCObject n : npc) {
            n.draw(g);
        }

        if (pc != null) {
            pc.draw(g);
        }

        if (exit != null) {
            exit.draw(g);
        }

        for (WallObject w : wall) {
            w.draw(g);
        }
    }

    @Override
    public void run() {
        int moveNpc = 0;

        while (true) {
            if (running) {
                // 1. update
                // block은 resetStage에서 clear
                if (block.isEmpty()) {
                    nextStage();
                }

                // pc 움직임
                pc.update(1.5);

                // npc 움직임
                for (NPCObject n : npc) {
                    n.update(1.5);

                    moveNpc++;
                    if (moveNpc > 50) {
                        n.setDirection();
                        moveNpc = 0;
                    }
                }

                // coin을 모두 획득한 상태에서 exit이 없으면 생성
                if (coinNum <= 0 && exit == null) {
                    setExit();
                }

                // 2. resolve
                // pc와 npc 벽(천장, 좌우벽) 충돌 확인
                for (WallObject w : wall) {
                    pc.resolve(w);
                    for (NPCObject n : npc) {
                        n.resolve(w);
                    }
                }

                // pc와 npc 블록 충돌 확인
                for (WallObject b : block) {
                    pc.resolve(b);
                    for (NPCObject n : npc) {
                        n.resolve(b);
                    }
                }

                // pc와 npc 충돌 확인
                List<NPCObject> npcCopy;
                synchronized (npc) {
                    npcCopy = new ArrayList<>(npc);
                }
                for (NPCObject n : npcCopy) {
                    if (pc.resolve(n)) {
                        resetStage();
                        gameOver();
                    }
                }

                // coin 획득 확인, 점수 증가
                Iterator<CoinObject> c = coin.iterator();
                while (c.hasNext()) {
                    if (pc.resolve(c.next())) {
                        c.remove();
                        coinNum--;
                        yourScore += 10;
                    }
                }

                // 스테이지 클리어 조건 확인
                // (coin 모두 획득 후 exit 접촉)
                if (pc.resolve(exit)) {
                    resetStage();
                }

                // 3. render
                repaint();

                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    return;
                }
            }
            else {
                resetStage();
                Thread.yield();
            }
        }
    }
}
