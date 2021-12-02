import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

public class Game extends JPanel implements KeyListener, ActionListener {

    Image background;
    JLabel p1;
    JLabel p2;

    // Game updates 60 times per second, can change if needed
    Timer timer = new Timer(1000 / 60, this);

    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;

    private int p1Score;
    private int p2Score;

    // Initial setup of graphics and game settings
    public Game() {
        timer.start();

        p1Score = 0;
        p2Score = 0;

        this.setFocusable(true);
        this.addKeyListener(this);

        try {
            background = ImageIO.read(getClass().getResource("/resources/game_background.png"));
        } catch (IOException e) {
            System.out.println("Could not load background!");
        }

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

        ball = new Ball(((Window.WINDOW_WIDTH/2) - Ball.RADIUS), ((Window.WINDOW_HEIGHT/2) - Ball.RADIUS));
        leftPaddle = new Paddle(0);
        rightPaddle = new Paddle(Window.WINDOW_WIDTH - Paddle.WIDTH);
    }

    // Paints background and game objects
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draws background
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(background, 0, 0, this);

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
        ball.move(leftPaddle, rightPaddle, p1Score, p2Score);
        if (ball.x > 1024) {
            p1Score += 1;
            ball.x = 512;
            ball.y = 384;
            ball.randDirection();
        }
        else if (ball.x < 0) {
            p2Score += 1;
            ball.x = 512;
            ball.y = 384;
            ball.randDirection();
        }
        leftPaddle.onUpdate();
        rightPaddle.onUpdate();
        repaint(); // Leave me at the bottom!
    }

    // This method is only here because the KeyListener interface requires it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP) {
            rightPaddle.moveUp();
        }
        if (key == KeyEvent.VK_DOWN) {
            rightPaddle.moveDown();
        }
        if (key == KeyEvent.VK_W) {
            leftPaddle.moveUp();
        }
        if (key == KeyEvent.VK_S) {
            leftPaddle.moveDown();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN) {
            rightPaddle.stop();
        }
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            leftPaddle.stop();
        }
    }

}