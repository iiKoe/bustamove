package com.group66.game.cannon.ballgraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.Ball;
import com.group66.game.cannon.ColoredBall;

public class ColoredBallGraphBreadthFirstConditionalIterator implements BallGraphBreadthFirstConditionalIterator {

    /** List of all objects the iterator needs to give. */
    private ArrayList<Ball> list = new ArrayList<Ball>();
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
     * Returns if the iterator has a next object
     * 
     * @return if the iterator has another object
     */
    @Override
    public boolean hasNext() {
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * returns the next object
     * 
     * @return The next object of the iterator
     */
    @Override
    public Ball next() {
        Ball ret = list.get(0);
        list.remove(0);
        return ret;
    }
    
    /**
     * remove is not supported by the iterator
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    /**
     * current size of the iterator
     * 
     * @return the size of the iterator
     */
    public int size() {
        return list.size();
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
