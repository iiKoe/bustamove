package com.group66.game.cannon.ballgraph;

import java.util.Iterator;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import com.group66.game.cannon.Ball;
import com.group66.game.cannon.BallType;
import com.group66.game.cannon.BombBall;
import com.group66.game.cannon.ColoredBall;
import com.group66.game.cannon.TopBall;
import com.group66.game.settings.Config;

public class BallGraphAdjacentTest {
    
    @Test
    public void creationTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        Ball start = new TopBall(BallType.BLUE,0,0,0,0.0f);
        graph.addVertex(start);
        new BallGraphAdjacentIterator(graph, start);
        new BallGraphAdjacentIterator(graph, null);
        new BallGraphAdjacentIterator(null, start);
    }
    
    @Test
    public void nullTest() {
        new BallGraphAdjacentIterator(null, null);
    }
    
    @Test
    public void nextTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        Ball start = new TopBall(BallType.BLUE,0,0,0,0.0f);
        graph.addVertex(start);
        
        Iterator<Ball> iterator = new BallGraphAdjacentIterator(graph, start);
        iterator.hasNext();
        
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.BLUE, Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball1);

        Ball ball2 = new ColoredBall(BallType.BLUE, -Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball2);
        
        iterator = new BallGraphAdjacentIterator(graph, start);
        //assertTrue(iterator.hasNext());
        iterator.hasNext();
        iterator.next();
    }
    
    @Test
    public void nextTest2() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        Ball start = new TopBall(BallType.BLUE,0,0,0,0.0f);
        graph.addVertex(start);
        
        Iterator<Ball> iterator = new BallGraphAdjacentIterator(graph, start);
        iterator.hasNext();
        
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.BLUE, Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball1);

        Ball ball2 = new ColoredBall(BallType.BLUE, -Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball2);
        
        iterator = new BallGraphAdjacentIterator(graph, start);
        //assertTrue(iterator.hasNext());
        iterator.hasNext();
        iterator.next();
        
        graph.addEdge(ball1, start);
        
        iterator = new BallGraphAdjacentIterator(graph, ball1);
    }
    
    
    @Test
    public void bombTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        Ball start = new BombBall(0, Config.HEIGHT - Config.BORDER_SIZE_TOP, 0, 0.0f);
        graph.addVertex(start);
        
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.BLUE, Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball1);

        Ball ball2 = new ColoredBall(BallType.BLUE, -Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball2);
        
        @SuppressWarnings("unused")
        Iterator<Ball> iterator = new BallGraphAdjacentIterator(graph, start);        
    }
    
    @Test
    public void removeTest() {
        Iterator<Ball> iterator = new BallGraphAdjacentIterator(null, null);
        try {
            iterator.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
