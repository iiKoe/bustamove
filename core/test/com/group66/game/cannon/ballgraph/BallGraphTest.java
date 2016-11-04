package com.group66.game.cannon.ballgraph;

import com.group66.game.cannon.Ball;
import com.group66.game.cannon.BallType;
import com.group66.game.cannon.BombBall;
import com.group66.game.cannon.ColoredBall;
import com.group66.game.cannon.ballgraph.BallGraph;
import com.group66.game.settings.Config;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

/**
 * The Class BallGraphTest.
 */
public class BallGraphTest {
    
    /**
     * Ball graph creation test.
     */
    @Test
    public void ballGraphCreationTest() {
        new BallGraph();       
    }
    
    /**
     * Basic Ball testing 1:
     * Tests ball insertion, ball type creation, numberOfBalls function,
     * ball removal test
     */
    @Test
    public void basicBallGraphTest() {
        BallGraph testBallGraph = new BallGraph();
        assertEquals(testBallGraph.numberOfBalls(), 0);
        Ball ball1 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        testBallGraph.insertBall(ball1);
        assertEquals(testBallGraph.numberOfBalls(), 1);
        Ball ball2 = new BombBall(10.0f, 5.0f, 0, 0);
        testBallGraph.insertBall(ball2);
        assertEquals(testBallGraph.numberOfBalls(), 2);
        testBallGraph.removeBall(ball1);
        assertEquals(testBallGraph.numberOfBalls(), 1);
        testBallGraph.removeBall(null);
        assertEquals(testBallGraph.numberOfBalls(), 1);
        testBallGraph.removeBall(ball2);
        assertEquals(testBallGraph.numberOfBalls(), 0);      
    }
    
    /**
     * Advanced ball insertion test:
     * Tests null ball insertion, top row ball insertion
     */
    @Test
    public void insertBallTest() {
        BallGraph testBallGraph = new BallGraph();
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.GREEN, 50, ypos, 0, 0);
        testBallGraph.insertBall(ball1);
        assertEquals(testBallGraph.numberOfBalls(), 1);
        Ball ball2 = null;
        testBallGraph.insertBall(ball2);
        assertEquals(testBallGraph.numberOfBalls(), 1);
    }
    
    /**
     * Adjacent balls test:
     * Tests getting adjacent balls
     */
    @Test
    public void adjacentBallsTest() {
        //test the numberOfAdjacentColoredBalls
        BallGraph testBallGraph = new BallGraph();
        Ball ball1 = new ColoredBall(BallType.GREEN, 50, 50, 0, 0);
        testBallGraph.insertBall(ball1);
        Ball ball2 = new ColoredBall(BallType.GREEN, ball1.getX() + Config.BALL_DIAM, 50, 0, 0);
        testBallGraph.insertBall(ball2);
        assertEquals(testBallGraph.numberOfAdjacentColoredBalls(ball1), 2);
        //Tests the null check of numberOfAdjacentColoredBalls
        Ball nullBall = null;
        assertEquals(testBallGraph.numberOfAdjacentColoredBalls(nullBall), 0);
        
        // Tests getAdjacentColoredBalls
        ArrayList<Ball> ret = new ArrayList<Ball>();
        ret.add(ball1);
        ret.add(ball2);
        assertEquals(testBallGraph.getAdjacentColoredBalls(ball1), ret);
        
        // Tests null getAdjacentColoredBalls
        assertEquals(testBallGraph.getAdjacentColoredBalls(nullBall), null);
    }
    
    
    /**
     * BallGraph test:
     * Tests if place is taken
     */
    @Test
    public void placeTakenTest() {
        BallGraph testBallGraph = new BallGraph();
        Ball ball1 = new ColoredBall(BallType.GREEN, 50, 50, 0, 0);
        testBallGraph.insertBall(ball1);
        assertEquals(true, testBallGraph.placeTaken(ball1.getX(), ball1.getY()));
        assertEquals(false, testBallGraph.placeTaken(10.0f,  10.0f));
        assertEquals(false, testBallGraph.placeTaken(ball1.getX(), 100.0f));
    }

    /**
     * Test for free balls
     */
    @Test
    public void freeBallTest() {
        BallGraph testBallGraph = new BallGraph();
        testBallGraph.getFreeBalls();
        
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.GREEN, -Config.BALL_RAD, ypos, 0, 0);
        testBallGraph.insertBall(ball1);
        Ball ball2 = new ColoredBall(BallType.YELLOW, Config.BALL_RAD, ypos, 0, 0);
        testBallGraph.insertBall(ball2);

        testBallGraph.getFreeBalls();
    }
    
    /**
     * Test2 for free balls
     */
    @Test
    public void freeBallTest2() {
        BallGraph testBallGraph = new BallGraph();
        testBallGraph.getFreeBalls(null);
        
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.GREEN, -Config.BALL_RAD, ypos, 0, 0);
        testBallGraph.insertBall(ball1);
        Ball ball2 = new ColoredBall(BallType.YELLOW, Config.BALL_RAD, ypos, 0, 0);
        testBallGraph.insertBall(ball2);

        testBallGraph.getFreeBalls(ball1);
    }
    
    /**
     * Test3 for free balls
     */
    @Test
    public void freeBallTest3() {
        BallGraph testBallGraph = new BallGraph();
        testBallGraph.getFreeBalls(null);
        
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.GREEN, -Config.BALL_RAD, ypos, 0, 0);
        testBallGraph.insertBall(ball1);
        Ball ball2 = new ColoredBall(BallType.YELLOW, Config.BALL_RAD + 1000, ypos + 1000, 0, 0);
        testBallGraph.insertBall(ball2);
        Ball ball3 = new ColoredBall(BallType.YELLOW, Config.BALL_RAD + 10000, ypos + 10000, 0, 0);
        testBallGraph.insertBall(ball3);
        Ball ball4 = new ColoredBall(BallType.YELLOW, 0, 0, 0, 0);
        testBallGraph.insertBall(ball4);
        Ball ball5 = new ColoredBall(BallType.YELLOW, 50, 50, 0, 0);
        testBallGraph.insertBall(ball5);
        testBallGraph.getFreeBalls();
        testBallGraph.getFreeBalls(null);

        testBallGraph.getFreeBalls(ball2);
    }
    
    /**
     * Connect test.
     */
    @Test
    public void connectTest() {
        BallGraph testBallGraph = new BallGraph();
        Ball ball1 = new ColoredBall(BallType.GREEN, 0, 0, 0, 0);
        testBallGraph.insertBall(ball1);
        testBallGraph.connectBalls(null, null);
        testBallGraph.connectBalls(ball1, null);
        testBallGraph.connectBalls(null, ball1);
    }
    
    /**
     * Roof shift test.
     */
    @Test
    public void roofShiftTest() {
        BallGraph testBallGraph = new BallGraph();
        testBallGraph.setRoofShift(10);
    }
    
    @Test
    public void getBallsTest() {
        BallGraph testBallGraph = new BallGraph();
        Ball ball1 = new ColoredBall(BallType.GREEN, 0, 0, 0, 0);
        testBallGraph.insertBall(ball1);
        testBallGraph.getBalls(null);
        testBallGraph.getBalls(ball1);
    }
}
