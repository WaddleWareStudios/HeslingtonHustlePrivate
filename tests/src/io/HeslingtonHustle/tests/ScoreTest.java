package io.HeslingtonHustle.tests;

import static org.junit.Assert.*;

import com.main.utils.Score;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the Score class
 */
public class ScoreTest {

    private Score score;

    /**
     * Initialises Score
     */
    @Before
    public void initialise() {
        score = new Score();
    }

    /**
     * Tests if the initial score is 0
     */
    @Test
    public void testInitialScore() {
        assertEquals(0, score.getScore());
    }

    /**
     * Tests studying.
     */
    @Test
    public void testStudy() {
        score.study(3, "Comp_sci_door");
        assertEquals(3, score.getStudyCount());
    }

    /**
     * Tests eating.
     */
    @Test
    public void testEat() {
        score.eat(12); // Eat at 12pm
        assertEquals(1, score.getMealCount());
    }

    /**
     * Tests recreational activity.
     */
    @Test
    public void testDoRecActivity() {
        score.doRecActivity("Feed the Ducks");
        assertEquals(1, score.getRecreationCount());
    }

    /**
     * Tests a calculating score based on various activities.
     */
    @Test
    public void testCalculateScore() {
        score.study(4, "Comp_sci_door");
        score.eat(12);
        score.doRecActivity("Feed the Ducks");

        assertEquals(27, score.calculateScore());
    }

    /**
     * Tests resetting daily counters.
     */
    @Test
    public void testResetDailyCounters() {
        score.study(3, "Comp_sci_door");
        score.eat(12);
        score.doRecActivity("Feed the Ducks");

        score.resetDailyCounters();

        assertEquals(0, score.getStudyCount());
        assertEquals(0, score.getMealCount());
        assertEquals(0, score.getRecreationCount());
        assertFalse(score.hasMissedStudy());
    }

    /**
     * Tests multiple activities
     */
    @Test
    public void testMultipleActivities() {
        score.study(3, "Comp_sci_door");
        score.eat(12);
        score.doRecActivity("Feed the Ducks");

        assertEquals(3, score.getStudyCount());
        assertEquals(1, score.getMealCount());
        assertEquals(1, score.getRecreationCount());
    }
}
