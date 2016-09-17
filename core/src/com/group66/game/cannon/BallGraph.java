/**
 * 
 */
package com.group66.game.cannon;

/**
 * @author Jeroen
 *
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.alg.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import com.badlogic.gdx.math.Rectangle;
import com.group66.game.settings.Config;

/**
 *  A class to keep track of all the connections between balls. It uses of a graph data structure.
 * @author Jeroen
 *
 */
public class BallGraph {
	
	/** top hitbox to detect wheter the balls are still connected. */
	private Rectangle topHitbox; 
	
	private Ball top;
	

	/** The graph where the balls and relations are stored in. */
	private UndirectedGraph<Ball, DefaultEdge> graph;
	
	
	/**
	 * Instantiates a new Ball graph.
	 * 
	 */
	public BallGraph() {
		graph = new SimpleGraph<Ball, DefaultEdge>(DefaultEdge.class);
		topHitbox = new Rectangle(0.0f, Config.BOUNCE_Y_MAX - 10, Config.WIDTH, 10.0f);
		top = new Ball(-1,9999,9999,0,0,0.0f);
		graph.addVertex(top);
		
	}

	/**
	 * Retrieves the number of balls in the graph
	 * 
	 * @return the number of balls in the graph
	 */
	public int numberOfBalls() {
		// -1 to compensate for the top vertex
		return graph.vertexSet().size() - 1;
	}

	/**
	 * Adds a ball to the graph
	 * @param insert the ball to be inserted
	 * 
	 */
	public void insertBall(Ball insert) {
		if (insert != null) {
			graph.addVertex(insert);
			if (this.topHitbox.overlaps(insert.getTopHitbox())) {
				System.out.println("Is connected to top");
				this.connectBalls(insert, top);
			}
			if (this.getBalls().size() > 0) {

				for (Ball e:this.getBalls()) {
					if (e != insert && insert.isNextTo(e.getNeighborBox())) {

						System.out.println("Balls connected");
						this.connectBalls(insert, e);
					}
				}
			}
		}
	}

	/**
	 * Removes a ball from the graph
	 * @param remove, the ball to be removed from the graph
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
	 * Gives the number of adjacent balls of the same color
	 * @param ball the ball of whose adjacent balls should be checked
	 * @return integer with the number of adjacent balls
	 */
	public int numberOfAdjacentBalls(Ball ball) {
		if (ball == null) {
			return 0;
		}
		return getAdjacentBalls(ball).size();
	}

	/**
	 * Gives a list of adjacent balls of the same color
	 * @param ball, the ball of whose adjacent balls should be checked
	 * @return ArrayList<Ball> a list of the adjacent balls
	 */
	public ArrayList<Ball> getAdjacentBalls(Ball ball) {
		if (ball == null) {
			return null;
		}
		//queue of balls to be processed
		Queue<Ball> Q = new LinkedList<Ball>();
		//List of adjacent balls
		ArrayList<Ball> ret = new ArrayList<Ball>();
		//Add the ball that is given as parameter
		Q.add(ball);
		ret.add(ball);
		//Process all the balls in the queue
		while (!Q.isEmpty()) {
			Ball t = Q.remove();
			//investigate all the edges of the ball
			for (DefaultEdge e:graph.edgesOf(t)) {
				//Check target of the edge
				Ball o = graph.getEdgeTarget(e);
				if (o.getColor() == ball.getColor() && !ret.contains(o)) {
					Q.add(o);
					ret.add(o);
				}
				//check source of the edge
				o = graph.getEdgeSource(e);
				if (o.getColor() == ball.getColor() && !ret.contains(o)) {
					Q.add(o);
					ret.add(o);
				}
			}
		}
		return ret;
	}

	/**
	 * This function provides a list of all the balls that are not in some way connected to the top of the screen
	 * @return an ArrayList<ball> of the balls that are not connected to the top
	 */
	public ArrayList<Ball> getFreeBalls() {
		return this.getFreeBalls(this.top);
	}
	
	/**
	 * This function provides a list of all the balls that are not in some way connected to the top of the screen
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
	 * @param exclude, a ball that will be excluded from the list. (meant for the top ball)
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
	 * This function return a list of all the balls in the graph
	 * 
	 * @return A list of all the balls in the graph
	 */
	public ArrayList<Ball> getBalls() {
		
		return getBalls(this.top);
	}

}
