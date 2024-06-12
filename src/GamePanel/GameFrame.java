package GamePanel;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GameFrame extends JFrame implements Runnable, KeyListener {

    int display = 0;
    int size = 800;
    IntroPanel introPanel = new IntroPanel(size, size);
    PlayPanel playPanel = new PlayPanel(size, size);
    GameoverPanel gameoverPanel = new GameoverPanel(size, size);

    public GameFrame() {
        setTitle("SkyJump");
        setSize(size, size);
        setResizable(false);
        Thread thread = new Thread(this);
        thread.start();

        add(introPanel);
        addKeyListener(this);

        setFocusable(true);
        requestFocusInWindow();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void runnigPanel() {
        //어떤 패널이 돌아가는지
        playPanel.run();
    }

    @Override
    public void run() {
        while (true) {
            getContentPane().removeAll();
            if (display == 0) {
                playPanel.setScore();
                getContentPane().add(introPanel);
                playPanel.threadState(false);
            } else if (display == 1) {
                getContentPane().add(playPanel);
                playPanel.threadState(true);
            } else if (display == 2) {
                getContentPane().add(gameoverPanel);
                playPanel.threadState(false);
            }
            revalidate();
            repaint();

            try {
                Thread.sleep(16);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            display++;
            if (display > 2) {
                display = 0;
            }
        } else if (display == 1 && e.getKeyCode() == KeyEvent.VK_LEFT) {
            playPanel.keyPressed(e);
        } else if (display == 1 && e.getKeyCode() == KeyEvent.VK_RIGHT) {
            playPanel.keyPressed(e);
        } else if (display == 1 && e.getKeyCode() == KeyEvent.VK_UP) {
            playPanel.keyPressed(e);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (display == 1 && e.getKeyCode() == KeyEvent.VK_LEFT) {
            playPanel.keyPressed(e);
        } else if (display == 1 && e.getKeyCode() == KeyEvent.VK_RIGHT) {
            playPanel.keyPressed(e);
        } else if (display == 1 && e.getKeyCode() == KeyEvent.VK_UP) {
            playPanel.keyPressed(e);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {}
}
