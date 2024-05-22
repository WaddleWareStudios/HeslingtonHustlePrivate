package com.main.utils;

/**
 * NEW FOR ASSESSMENT 2
 * Represents the time system for a game, where time is measured in game hours.
 * The class provides functionality to manipulate and retrieve game time in terms of elapsed seconds
 * and game hours, Each day starts at 8am.
 *
 * <p>A game day is defined as 60 real-time seconds, divided into 16 in-game hours, making each in-game hour
 * equivalent to 3.75 real-time seconds.</p>
 */
public class Time {
    public static final float GAME_DAY_LENGTH_IN_SECONDS    = 60f; // Total seconds for an in-game day
    public static final float SECONDS_PER_GAME_HOUR         = GAME_DAY_LENGTH_IN_SECONDS / 16; // Seconds per in-game hour
    private float timeElapsed; // Elapsed time in seconds

    /**
     * Constructs a new Time object with time initially set to 0.
     */
    public Time() {
        timeElapsed = 0;
    }

    /**
     * Resets the elapsed time to 0.
     */
    public void reset(){
        timeElapsed = 0;
    }

    /**
     * Increments the elapsed time by a specified number of seconds.
     *
     * @param toAdd the number of seconds to add to the elapsed time
     */
    public void incTimeElapsed(float toAdd) {
        if (toAdd>0){
        timeElapsed += toAdd;}
    }

    /**
     * Retrieves the total elapsed time in seconds.
     *
     * @return the elapsed time in seconds
     */
    public float getTimeElapsed() {
        return timeElapsed;
    }

    /**
     * Calculates and returns the current in-game hour.
     *
     * @return the current in-game hour, based on elapsed time
     */
    public int getTimeHours() {
        return 8 + (int)(timeElapsed / SECONDS_PER_GAME_HOUR);
    }
}
