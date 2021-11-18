import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements ActionListener, KeyListener {

    JLabel p1;
    JLabel p2;

    // Game updates 60 times per second, can change if needed
    Timer timer = new Timer(1000 / 60, this);

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    private int p1Score;
    private int p2Score;

    private int yVel;
    private int indicator;

    // Initial setup of graphics and game settings
    public Game() {
        timer.start();

        addKeyListener(this);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        p1Score = 0;
        p2Score = 0;

        this.setLayout(null);
        p1 = new JLabel("" + p1Score);
        p1.setSize(60, 60);
        p1.setLocation(450, 0);
        p1.setFont(new Font(p1.getFont().getName(), Font.PLAIN, 60));
        p1.setForeground(Color.white);
        this.add(p1);
        p2 = new JLabel("" + p2Score);
        p2.setSize(60, 60);
        p2.setLocation(540, 0);
        p2.setFont(new Font(p2.getFont().getName(), Font.PLAIN, 60));
        p2.setForeground(Color.white);
        this.add(p2);

        ball = new Ball();
        leftPaddle = new Paddle(0);
        rightPaddle = new Paddle(Window.WINDOW_WIDTH - Paddle.WIDTH);
    }

    // Paints background and game objects
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws background
        Graphics2D g2d = (Graphics2D)g;
        try {
            Image background = ImageIO.read(new File("images/game_background.png"));
            g2d.drawImage(background, 0, 0, this);
        } catch (IOException e) {
            System.out.println("Could not load background!");
        }

        p1.setText("" + p1Score);
        p2.setText("" + p2Score);

        // Draw game objects
        ball.draw(g2d);
        leftPaddle.draw(g2d);
        rightPaddle.draw(g2d);
    }

    // This method is called every time the game updates
    @Override
    public void actionPerformed(ActionEvent e) {
        if (Menu.indicator == 1) {
            rightPaddle.y += yVel;
        }
        else {
            if (indicator == 1) {
                leftPaddle.y += yVel;
            }
            else {
                rightPaddle.y += yVel;
            }

        }
        repaint(); // Leave me at the bottom!
    }

    public void up() {
        yVel = -6;
    }

    public void down() {
        yVel = 6;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            up();
            indicator = 0;
        }
        else if (key == KeyEvent.VK_DOWN) {
            down();
            indicator = 0;
        }
        else if (key == KeyEvent.VK_W) {
            up();
            indicator = 1;
        }
        else if (key == KeyEvent.VK_S) {
            down();
            indicator = 1;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        yVel = 0;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
