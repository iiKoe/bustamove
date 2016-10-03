package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.BustaMove;
import com.group66.game.cannon.Ball.BallType;
import com.group66.game.cannon.Ball;
import com.group66.game.helpers.AssetLoader;
import com.group66.game.helpers.AudioManager;
import com.group66.game.helpers.HighScoreManager;
import com.group66.game.helpers.ScoreKeeper;
import com.group66.game.logging.MessageType;
import com.group66.game.screens.GameScreen;
import com.group66.game.screens.SplitGameScreen;
import com.group66.game.screens.YouWinScreen;
import com.group66.game.settings.Config;

/**
 * A Class to manage the Balls in the game.
 */
public class BallManager {

    /** The cannon instance to shoot out. */
    public Cannon cannon;

    /** The roof hitbox. */
    private Rectangle roofHitbox;

    /** The graph where all the connections between balls are stored. */
    private BallGraph ballGraph;

    /** The ball count. */
    private int ballCount;

    private boolean isSplit = false;
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
     */
    public BallManager() {
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        cannon = new Cannon(new Texture("cannon.png"), xoffset + Config.LEVEL_WIDTH / 2, Config.CANNON_Y_OFFSET,
                Config.CANNON_WIDTH, Config.CANNON_HEIGHT, Config.CANNON_MIN_ANGLE, Config.CANNON_MAX_ANGLE);
        this.ballCount = 0;
        this.roofHitbox  = new Rectangle(xoffset, Config.HEIGHT - Config.BORDER_SIZE_TOP - ROOF_OFFSET,
                Config.LEVEL_WIDTH + 2 * Config.BORDER_SIZE_SIDES, Config.LEVEL_HEIGHT);
        this.ballGraph = new BallGraph();

        Random random = new Random();
        int rand = random.nextInt(Ball.MAX_COLORS + 1);
        if (rand < Ball.MAX_COLORS) {
            cannonBallList.add(new ColoredBall(rand, cannon.getX(), cannon.getY(), 0, 0.0f));  
        } else {
            cannonBallList.add(new BombBall(cannon.getX(), cannon.getY(), 0, 0.0f));
        }
        
        for (int i = 0; i < Ball.MAX_COLORS; i++) {
            this.colorList.add(new AtomicInteger(0));
        }
    }
    
