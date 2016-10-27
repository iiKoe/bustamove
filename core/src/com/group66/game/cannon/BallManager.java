package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.BustaMove;
import com.group66.game.cannon.BallType;
import com.group66.game.cannon.Ball;
import com.group66.game.helpers.AudioManager;
import com.group66.game.helpers.ScoreKeeper;
import com.group66.game.helpers.TextDrawer;
import com.group66.game.helpers.TimeKeeper;
import com.group66.game.logging.MessageType;
import com.group66.game.screens.AbstractGameScreen;
import com.group66.game.settings.Config;
import com.group66.game.settings.DynamicSettings;

/**
 * A Class to manage the Balls in the game.
 */
public class BallManager {
    
    /**  Dynamic settings. */
    private DynamicSettings dynamicSettings;

    /** The cannon instance to shoot out. */
    public Cannon cannon;

    /** The roof hitbox. */
    private Rectangle roofHitbox;

    /** The graph where all the connections between balls are stored. */
    private BallGraph ballGraph;

    /**  The score keeper. */
    public ScoreKeeper scoreKeeper = new ScoreKeeper();
    
    /**  needed to draw text, draw score. */
    private TextDrawer textDrawer = new TextDrawer();

    /**  The TimeKeeper. */
    public TimeKeeper timeKeeper;
    
    /** The ball count. */
    private int ballCount;

    /** The is split. */
    private boolean isSplit = false;
    
    /** The segment offset. */
    private int segmentOffset = 0;
    
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

    /** The roof hitbox offset. */
    private static final float ROOF_OFFSET = 10;

