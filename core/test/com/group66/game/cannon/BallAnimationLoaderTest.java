package com.group66.game.cannon;

import org.junit.Test;

public class BallAnimationLoaderTest {
    
    BallAnimationLoader ballAnimationLoader = new BallAnimationLoader();

    @Test
    public void testLoad() {
        BallAnimationLoader.load();
    }

    @Test
    public void testGetBluePopAnimation() {
        BallAnimationLoader.getBluePopAnimation();
    }

    @Test
    public void testGetGreenPopAnimation() {
        BallAnimationLoader.getGreenPopAnimation();
    }

    @Test
    public void testGetRedPopAnimation() {
        BallAnimationLoader.getRedPopAnimation();
    }

    @Test
    public void testGetYellowPopAnimation() {
        BallAnimationLoader.getYellowPopAnimation();
    }

    @Test
    public void testDispose() {
        BallAnimationLoader.dispose();
    }

}
