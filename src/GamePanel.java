import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class GamePanel extends JPanel implements ActionListener {
    public boolean endGame = false;
    private boolean rigth = true;
    private boolean left = false;
    private boolean up = false;
    private boolean down = false;
    private int dots;
    private final int dotSize = 16;
    private final int allDots = 900;
    private final int[] x = new int[allDots];
    private final int[] y = new int[allDots];
    private int redAppelX;
    private int redAppelY;
    private int greenAppelX;
    private int greenAppelY;
    private int yellowAppelX;
    private int yellowAppelY;
    private Image dot;
    private Image redAppel;
    private Image greenApple;
    private Image yellowApple;

    GamePanel() {
        int panelSize = 480;
        setSize(panelSize, panelSize);
        setBackground(Color.DARK_GRAY);
        gameInit();
        loadImage();
        addKeyListener(new PanelKeyListener());
        setFocusable(true);
    }

    private void gameInit() {
        dots = 3;
        for (int i = 0; i < dots; i++) {
            x[i] = 48 - i * dotSize;
            y[i] = 48;
        }
        Timer timer = new Timer(500, this);
        timer.start();
        creatRedApple();
        creatGreenApple();
        creatYellowApple();
    }

    private void creatRedApple() {
        redAppelX = new Random().nextInt(30) * dotSize;
        redAppelY = new Random().nextInt(30) * dotSize;
    }

    private void creatGreenApple() {
        greenAppelX = new Random().nextInt(30) * dotSize;
        if (greenAppelX == redAppelX) {
            greenAppelX = new Random().nextInt(30) * dotSize;
        }
        greenAppelY = new Random().nextInt(30) * dotSize;
        if (greenAppelY == redAppelY) {
            greenAppelY = new Random().nextInt(30) * dotSize;
        }
    }

    public void creatYellowApple() {
        yellowAppelX = new Random().nextInt(30) * dotSize;
        if (yellowAppelX == redAppelX || yellowAppelX == greenAppelX) {
            yellowAppelX = new Random().nextInt(30) * dotSize;
        }
        yellowAppelY = new Random().nextInt(30) * dotSize;
        if (yellowAppelY == redAppelY || yellowAppelY == greenAppelY) {
            yellowAppelY = new Random().nextInt(30) * dotSize;
        }
    }

    private void loadImage() {
        ImageIcon iid = new ImageIcon("dot.png");
        dot = iid.getImage();
        ImageIcon iirA = new ImageIcon("rA.png");
        redAppel = iirA.getImage();
        ImageIcon iigA = new ImageIcon("gA.png");
        greenApple = iigA.getImage();
        ImageIcon iiyA = new ImageIcon("yA.png");
        yellowApple =iiyA.getImage();
    }


    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= dotSize;
        }
        if (rigth) {
            x[0] += dotSize;
        }
        if (up) {
            y[0] -= dotSize;
        }
        if (down) {
            y[0] += dotSize;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (!endGame) {
            checkRedApple();
            checkGreenApple();
            checkYellowApple();
            move();
        }
        repaint();
    }




    private void checkRedApple() {
        if (x[0] == redAppelX && y[0] == redAppelY) {
            dots++;
            creatRedApple();

        }
    }
    private void checkGreenApple() {
        if (x[0] == greenAppelX && y[0] == greenAppelY) {
            dots++;
            creatGreenApple();
        }
    }

    public void checkYellowApple(){
       if (x[0]==yellowAppelX&&y[0]==yellowAppelY){
           dots++;
           creatYellowApple();
       }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        //Graphics2D g2d = (Graphics2D) g;
        if (!endGame) {
            g.drawImage(redAppel, redAppelX, redAppelY, this);
            g.drawImage(greenApple, greenAppelX, greenAppelY, this);
            g.drawImage(yellowApple,yellowAppelX,yellowAppelY,this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        }
    }

    class PanelKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int code = e.getKeyCode();
            if (code == KeyEvent.VK_RIGHT && !left) {
                rigth = true;
                up = false;
                down = false;
            }
            if (code == KeyEvent.VK_LEFT && !rigth) {
                left = true;
                up = false;
                down = false;
            }
            if (code == KeyEvent.VK_UP && !down) {
                up = true;
                left = false;
                rigth = false;
            }
            if (code == KeyEvent.VK_DOWN && !up) {
                down = true;
                left = false;
                rigth = false;
            }
        }
    }
}
