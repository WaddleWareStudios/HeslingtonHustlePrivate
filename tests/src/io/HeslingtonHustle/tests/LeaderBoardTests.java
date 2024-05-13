package io.HeslingtonHustle.tests;

import com.main.utils.Leaderboards;
import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
Tests the Leaderboards class
 */
public class LeaderBoardTests
{
    private Leaderboards leaderboards;

    private int countEntries(Leaderboards.Entry[] entries) {
        int count = 0;
        for (Leaderboards.Entry x : entries) {
            if (x != null) {
                count += 1;
            }
        }
        return count;
    }

    /**
     * Initialises a Leaderboard with csv name 'test.csv'
     */
    @Before
    public void initialise() {
        leaderboards = new Leaderboards("test.csv");
    }

    /**
     * Erases the file for new testing after each test
     */
    @After
    public void eraseFile() {
        // Delete the test file after test methods are completed
        File file = new File("test.csv");
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Test if doesPlaceTop10 method accurately determines a score belonging in the top 10
     */
    @Test
    public void testDoesTop10_true() {
        assertTrue(leaderboards.doesPlaceT10(1000));
    }

    /**
     * Test if doesPlaceTop10 method accurately determines a score not belonging in the top 10
     */
    @Test
    public void testDoesTop10_false() {
        for (int i=0; i<10; i++) {
            leaderboards.registerResult(i+100, "Player"+i);
        }
        assertFalse(leaderboards.doesPlaceT10(0));
    }

    /**
     * Test if readFromSaved successfully reads from an empty csv.
     */
    @Test
    public void testReadFromSaved_emptyFile() {
        leaderboards = new Leaderboards("test.csv");
        assertEquals(0, countEntries(leaderboards.getEntries()));
    }

    /**
     * Test if readFromSaved successfully reads from a csv already containing values.
     */
    @Test
    public void testReadFromSaved_nonEmptyFile() {
        leaderboards.registerResult(100, "Player1");
        leaderboards.registerResult(200, "Player2");
        Leaderboards.Entry[] entries = leaderboards.getEntries();

        assertEquals(2, countEntries(entries));
        assertEquals("Player2", entries[0].name);
        assertEquals((Integer) 200, entries[0].score);
        assertEquals("Player1", entries[1].name);
        assertEquals((Integer) 100, entries[1].score);
    }

    /**
     * Test if writeToFile method successfully writes new scores to the leaderboards csv file
     */
    @Test
    public void testWriteToFile() {
        leaderboards.registerResult(100, "Player1");
        leaderboards.registerResult(200, "Player2");
        leaderboards.writeToFile();
        Leaderboards.Entry[] entries = leaderboards.getEntries();

        assertEquals(2, countEntries(entries));
        assertEquals("Player2", entries[0].name);
        assertEquals((Integer) 200, entries[0].score);
        assertEquals("Player1", entries[1].name);
        assertEquals((Integer) 100, entries[1].score);
    }

    /**
     * Test that registerResult method successfully adds a new player and score to the leaderboard
     */
    @Test
    public void testRegisterResult_RegisterNew() {
        leaderboards.registerResult(100, "James");
        Leaderboards.Entry[] entry = leaderboards.getEntries();

        assertEquals(1, countEntries(entry));
        assertEquals("James", entry[0].name);
        assertEquals((Integer) 100, entry[0].score);
    }

    /**
     * Test that registerResult method successfully replaces an existing player's improved score.
     */
    @Test
    public void testRegisterResult_existingScoreHigher() {
        leaderboards.registerResult(200, "James");
        Leaderboards.Entry[] entry = leaderboards.getEntries();

        assertEquals(1, countEntries(entry));
        assertEquals("James", entry[0].name);
        assertEquals((Integer) 200, entry[0].score);
    }

    /**
     * Test that registerResult method successfully rejects a player's score if they already have a score on
     * a leaderbaord that is not full
     */
    @Test
    public void testRegisterResult_existingScoreLower() {
        leaderboards.registerResult(50, "James");
        Leaderboards.Entry[] entry = leaderboards.getEntries();

        assertEquals(1, countEntries(entry));
        assertEquals("James", entry[0].name);
        assertEquals((Integer) 200, entry[0].score);
    }

    /**
     * Test that registerResult method successfully orders 10 scores that are added to the leaderboard. 
     */
    @Test
    public void testRegisterResult_10ScoresOrdered() {
        Random random = new Random();

        for (int i=1; i < 11; i++) {
            leaderboards.registerResult(random.nextInt(100), "Player" + i);
        }

        int prevScore = 9999999; //arbitrary large value
        for (Leaderboards.Entry entry : leaderboards.getEntries()) {
            assertTrue("Failed as "+entry.score+" is greater than "+prevScore, entry.score <= prevScore);
            prevScore = entry.score;
        }
    }
}
