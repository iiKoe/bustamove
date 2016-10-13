package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class BallGraphBreadthFirstConditionalIterator implements Iterator<Ball> {

    /** List of all objects the iterator needs to give. */
    private ArrayList<Ball> list = new ArrayList<Ball>();

    public BallGraphBreadthFirstConditionalIterator(UndirectedGraph<Ball, DefaultEdge> graph, Ball start) {

        //queue of balls to be processed
        Queue<Ball> queue = new LinkedList<Ball>();

        //Add the ball that is given as parameter
        if (!(start instanceof TopBall)) {
            list.add(start);
            queue.add(start);
        }
        if (!(start instanceof BombBall)) {
            //Process all the balls in the queue
            while (!queue.isEmpty()) {
                Ball qball = queue.remove();
                //investigate all the edges of the ball
                for (DefaultEdge e : graph.edgesOf(qball)) {
                    //Check target of the edge
                    Ball eball = graph.getEdgeTarget(e);
                    if (eball.isEqual(start) && !list.contains(eball)) {
                        queue.add(eball);
                        list.add(eball);
                    }
                    //check source of the edge
                    eball = graph.getEdgeSource(e);
                    if (eball.isEqual(start) && !list.contains(eball)) {
                        queue.add(eball);
                        list.add(eball);
                    }
                }
            }
        } else {
            Queue<Ball> ballsToCheckAdjacenBalls = new LinkedList<Ball>();
            ballsToCheckAdjacenBalls.add(start);
            while (!queue.isEmpty()) {
                Ball qball = queue.remove();
                for (DefaultEdge e : graph.edgesOf(qball)) {
                    //Check target of the edge
                    Ball eball = graph.getEdgeTarget(e);
                    if ((eball instanceof BombBall) && !list.contains(eball)) {
                        queue.add(eball);
                        ballsToCheckAdjacenBalls.add(eball);
                        list.add(eball);
                    }
                    //check source of the edge
                    eball = graph.getEdgeSource(e);
                    if ((eball instanceof BombBall) && !list.contains(eball)) {
                        queue.add(eball);
                        ballsToCheckAdjacenBalls.add(eball);
                        list.add(eball);
                    }
                }
            }

            while (!ballsToCheckAdjacenBalls.isEmpty() && list.size() > 1) {
                Ball qball = ballsToCheckAdjacenBalls.remove();
                //investigate all the edges of the ball
                for (DefaultEdge e : graph.edgesOf(qball)) {
                    //Check target of the edge
                    Ball eball = graph.getEdgeTarget(e);
                    Iterator<Ball> iterator = new BallGraphAdjacentIterator(graph, eball);

                    while (iterator.hasNext()) {
                        Ball adjacentBall = iterator.next();
                        if (!(adjacentBall instanceof BombBall)) {
                            BallGraphBreadthFirstConditionalIterator checkIterator = new BallGraphBreadthFirstConditionalIterator(graph, adjacentBall);
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

                    //check source of the edge
                    eball = graph.getEdgeSource(e);
                    iterator = new BallGraphAdjacentIterator(graph, eball);

                    while (iterator.hasNext()) {
                        Ball adjacentBall = iterator.next();
                        if (!(adjacentBall instanceof BombBall)) {
                            BallGraphBreadthFirstConditionalIterator checkIterator = new BallGraphBreadthFirstConditionalIterator(graph, adjacentBall);
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
            }
        }

    }

    @Override
    public boolean hasNext() {
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Ball next() {
        Ball ret = list.get(0);
        list.remove(0);
        return ret;
    }

    public int size() {
        return list.size();
    }

}
