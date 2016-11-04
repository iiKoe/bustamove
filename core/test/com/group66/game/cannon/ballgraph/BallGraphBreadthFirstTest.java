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

public class BallGraphBreadthFirstTest {
    @Test
    public void creationTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        Ball start = new TopBall(BallType.BLUE,0,0,0,0.0f);
        graph.addVertex(start);
        //new BallGraphBreadthFirstConditionalIterator(graph, start);
        start.createBallGraphBreadthFirstConditionalIterator(graph);
    }
    
    @Test
    public void nullTest() {
        //new BallGraphBreadthFirstConditionalIterator(null, null);
        
    }
    
    @Test
    public void nextTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        Ball start = new TopBall(BallType.BLUE,0,0,0,0.0f);
        graph.addVertex(start);
        
        Iterator<Ball> iterator = start.createBallGraphBreadthFirstConditionalIterator(graph);
        iterator.hasNext();
        
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.BLUE, Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball1);

        Ball ball2 = new ColoredBall(BallType.BLUE, Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball2);
        graph.addEdge(ball1, ball2);
        
        iterator = start.createBallGraphBreadthFirstConditionalIterator(graph);
        //assertTrue(iterator.hasNext());
        iterator.hasNext();
        //iterator.next();
    }
    
    @Test
    public void bombTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);

        Ball start = new TopBall(BallType.BLUE,0,0,0,0.0f);
        graph.addVertex(start);

        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball0 = new BombBall(0, ypos - Config.BALL_DIAM, 0, 0.0f);
        graph.addVertex(ball0);
        
        Ball ball1 = new ColoredBall(BallType.BLUE, Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball1);

        Ball ball2 = new ColoredBall(BallType.RED, -Config.BALL_RAD, ypos, 0, 0.0f);
        graph.addVertex(ball2);
        
        Iterator<Ball> iterator = ball0.createBallGraphBreadthFirstConditionalIterator(graph);        
    }
}
