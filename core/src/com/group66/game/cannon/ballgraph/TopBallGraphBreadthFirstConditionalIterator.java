package com.group66.game.cannon.ballgraph;

import java.util.ArrayList;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.Ball;
import com.group66.game.cannon.TopBall;

public class TopBallGraphBreadthFirstConditionalIterator implements BallGraphBreadthFirstConditionalIterator {

    /** List of all objects the iterator needs to give. */
    private ArrayList<Ball> list = new ArrayList<Ball>();

    /**
     * Instantiates the iterator
     * @param graph  which the iterator should iterate over
     * @param start where the iterator should start to iterate.
     */
    public TopBallGraphBreadthFirstConditionalIterator(UndirectedGraph<Ball, DefaultEdge> graph, TopBall start) {

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

}
