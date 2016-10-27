package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
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
    public ScoreKeeper scoreKeeper;

    /**  The ball to be added to static List. */
    private ArrayList<Ball> ballToBeAdded = new ArrayList<Ball>();

    /** The balls the canon will shoot. */
    private ArrayList<Ball> cannonBallList = new ArrayList<Ball>();
    
    /** The colors that exist in the grid. */
    private ArrayList<AtomicInteger> colorList = new ArrayList<AtomicInteger>();
    
    private BallsPop ballsPopManager = new BallsPop();
    
    private BallsStatic ballsStaticManager = new BallsStatic();
    
    private BallsMoving ballsMovingManager = new BallsMoving();

    
    public BallManager(DynamicSettings dynamicSettings) {
        
    }
    
    /**
     * Adds a static ball.
     * 
     * @param type the color
     * @param xpos the x coordinate
     * @param ypos the y coordinate
     */
    public void addStaticBall(BallType type, float xpos, float ypos) {
        Ball ball;
        ball = type.newBall(xpos, ypos, 0, 0.0f);
        ballsStaticManager.add(ball);
        colorList.get(ball.getColor()).incrementAndGet();
        ballGraph.insertBall(ball);
        
        BustaMove.getGameInstance().log(MessageType.Debug, "add ball: color(" + ball.getColor() + "), x(" + ball.getX()
            + "), y(" + ball.getY() + "), pointer(" + ball.toString() + ")");
    }
    
    /**
     * Adds a static ball.
     *
     * @param color the color
     * @param xpos the xpos
     * @param ypos the ypos
     */
    public void addStaticBall(int color, float xpos, float ypos) { 
        color %= BallType.MAX_COLORS.ordinal();
        BallType type = BallType.values()[color];
        addStaticBall(type, xpos, ypos);
    }

    /**
     * Adds a random static ball.
     * 
     * @param xpos the x coordinate
     * @param ypos the y coordinate
     */
    public void addRandomStaticBall(float xpos, float ypos) {
        Random random = new Random();
        int rand = random.nextInt(BallType.MAX_COLORS.ordinal() + 1);
        addStaticBall(rand, xpos, ypos);
    }
    
    /**
     * Shoot random colored ball.
     */
    public void addRandomBallToCanon() {
        if (ballsStaticManager.isEmpty()) {
            return;
        }
        Random random = new Random();
        int rand;
        int maxType = BallType.BOMB.ordinal();

        
        BustaMove.getGameInstance().log(MessageType.Info, "check if bomb balls is equal to total number of balls: " 
                + (colorList.get(BallType.BOMB.ordinal()).get() == ballGraph.numberOfBalls()));
        rand = random.nextInt(100);
        if (rand <= (Config.BOMB_BALL_CHANCE * dynamicSettings.getSpecialBombChanceMultiplier())
                || colorList.get(BallType.BOMB.ordinal()).get() == ballGraph.numberOfBalls()) {
            BallType ballType = BallType.BOMB;
            cannonBallList.add(ballType.newBall(cannon.getX(), cannon.getY(), 0, 0.0f));
            return;
        }
        do {
            rand = random.nextInt(maxType);
        } while (colorList.get(rand).get() <= 0);
        BallType ballType = BallType.values()[rand];
        cannonBallList.add(ballType.newBall(cannon.getX(), cannon.getY(), 0, 0.0f));
    }
    
    public void ballCheckDead(Ball ball) {
        if (ball.isDead()) {
            ballsMovingManager.addDeadBall(ball);
        }
    }
    
    public void ballCleanDead() {
        ballsMovingManager.cleanDead();
    }
    
    public void cleanStaticDead(Cannon cannon) {
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
                if (ballGraph.getFreeBalls().isEmpty() && cannonBallList.isEmpty()) {
                    addRandomBallToCanon();
                }
            }
        } 
    }
    
    public void addStaticBalls(Cannon cannon) {
        while (ballToBeAdded.size() != 0) {
            addStaticBall(ballToBeAdded.get(0).getType(), ballToBeAdded.get(0).getX(), ballToBeAdded.get(0).getY());
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
                addRandomBallToCanon();
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
        ballsMovingManager.add(cannonBallList.get(0));
        Ball lastBall = ballsMovingManager.getLastBall();
        lastBall.setAngle((float) Math.toRadians(cannon.getAngle()));
        lastBall.setSpeed(newSpeed);
        cannonBallList.remove(0);
        
        BustaMove.getGameInstance().log(MessageType.Info, "Shot a " + cannonBallList.get(0).getColor()
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
        for (Ball ball: cannonBallList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }
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
        return cannonBallList.size();
    }
    
    public void increaseColorList() {
        this.colorList.add(new AtomicInteger(0));
    }

}
