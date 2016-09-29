package com.group66.game.cannon;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.BustaMove;
import com.group66.game.cannon.Ball.BallType;
import com.group66.game.logging.MessageType;
import com.group66.game.settings.Config;

/**
 * The Class BallGraph.
 */
public class BallGraph {

    /** top hitbox to detect whether the balls are still connected. */
    private Rectangle topHitbox; 

    /** The top. */
    private TopBall top;


    /** The graph where the balls and relations are stored in. */
    private UndirectedGraph<Ball, DefaultEdge> graph;


    /**
     * Instantiates a new Ball graph.
     *
     * @param roofHitbox the roof hitbox
     */
    public BallGraph(Rectangle roofHitbox) {
        graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
        //topHitbox = new Rectangle(0.0f, Config.BOUNCE_Y_MAX - 10, Config.WIDTH, 10.0f);
        this.topHitbox = roofHitbox;
        top = new TopBall(BallType.BLUE,9999,9999,0,0,0.0f);
        graph.addVertex(top);

    }

    /**
     * Retrieves the number of balls in the graph.
     *
     * @return the number of balls in the graph
     */
    public int numberOfBalls() {
        // -1 to compensate for the top vertex
        return graph.vertexSet().size() - 1;
    }

    /**
     * Adds a ball to the graph.
     *
     * @param insert the ball to be inserted
     */
    public void insertBall(Ball insert) {
        if (insert != null) {
            graph.addVertex(insert);
            if (this.topHitbox.overlaps(insert.getTopHitbox())) {
                //System.out.println("Is connected to top");
                this.connectBalls(insert, top);
            }
            if (this.getBalls().size() > 0) {

                for (Ball e:this.getBalls()) {
                    if (e != insert && insert.isNextTo(e.getNeighborBox())) {

                        //System.out.println("Balls connected");
                        this.connectBalls(insert, e);
                    }
                }
            }
        }
    }

    /**
     * Removes a ball from the graph.
     *
     * @param remove the ball to be removed from the graph
     */
    public void removeBall(Ball remove) {
        graph.removeVertex(remove);
    }

    /**
     * This function creates a connection between to balls. (An edge in the graph)
     * @param ball1 ball that needs to be connected to ball2
     * @param ball2 ball that needs to be connected to ball1
     */
    public void connectBalls(Ball ball1, Ball ball2) {
        graph.addEdge(ball1, ball2);
    }

    /**
     * Gives the number of adjacent balls of the same color.
     *
     * @param ball the ball of whose adjacent balls should be checked
     * @return integer with the number of adjacent balls
     */
    public int numberOfAdjacentColoredBalls(Ball ball) {
        if (ball == null) {
            return 0;
        }
        return getAdjacentColoredBalls(ball).size();
    }

    /**
     * Gives a list of adjacent balls of the same color.
     *
     * @param ball the ball of whose adjacent balls should be checked
     * @return ArrayList<Ball> a list of the adjacent balls
     */
    public ArrayList<Ball> getAdjacentColoredBalls(Ball ball) {
        if (ball == null) {
            return null;
        }
        //queue of balls to be processed
        Queue<Ball> queue = new LinkedList<Ball>();
        //List of adjacent balls
        ArrayList<Ball> ret = new ArrayList<Ball>();
        //Add the ball that is given as parameter
        if (ball != top) {
            ret.add(ball);
            queue.add(ball);
        }
        if (ball.getType() != BallType.BOMB) {
            //Process all the balls in the queue
            while (!queue.isEmpty()) {
                Ball qball = queue.remove();
                //investigate all the edges of the ball
                for (DefaultEdge e:graph.edgesOf(qball)) {
                    //Check target of the edge
                    Ball eball = graph.getEdgeTarget(e);
                    if (eball.isEqual(ball) && !ret.contains(eball)) {
                        queue.add(eball);
                        ret.add(eball);
                    }
                    //check source of the edge
                    eball = graph.getEdgeSource(e);
                    if (eball.isEqual(ball) && !ret.contains(eball)) {
                        queue.add(eball);
                        ret.add(eball);
                    }
                }
            }
        } else {
            Queue<Ball> ballsToCheckAdjacenBalls = new LinkedList<Ball>();
            ballsToCheckAdjacenBalls.add(ball);
            while (!queue.isEmpty()) {
                Ball qball = queue.remove();
                for (DefaultEdge e:graph.edgesOf(qball)) {
                    //Check target of the edge
                    Ball eball = graph.getEdgeTarget(e);
                    if (eball.getType() == BallType.BOMB && !ret.contains(eball)) {
                        queue.add(eball);
                        ballsToCheckAdjacenBalls.add(eball);
                        ret.add(eball);
                    }
                    //check source of the edge
                    eball = graph.getEdgeSource(e);
                    if (eball.getType() == BallType.BOMB && !ret.contains(eball)) {
                        queue.add(eball);
                        ballsToCheckAdjacenBalls.add(eball);
                        ret.add(eball);
                    }
                }
            }
            
            while (!ballsToCheckAdjacenBalls.isEmpty() && ret.size() > 1) {
                Ball qball = ballsToCheckAdjacenBalls.remove();
                ArrayList<Ball> ballsToCheck = new ArrayList<Ball>();
                //investigate all the edges of the ball
                for (DefaultEdge e:graph.edgesOf(qball)) {
                    //Check target of the edge
                    Ball eball = graph.getEdgeTarget(e);
                    ballsToCheck = this.getAdjacentBalls(eball);
                    for (Ball adjacentBall:ballsToCheck) {
                        ArrayList<Ball> ballsToCheckColor = new ArrayList<Ball>();
                        if (adjacentBall.getType() != BallType.BOMB) {
                            ballsToCheckColor = this.getAdjacentColoredBalls(adjacentBall);
                            if (ballsToCheckColor.size() >= 2) {
                                for (Ball coloredBall:ballsToCheckColor) {
                                    if (!ret.contains(coloredBall)) {
                                        ret.add(coloredBall);
                                    }
                                }
                            }
                        }
                    }
                    //check source of the edge
                    eball = graph.getEdgeSource(e);
                    ballsToCheck = this.getAdjacentBalls(eball);
                    for (Ball adjacentBall:ballsToCheck) {
                        ArrayList<Ball> ballsToCheckColor = new ArrayList<Ball>();
                        if (adjacentBall.getType() != BallType.BOMB) {
                            ballsToCheckColor = this.getAdjacentColoredBalls(adjacentBall);
                            if (ballsToCheckColor.size() >= 2) {
                                for (Ball coloredBall:ballsToCheckColor) {
                                    if (!ret.contains(coloredBall)) {
                                        ret.add(coloredBall);
                                    }
                                }
                            }
                        }
                    }
                }
            }
            BustaMove.logger.log(MessageType.Info, "GetAdjacentColoredBalls(BOMB) return size: " + ret.size());
        }
        return ret;
    }

