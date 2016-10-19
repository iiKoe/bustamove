package com.group66.game.cannon;

import com.group66.game.cannon.BallGraph;
import com.group66.game.cannon.Ball;
import com.group66.game.cannon.Ball.BallType;
import com.group66.game.settings.Config;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.jgrapht.graph.SimpleGraph;

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
    
    
//    /**
//     * Connect balls test.
//     */
//    @Test
//    public void connectBallsTest(Ball ball1, Ball ball2) {
//        
//    }
//    
//    /**
//     * Number of adjacent colored balls.
//     */
//    @Test
//    public void numberOfAdjacentColoredBalls (Ball ball) {
//        
//        
//    }
//    
//    /**
//     * Gets the adjacent colored balls.
//     *
//     * @param ball the ball
//     * @return the adjacent colored balls
//     */
//    @Test
//    public ArrayList <Ball> getAdjacentColoredBalls(Ball ball) {
//        
//    }
//    
//    /**
//     * Gets the free balls test.
//     *
//     * @return the free balls test
//     */
//    @Test
//    public ArrayList<Ball> getFreeBallsTest() {
//        
//    }
//    
//    //public ArrayList<Ball> getFreeBallsTest(Ball top) {}
//    
//    //public ArrayList<Ball> getBallsTest(Ball exclude) {}
//    
//    
//    /**
//     * Gets the balls test.
//     *
//     * @return the balls test
//     */
//    @Test
//    public ArrayList<Balls> getBallsTest() {
//        
//    }
//    
//    //public boolean placeTakenTest(float xpos, float ypos) { }
//    
//    /**
//     * Sets the roof shift.
//     *
//     * @param shift the new roof shift
//     */
//    @Test
//    public void setRoofShift(int shift) {
//        
//    }
    
}
