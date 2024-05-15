package io.HeslingtonHustle.tests;

import static org.junit.Assert.*;

import com.main.utils.Score;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

        String[] studLocations =  score.getStudyLocations().toArray(new String[0]);
        assertEquals("Comp_sci_door", studLocations[0]);
    }

    /**
     * Tests eating.
     */
    @Test
    public void testEat() {
        score.eat(12); // Eat at 12pm
        assertEquals(1, score.getMealCount());

        List<Integer> mealTimes = score.getMealTimes();
        assertEquals((Integer) 12, mealTimes.get(0));
    }

    /**
     * Tests recreational activity.
     */
    @Test
    public void testDoRecActivity() {
        score.doRecActivity(3, "Feed the Ducks");
        assertEquals(3, score.getRecreationCount());

        String[] recLocations =  score.getRecreationLocations().toArray(new String[0]);
        assertEquals("Feed the Ducks", recLocations[0]);
    }

    /**
     * Tests a calculating score based on various activities.
     */
    @Test
    public void testCalculateScore() {
        score.study(4, "Comp_sci_door");
        score.eat(12);
        score.doRecActivity(2, "Feed the Ducks");

        assertEquals(31, score.calculateScore());
    }

    /**
     * Tests resetting daily counters.
     */
    @Test
    public void testResetDailyCounters() {
        score.study(3, "Comp_sci_door");
        score.eat(12);
        score.doRecActivity(4, "Feed the Ducks");

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
        score.doRecActivity(1, "Feed the Ducks");

        assertEquals(3, score.getStudyCount());
        assertEquals(1, score.getMealCount());
        assertEquals(1, score.getRecreationCount());
    }

    /**
     * Test correct score is calculated after a full game run through
     */
    @Test
    public void testFUllGameScore() {
        int total_score = 0;

        for (int day = 0; day < 7; day++) {
            score.study(3, "Comp_sci_door");
            score.study(1, "Piazza");
            score.doRecActivity(2, "Feed the Ducks");
            score.doRecActivity(3, "Gym_door");
            score.eat(9);
            score.eat(1);
            score.eat(6);
            score.incrementSleep();

            total_score += score.calculateScore();
            score.resetDailyCounters();
        }

        total_score += score.checkStreaks();

        assertEquals(440, total_score);
    }

    /**
     * Test score is set to 0 after player fails to study for 2 days
     */
    @Test
    public void testFailedGameScore() {
        int total_score = 0;
        boolean hasFailed = false;

        for (int day = 0; day < 7; day++) {
            if (day != 2 && day != 4) {
                score.study(3, "Piazza_door");
            }
            if (score.hasMissedStudy()) { //check if player has already missed study for a day
                if (score.getStudyCount() == 0) { //check if the player hasn't studied today
                    hasFailed = true;
                }
            }

            if (!hasFailed) {
                total_score += score.calculateScore();
            }
            else {
                total_score = 0;
            }
            score.resetDailyCounters();
        }

        total_score += score.checkStreaks();
        
        assertEquals(0, total_score);
        assertTrue(hasFailed);
    }


    /**
     * Tests that the streaks are correctly awarded
     */
    @Test
    public void testStreaks() {
        for(int day = 0; day < 7; day++) {
            score.study(1, "Comp_sci_door");
            score.study(1, "Piazza");
            score.doRecActivity(1, "Feed_ducks");
            score.doRecActivity(1, "Gym_door");
            score.doRecActivity(1, "Visit_city");
            score.doRecActivity(1, "Ron_cooke_door");
            score.incrementSleep();
            score.resetDailyCounters();
        }
        score.checkStreaks();

        assertEquals("WaddleWare Representative\n" +
                        "Programmer\n" +
                        "Athlete\n" +
                        "Early Nights\n" +
                        "All Rounder\n" +
                        "Daily Routine\n",
                score.getStreaks());
    }
}