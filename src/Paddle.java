import java.awt.*;

public class Paddle {

    public static final int HEIGHT = 100;
    public static final int WIDTH = 15;
    private static final int MAX_SPEED = 10;

    private int x;
    private int y;
    private int speed;

    Paddle(int x) {
        this.x = x;
        y = (Window.WINDOW_HEIGHT - HEIGHT) / 2;
        stop();
    }

    // Draws paddles
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    // Returns Y
    public int getY() {
        return y;
    }

    // Returns the y value of the center of the paddle
    public int getCenteredY() {
        return y + HEIGHT / 2;
    }

    // Moves the paddle up
    public void moveUp() {
        speed = -MAX_SPEED;
    }

    // Moves the paddle down
    public void moveDown() {
        speed = MAX_SPEED;
    }

    // Stops the paddle
    public void stop() {
        speed = 0;
    }

    // This method is called every time the game updates
    public void onUpdate() {
        if(y + speed < 0 || y + speed + HEIGHT > Window.WINDOW_HEIGHT) {
            stop();
        }
        y += speed;
    }

}
