package com.group66.game.cannon.ballgraph;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.Ball;

public class NullBallGraphBreadthFirstConditionalIterator implements BallGraphBreadthFirstConditionalIterator {

    /**
     * Instantiates the iterator
     * @param graph  which the iterator should iterate over
     * @param start where the iterator should start to iterate.
     */
    public NullBallGraphBreadthFirstConditionalIterator(UndirectedGraph<Ball, DefaultEdge> graph, Ball start) {

    }

    /**
     * Returns if the iterator has a next object
     * s
     * @return if the iterator has another object
     */
    @Override
    public boolean hasNext() {
        return false;
    }

    /**
     * returns the next object
     * 
     * @return The next object of the iterator
     */
    @Override
    public Ball next() {
        return null;
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
        return 0;
    }

}
