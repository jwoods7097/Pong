import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Game extends JPanel implements KeyListener, ActionListener {

    public enum Mode {
        SINGLEPLAYER,
        MULTIPLAYER,
    }

    Image background;
    JLabel p1;
    JLabel p2;

    // Game updates 60 times per second, can change if needed
    Timer timer = new Timer(1000 / 60, this);

    private Window window;
    private Mode gamemode;
    private Ball ball;
    private Paddle leftPaddle;
    private Paddle rightPaddle;
    private AI ai;

    private int p1Score;
    private int p2Score;

    // Initial setup of graphics and game settings
    public Game(Mode mode, Window currentWindow) {
        timer.start();

        window = currentWindow;
        gamemode = mode;
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

        ball = new Ball();
        leftPaddle = new Paddle(0);
        rightPaddle = new Paddle(Window.WINDOW_WIDTH - Paddle.WIDTH);
        if(gamemode == Mode.SINGLEPLAYER) {
            ai = new AI(rightPaddle, ball);
        }
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
        ball.move(leftPaddle, rightPaddle);

        // Updates the score if the ball crosses the window border
        if (ball.getX() > Window.WINDOW_WIDTH) {
            p1Score += 1;
            ball.respawn();
        }
        else if (ball.getX() < 0) {
            p2Score += 1;
            ball.respawn();
        }

        // Switch to Winner panel when a player reaches 10 points
        if(p1Score >= 10) {
            timer.stop();
            window.setPanel(new Winner(1, window));
            this.setVisible(false);
        } else if(p2Score >= 10) {
            timer.stop();
            window.setPanel(new Winner(2, window));
            this.setVisible(false);
        }

        // Run update methods of game objects
        if(gamemode == Mode.SINGLEPLAYER) {
            ai.onUpdate(p1Score, p2Score);
        }
        leftPaddle.onUpdate();
        rightPaddle.onUpdate();

        repaint(); // Leave me at the bottom!
    }

    // This method is only here because the KeyListener interface requires it
    @Override
    public void keyTyped(KeyEvent e) {

    }

    // This method is called everytime a key is pressed
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(gamemode == Mode.MULTIPLAYER) {
            if (key == KeyEvent.VK_UP) {
                rightPaddle.moveUp();
            }
            if (key == KeyEvent.VK_DOWN) {
                rightPaddle.moveDown();
            }
        }
        if (key == KeyEvent.VK_W) {
            leftPaddle.moveUp();
        }
        if (key == KeyEvent.VK_S) {
            leftPaddle.moveDown();
        }
    }

    // This method is called everytime a key is released after being pressed
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if(gamemode == Mode.MULTIPLAYER && (key == KeyEvent.VK_UP || key == KeyEvent.VK_DOWN)) {
            rightPaddle.stop();
        }
        if (key == KeyEvent.VK_W || key == KeyEvent.VK_S) {
            leftPaddle.stop();
        }
    }

}