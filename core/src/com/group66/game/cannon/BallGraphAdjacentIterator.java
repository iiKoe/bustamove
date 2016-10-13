package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.Iterator;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

public class BallGraphAdjacentIterator implements Iterator<Ball> {

    /** List of all objects the iterator needs to give. */
    private ArrayList<Ball> list = new ArrayList<Ball>();
    
    public BallGraphAdjacentIterator(UndirectedGraph<Ball, DefaultEdge> graph, Ball start) {
        // TODO Auto-generated constructor stub
        for (DefaultEdge e : graph.edgesOf(start)) {
            //Check target of the edge
            Ball eball = graph.getEdgeTarget(e);
            if (eball.isEqual(start) && !list.contains(eball) && !(eball instanceof TopBall)) {
                list.add(eball);
            }
            //check source of the edge
            eball = graph.getEdgeSource(e);
            if (eball.isEqual(start) && !list.contains(eball) && !(eball instanceof TopBall)) {
                list.add(eball);
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

}
