package com.group66.game.cannon.ballgraph;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.Ball;

public class NullBallGraphBreadthFirstConditionalIterator extends BallGraphBreadthFirstConditionalIterator {

    /**
     * Instantiates the iterator
     * @param graph  which the iterator should iterate over
     * @param start where the iterator should start to iterate.
     */
    public NullBallGraphBreadthFirstConditionalIterator(UndirectedGraph<Ball, DefaultEdge> graph, Ball start) {

    }
}