    /**
     * Gets the adjacentballs
     * 
     * @param ball
     * @return List of adjacentballs
     */
    public ArrayList<Ball> getAdjacentBalls(Ball ball) {
        ArrayList<Ball> ret = new ArrayList<Ball>();

        for (DefaultEdge e:graph.edgesOf(ball)) {
            //Check target of the edge
            Ball eball = graph.getEdgeTarget(e);
            if (eball.isEqual(ball) && !ret.contains(eball)) {
                ret.add(eball);
            }
            //check source of the edge
            eball = graph.getEdgeSource(e);
            if (eball.isEqual(ball) && !ret.contains(eball)) {
                ret.add(eball);
            }
        }        
        return ret;
    }

    /**
     * This function provides a list of all the balls that are not in some way connected to the top of the screen.
     *
     * @return an ArrayList<ball> of the balls that are not connected to the top
     */
    public ArrayList<Ball> getFreeBalls() {
        return this.getFreeBalls(this.top);
    }

    /**
     * This function provides a list of all the balls that are not in some way connected to the top of the screen.
     *
     * @param top the top is represented in the graph as a ball. this parameter is the ball that represents the top
     * @return an ArrayList<Ball> of the balls that are not connected to the top ball
     */
    public ArrayList<Ball> getFreeBalls(Ball top) {
        ConnectivityInspector<Ball, DefaultEdge> inspector = 
                new ConnectivityInspector<Ball, DefaultEdge>(graph);
        ArrayList<Ball> ret = new ArrayList<Ball>();
        if (inspector.connectedSets().size() == 1) {
            return ret;
        }
        for (Set<Ball> e:inspector.connectedSets()) {
            if (!e.contains(top)) {
                for (Ball f:e) {
                    ret.add(f);
                }
            }
        }
        return ret;
    }

    /**
     * This function returns a list of all the balls in the graph.
     * @param exclude a ball that will be excluded from the list. (meant for the top ball)
     * @return A list of all the balls in the graph
     */
    public ArrayList<Ball> getBalls(Ball exclude) {
        ArrayList<Ball> ret = new ArrayList<Ball>();
        for (Ball e:graph.vertexSet()) {
            if (e != exclude) {
                ret.add(e);
            }
        }
        return ret;
    }

    /**
     * This function return a list of all the balls in the graph.
     *
     * @return A list of all the balls in the graph
     */
    public ArrayList<Ball> getBalls() {

        return getBalls(this.top);
    }

    /**
     * Check is a ball location is taken.
     *
     * @param xpos the x coordinate
     * @param ypos the y coordinate
     * @return true, if successful
     */
    public boolean placeTaken(float xpos, float ypos) {
        ArrayList<Ball> checkb = getBalls(this.top);
        for (Ball cb : checkb) {
            if (Math.abs(cb.getX() - xpos) < Config.BALL_RAD / 1 
                    && Math.abs(cb.getY() - ypos) < Config.BALL_RAD / 1) {
                return true;
            }
        }
        return false;
    }
}
