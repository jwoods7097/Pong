import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.util.Random;
/*
This class is in charge of the ball object. The multiple methods in this class handle collision with the upper and lower
window bounds, as well as collision with the paddles. Additionally, there is code that enhances gameplay by making the
ball move faster as the game goes on and sends the ball in a random direction at each "kick-off".

Note: The following code was consulted in the making of this class for some format and conceptual reference.
https://gist.github.com/hayate891/b09670e980ad02737ad6892c98e7e770
 */

public class Ball {
// These variables control how big the ball is and how fast it travels. Modify these values
// for a different experience.
    public static final int RADIUS = 10;
    private static final int BASE_SPEED = 4;
// x and y are (x,y) of the ball on the screen. Direction variables tell the code which way
// the ball is traveling (positive directionX means the ball is moving left to right).
// The multiplier variable increases each time directionX changes, which gradually increases ball speed until
// someone scores, making it more difficult as time progresses.
    private int x;
    private int y;
    private double directionX = 0;
    private double directionY = 0;
    private double multiplier = 1;
// Constructor calls the respawn method
    public Ball() {
        respawn();
    }
// Returns the current x value
    public int getX() {
        return x;
    }
// Returns the current y value
    public int getY() {
        return y;
    }
// Creates a random direction for "kick-off" of the ball at the beginning of every point
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
// Draws the ball using x and y variables
    public void draw(Graphics2D g) {
        g.setColor(Color.white);
        g.fillOval(this.x, this.y, RADIUS * 2, RADIUS * 2);
    }
// In charge of the movement of the ball. "If" statements manage collision with the paddles and the top and bottom bounds of the window.
// The speed of the ball slightly increases each time it collides with either paddle.
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
// Resets the ball to beginning position and multiplier to 1.
    public void respawn() {
        x = Window.WINDOW_WIDTH / 2 - RADIUS;
        y = Window.WINDOW_HEIGHT / 2 - RADIUS;
        randDirection();
        multiplier = 1;
    }
/*
    public void makeSound(){
        File lol = (this.getClass().getResource("/leftHit.wav"));


        try{
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(lol));
            clip.start();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
*/
}