package com.group66.game.cannon.ballgraph;

import static org.junit.Assert.assertEquals;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.junit.Test;

import com.group66.game.cannon.Ball;
import com.group66.game.cannon.BallType;
import com.group66.game.cannon.BombBall;
import com.group66.game.cannon.ColoredBall;
import com.group66.game.cannon.ballgraph.BallGraph;
import com.group66.game.cannon.ballgraph.BallGraphBreadthFirstConditionalIterator;
import com.group66.game.cannon.ballgraph.ColoredBallGraphBreadthFirstConditionalIterator;
import com.group66.game.settings.Config;

public class BreadthFirstConditionalIteratorTest {

    /**
     * iterator creation test.
     */
    @Test
    public void breadthFirstConditionalIteratorTest() {
        ColoredBall ball1 = new ColoredBall(BallType.BLUE, 0, 0, 0, 0);
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        graph.addVertex(ball1);
        new ColoredBallGraphBreadthFirstConditionalIterator(graph, ball1);
        new ColoredBallGraphBreadthFirstConditionalIterator(null, null);
    }
    
    
    @Test
    public void getIteratorTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        ColoredBall ball1 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball1);
        ColoredBallGraphBreadthFirstConditionalIterator iterator = new ColoredBallGraphBreadthFirstConditionalIterator(graph, ball1);
        assertEquals(iterator.size(), 1);
    }
    /**
     * Basic Ball testing 1:
     * Tests ball insertion, ball type creation, numberOfBalls function,
     * ball removal test
     */
    @Test
    public void basicBallGraphIteratorTest() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        assertEquals(graph.vertexSet().size(), 0);
        Ball ball1 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball1);
        Ball ball2 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball2);
        graph.addEdge(ball1, ball2);
        Ball ball3 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball3);
        graph.addEdge(ball1, ball3);
        graph.addEdge(ball2, ball3);
        Ball ball4 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball4);
        graph.addEdge(ball1, ball4);
        graph.addEdge(ball2, ball4);
        graph.addEdge(ball3, ball4);
        assertEquals(4, graph.vertexSet().size());
        Ball ball22 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball22);
        graph.addEdge(ball1, ball22);
        graph.addEdge(ball2, ball22);
        graph.addEdge(ball3, ball22);
        graph.addEdge(ball4, ball22);
        Ball ball23 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball23);
        graph.addEdge(ball1, ball23);
        graph.addEdge(ball2, ball23);
        graph.addEdge(ball3, ball23);
        graph.addEdge(ball4, ball23);
        graph.addEdge(ball22, ball23);
        assertEquals(6, graph.vertexSet().size());
        BallGraphBreadthFirstConditionalIterator iterator = ball22.createBallGraphBreadthFirstConditionalIterator(graph);
        assertEquals(6, iterator.size());
        iterator = ball1.createBallGraphBreadthFirstConditionalIterator(graph);
        try{
            iterator.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Basic ball graph iterator test 3.
     */
    @Test
    public void basicBallGraphIteratorTest3() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        assertEquals(graph.vertexSet().size(), 0);
        
        Ball ball22 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball22);
       
        Ball ball23 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball23);
        
        graph.addEdge(ball23, ball22);
        Ball ball24 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball24);
        
        graph.addEdge(ball22, ball24);
        graph.addEdge(ball24,  ball23);
        assertEquals(3, graph.vertexSet().size());
        BallGraphBreadthFirstConditionalIterator iterator = ball22.createBallGraphBreadthFirstConditionalIterator(graph);
        assertEquals(3, iterator.size());
        BallGraphBreadthFirstConditionalIterator iterator2 = ball23.createBallGraphBreadthFirstConditionalIterator(graph);
        assertEquals(3, iterator2.size());
        BombBallGraphBreadthFirstConditionalIterator iterator3 = new BombBallGraphBreadthFirstConditionalIterator(null, null);
        assertEquals(0, iterator3.size());
        BombBallGraphBreadthFirstConditionalIterator iterator4 = new BombBallGraphBreadthFirstConditionalIterator(graph, null);
        assertEquals(0, iterator4.size());
        BombBallGraphBreadthFirstConditionalIterator iterator5 = new BombBallGraphBreadthFirstConditionalIterator(null, (BombBall)ball22);
        assertEquals(0, iterator5.size());
        
        try{
            iterator.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Basic ball graph iterator test 2.
     */
    @Test
    public void basicBallGraphIteratorTest2() {
        UndirectedGraph<Ball, DefaultEdge> graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        assertEquals(graph.vertexSet().size(), 0);
        Ball ball1 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball1);
        Ball ball2 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball2);
        graph.addEdge(ball1, ball2);
        Ball ball3 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball3);
        graph.addEdge(ball1, ball3);
        graph.addEdge(ball2, ball3);
        Ball ball4 = new ColoredBall(BallType.BLUE, 10.0f, 5.0f, 0, 0);
        graph.addVertex(ball4);
        graph.addEdge(ball1, ball4);
        graph.addEdge(ball2, ball4);
        graph.addEdge(ball3, ball4);
        assertEquals(4, graph.vertexSet().size());
        Ball ball22 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball22);
        graph.addEdge(ball1, ball22);
        graph.addEdge(ball2, ball22);
        graph.addEdge(ball3, ball22);
        graph.addEdge(ball4, ball22);
        Ball ball23 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball23);
        graph.addEdge(ball1, ball23);
        graph.addEdge(ball2, ball23);
        graph.addEdge(ball3, ball23);
        graph.addEdge(ball4, ball23);
        graph.addEdge(ball23, ball22);
        Ball ball24 = new BombBall(10.0f, 5.0f, 0, 0);
        graph.addVertex(ball24);
        graph.addEdge(ball1, ball24);
        graph.addEdge(ball2, ball24);
        graph.addEdge(ball3, ball24);
        graph.addEdge(ball4, ball24);
        graph.addEdge(ball22, ball24);
        graph.addEdge(ball24,  ball23);
        assertEquals(7, graph.vertexSet().size());
        BallGraphBreadthFirstConditionalIterator iterator = ball22.createBallGraphBreadthFirstConditionalIterator(graph);
        assertEquals(7, iterator.size());
        BallGraphBreadthFirstConditionalIterator iterator2 = ball4.createBallGraphBreadthFirstConditionalIterator(graph);
        assertEquals(4, iterator2.size());
        BombBallGraphBreadthFirstConditionalIterator iterator3 = new BombBallGraphBreadthFirstConditionalIterator(null, null);
        assertEquals(0, iterator3.size());
        
        try{
            iterator.remove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Advanced ball insertion test:
     * Tests null ball insertion, top row ball insertion
     */
    @Test
    public void insertBallTest() {
        BallGraph testBallGraph = new BallGraph();
        float ypos = Config.HEIGHT - Config.BORDER_SIZE_TOP - Config.BALL_RAD;
        Ball ball1 = new ColoredBall(BallType.GREEN, 50, ypos, 0, 0);
        testBallGraph.insertBall(ball1);
        assertEquals(testBallGraph.numberOfBalls(), 1);
        Ball ball2 = null;
        testBallGraph.insertBall(ball2);
        assertEquals(testBallGraph.numberOfBalls(), 1);
      }
    
    

}
