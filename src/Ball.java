import java.awt.*;
import java.util.Random;


public class Ball {

    public static final int RADIUS = 10;

    int x = 0;
    int y = 0;
    double directionX = 0;
    double directionY = 0;
    double multiplier = 1;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        this.randDirection();
    }

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

        //int randX = r.nextInt(2);
        //int randY = r.nextInt(2);

        setXDir(randX);
        setYDir(randY);
    }

    public void setXDir(double i){
        directionX = i;
    }

    public void setYDir(double i) {
        directionY = i;
    }


    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(this.x, this.y, RADIUS * 2, RADIUS * 2);
    }

    public void move(Paddle left, Paddle right, int p1, int p2) {

        this.x += directionX*4*this.multiplier;
        this.y += directionY*4;
        if (this.x <= left.WIDTH && this.x > 0 && ((this.y > left.y) && (this.y < (left.y + left.HEIGHT)))) {
            this.multiplier += .05;
            setXDir(1);
        }
        else if (this.x > left.WIDTH)
            p1 += 1;
        if (this.x >= (Window.WINDOW_WIDTH - RADIUS*2 - right.WIDTH) && this.x < Window.WINDOW_WIDTH && ((this.y > right.y) && (this.y < (right.y + right.HEIGHT)))) {
            this.multiplier += .05;
            setXDir(-1);
        }
        else if (this.x < 0)
            p2 += 1;
        if (this.y >= Window.WINDOW_HEIGHT - RADIUS*2) {
            setYDir(-1);
        }
        if (this.y <= 0) {
            setYDir(1);
        }
    }

}