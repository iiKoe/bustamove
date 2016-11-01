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
import com.group66.game.cannon.ballgraph.BallGraph;

/**
 * A Class to manage the game rules.
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
    private TimeKeeper timeKeeper;
    
    /** The ball count. */
    private int ballCount;

    /** The is split. */
    private boolean isSplit = false;
    
    /** The segment offset. */
    private int segmentOffset = 0;

    /** The roof hitbox offset. */
    private static final float ROOF_OFFSET = 10;
    

    /** The ball manager. */
    private BallManager ballManager;

    /**
     * Instantiates a new ball manager.
     *
     * @param dynamicSettings the dynamic settings
     */
    public GameManager(DynamicSettings dynamicSettings) {      
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        this.cannon = new Cannon(new Texture("cannon.png"), xoffset + Config.LEVEL_WIDTH / 2, Config.CANNON_Y_OFFSET,
                Config.CANNON_WIDTH, Config.CANNON_HEIGHT, Config.CANNON_MIN_ANGLE, Config.CANNON_MAX_ANGLE);
        this.roofHitbox  = new Rectangle(xoffset, Config.HEIGHT - Config.BORDER_SIZE_TOP - ROOF_OFFSET,
                Config.LEVEL_WIDTH + 2 * Config.BORDER_SIZE_SIDES, Config.LEVEL_HEIGHT);
        
        reset(dynamicSettings, xoffset);
    }
    
    /**
     * Instantiates a new ball manager.
     *
     * @param segmentOffset the offset for this segments
     * @param dynamicSettings the dynamic settings
     */
    public GameManager(int segmentOffset, DynamicSettings dynamicSettings) {
        int xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        this.isSplit = true;
        this.segmentOffset = segmentOffset;
        this.roofHitbox = new Rectangle(xoffset + Config.BORDER_SIZE_SIDES,
                Config.HEIGHT - Config.BORDER_SIZE_TOP - ROOF_OFFSET, Config.LEVEL_WIDTH, Config.LEVEL_HEIGHT);
        
        reset(dynamicSettings, xoffset);
    }
    
    /**
     * Reset.
     *
     * @param dynamicSettings the dynamic settings
     * @param xoffset the xoffset
     */
    private void reset(DynamicSettings dynamicSettings, int xoffset) {
        this.dynamicSettings = dynamicSettings;
        this.ballCount = 0;
        this.ballGraph = new BallGraph();
        this.timeKeeper = new TimeKeeper(this);    
        this.cannon = new Cannon(new Texture("cannon.png"), xoffset + Config.LEVEL_WIDTH / 2, Config.CANNON_Y_OFFSET,
                Config.CANNON_WIDTH, Config.CANNON_HEIGHT, Config.CANNON_MIN_ANGLE, Config.CANNON_MAX_ANGLE);
        
        this.ballManager = new BallManager(this.dynamicSettings, this.ballGraph, this.cannon, this.scoreKeeper);
        
        for (int i = 0; i < BallType.MAX_COLORS.ordinal(); i++) {
            ballManager.increaseColorList();
        }
        this.timeKeeper.shotTimeReset();
    }

    /**
     * Shoot ball.
     */
    public void shootBall() {
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
        if (ballManager.getNumberOfShotBalls() > 0
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
        ballManager.moveRowDown();
        ballGraph.setRoofShift(Config.BALL_DIAM);
        BustaMove.getGameInstance().log(MessageType.Info, "Moved the roof a row down");
    }

    /**
     * Checks if it's game over.
     *
     * @return true, if is game over
     */
    public boolean isGameOver() {
        return ballManager.hitsBottom();
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
     * Draw the Balls managed by GameManager.
     *
     * @param gameScreen the game screen
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

    /**
     * Shift clone.
     *
     * @param other the other
     */
    public void shiftClone(GameManager other) {
        //TODO change to iterator
        for (Ball b : other.getBallManager().getBallsStaticManager().getBallStaticList()) {
            float xpos = Config.SEGMENT_OFFSET * segmentOffset + b.getX();
            ballManager.getBallsStaticManager().addStaticBall(b.getType(), xpos, b.getY());
        }
    }
    
    /**
     * Ball check roof.
     *
     * @param ball the ball
     */
    public void ballCheckRoof(Ball ball) {
        if (ball.getTopHitbox().overlaps(roofHitbox)) {
            System.out.println("Attach ball to top");
            ball.setSpeed(0);
            BallSnap.snapBallToRoof(ball, roofHitbox.y + ROOF_OFFSET, this.isSplit, this.segmentOffset);
            
            ballManager.addBallDeadlist(ball);
            ballManager.addStaticBallToBeAdded(ball);
        }
    }
    
    /**
     * Move balls down.
     */
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
        // TODO change to iterator
        for (Ball ball : ballManager.getBallList()) {
            ball.update(delta);
            ballManager.ballCheckDead(ball);
            ballCheckRoof(ball);
            ballManager.ballHitBall(ball, this.isSplit, this.segmentOffset);
            BallBounce.bounceEdge(ball, this.isSplit, this.segmentOffset);
            timeKeeper.shotTimeReset();
        }
        
        ballManager.ballCleanDead();
        ballManager.cleanStaticDead();
        ballManager.addStaticBalls();
        ballManager.checkPop();
        
        moveBallsDown();
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
    
    /**
     * Gets the ball manager.
     *
     * @return the ball manager
     */
    public BallManager getBallManager() {
        return this.ballManager;
    }
}
