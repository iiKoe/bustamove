package com.group66.game.cannon;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.ballgraph.BallGraphBreadthFirstConditionalIterator;
import com.group66.game.cannon.ballgraph.BombBallGraphBreadthFirstConditionalIterator;

/**
 * 
 * @author Jeroen
 *
 */
public class BombBall extends Ball {

    /**
     * Creates a new BombBall object
     * @param xpos
     * @param ypos
     * @param speed
     * @param angle
     */
    public BombBall(float xpos, float ypos, int speed, float angle) {
        super(BallType.BOMB, xpos, ypos, speed, angle);
    }
    
    
    /**
     * Checks if the balls are equal
     */
    public Boolean isEqual(Ball ball) {
        return ball != null && ball.getType().equals(this.getType());
    }
    
    /**
     * create iterator
     * @param graph in which the ball is situated
     * @return the iterator
     */
    public BallGraphBreadthFirstConditionalIterator createBallGraphBreadthFirstConditionalIterator(
            UndirectedGraph<Ball, DefaultEdge> graph) {
        return new BombBallGraphBreadthFirstConditionalIterator(graph, this);
    }

}
