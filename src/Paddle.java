import java.awt.*;

public class Paddle {

    public static final int HEIGHT = 100;
    public static final int WIDTH = 15;

    int x;
    int y;

    Paddle(int x) {
        this.x = x;
        y = (Window.WINDOW_HEIGHT - HEIGHT) / 2;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

}