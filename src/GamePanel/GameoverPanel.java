package GamePanel;

import javax.swing.*;
import java.awt.*;

public class GameoverPanel extends BackgroudPanel {
    int width = 0, height = 0;
    ImageIcon gameOverIcon = new ImageIcon("C:\\Users\\user\\OneDrive\\바탕 화면\\GAMEOVER.jpg");
    Image img = gameOverIcon.getImage();
    boolean newRecord = false;

    public GameoverPanel(int w, int h) {
        super(w, h);
    }

    public void setScore() {    // 점수 갱신 메서드
        if (highScore < yourScore) {
            highScore = yourScore;
            newRecord = true;
        } else if (highScore > yourScore)
            newRecord = false;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(img, 0,0,getWidth(), getHeight(), this);

        g.setColor(Color.white);    // 최고기록
        g.setFont(new Font("Consolas", Font.BOLD, 30));
        g.drawString(String.valueOf(highScore), 370, 190);  // 위치 추후 조정 고려

        g.setColor(Color.CYAN);     // 현재본인기록
        g.setFont(new Font("Consolas", Font.BOLD, 50));
        g.drawString(String.valueOf(yourScore), 660, 200);
    }
}
