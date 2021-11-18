import java.awt.*;

public class Paddle {

    public static final int HEIGHT = 100;
    public static final int WIDTH = 15;

    private int x;
    public int y = (Window.WINDOW_HEIGHT - HEIGHT) / 2;

    Paddle(int x) {
        this.x = x;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillRect(x, y, WIDTH, HEIGHT);
    }

}