    /**
     * Instantiates a new ball manager.
     *
     * @param dynamicSettings the dynamic settings
     */
    public BallManager(DynamicSettings dynamicSettings) {
        this.dynamicSettings = dynamicSettings;
        
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        
        cannon = new Cannon(new Texture("cannon.png"), xoffset + Config.LEVEL_WIDTH / 2, Config.CANNON_Y_OFFSET,
                Config.CANNON_WIDTH, Config.CANNON_HEIGHT, Config.CANNON_MIN_ANGLE, Config.CANNON_MAX_ANGLE);
        this.ballCount = 0;
        this.roofHitbox  = new Rectangle(xoffset, Config.HEIGHT - Config.BORDER_SIZE_TOP - ROOF_OFFSET,
                Config.LEVEL_WIDTH + 2 * Config.BORDER_SIZE_SIDES, Config.LEVEL_HEIGHT);
        this.ballGraph = new BallGraph();
        this.timeKeeper = new TimeKeeper(this);

        
        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            this.colorList.add(new AtomicInteger(0));
        }
    }
    
    /**
     * Instantiates a new ball manager.
     *
     * @param segmentOffset the offset for this segments
     * @param dynamicSettings the dynamic settings
     */
    public BallManager(int segmentOffset, DynamicSettings dynamicSettings) {
        this.dynamicSettings = dynamicSettings;
        
        int xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        this.isSplit = true;
        this.segmentOffset = segmentOffset;
        
        cannon = new Cannon(new Texture("cannon.png"), xoffset + Config.LEVEL_WIDTH / 2, Config.CANNON_Y_OFFSET,
                Config.CANNON_WIDTH, Config.CANNON_HEIGHT, Config.CANNON_MIN_ANGLE, Config.CANNON_MAX_ANGLE);
        this.ballCount = 0;
        this.roofHitbox = new Rectangle(xoffset + Config.BORDER_SIZE_SIDES,
                Config.HEIGHT - Config.BORDER_SIZE_TOP - ROOF_OFFSET, Config.LEVEL_WIDTH, Config.LEVEL_HEIGHT);
        this.ballGraph = new BallGraph();
        this.timeKeeper = new TimeKeeper(this);
        

        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            this.colorList.add(new AtomicInteger(0));
        }
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
     * Shoot ball.
     */
    public void shootBall() {
        // TODO add math so ball comes out the top of the cannon?
        if (canShoot()) {
            int newSpeed = (int) (Config.BALL_SPEED * dynamicSettings.getBallSpeedMultiplier());
            ballList.add(cannonBallList.get(0));
            ballList.get(ballList.size() - 1).setAngle((float) Math.toRadians(cannon.getAngle()));
            ballList.get(ballList.size() - 1).setSpeed(newSpeed);
            
            AudioManager.shoot();
            timeKeeper.shotTimeReset();
            BustaMove.getGameInstance().log(MessageType.Info, "Shot a " + cannonBallList.get(0).getColor()
                    + " ball at angle " + cannon.getAngle() + " with speed " + newSpeed);
            cannonBallList.remove(0);
            this.ballCount++;
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

    /**
     * Can shoot.
     *
     * @return true, if successful
     */
    public boolean canShoot() {
        if (ballList.size() > 0 || ballPopList.size() > 1 || cannonBallList.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Gets the ball count.
     *
     * @return the ball count
     */
    public int getBallCount() {
        return this.ballCount;
    }

    /**
     * Sets the ball count.
     *
     * @param bc the new ball count
     */
    public void setBallCount(int bc) {
        this.ballCount = bc;
    }

    /**
     * Gets the roof hitbox.
     *
     * @return the roof hitbox
     */
    public Rectangle getRoofHitbox() {
        return this.roofHitbox;
    }

    /**
     * Move row down.
     */
    public void moveRowDown() {
        // Move the top hitbox down
        this.roofHitbox.y -= Config.BALL_DIAM;
        // Move all the balls down
        for (Ball b : this.ballStaticList) {
            b.moveDown(Config.BALL_DIAM);
        }
        ballGraph.setRoofShift(Config.BALL_DIAM);
        BustaMove.getGameInstance().log(MessageType.Info, "Moved the roof a row down");
    }

    /**
     * Checks if it's game over.
     *
     * @return true, if is game over
     */
    public boolean isGameOver() {
        for (Ball b : ballStaticList) {
            // TODO fix the != check
            if (b.getY() - Config.BALL_DIAM <= Config.BORDER_SIZE_BOT && b.getY() != 0) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Checks if it's game complete.
     *
     * @return true, if is game complete
     */
    public boolean isGameComplete() {
        if (ballGraph.numberOfBalls() == 0) {
            return true;
        }
        return false;
    }

    /**
     * Draw the Balls managed by BallManager.
     *
     * @param batch the batch used to draw with
     * @param delta the delta
     */
    public void draw(AbstractGameScreen gameScreen, SpriteBatch batch, float delta) {
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        }
        
        //draw the background
        batch.draw(gameScreen.getBackground(), xoffset + Config.BORDER_SIZE_SIDES, Config.BORDER_SIZE_BOT,
                Config.LEVEL_WIDTH, Config.LEVEL_HEIGHT);
        
        /* Draw the brick wall */
        batch.draw(gameScreen.getBrickWall(), roofHitbox.x, roofHitbox.y + 10, roofHitbox.width, roofHitbox.height);
        
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
        
        //draw cannon
        cannon.draw(batch);
        
        /* Draw the score */
        textDrawer.draw(batch, "Score: " + scoreKeeper.getCurrentScore(), xoffset + Config.SCORE_OFFSET,
                Config.SCORE_OFFSET);
    }


    /**
     * Start popping animation.
     *
     * @param ball the ball
     */
    private void startPop(Ball ball) {
        ball.popStart(ball.getType().getPopAnimation());
        ballPopList.add(ball);
    }

    /**
     * Update balls, this includes the ball lists and the graph.
     *
     * @param delta the delta
     */
    public void update(float delta) {
        /* Start counting time*/
        timeKeeper.universalTimeCounter(delta);
        
        /* Check if a ball hasn't been shot in the past 10 seconds */
        timeKeeper.didHeShoot();
        
        /* Check shooting balls */
        // NB. Currently only 1 ball can be shot at a time in the game nevertheless the
        // current BallList implementation is kept for versatility and to be future proof
        for (Ball ball : ballList) {
            ball.update(delta);
            if (ball.isDead()) {
                ballDeadList.add(ball);
            }
            if (ball.getTopHitbox().overlaps(roofHitbox)) {
                System.out.println("Attach ball to top");
                ball.setSpeed(0);
                BallSnap.snapBallToRoof(ball, roofHitbox.y + ROOF_OFFSET, this.isSplit, this.segmentOffset);
                ballDeadList.add(ball);
                ballToBeAdded.add(ball);
            }
            for (Ball t : ballStaticList) {
                /* Does the ball hit a target ball? */
                if (t.doesHit(ball.getHitbox()) && !ballToBeAdded.contains(ball)) {
                    ball.setSpeed(0);
                    // A hack for now
                    BallSnap.snapBallToGrid(ball, t, this.isSplit, this.segmentOffset);
                    ballDeadList.add(ball);
                    ballToBeAdded.add(ball);
                    BustaMove.getGameInstance().log(MessageType.Info, "Ball hit");
                }
            }
            BallBounce.bounceEdge(ball, this.isSplit, this.segmentOffset);
            timeKeeper.shotTimeReset();
        }

        while (ballDeadList.size() != 0) {
            ballList.remove(ballDeadList.get(0));
            ballDeadList.remove(0);
        }

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

        /* Check if the popping balls are done */
        for (Iterator<Ball> it = ballPopList.iterator(); it.hasNext();) {
            Ball ball = it.next();
            if (ball.popDone() == true) {
                it.remove();
                //System.out.println("Pop list size: " + ballPopList.size());
            }
        }
        
        if (getBallCount() >= Config.NBALLS_ROW_DOWN && canShoot()) {
            System.out.println("Move balls down");
            moveRowDown();
            setBallCount(0);
        }
    }
    
    /**
     * Copy all ball from the other ball manager into this one.
     *
     * @param other The other ball manager
     */
    public void shiftClone(BallManager other) {
        for (Ball b : other.ballStaticList) {
            float xpos = Config.SEGMENT_OFFSET * segmentOffset + b.getX();
            addStaticBall(b.getType(), xpos, b.getY());
        }
    }

    /**
     * Gets the dynamic settings.
     *
     * @return the dynamic settings
     */
    public DynamicSettings getDynamicSettings() {
        return dynamicSettings;
    }

    /**
     * Sets the dynamic settings.
     *
     * @param dynamicSettings the new dynamic settings
     */
    public void setDynamicSettings(DynamicSettings dynamicSettings) {
        this.dynamicSettings = dynamicSettings;
    }
}

