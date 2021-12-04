import java.util.Random;

public class AI {

    private Paddle paddle;
    private Ball ball;
    private int scoreDifference;

    // Amount of frames AI waits before making a move to mimic reaction time
    private static int delay = 10;
    private int delayCount = 0;
    // Chance the AI will not move to mimic human error
    private double error;

    public AI(Paddle paddle, Ball ball) {
        this.paddle = paddle;
        this.ball = ball;
        scoreDifference = 0;
    }

    public void onUpdate(int playerScore, int aiScore) {
        Random random = new Random();
        scoreDifference = aiScore - playerScore;

        // The likelihood of making an error decreases if the AI is losing
        // Minimum error is 2%, error when tied is 10%, maximum error is 50%
        error = 0.1 * Math.pow(5, scoreDifference / (Game.MAX_SCORE - 1));

        // The delay is dependent on the score difference, e.g. the AI will have better reaction times if it's losing
        if(delayCount < delay + scoreDifference) {
            delayCount++;
        } else if(random.nextDouble() > error) {
            // Stop moving the paddle if it's the same height as the ball, otherwise move it towards the ball
            if(Math.abs(paddle.getCenteredY() - ball.getCenteredY()) < Paddle.HEIGHT / 2) {
                paddle.stop();
            } else if (paddle.getCenteredY() > ball.getCenteredY()) {
                paddle.moveUp();
            } else {
                paddle.moveDown();
            }
            delayCount = 0;
        }
    }

}
