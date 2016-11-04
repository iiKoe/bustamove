package com.group66.game.helpers;

public class HighScoreItem implements Comparable<HighScoreItem> {
    private final String name, date;
    private final int score;
    
    /**
     * Constructor for a high score item
     * @param name Name of the player
     * @param date Date when the score was achieved
     * @param score Score the player got
     */
    public HighScoreItem(String name, String date, int score) {
        this.name = name;
        this.date = date;
        this.score = score;
    }
    
    /**
     * Standard equals function
     */
    @Override
    public boolean equals(Object other) {
        if (other == null || !(other instanceof HighScoreItem)) {
            return false;
        }

        if (other == this) {
            return true;
        }

        HighScoreItem that = (HighScoreItem) other;
        return that.name.equals(this.name) && that.date.equals(this.date) && that.score == this.score;
    }
    
    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 123456;
    }
    
    /**
     * Standard compare function, used for sorting
     */
    public int compareTo(HighScoreItem other) {
        if (other == null) {
            return 1;
        }
        
        int compa = Integer.compare(other.score, this.score);
        if (compa != 0) {
            return compa;
        } else {
            int compb = this.date.compareTo(other.date);
            if (compb != 0) {
                return compb;
            } else {
                return this.name.compareTo(other.name);
            }
        }
    }
    
    /**
     * Returns the name
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the date
     * @return the date
     */
    public String getDate() {
        return date;
    }
    /**
     * Returns the score
     * @return the score
     */
    public int getScore() {
        return score;
    }
}
