package com.group66.game.helpers;

public class HighScoreItem implements Comparable<HighScoreItem> {
    public final String name, date;
    public final int score;
    
    public HighScoreItem(String name, String date, int score) {
        this.name = name;
        this.date = date;
        this.score = score;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof HighScoreItem)){
            return false;
        }

        HighScoreItem that = (HighScoreItem) other;
        return that.name.equals(this.name) && that.date.equals(this.date) && that.score == this.score;
    }
    
    public int compareTo(HighScoreItem other) {
        int a = Integer.compare(this.score, other.score);
        if(a != 0) {
            return a;
        } else {
            int b = this.date.compareTo(other.date);
            if(b != 0) {
                return b;
            } else {
                return this.name.compareTo(other.name);
            }
        }
    }
}