    /**
     * Instantiates a new ball manager.
     * @param segmentOffset the offset for this segments
     */
    public BallManager(int segmentOffset) {
        int xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        
        cannon = new Cannon(new Texture("cannon.png"), xoffset + Config.LEVEL_WIDTH / 2, Config.CANNON_Y_OFFSET,
                Config.CANNON_WIDTH, Config.CANNON_HEIGHT, Config.CANNON_MIN_ANGLE, Config.CANNON_MAX_ANGLE);
        this.ballCount = 0;
        this.roofHitbox = new Rectangle(xoffset + Config.BORDER_SIZE_SIDES,
                Config.HEIGHT - Config.BORDER_SIZE_TOP - ROOF_OFFSET, Config.LEVEL_WIDTH, Config.LEVEL_HEIGHT);
        this.ballGraph = new BallGraph();
        
        this.isSplit = true;
        this.segmentOffset = segmentOffset;

        Random random = new Random();
        int rand = random.nextInt(Ball.MAX_COLORS + 1);
        if (rand < Ball.MAX_COLORS) {
            cannonBallList.add(new ColoredBall(rand, cannon.getX(), cannon.getY(), 0, 0.0f));  
        } else {
            cannonBallList.add(new BombBall(cannon.getX(), cannon.getY(), 0, 0.0f));
        }

        for (int i = 0; i < Ball.MAX_COLORS; i++) {
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
        if (type == BallType.BOMB) {
            ballStaticList.add(new BombBall(xpos, ypos, 0, 0.0f));
        } else {
            ballStaticList.add(new ColoredBall(type, xpos, ypos, 0, 0.0f));
            colorList.get(ballStaticList.get(ballStaticList.size() - 1).getColor()).incrementAndGet();
        }
        ballStaticList.get(ballStaticList.size() - 1).addToGraph(ballGraph);
        BustaMove.logger.log(MessageType.Debug, "add ball: color(" 
                + ballStaticList.get(ballStaticList.size() - 1).getColor() 
                + "), " + "x(" + ballStaticList.get(ballStaticList.size() - 1).getX() + "), y(" 
                + ballStaticList.get(ballStaticList.size() - 1).getY() + "), pointer(" 
                + ballStaticList.get(ballStaticList.size() - 1).toString() + ")");
    }
    
    /**
     * Adds a static ball
     * 
     * @param color
     * @param xpos
     * @param ypos
     */
    public void addStaticBall(int color, float xpos, float ypos) { 
        color %= Ball.MAX_COLORS;
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
        int rand = random.nextInt(Ball.MAX_COLORS + 1);
        addStaticBall(rand, xpos, ypos);
    }

    /**
     * Shoot ball.
     * 
     * @param color the color of the Ball
     */
    public void shootBall(int color) {
        // TODO add math so ball comes out the top of the cannon?
        if (canShoot()) {

            ballList.add(cannonBallList.get(0));
            ballList.get(ballList.size() - 1).setAngle((float) Math.toRadians(cannon.getAngle()));
            ballList.get(ballList.size() - 1).setSpeed(Config.BALL_SPEED);
            cannonBallList.remove(0);
            if (color < Ball.MAX_COLORS) {
                cannonBallList.add(new ColoredBall(color, cannon.getX(), cannon.getY(), 0, 0.0f));  
            } else {
                cannonBallList.add(new BombBall(cannon.getX(), cannon.getY(), 0, 0.0f));
            }
            AudioManager.shoot();
            if (isSplit) {
                SplitGameScreen.timeKeeper.shotTimeReset();
            } else {
                GameScreen.timeKeeper.shotTimeReset();
            }
            BustaMove.logger.log(MessageType.Info, "Shot a " + color
                    + " ball at angle " + cannon.getAngle());
            this.ballCount++;
        }
    }

    /**
     * Shoot random colored ball.
     */
    public void shootRandomBall() {
        Random random = new Random();
        int rand;
        do {
            rand = random.nextInt(Ball.MAX_COLORS + 1);
        } while (rand < Ball.MAX_COLORS && colorList.get(rand).get() <= 0);
        shootBall(rand);
    }

    /**
     * Can shoot.
     *
     * @return true, if successful
     */
    public boolean canShoot() {
        if (ballList.size() > 0 || ballPopList.size() > 1) {
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
        BustaMove.logger.log(MessageType.Info, "Moved the roof a row down");
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
     * Draw the Balls managed by BallManager.
     *
     * @param batch the batch used to draw with
     * @param delta the delta
     */
    public void draw(SpriteBatch batch, float delta) {

        /* Update the ball lists and graph */
        updateBalls(delta);

        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        }
        
        //draw the background
        batch.draw(AssetLoader.bg, xoffset + Config.BORDER_SIZE_SIDES, Config.BORDER_SIZE_BOT,
                Config.LEVEL_WIDTH, Config.LEVEL_HEIGHT);
        
        /* Draw the brick wall */
        batch.draw(AssetLoader.bw, roofHitbox.x, roofHitbox.y + 10, roofHitbox.width, roofHitbox.height);
        
        /* Draw shot ball */
        for (Ball ball : ballList) {
            ball.draw(batch, delta);
        }

        /* Draw static target balls */
        for (Ball ball : ballStaticList) {
            ball.draw(batch, delta);
        }

        /* Draw popping balls */
        for (Ball ball : ballPopList) {
            ball.draw(batch, delta);
        }

        /* Draw cannon balls */
        for (Ball ball: cannonBallList) {
            ball.draw(batch, delta);
        }
        
        //draw cannon
        cannon.draw(batch);
    }

    /**
     * Bounce the ball of the edges if needed.
     *
     * @param ball the ball
     */
    private void bounceEdge(Ball ball) {
        /* Check if an edge is hit */
        int left = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            left = Config.SEGMENT_OFFSET * segmentOffset + Config.BORDER_SIZE_SIDES;
        }
        int right = left + Config.LEVEL_WIDTH;

        if (ball.getX() - Config.BALL_RAD <= left
                && Math.toDegrees(ball.getAngle()) > 90) {
            // LEFT EDGE
            ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
            AudioManager.wallhit();
            BustaMove.logger.log(MessageType.Info, "Ball hit the wall");
        } else if (ball.getX() + Config.BALL_RAD >= right
                && Math.toDegrees(ball.getAngle()) < 90) {
            // RIGHT EDGE
            ball.setAngle((float) Math.toRadians(180) - ball.getAngle());
            AudioManager.wallhit();
            BustaMove.logger.log(MessageType.Info, "Ball hit the wall");
        }
    }

    /**
     * Start popping animation.
     *
     * @param ball the ball
     */
    private void startPop(Ball ball) {
        ball.popStart();
        ballPopList.add(ball);
    }

    /**
     * Snap ball to grid. TODO Make less HACKY
     *
     * @param ball the ball
     * @param hitb the hitb
     */
    private void snapBallToGrid(Ball ball, Ball hitb) {
        // Check what the closest "snap" coordinate is
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        }

        float hitx = hitb.getX();
        float hity = hitb.getY();

        float o1x = hitx - Config.BALL_DIAM;
        float o1y = hity;

        float o2x = hitx - Config.BALL_RAD;
        float o2y = hity - Config.BALL_DIAM;

        float o3x = hitx + Config.BALL_RAD;
        float o3y = hity - Config.BALL_DIAM;

        float o4x = hitx + Config.BALL_DIAM;
        float o4y = hity;

        float o5x = hitx + Config.BALL_RAD;
        float o5y = hity + Config.BALL_DIAM;

        float o6x = hitx - Config.BALL_RAD;
        float o6y = hity + Config.BALL_DIAM;

        float origx = ball.getX();
        float origy = ball.getY();

        float do1 = Math.abs(origx - o1x) + Math.abs(origy - o1y);
        float do2 = Math.abs(origx - o2x) + Math.abs(origy - o2y);
        float do3 = Math.abs(origx - o3x) + Math.abs(origy - o3y);
        float do4 = Math.abs(origx - o4x) + Math.abs(origy - o4y);
        float do5 = Math.abs(origx - o5x) + Math.abs(origy - o5y);
        float do6 = Math.abs(origx - o6x) + Math.abs(origy - o6y);

        // Check bounds
        float redge = xoffset + Config.LEVEL_WIDTH - Config.BALL_RAD;
        if (o4x > redge) {
            do4 = Float.MAX_VALUE;
        }
        if (o3x > redge) {
            do3 = Float.MAX_VALUE;
        }
        if (o5x > redge) {
            do5 = Float.MAX_VALUE;
        }

        float ledge = xoffset + Config.BALL_RAD;
        if (o1x < ledge) {
            do1 = Float.MAX_VALUE;
        }
        if (o2x < ledge) {
            do2 = Float.MAX_VALUE;
        }
        if (o6x < ledge) {
            do6 = Float.MAX_VALUE;
        }

        // Check best option
        if (do1 < do2 && do1 < do3 && do1 < do4 && do1 < do5 && do1 < do6) {
            ball.setX(o1x);
            ball.setY(o1y);
            //System.out.println("Option 1");
        } else if (do2 < do1 && do2 < do3 && do2 < do4 && do2 < do5 && do2 < do6) {
            ball.setX(o2x);
            ball.setY(o2y);
            //System.out.println("Option 2");
        } else if (do3 < do1 && do3 < do2 && do3 < do4 && do3 < do5 && do3 < do6) {
            ball.setX(o3x);
            ball.setY(o3y);
            //System.out.println("Option 3");
        } else if (do4 < do1 && do4 < do2 && do4 < do3 && do4 < do5 && do4 < do6) {
            ball.setX(o4x);
            ball.setY(o4y);
            //System.out.println("Option 4");
        } else if (do5 < do1 && do5 < do2 && do5 < do3 && do5 < do4 && do5 < do6) {
            ball.setX(o5x);
            ball.setY(o5y);
        } else if (do6 < do1 && do6 < do2 && do6 < do3 && do6 < do4 && do6 < do5) {
            ball.setX(o6x);
            ball.setY(o6y);
        }
    }

