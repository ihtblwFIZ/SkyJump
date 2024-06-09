package GamePanel;

import javax.swing.*;
import java.awt.*;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class IntroPanel extends BackgroudPanel {
    int width = 0, height = 0;
    JLabel gameName = new JLabel("Sky Jump", SwingConstants.CENTER); // 게임 이름 : Sky Jump, 중앙으로 정렬
    ImageIcon introTitleIcon = new ImageIcon("images/intro_title_cloud.png"); // title쪽 이미지 아이콘 생성
    ImageIcon introImageIcon = new ImageIcon("images/intro_centerImg_coin.png"); // 본문 이미지 아이콘 생성
    JLabel introImageLabel; //대표 이미지
    JLabel startInfo = new JLabel("PRESS SPACEBAR TO PLAY!", SwingConstants.CENTER);


    public IntroPanel(int w, int h) {
        super(w, h);
        setLayout(new GridLayout(3, 1, 0, 50)); // 3행 1열 그리드 레이아웃 적용, 행 간격 50픽셀

        /* ------------ 게임이름 ------------ */

        // 게임 이름 패널
        JPanel gameNamePanel = new JPanel(new BorderLayout());
        gameNamePanel.setOpaque(false); // 배경 투명하게 설정

        // game Name쪽 이미지
        Image gameNameImage = introTitleIcon.getImage();
        Image gameNameScaledImage = gameNameImage.getScaledInstance(78, 47, Image.SCALE_SMOOTH); // 원하는 크기로 조정 (예: 100x100)
        ImageIcon gameNameScaledIcon = new ImageIcon(gameNameScaledImage);

        //game Name 왼쪽
        JLabel leftImageLabel = new JLabel(gameNameScaledIcon);
        leftImageLabel.setBorder(new EmptyBorder(0, 50, 0, 0)); // 좌측 이미지에 50픽셀 왼쪽 여백 추가

        //game Name 오른쪽
        JLabel rightImageLabel = new JLabel(gameNameScaledIcon);
        rightImageLabel.setBorder(new EmptyBorder(0, 0, 0, 50)); // 우측 이미지에 50픽셀 오른쪽 여백 추가

        // 게임 이름
        gameName.setFont(new Font("Goudy Stout", Font.BOLD, 45)); // 폰트 이름, 스타일, 크기 설정
        gameName.setForeground(new Color(0, 0, 0)); // 글자 색상 설정

        // 중앙에 게임 이름 추가
        gameNamePanel.add(leftImageLabel, BorderLayout.WEST);
        gameNamePanel.add(gameName, BorderLayout.CENTER);
        gameNamePanel.add(rightImageLabel, BorderLayout.EAST);

        //게임이름 Panel 띄우기
        add(gameNamePanel);


        /* ------------ 대표 이미지(로고..?) ------------ */
        //대표 이미지(코인) 크기 조정
        Image img = introImageIcon.getImage();
        Image scaledImg = img.getScaledInstance(150, 150, Image.SCALE_SMOOTH); // 원하는 크기로 조정(단위는 픽셀)
        ImageIcon scaledIcon = new ImageIcon(scaledImg);
        introImageLabel = new JLabel(scaledIcon); // 크기 조정된 이미지 label 생성
        introImageLabel.setHorizontalAlignment(SwingConstants.CENTER); // 이미지 label 중앙 정렬

        //대표 이미지 띄우기
        add(introImageLabel);


        /* ------------ 시작 정보 - 스페이스바 누르면 시작! ------------ */

        // 시작 정보 설정 (폰트, 색상)
        startInfo.setFont(new Font("Forte", Font.PLAIN, 40)); // 폰트 이름, 스타일, 크기 설정
        startInfo.setForeground(new Color(0, 0, 0)); // 글자 색상 설정

        //시작 정보 띄우기
        add(startInfo);
    }
}
