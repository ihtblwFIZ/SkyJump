import GamePanel.PlayPanel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // JFrame 생성
        JFrame frame = new JFrame("Sky Jump");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);

        // PlayPanel 생성
        PlayPanel playPanel = new PlayPanel(800, 800);

        // PlayPanel을 JFrame에 추가
        frame.add(playPanel);

        // JFrame을 화면에 표시
        frame.setVisible(true);
    }
}