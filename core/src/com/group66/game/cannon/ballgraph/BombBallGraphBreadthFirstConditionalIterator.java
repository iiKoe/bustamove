package com.group66.game.cannon.ballgraph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.group66.game.cannon.Ball;
import com.group66.game.cannon.BombBall;
import com.group66.game.cannon.ColoredBall;

public class BombBallGraphBreadthFirstConditionalIterator extends BallGraphBreadthFirstConditionalIterator {

    /** queue of balls to be processed. */
    Queue<Ball> queue = new LinkedList<Ball>();
    
    Queue<Ball> ballsToCheckAdjacenBalls = new LinkedList<Ball>();
    /** The start ball for the iterator. */
    BombBall startBall;
    /** The graph where the iterator iterates over. */
    UndirectedGraph<Ball, DefaultEdge> graph;
    
    /**
     * Instantiates the iterator
     * @param graph  which the iterator should iterate over
     * @param start where the iterator should start to iterate.
     */
    public BombBallGraphBreadthFirstConditionalIterator(UndirectedGraph<Ball, DefaultEdge> graph, BombBall start) {
        startBall = start;
        this.graph = graph;
        
        //Add the ball that is given as parameter
        list.add(start);
        queue.add(start);

        ballsToCheckAdjacenBalls.add(start);
        while (!queue.isEmpty()) {
            Ball qball = queue.remove();
            addEqualBombBallsOnEdges(qball);
        }

        while (!ballsToCheckAdjacenBalls.isEmpty() && list.size() > 1) {
            Ball qball = ballsToCheckAdjacenBalls.remove();
            //investigate all the edges of the ball
            investigateEdgesOfBombBall(qball);
        }

    }

    /**
     * adds all the connected bomb balls to the queue and the list
     * @param qball
     */
    private void addEqualBombBallsOnEdges(Ball qball) {
        for (DefaultEdge e : graph.edgesOf(qball)) {
            //Check target of the edge
            Ball eball = graph.getEdgeTarget(e);
            if (eball instanceof BombBall && !list.contains(eball)) {
                queue.add(eball);
                ballsToCheckAdjacenBalls.add(eball);
                list.add(eball);
            }
            //check source of the edge
            eball = graph.getEdgeSource(e);
            if (eball instanceof BombBall && !list.contains(eball)) {
                queue.add(eball);
                ballsToCheckAdjacenBalls.add(eball);
                list.add(eball);
            }
        }
    }
    
    /**
     * checks all the balls adjacent to a bomb ball
     * @param ball the bomb ball to investigate
     */
    private void investigateEdgesOfBombBall(Ball ball) {
        for (DefaultEdge e : graph.edgesOf(ball)) {
            //Check target of the edge
            Ball eball = graph.getEdgeTarget(e);
            getAllAdjacentBalls(eball);

            //check source of the edge
            eball = graph.getEdgeSource(e);
            getAllAdjacentBalls(eball);
        }

    }
    
    /**
     * Gets all the balls adjacent to the given ball.
     * @param ball 
     */
    private void getAllAdjacentBalls(Ball ball) {
        Iterator<Ball> iterator = new BallGraphAdjacentIterator(graph, ball);

        while (iterator.hasNext()) {
            Ball adjacentBall = iterator.next();
            checkAndAddColoredBalls(adjacentBall);
        }
    }
    
    private void checkAndAddColoredBalls(Ball ball) {
        if (ball instanceof ColoredBall) {
            BallGraphBreadthFirstConditionalIterator checkIterator = 
                    ball.createBallGraphBreadthFirstConditionalIterator(graph);
            if (checkIterator.size() >= 2) {
                while (checkIterator.hasNext()) {
                    Ball next = checkIterator.next();
                    if (!list.contains(next)) {
                        list.add(next);
                    }            
                }
            }
        }
    }
}