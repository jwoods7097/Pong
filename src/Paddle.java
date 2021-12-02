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

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

    public int getY() {
        return y;
    }

    public int getCenteredY() {
        return y + HEIGHT / 2;
    }

    public void moveUp() {
        speed = -MAX_SPEED;
    }

    public void moveDown() {
        speed = MAX_SPEED;
    }

    public void stop() {
        speed = 0;
    }

    public void onUpdate() {
        if(y + speed < 0 || y + speed + HEIGHT > Window.WINDOW_HEIGHT) {
            stop();
        }
        y += speed;
    }

}