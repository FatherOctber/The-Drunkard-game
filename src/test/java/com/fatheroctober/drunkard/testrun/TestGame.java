package com.fatheroctober.drunkard.testrun;

import org.junit.Test;


public class TestGame extends AbstractIntegrationTest {

    @Test
    public void testPlayGame() throws Exception {
        int frames = 5000;
        while (frames > 0) {
            totalCommander.update();
            frames--;
        }
    }
}
