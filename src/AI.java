import java.util.Random;

public class AI {

    private Paddle paddle;
    private Ball ball;
    private int scoreDifference;

    // Amount of frames AI waits before making a move
    private static int delay = 10;
    private int delayCount = 0;
    // Chance the AI will make an error and not move
    private double error;

    public AI(Paddle paddle, Ball ball) {
        this.paddle = paddle;
        this.ball = ball;
        scoreDifference = 0;
    }

    public void onUpdate(int playerScore, int aiScore) {
        Random random = new Random();
        scoreDifference = aiScore - playerScore;
        error = 0.1 * Math.pow(5, scoreDifference / 9);

        // The delay is dependent on the score difference, e.g. the AI will have better reaction times if it's losing
        if(delayCount < delay + scoreDifference) {
            delayCount++;
        } else if(random.nextDouble() > error) {
            // Stop moving the paddle if it's the same height as the ball, otherwise move it accordingly
            if(Math.abs(paddle.getCenteredY() - ball.getY()) < Paddle.HEIGHT / 2) {
                paddle.stop();
            } else if (paddle.getCenteredY() > ball.getY()) {
                paddle.moveUp();
            } else {
                paddle.moveDown();
            }
            delayCount = 0;
        }
    }

}
