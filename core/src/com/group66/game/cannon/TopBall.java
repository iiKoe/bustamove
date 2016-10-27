package com.group66.game.cannon;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.ballgraph.BallGraphBreadthFirstConditionalIterator;
import com.group66.game.cannon.ballgraph.TopBallGraphBreadthFirstConditionalIterator;

public class TopBall extends Ball {

    /**
     * Creates a new top ball object
     * @param type
     * @param xpos
     * @param ypos
     * @param speed
     * @param angle
     */
    public TopBall(BallType type, float xpos, float ypos, int speed, float angle) {
        super(type, xpos, ypos, speed, angle);
    }

    /**
     * Checks if two balls of of the same type.
     *
     * @param ball to compare
     * @return Boolean whether balls are of the same type
     */
    public Boolean isEqual(Ball ball) {
        return false;
    }
    
    /**
     * create iterator
     * @param graph in which the ball is situated
     * @return the iterator
     */
    public BallGraphBreadthFirstConditionalIterator createBallGraphBreadthFirstConditionalIterator(UndirectedGraph<Ball, 
            DefaultEdge> graph) {
        return new TopBallGraphBreadthFirstConditionalIterator(graph, this);
    }

}
