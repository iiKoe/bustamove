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
    
    /** The ball list. */
    private ArrayList<Ball> ballList = new ArrayList<Ball>();

    /** The ball dead list. */
    private ArrayList<Ball> ballDeadList = new ArrayList<Ball>();

    /** The static ball list. */
    private ArrayList<Ball> ballStaticList = new ArrayList<Ball>();

    /** The static ball dead list. */
    private ArrayList<Ball> ballStaticDeadList = new ArrayList<Ball>();

    /** The ball pop animation list. */
    private ArrayList<Ball> ballPopList = new ArrayList<Ball>();

    /**  The ball to be added to static List. */
    private ArrayList<Ball> ballToBeAdded = new ArrayList<Ball>();

    /** The balls the canon will shoot. */
    private ArrayList<Ball> cannonBallList = new ArrayList<Ball>();
    
    /** The colors that exist in the grid. */
    private ArrayList<AtomicInteger> colorList = new ArrayList<AtomicInteger>();

    
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
        ballStaticList.add(ball);
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
     * Start popping animation.
     *
     * @param ball the ball
     */
    public void startPop(Ball ball) {
        ball.popStart(ball.getType().getPopAnimation());
        ballPopList.add(ball);
    }
    
    public void checkPop() {
        /* Check if the popping balls are done */
        for (Iterator<Ball> it = ballPopList.iterator(); it.hasNext();) {
            Ball ball = it.next();
            if (ball.popDone() == true) {
                it.remove();
                //System.out.println("Pop list size: " + ballPopList.size());
            }
        }
    }
    
    /**
     * Shoot random colored ball.
     */
    public void addRandomBallToCanon() {
        if (ballStaticList.isEmpty()) {
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
            ballDeadList.add(ball);
        }
    }
    
    public void ballCleanDead() {
        while (ballDeadList.size() != 0) {
            ballList.remove(ballDeadList.get(0));
            ballDeadList.remove(0);
        }
    }
    
    public void cleanStaticDead(Cannon cannon) {
        while (ballStaticDeadList.size() != 0) {
            ballGraph.removeBall(ballStaticDeadList.get(0));
            ballStaticList.remove(ballStaticDeadList.get(0));
            colorList.get(ballStaticDeadList.get(0).getColor()).decrementAndGet();
            
            ballStaticDeadList.remove(0);
            //System.out.println("number of balls left: " + ballGraph.numberOfBalls());
            if (ballStaticDeadList.size() == 0) {
                scoreKeeper.addCurrentScore(0, ballGraph.getFreeBalls().size(), dynamicSettings.getScoreMultiplier());
                
                for (Ball e:ballGraph.getFreeBalls()) {
                    ballStaticDeadList.add(e);
                    if (!ballPopList.contains(e) && e.getType() != BallType.BOMB) {
                        startPop(e);
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
            if (ballGraph.numberOfAdjacentColoredBalls(ballStaticList.get(ballStaticList.size() - 1)) >= 3) {
                //int score = 0;
                for (Ball e:ballGraph.getAdjacentColoredBalls(ballStaticList.get(ballStaticList.size() - 1))) {
                    //System.out.println("ball added to deadlist (adjacent)");
                    //score++;
                    if (!ballStaticDeadList.contains(e)) {
                        BustaMove.getGameInstance().log(MessageType.Debug, "Ball added to deadlist: " + e.toString() 
                            + " Location: (" + e.getX() + "," + e.getY() + ")");
                        ballStaticDeadList.add(e);
                        if (e.getType() != BallType.BOMB) {
                            startPop(e);
                        } else {
                            scoreKeeper.doubleCurrentScore();
                        }
                        //scoreKeeper.setCurrentScore(1, 0);
                    }
                }
                BustaMove.getGameInstance().log(MessageType.Info, "Started popping " + ballStaticDeadList.size() 
                    + " balls");
                //scoreKeeper.setCurrentScore(score, 0);
                //TODO 
            } else {
                addRandomBallToCanon();
            }
        }
    }
    
    public void ballHitBall(Ball ball, boolean isSplit, int segmentOffset) {
        for (Ball t : ballStaticList) {
            // Does the ball hit a target ball?
            if (t.doesHit(ball.getHitbox()) && !ballToBeAdded.contains(ball)) {
                ball.setSpeed(0);
                // A hack for now
                BallSnap.snapBallToGrid(ball, t, isSplit, segmentOffset);
                ballDeadList.add(ball);
                ballToBeAdded.add(ball);
                BustaMove.getGameInstance().log(MessageType.Info, "Ball hit");
            }
        }
    }
    
    public void shootBall() {
        int newSpeed = (int) (Config.BALL_SPEED * dynamicSettings.getBallSpeedMultiplier());
        ballList.add(cannonBallList.get(0));
        ballList.get(ballList.size() - 1).setAngle((float) Math.toRadians(cannon.getAngle()));
        ballList.get(ballList.size() - 1).setSpeed(newSpeed);
        cannonBallList.remove(0);
        
        BustaMove.getGameInstance().log(MessageType.Info, "Shot a " + cannonBallList.get(0).getColor()
                + " ball at angle " + cannon.getAngle() + " with speed " + newSpeed);
    }
    
    public void update(SpriteBatch batch, float delta) {
        /* Draw shot ball */
        for (Ball ball : ballList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }

        /* Draw static target balls */
        for (Ball ball : ballStaticList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }

        /* Draw popping balls */
        for (Ball ball : ballPopList) {
            ball.draw(batch, null, delta);
        }

        /* Draw cannon balls */
        for (Ball ball: cannonBallList) {
            ball.draw(batch, ball.getType().getAnimation(), delta);
        }
    }
    
    public void addBallDeadlist(Ball ball) {
        ballDeadList.add(ball);
    }
    
    public void addStaticBallToBeAdded(Ball ball) {
        ballToBeAdded.add(ball);
    }
    
    public int getNumberOfPendingBalls() {
        return ballList.size();
    }
    
    public int getNumberOfPoppingBalls() {
        return ballPopList.size();
    }
    
    public int getNumberOfCannonBalls() {
        return cannonBallList.size();
    }
    
    public ArrayList<Ball> getBallList() {
        return ballList;
    }
    
    public ArrayList<Ball> getStaticBallList() {
        return ballStaticList;
    }
    
    public void increaseColorList() {
        this.colorList.add(new AtomicInteger(0));
    }

}
