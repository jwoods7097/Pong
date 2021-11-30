import java.awt.*;

public class Ball {

    public static final int RADIUS = 10;

    Ball() {

    }

    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval((Window.WINDOW_WIDTH / 2) - RADIUS, (Window.WINDOW_HEIGHT / 2) - RADIUS, RADIUS * 2, RADIUS * 2);
    }

}