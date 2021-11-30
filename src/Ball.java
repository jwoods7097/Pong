import java.awt.*;

public class Ball {

    public static final int RADIUS = 10;
    int y;
    int x;

    Ball() {
        y = (Window.WINDOW_HEIGHT / 2) - RADIUS;
        x = (Window.WINDOW_WIDTH / 2) - RADIUS;
    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(x, y, RADIUS * 2, RADIUS * 2);
    }

}