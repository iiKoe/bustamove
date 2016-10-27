package com.group66.game.cannon;

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
public class GameManager {
    
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

    /** The roof hitbox offset. */
    private static final float ROOF_OFFSET = 10;
    
    // TODO INIT IN CONSTRUCTOR
    private BallManager ballManager;

    /**
     * Instantiates a new ball manager.
     *
     * @param dynamicSettings the dynamic settings
     */
    public GameManager(DynamicSettings dynamicSettings) {
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
            ballManager.increaseColorList();
        }
    }
    
    /**
     * Instantiates a new ball manager.
     *
     * @param segmentOffset the offset for this segments
     * @param dynamicSettings the dynamic settings
     */
    public GameManager(int segmentOffset, DynamicSettings dynamicSettings) {
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
            //this.colorList.add(new AtomicInteger(0));
            ballManager.increaseColorList();
        }
    }

    /**
     * Shoot ball.
     */
    public void shootBall() {
        // TODO add math so ball comes out the top of the cannon?
        if (canShoot()) {
            ballManager.shootBall();
            AudioManager.shoot();
            timeKeeper.shotTimeReset();
            this.ballCount++;
        }
    }

    /**
     * Can shoot.
     *
     * @return true, if successful
     */
    public boolean canShoot() {
        if (ballManager.getNumberOfPendingBalls() > 0
                || ballManager.getNumberOfPoppingBalls() > 1
                || ballManager.getNumberOfCannonBalls() == 0) {
            return false;
        }
        return true;
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
        for (Ball b : ballManager.getStaticBallList()) {
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
        for (Ball b : ballManager.getStaticBallList()) {
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
        
        /* Update the balls */
        ballManager.update(batch, delta);
        
        //draw cannon
        cannon.draw(batch);
        
        /* Draw the score */
        textDrawer.draw(batch, "Score: " + scoreKeeper.getCurrentScore(), xoffset + Config.SCORE_OFFSET,
                Config.SCORE_OFFSET);
    }


    
    
    public void ballCheckRoof(Ball ball) {
        if (ball.getTopHitbox().overlaps(roofHitbox)) {
            System.out.println("Attach ball to top");
            ball.setSpeed(0);
            BallSnap.snapBallToRoof(ball, roofHitbox.y + ROOF_OFFSET, this.isSplit, this.segmentOffset);
            
            ballManager.addBallDeadlist(ball);
            ballManager.addStaticBallToBeAdded(ball);
            //ballDeadList.add(ball);
            //ballToBeAdded.add(ball);
        }
    }
    
    public void moveBallsDown() {
        if (getBallCount() >= Config.NBALLS_ROW_DOWN && canShoot()) {
            System.out.println("Move balls down");
            moveRowDown();
            setBallCount(0);
        }
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
        for (Ball ball : ballManager.getBallList()) {
            ball.update(delta);
            ballManager.ballCheckDead(ball);
            /*
            if (ball.isDead()) {
                ballDeadList.add(ball);
            }
            */
            
            ballCheckRoof(ball);
            /*
            if (ball.getTopHitbox().overlaps(roofHitbox)) {
                System.out.println("Attach ball to top");
                ball.setSpeed(0);
                BallSnap.snapBallToRoof(ball, roofHitbox.y + ROOF_OFFSET, this.isSplit, this.segmentOffset);
                ballDeadList.add(ball);
                ballToBeAdded.add(ball);
            }
            */
            ballManager.ballHitBall(ball, this.isSplit, this.segmentOffset);
            /*
            for (Ball t : ballStaticList) {
                // Does the ball hit a target ball?
                if (t.doesHit(ball.getHitbox()) && !ballToBeAdded.contains(ball)) {
                    ball.setSpeed(0);
                    // A hack for now
                    BallSnap.snapBallToGrid(ball, t, this.isSplit, this.segmentOffset);
                    ballDeadList.add(ball);
                    ballToBeAdded.add(ball);
                    BustaMove.getGameInstance().log(MessageType.Info, "Ball hit");
                }
            }
            */
            BallBounce.bounceEdge(ball, this.isSplit, this.segmentOffset);
            timeKeeper.shotTimeReset();
        }
        
        ballManager.ballCleanDead();
        /*
        while (ballDeadList.size() != 0) {
            ballList.remove(ballDeadList.get(0));
            ballDeadList.remove(0);
        }
        */

        ballManager.cleanStaticDead(this.cannon);
        /*
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
        */

        ballManager.addStaticBalls(this.cannon);
        /*
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
        */

        ballManager.checkPop();
        /* Check if the popping balls are done */
        /*
        for (Iterator<Ball> it = ballPopList.iterator(); it.hasNext();) {
            Ball ball = it.next();
            if (ball.popDone() == true) {
                it.remove();
                //System.out.println("Pop list size: " + ballPopList.size());
            }
        }
        */
        
        moveBallsDown();
        /*
        if (getBallCount() >= Config.NBALLS_ROW_DOWN && canShoot()) {
            System.out.println("Move balls down");
            moveRowDown();
            setBallCount(0);
        }
        */
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
     * Copy all ball from the other ball manager into this one.
     *
     * @param other The other ball manager
     */
    /*
    public void shiftClone(GameManager other) {
        for (Ball b : other.ballStaticList) {
            float xpos = Config.SEGMENT_OFFSET * segmentOffset + b.getX();
            ballManager.addStaticBall(b.getType(), xpos, b.getY());
        }
    }
    */

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
    
    public BallManager getBallManager() {
        return this.ballManager;
    }
}

