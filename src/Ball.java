// format and concept heavily influenced by java pong project at
// https://gist.github.com/hayate891/b09670e980ad02737ad6892c98e7e770

import java.awt.*;
import java.util.Random;


public class Ball implements Runnable {

    Paddle leftPaddle = new Paddle(0);
    Paddle rightPaddle = new Paddle(Window.WINDOW_WIDTH - Paddle.WIDTH);

    public static final int RADIUS = 10;

    int x = 0;
    int y = 0;
    int directionX = 0;
    int directionY = 0;

    public Ball(int x, int y) {
        Random r = new Random();
        this.x = x;
        this.y = y;

        int randX = r.nextInt(1);
        if (randX == 0) {
            randX--;
        }
        int randY = r.nextInt(1);
        if (randY == 0) {
            randY--;
        }

        setXDir(randX);
        setYDir(randY);
    }

    public void setXDir(int i) {
        directionX = i;
    }

    public void setYDir(int i) {
        directionY = i;
    }


    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(this.x, this.y, RADIUS * 2, RADIUS * 2);
    }
    /*
    public void contact() {
        if (this.intersects(leftPaddle)) {
            setXDir(1);
        }
        if (this.intersects(rightPaddle)) {
            setXDir(-1);
        }
    }
     */

    public void move() {
        this.x += directionX;
        this.y = directionY;

        if (this.x <= 0) {
            setXDir(1);
        }

        if (this.x >= Window.WINDOW_WIDTH) {
            setXDir(-1);
        }

        if (this.y <= RADIUS) {
            setYDir(1);
        }

        if (this.y >= Window.WINDOW_HEIGHT) {
            setYDir(-1);
        }
    }

    public void run() {
        try {
            while(true) {
                move();
                Thread.sleep(8);
            }
        }catch(Exception e) { System.err.println(e.getMessage()); }

    }

}



