package io.HeslingtonHustle.tests;

import com.main.utils.Time;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Tests the Time class
 */
public class TimeTest {
    private Time time;

    /**
     * initialise the environment for each test case.
     * This method is called before the execution of each test.
     */
    @Before
    public void initialise() {
        time = new Time();
    }

    /**
     * Test the initial state of Time.
     * Checks the time starts at 0.
     */
    @Test
    public void testInitialTime() {
        assertEquals(0, time.getTimeEl(), 0.001);
    }

    /**
     * Test Time classes reset functionality.
     * Checks the time is set back to 0 after being incremented.
     */
    @Test
    public void testReset() {
        time.incTimeElapsed(10);
        time.reset();
        assertEquals(0, time.getTimeEl(), 0.001);
    }

    /**
     * Test the increment time functionality.
     * Checks the elapsed time is correctly incremented.
     */
    @Test
    public void testIncrementTimeElapsed() {
        time.incTimeElapsed(5);
        assertEquals(5, time.getTimeEl(), 0.001);
        time.incTimeElapsed(15);
        assertEquals(20, time.getTimeEl(), 0.001);
    }

    /**
     * Tests calculation of game hours based on elapsed time.
     * Checks the hours are calculated correctly starting at 8am.
     */
    @Test
    public void testGetTimeHours() {
        time.incTimeElapsed(30); // Half an in-game day
        assertEquals(8 + 8, time.getTimeHours()); // Starting at 8am + half a day (8 hours)
    }

    /**
     * Tests Time class when a negative increment is applied.
     * Checks the elapsed time does not decrease below zero
     */
    @Test
    public void testNegativeTimeIncrement() {
        time.incTimeElapsed(-5);
        assertEquals(0, time.getTimeEl(), 0.001);
    }
}