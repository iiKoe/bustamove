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
        } /*else {
                Queue<Ball> ballsToCheckAdjacenBalls = new LinkedList<Ball>();
                ballsToCheckAdjacenBalls.add(start);
                while (!queue.isEmpty()) {
                    Ball qball = queue.remove();
                    for (DefaultEdge e : graph.edgesOf(qball)) {
                        //Check target of the edge
                        Ball eball = graph.getEdgeTarget(e);
                        if (eball.getType() == BallType.BOMB && !list.contains(eball)) {
                            queue.add(eball);
                            ballsToCheckAdjacenBalls.add(eball);
                            list.add(eball);
                        }
                        //check source of the edge
                        eball = graph.getEdgeSource(e);
                        if (eball.getType() == BallType.BOMB && !list.contains(eball)) {
                            queue.add(eball);
                            ballsToCheckAdjacenBalls.add(eball);
                            list.add(eball);
                        }
                    }
                }

                while (!ballsToCheckAdjacenBalls.isEmpty() && list.size() > 1) {
                    Ball qball = ballsToCheckAdjacenBalls.remove();
                    ArrayList<Ball> ballsToCheck = new ArrayList<Ball>();
                    //investigate all the edges of the ball
                    for (DefaultEdge e : graph.edgesOf(qball)) {
                        //Check target of the edge
                        Ball eball = graph.getEdgeTarget(e);
                        ballsToCheck = getAdjacentBalls(eball);
                        for (Ball adjacentBall : ballsToCheck) {
                            ArrayList<Ball> ballsToCheckColor = new ArrayList<Ball>();
                            if (adjacentBall.getType() != BallType.BOMB) {
                                ballsToCheckColor = getAdjacentColoredBalls(adjacentBall);
                                if (ballsToCheckColor.size() >= 2) {
                                    for (Ball coloredBall:ballsToCheckColor) {
                                        if (!list.contains(coloredBall)) {
                                            list.add(coloredBall);
                                        }
                                    }
                                }
                            }
                        }
                        //check source of the edge
                        eball = graph.getEdgeSource(e);
                        ballsToCheck = getAdjacentBalls(eball);
                        for (Ball adjacentBall : ballsToCheck) {
                            ArrayList<Ball> ballsToCheckColor = new ArrayList<Ball>();
                            if (adjacentBall.getType() != BallType.BOMB) {
                                ballsToCheckColor = getAdjacentColoredBalls(adjacentBall);
                                if (ballsToCheckColor.size() >= 2) {
                                    for (Ball coloredBall:ballsToCheckColor) {
                                        if (!list.contains(coloredBall)) {
                                            list.add(coloredBall);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                BustaMove.logger.log(MessageType.Info, "GetAdjacentColoredBalls(BOMB) return size: " + list.size());
            }*/

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

}
