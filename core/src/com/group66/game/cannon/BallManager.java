package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.cannon.ballgraph.BallGraph;
import com.group66.game.helpers.ScoreKeeper;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * A Class to manage the Balls in the game.
 */
public class BallManager {
    
    /**  Dynamic settings. */
    private DynamicSettings dynamicSettings;
    
    /** The cannon. */
    private Cannon cannon;
    
    /** The graph where all the connections between balls are stored. */
    private BallGraph ballGraph;
    
    /**  The score keeper. */
    private ScoreKeeper scoreKeeper;

    /**  The ball to be added to static List. */
    private ArrayList<Ball> ballToBeAdded = new ArrayList<Ball>();
    
    /** The colors that exist in the grid. */
    private ArrayList<AtomicInteger> colorList = new ArrayList<AtomicInteger>();
    
    /** The balls pop manager. */
    private BallsPop ballsPopManager;
    
    /** The balls static manager. */
    private BallsStatic ballsStaticManager;
    
    /** The balls moving manager. */
    private BallsMoving ballsMovingManager;
    
    /** The balls cannon manager. */
    private BallsCannon ballsCannonManager;

    /**
     * Instantiates a new ball manager.
     *
     * @param dynamicSettings the dynamic settings
     * @param ballGraph the ball graph
     * @param cannon the cannon
     * @param scoreKeeper the score keeper
     */
    public BallManager(DynamicSettings dynamicSettings, BallGraph ballGraph, Cannon cannon, ScoreKeeper scoreKeeper) {
        this.dynamicSettings = dynamicSettings;
        this.cannon = cannon;
        this.ballGraph = ballGraph;
        this.scoreKeeper = scoreKeeper;
        this.ballsPopManager = new BallsPop();
        this.ballsStaticManager = new BallsStatic(dynamicSettings, ballGraph, this.colorList);
        this.ballsMovingManager = new BallsMoving();
        this.ballsCannonManager = new BallsCannon(dynamicSettings, ballGraph, cannon, this.colorList);
    }
    
    /**
     * Check if a Ball is dead
     *
     * @param ball the ball
     */
    public void ballCheckDead(Ball ball) {
        if (ball != null && ball.isDead()) {
            ballsMovingManager.addDeadBall(ball);
        }
    }
    
    /**
     * Clean the dead Balls.
     */
    public void ballCleanDead() {
        ballsMovingManager.cleanDead();
    }
    
    /**
     * Clean static dead balls.
     */
    public void cleanStaticDead() {
        while (ballsStaticManager.deadSize() != 0) {
            Ball kball = ballsStaticManager.getFirstDeadBall();
            ballGraph.removeBall(kball);
            ballsStaticManager.removeStaticBall(kball);
            colorList.get(kball.getColor()).decrementAndGet();
            
            ballsStaticManager.removeFirstDeadBall();
            if (ballsStaticManager.deadSize() == 0) {
                scoreKeeper.addCurrentScore(0, ballGraph.getFreeBalls().size(), dynamicSettings.getScoreMultiplier());
                
                for (Ball e:ballGraph.getFreeBalls()) {
                    ballsStaticManager.addDeadBall(e);
                    if (!ballsPopManager.contains(e) && e.getType() != BallType.BOMB) {
                        ballsPopManager.startPop(e);
                    }
                }
                BustaMove.getGameInstance().log(MessageType.Info, "Number of balls in grid: " 
                        + ballGraph.numberOfBalls());
                int count = 0;
                for (AtomicInteger e: colorList) {
                    BustaMove.getGameInstance().log(MessageType.Info, "Number of balls with color " 
                            + count + " :" + e.get());
                    count++;
                }
                if (ballGraph.getFreeBalls().isEmpty() && ballsCannonManager.isEmpty()) {
                    ballsCannonManager.addRandomBallToCanon();
                }
            }
        } 
    }
    
    /**
     * Adds the static balls.
     */
    public void addStaticBalls() {
        while (ballToBeAdded.size() != 0) {
            ballsStaticManager.addStaticBall(ballToBeAdded.get(0).getType(), 
                    ballToBeAdded.get(0).getX(), ballToBeAdded.get(0).getY());
            ballToBeAdded.remove(0);
            int count = 0;
            for (AtomicInteger e: colorList) {
                BustaMove.getGameInstance().log(MessageType.Info, "Number of balls with color " 
                        + count + " :" + e.get());
                count++;
            }
            if (ballGraph.numberOfAdjacentColoredBalls(ballsStaticManager.getLastStaticBall()) >= 3) {
                for (Ball e:ballGraph.getAdjacentColoredBalls(ballsStaticManager.getLastStaticBall())) {
                    if (!ballsStaticManager.deadContains(e)) {
                        BustaMove.getGameInstance().log(MessageType.Debug, "Ball added to deadlist: " + e.toString() 
                            + " Location: (" + e.getX() + "," + e.getY() + ")");
                        ballsStaticManager.addDeadBall(e);
                        if (e.getType() != BallType.BOMB) {
                            ballsPopManager.startPop(e);
                        } else {
                            scoreKeeper.doubleCurrentScore();
                        }
                    }
                }
                BustaMove.getGameInstance().log(MessageType.Info, "Started popping " + ballsStaticManager.deadSize()
                    + " balls");
            } else {
                ballsCannonManager.addRandomBallToCanon();
            }
        }
    }
    
