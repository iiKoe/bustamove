package com.group66.game.cannon.ballgraph;

import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.Ball;
import com.group66.game.cannon.ColoredBall;

public class ColoredBallGraphBreadthFirstConditionalIterator extends BallGraphBreadthFirstConditionalIterator {

    /** queue of balls to be processed. */
    Queue<Ball> queue = new LinkedList<Ball>();
    /** The start ball for the iterator. */
    ColoredBall startBall;
    /** The graph where the iterator iterates over. */
    UndirectedGraph<Ball, DefaultEdge> graph;

    
    /**
     * Instantiates the iterator
     * @param graph  which the iterator should iterate over
     * @param start where the iterator should start to iterate.
     */
    public ColoredBallGraphBreadthFirstConditionalIterator(UndirectedGraph<Ball, DefaultEdge> graph, 
            ColoredBall start) {
        startBall = start;
        this.graph = graph;
        list.add(start);
        queue.add(start);

        //Process all the balls in the queue
        while (!queue.isEmpty()) {
            Ball qball = queue.remove();
            //investigate all the edges of the ball
            addEqualBallsOnEdges(qball);            
        }
    }
    
    /**
     * adds al the connected equal balls to the queue and the list
     * @param ball
     */
    private void addEqualBallsOnEdges(Ball ball) {
        for (DefaultEdge e : graph.edgesOf(ball)) {
            //Check target of the edge
            Ball eball = graph.getEdgeTarget(e);
            if (eball.isEqual(startBall) && !list.contains(eball)) {
                queue.add(eball);
                list.add(eball);
            }
            //check source of the edge
            eball = graph.getEdgeSource(e);
            if (eball.isEqual(startBall) && !list.contains(eball)) {
                queue.add(eball);
                list.add(eball);
            }
        }
    }

}
