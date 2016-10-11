package com.group66.game.helpers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class HighScoreItemTest {
    
    @Test
    public void createTest() {
        new HighScoreItem("Alice","2016-10-01",100);
    }
    
    @Test
    public void compareTest() {
        HighScoreItem hsi1 = new HighScoreItem("Alice","2016-10-01",100);
        HighScoreItem hsi2 = new HighScoreItem("Bob","2016-10-01",100);
        HighScoreItem hsi3 = new HighScoreItem("Alice","2016-01-01",100);
        HighScoreItem hsi4 = new HighScoreItem("Alice","2016-10-01",200);
        HighScoreItem hsi5 = new HighScoreItem("Alice","2016-10-01",100);
        
        assertNotEquals(hsi1, hsi2);
        assertNotEquals(hsi1, hsi3);
        assertNotEquals(hsi1, hsi4);
        assertEquals(hsi1, hsi5);
        assertNotEquals(hsi2, hsi3);
        assertNotEquals(hsi2, hsi4);
        assertNotEquals(hsi3, hsi4);
    }
    
    @Test
    public void sortTest1() {
        HighScoreItem hsi1 = new HighScoreItem("Alice","2016-10-04",200);
        HighScoreItem hsi2 = new HighScoreItem("Bob","2016-10-03",100);
        HighScoreItem hsi3 = new HighScoreItem("Charlie","2016-10-02",300);
        HighScoreItem hsi4 = new HighScoreItem("Aaron","2016-10-01",100);
        
        SortedSet<HighScoreItem> ss = new TreeSet<HighScoreItem>();
        ss.add(hsi1);
        ss.add(hsi2);
        ss.add(hsi3);
        ss.add(hsi4);
        
        Iterator<HighScoreItem> iter = ss.iterator();
        assertEquals(iter.next(),hsi3);
        assertEquals(iter.next(),hsi1);
        assertEquals(iter.next(),hsi4);
        assertEquals(iter.next(),hsi2);
        assertFalse(iter.hasNext());
    }
    
    @Test
    public void sortTest2() {
        HighScoreItem hsi1 = new HighScoreItem("Alice","2016-10-01",100);
        HighScoreItem hsi2 = new HighScoreItem("Bob","2016-10-01",100);
        HighScoreItem hsi3 = new HighScoreItem("Alice","2016-01-01",100);
        HighScoreItem hsi4 = new HighScoreItem("Alice","2016-10-01",200);
        HighScoreItem hsi5 = new HighScoreItem("Alice","2016-10-01",100);
        
        assertTrue(hsi1.compareTo(hsi2) < 0); //alice precedes bob
        assertTrue(hsi1.compareTo(hsi3) > 0); //later date
        assertTrue(hsi1.compareTo(hsi4) > 0); //lower score
        assertTrue(hsi1.compareTo(hsi5) == 0); //exaclty the same
        assertTrue(hsi2.compareTo(hsi1) > 0); //bob is later
        assertTrue(hsi2.compareTo(hsi3) > 0); //later date
        assertTrue(hsi2.compareTo(hsi4) > 0); //lower score
        assertTrue(hsi2.compareTo(hsi5) > 0); //bob is later
        assertTrue(hsi3.compareTo(hsi1) < 0); //earlier date
        assertTrue(hsi3.compareTo(hsi2) < 0); //earlier date
        assertTrue(hsi3.compareTo(hsi4) > 0); //lower score
        assertTrue(hsi3.compareTo(hsi5) < 0); //earlier date
        assertTrue(hsi4.compareTo(hsi1) < 0); //higher score
        assertTrue(hsi4.compareTo(hsi2) < 0); //higher score
        assertTrue(hsi4.compareTo(hsi3) < 0); //higher score
        assertTrue(hsi4.compareTo(hsi5) < 0); //higher score
        assertTrue(hsi5.compareTo(hsi1) == 0); //exacly the same
        assertTrue(hsi5.compareTo(hsi2) < 0); //alice is first
        assertTrue(hsi5.compareTo(hsi3) > 0); //later date
        assertTrue(hsi5.compareTo(hsi4) > 0); //lower score
    }
}
