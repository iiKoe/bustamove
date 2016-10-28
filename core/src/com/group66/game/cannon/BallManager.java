package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.BustaMove;
import com.group66.game.helpers.ScoreKeeper;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

public class BallManager {
    
    /**  Dynamic settings. */
    private DynamicSettings dynamicSettings;
    
    private Cannon cannon;
    
    /** The graph where all the connections between balls are stored. */
    private BallGraph ballGraph;
    
    /**  The score keeper. */
    private ScoreKeeper scoreKeeper;

    /**  The ball to be added to static List. */
    private ArrayList<Ball> ballToBeAdded = new ArrayList<Ball>();
    
    /** The colors that exist in the grid. */
    private ArrayList<AtomicInteger> colorList = new ArrayList<AtomicInteger>();
    
    private BallsPop ballsPopManager;
    
    private BallsStatic ballsStaticManager;
    
    private BallsMoving ballsMovingManager;
    
    private BallsCannon ballsCannonManager;

    
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
    
    public void ballCheckDead(Ball ball) {
        if (ball.isDead()) {
            ballsMovingManager.addDeadBall(ball);
        }
    }
    
    public void ballCleanDead() {
        ballsMovingManager.cleanDead();
    }
    
    public void cleanStaticDead() {
        while (ballsStaticManager.deadSize() != 0) {
            Ball kball = ballsStaticManager.getFirstDeadBall();
            ballGraph.removeBall(kball);
            ballsStaticManager.removeStaticBall(kball);
            colorList.get(kball.getColor()).decrementAndGet();
            
            ballsStaticManager.removeFirstDeadBall();
            //ballStaticDeadList.remove(0);
            //System.out.println("number of balls left: " + ballGraph.numberOfBalls());
            if (ballsStaticManager.deadSize() == 0) {
                scoreKeeper.addCurrentScore(0, ballGraph.getFreeBalls().size(), dynamicSettings.getScoreMultiplier());
                
                for (Ball e:ballGraph.getFreeBalls()) {
                    ballsStaticManager.addDeadBall(e);
                    if (!ballsPopManager.contains(e) && e.getType() != BallType.BOMB) {
                        ballsPopManager.startPop(e);
                    }
                    //System.out.println("ball added to deadlist(free)");
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
    
    public void addStaticBalls() {
        while (ballToBeAdded.size() != 0) {
            ballsStaticManager.addStaticBall(ballToBeAdded.get(0).getType(), ballToBeAdded.get(0).getX(), ballToBeAdded.get(0).getY());
            ballToBeAdded.remove(0);
            int count = 0;
            for (AtomicInteger e: colorList) {
                BustaMove.getGameInstance().log(MessageType.Info, "Number of balls with color " 
                        + count + " :" + e.get());
                count++;
            }
            if (ballGraph.numberOfAdjacentColoredBalls(ballsStaticManager.getLastStaticBall()) >= 3) {
                //int score = 0;
                for (Ball e:ballGraph.getAdjacentColoredBalls(ballsStaticManager.getLastStaticBall())) {
                    //System.out.println("ball added to deadlist (adjacent)");
                    //score++;
                    if (!ballsStaticManager.deadContains(e)) {
                        BustaMove.getGameInstance().log(MessageType.Debug, "Ball added to deadlist: " + e.toString() 
                            + " Location: (" + e.getX() + "," + e.getY() + ")");
                        ballsStaticManager.addDeadBall(e);
                        if (e.getType() != BallType.BOMB) {
                            ballsPopManager.startPop(e);
                        } else {
                            scoreKeeper.doubleCurrentScore();
                        }
                        //scoreKeeper.setCurrentScore(1, 0);
                    }
                }
                BustaMove.getGameInstance().log(MessageType.Info, "Started popping " + ballsStaticManager.deadSize()
                    + " balls");
                //scoreKeeper.setCurrentScore(score, 0);
                //TODO 
            } else {
                ballsCannonManager.addRandomBallToCanon();
            }
        }
    }
    
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
    
    public void update(SpriteBatch batch, float delta) {
        /* Draw shot ball */
        ballsMovingManager.draw(batch, delta);

        /* Draw static target balls */
        ballsStaticManager.draw(batch, delta);

        /* Draw popping balls */
        ballsPopManager.draw(batch, delta);

        /* Draw cannon balls */
        ballsCannonManager.draw(batch, delta);
    }
    
    public void moveRowDown() {
        ballsStaticManager.moveRowDown();
    }
    
    public boolean hitsBotom() {
        return ballsStaticManager.hitsBotom();
    }
    
    
    public BallsStatic getBallsStaticManager() {
        return this.ballsStaticManager;
    }
    
    public BallsCannon getBallsCannonManager() {
        return this.ballsCannonManager;
    }
    
    public ArrayList<Ball> getBallList() {
        return ballsMovingManager.getBallList();
    }
    
    public void addBallDeadlist(Ball ball) {
        ballsMovingManager.addDeadBall(ball);
    }
    
    public void addStaticBallToBeAdded(Ball ball) {
        ballToBeAdded.add(ball);
    }
    
    public int getNumberOfShotBalls() {
        return ballsMovingManager.aliveSize();
    }
    
    public int getNumberOfPoppingBalls() {
        return ballsPopManager.size();
    }
    
    public int getNumberOfCannonBalls() {
        return ballsCannonManager.size();
    }
    
    public void increaseColorList() {
        this.colorList.add(new AtomicInteger(0));
    }
    
    public void checkPop() {
        this.ballsPopManager.checkPop();
    }

}
