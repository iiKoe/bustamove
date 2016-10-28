/**
 * 
 */
package com.group66.game.cannon.ballgraph;

import java.util.ArrayList;
import java.util.Iterator;

import com.group66.game.cannon.Ball;

public abstract class BallGraphBreadthFirstConditionalIterator implements Iterator<Ball> {

    /** List of all objects the iterator needs to give. */
    protected ArrayList<Ball> list = new ArrayList<Ball>();

    /* (non-Javadoc)
     * @see java.util.Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        if (list.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#next()
     */
    @Override
    public Ball next() {
        Ball ret = list.get(0);
        list.remove(0);
        return ret;
    }

    /* (non-Javadoc)
     * @see java.util.Iterator#remove()
     */@Override 
     public void remove() {
         throw new UnsupportedOperationException();
     }

     /**
      * Get size
      * @return the size
      */
    public int size() {
        return list.size();
    }

}
