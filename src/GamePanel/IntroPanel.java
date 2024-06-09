package GamePanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.SwingConstants;

public class IntroPanel extends BackgroudPanel {
    int width = 0, height = 0;
    JLabel gameName = new JLabel("Sky Jump", SwingConstants.CENTER); // 게임 이름 : Sky Jump, 중앙으로 정렬
    ImageIcon introImageIcon = new ImageIcon("image/intro_coin.jpg"); // 이미지 아이콘 생성
    JLabel introImageLabel = new JLabel(introImageIcon); // 이미지 라벨 생성
    JLabel startInfo = new JLabel("PRESS SPACEBAR TO PLAY!",SwingConstants.CENTER);

    public IntroPanel(int w, int h) {
        super(w, h);

        setLayout(new GridLayout(3, 1, 0, 50)); // 3행 1열 그리드 레이아웃 적용, 행 간격 50픽셀
        // 게임 이름
        gameName.setFont(new Font("Arial", Font.BOLD, 48)); // 폰트 이름, 스타일, 크기 설정
        gameName.setForeground(Color.BLUE); // 글자 색상 설정
        add(gameName);
        // 이미지
        introImageLabel.setHorizontalAlignment(SwingConstants.CENTER); // 이미지 라벨 중앙 정렬
        add(introImageLabel);
        // 시작 정보
        startInfo.setFont(new Font("Arial", Font.PLAIN, 24)); // 폰트 이름, 스타일, 크기 설정
        startInfo.setForeground(Color.BLACK); // 글자 색상 설정
        add(startInfo);
    }
}