    /**
     * Ball hit ball.
     *
     * @param ball the ball
     * @param isSplit the is split
     * @param segmentOffset the segment offset
     */
    public void ballHitBall(Ball ball, boolean isSplit, int segmentOffset) {
        Ball hitBall = ballsStaticManager.hitsStaticBall(ball);
        if (hitBall != null) {
            ball.setSpeed(0);
            BallSnap.snapBallToGrid(ball, hitBall, isSplit, segmentOffset);
            ballsMovingManager.addDeadBall(ball);
            ballToBeAdded.add(ball);
            BustaMove.getGameInstance().log(MessageType.Info, "Ball hit");
        }
    }
    
    /**
     * Shoot ball.
     */
    public void shootBall() {
        int newSpeed = (int) (Config.BALL_SPEED * dynamicSettings.getBallSpeedMultiplier());
        Ball ball = ballsCannonManager.getFirst();
        ballsMovingManager.add(ball);
        Ball lastBall = ballsMovingManager.getLastBall();
        lastBall.setAngle((float) Math.toRadians(cannon.getAngle()));
        lastBall.setSpeed(newSpeed);
        ballsCannonManager.removeFirst();
        
        BustaMove.getGameInstance().log(MessageType.Info, "Shot a " + ball.getColor()
                + " ball at angle " + cannon.getAngle() + " with speed " + newSpeed);
    }
    
    /**
     * Update.
     *
     * @param batch the batch
     * @param delta the delta
     */
    public void update(SpriteBatch batch, float delta) {
        if (batch != null) {
            /* Draw shot ball */
            ballsMovingManager.draw(batch, delta);
    
            /* Draw static target balls */
            ballsStaticManager.draw(batch, delta);
    
            /* Draw popping balls */
            ballsPopManager.draw(batch, delta);
    
            /* Draw cannon balls */
            ballsCannonManager.draw(batch, delta);
        }
    }
    
    /**
     * Move row down.
     */
    public void moveRowDown() {
        ballsStaticManager.moveRowDown();
    }
    
    /**
     * Hits bottom (for game-over).
     *
     * @return true, if successful
     */
    public boolean hitsBottom() {
        return ballsStaticManager.hitsBottom();
    }
    
    /**
     * Gets the balls static manager.
     *
     * @return the balls static manager
     */
    public BallsStatic getBallsStaticManager() {
        return this.ballsStaticManager;
    }
    
    /**
     * Gets the balls cannon manager.
     *
     * @return the balls cannon manager
     */
    public BallsCannon getBallsCannonManager() {
        return this.ballsCannonManager;
    }
    
    /**
     * Gets the ball list.
     *
     * @return the ball list
     */
    public ArrayList<Ball> getBallList() {
        return ballsMovingManager.getBallList();
    }
    
    /**
     * Adds the ball to the dead list.
     *
     * @param ball the ball
     */
    public void addBallDeadlist(Ball ball) {
        if (ball != null) {
            ballsMovingManager.addDeadBall(ball);
        }
    }
    
    /**
     * Adds the static ball to be added.
     *
     * @param ball the ball
     */
    public void addStaticBallToBeAdded(Ball ball) {
        if (ball != null) {
            ballToBeAdded.add(ball);
        }
    }
    
    /**
     * Gets the number of shot balls.
     *
     * @return the number of shot balls
     */
    public int getNumberOfShotBalls() {
        return ballsMovingManager.aliveSize();
    }
    
    /**
     * Gets the number of popping balls.
     *
     * @return the number of popping balls
     */
    public int getNumberOfPoppingBalls() {
        return ballsPopManager.size();
    }
    
    /**
     * Gets the number of cannon balls.
     *
     * @return the number of cannon balls
     */
    public int getNumberOfCannonBalls() {
        return ballsCannonManager.size();
    }
    
    /**
     * Increase color list.
     */
    public void increaseColorList() {
        this.colorList.add(new AtomicInteger(0));
    }
    
    /**
     * Check popping balls.
     */
    public void checkPop() {
        this.ballsPopManager.checkPop();
    }

}