    /**
     * Snap ball to roof.
     *
     * @param ball the ball
     */
    private void snapBallToRoof(Ball ball, float roofy) {
        float newx;
        int xoffset = Config.SINGLE_PLAYER_OFFSET;
        if (isSplit) {
            xoffset = Config.SEGMENT_OFFSET * segmentOffset;
        }

        for (double xpos = xoffset + Config.LEVEL_WIDTH - Config.BALL_RAD; 
                xpos >= xoffset + Config.BALL_RAD; xpos -= Config.BALL_DIAM) {
            newx = (float)xpos;
            if (Math.abs(newx - ball.getX()) <=  Config.BALL_RAD) {
                ball.setX(newx);
                ball.setY(roofy - Config.BALL_RAD);
                return;
            }
        }
        BustaMove.logger.log(MessageType.Info, "Ball snapped into place");
    }

    /**
     * Update balls, this includes the ball lists and the graph.
     *
     * @param delta the delta
     */
    private void updateBalls(float delta) {
        /* Check shooting balls */
        // NB. Currently only 1 ball can be shot at a time in the game
        // nevertheless the current BallList implementation is kept for
        // versatility and to be future proof
        for (Ball ball : ballList) {
            ball.update(delta);
            if (ball.isDead()) {
                ballDeadList.add(ball);
            }
            if (ball.getTopHitbox().overlaps(roofHitbox)) {
                System.out.println("Attach ball to top");
                ball.setSpeed(0);
                snapBallToRoof(ball, roofHitbox.y + ROOF_OFFSET);
                ballDeadList.add(ball);
                ballToBeAdded.add(ball);
            }
            for (Ball t : ballStaticList) {
                /* Does the ball hit a target ball? */
                if (t.doesHit(ball.getHitbox()) && !ballToBeAdded.contains(ball)) {
                    ball.setSpeed(0);
                    // A hack for now
                    snapBallToGrid(ball, t);
                    ballDeadList.add(ball);
                    ballToBeAdded.add(ball);
                    BustaMove.logger.log(MessageType.Info, "Ball hit");
                }
            }
            bounceEdge(ball);
        }

        while (ballDeadList.size() != 0) {
            ballList.remove(ballDeadList.get(0));
            ballDeadList.remove(0);
        }

        while (ballStaticDeadList.size() != 0) {
            ballGraph.removeBall(ballStaticDeadList.get(0));
            ballStaticList.remove(ballStaticDeadList.get(0));
            if (ballStaticDeadList.get(0).getType() != BallType.BOMB) {
                colorList.get(ballStaticDeadList.get(0).getColor()).decrementAndGet();
            }
            ballStaticDeadList.remove(0);
            //System.out.println("number of balls left: " + ballGraph.numberOfBalls());
            if (ballStaticDeadList.size() == 0) {
                GameScreen.scoreKeeper.setCurrentScore(0, ballGraph.getFreeBalls().size());
                for (Ball e:ballGraph.getFreeBalls()) {
                    ballStaticDeadList.add(e);
                    if (!ballPopList.contains(e) && e.getType() != BallType.BOMB) {
                        startPop(e);
                    }
                    //System.out.println("ball added to deadlist(free)");
                }
                BustaMove.logger.log(MessageType.Info, "Number of balls in grid: " + ballGraph.numberOfBalls());
                int count = 0;
                for (AtomicInteger e: colorList) {
                    BustaMove.logger.log(MessageType.Info, "Number of balls with color " 
                            + count + " :" + e.get());
                    count++;
                }
            }
        }

        while (ballToBeAdded.size() != 0) {
            addStaticBall(ballToBeAdded.get(0).getType(), 
                    (int)ballToBeAdded.get(0).getX(), (int)ballToBeAdded.get(0).getY());
            ballToBeAdded.remove(0);
            int count = 0;
            for (AtomicInteger e: colorList) {
                BustaMove.logger.log(MessageType.Info, "Number of balls with color " 
                        + count + " :" + e.get());
                count++;
            }
            if (ballGraph.numberOfAdjacentColoredBalls(ballStaticList.get(ballStaticList.size() - 1)) >= 3) {
                //int score = 0;
                for (Ball e:ballGraph.getAdjacentColoredBalls(ballStaticList.get(ballStaticList.size() - 1))) {
                    //System.out.println("ball added to deadlist (adjacent)");
                    //score++;
                    if (!ballStaticDeadList.contains(e)) {
                        BustaMove.logger.log(MessageType.Debug, "Ball added to deadlist: " + e.toString() 
                            + " Location: (" + e.getX() + "," + e.getY() + ")");
                        ballStaticDeadList.add(e);
                        if (e.getType() != BallType.BOMB) {
                            startPop(e);
                        } else {
                            GameScreen.scoreKeeper.doubleCurrentScore();
                        }
                        //GameScreen.scoreKeeper.setCurrentScore(1, 0);
                    }
                }
                BustaMove.logger.log(MessageType.Info, "Started popping "
                        + ballStaticDeadList.size() + " balls");
                //GameScreen.scoreKeeper.setCurrentScore(score, 0);
                //TODO 
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

        /* Check if there are no balls left i.e. player wins */
        if (ballGraph.numberOfBalls() == 0) {
            BustaMove.logger.log(MessageType.Info, "Level completed");
            HighScoreManager.addScore(0); //TODO: get score from ScoreKeeper
            if (isSplit) {
                SplitGameScreen.game.setScreen(new YouWinScreen(SplitGameScreen.game));
            } else {
                GameScreen.game.setScreen(new YouWinScreen(GameScreen.game));
            }
        }
        
        if (getBallCount() >= Config.NBALLS_ROW_DOWN && canShoot()) {
            System.out.println("Move balls down");
            moveRowDown();
            setBallCount(0);
        }
    }
    
    /**
     * Copy all ball from the other ball manager into this one
     * @param other The other ball manager
     */
    public void shiftClone(BallManager other) {
        for (Ball b : other.ballStaticList) {
            float xpos = Config.SEGMENT_OFFSET * segmentOffset + b.getX();
            addStaticBall(b.getType(), xpos, b.getY());
        }
    }
}

