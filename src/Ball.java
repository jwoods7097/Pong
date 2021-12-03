import java.awt.*;
import java.util.Random;


public class Ball {

    public static final int RADIUS = 10;
    private static final int BASE_SPEED = 4;

    private int x;
    private int y;
    private double directionX = 0;
    private double directionY = 0;
    private double multiplier = 1;

    public Ball() {
        respawn();
    }

    // Returns x
    public int getX() {
        return x;
    }

    // Returns Y
    public int getY() {
        return y;
    }

    // Gives the ball a random direction to go
    public void randDirection() {
        Random r = new Random();
        int randX = r.nextInt(2);
        if (randX == 0) {
            randX--;
        }
        int randY = r.nextInt(2);
        if (randY == 0) {
            randY--;
        }

        directionX = randX;
        directionY = randY;
    }

    // Draws the ball
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(this.x, this.y, RADIUS * 2, RADIUS * 2);
    }

    // Moves the ball
    public void move(Paddle left, Paddle right) {

        x += directionX * BASE_SPEED * multiplier;
        y += directionY * BASE_SPEED;
        if (x <= Paddle.WIDTH && x > 0 && (y > left.getY() && y < left.getY() + Paddle.HEIGHT)) {
            multiplier += .05;
            directionX = 1;
        }
        if (x >= Window.WINDOW_WIDTH - RADIUS*2 - Paddle.WIDTH && x < Window.WINDOW_WIDTH && (y > right.getY() && y < right.getY() + Paddle.HEIGHT)) {
            multiplier += .05;
            directionX = -1;
        }
        if (y >= Window.WINDOW_HEIGHT - RADIUS*2) {
            directionY = -1;
        }
        if (y <= 0) {
            directionY = 1;
        }
    }

    // Places the ball in the middle after a point is scored
    public void respawn() {
        x = Window.WINDOW_WIDTH / 2 - RADIUS;
        y = Window.WINDOW_HEIGHT / 2 - RADIUS;
        randDirection();
    }

}