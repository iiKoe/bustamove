/**
 * 
 */
package com.group66.game.cannon.ballgraph;

import java.util.Iterator;

import com.group66.game.cannon.Ball;

public interface BallGraphBreadthFirstConditionalIterator extends Iterator<Ball> {


    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    @Override
    boolean hasNext();

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    @Override
    Ball next();

    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */@Override 
     void remove();

     /**
      * Get size
      * @return the size
      */
    int size();

}
