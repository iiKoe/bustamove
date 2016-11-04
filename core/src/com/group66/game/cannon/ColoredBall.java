package com.group66.game.cannon;

import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.group66.game.cannon.ballgraph.BallGraphBreadthFirstConditionalIterator;
import com.group66.game.cannon.ballgraph.ColoredBallGraphBreadthFirstConditionalIterator;
/**
 * A basic Ball class.
 */
public class ColoredBall extends Ball {

    /**
     * Instantiates a new ball.
     * 
     * @param type the type
     * @param xpos the x coordinate of the Ball
     * @param ypos the y coordinate of the Ball
     * @param speed the speed of the Ball
     * @param angle the angle of the Ball
     */
    public ColoredBall(BallType type, float xpos, float ypos, int speed, float angle) {
        super(type, xpos, ypos, speed, angle);
    }
    
    /**
     * Checks if two balls have the same color or are of the same type.
     * 
     * @param ball the ball which type needs to be compared
     * @return Boolean value that indicates whether the types are the same
     */
    public Boolean isEqual(Ball ball) {
        return ball != null && ball instanceof ColoredBall && this.getType().equals(ball.getType());
    }
    
    /**
     * Draw the Ball.
     * 
     * @param batch the batch used to draw with
     * @param delta the delta since the last draw
     */
    public void draw(SpriteBatch batch, float delta) {
        if (batch != null) {
            draw(batch, this.getType().getPopAnimation(), delta);
        }
    }
    
    /**
     * Start the Pop animation.
     */
    public void popStart() {
        super.popStart(this.getType().getPopAnimation());
    }
    
    /**
     * create iterator
     * @param graph in which the ball is situated
     * @return the iterator
     */
    public BallGraphBreadthFirstConditionalIterator createBallGraphBreadthFirstConditionalIterator(
            UndirectedGraph<Ball, DefaultEdge> graph) {
        return new ColoredBallGraphBreadthFirstConditionalIterator(graph, this);
    }
}
