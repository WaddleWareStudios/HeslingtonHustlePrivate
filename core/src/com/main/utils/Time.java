package com.main.utils;

public class Time
{
    public static final float GAME_DAY_LENGTH_IN_SECONDS    = 60f; // Added code
    public static final float SECONDS_PER_GAME_HOUR         = GAME_DAY_LENGTH_IN_SECONDS / 16;
    private float timeEl;

    public Time()
    {
        timeEl = 0;
    }
    public void reset()
    {
        timeEl = 0;
    }
    public void incTimeElapsed(float to_add)
    {
        timeEl += to_add;
    }
    public float getTimeEl() {
        return timeEl;
    }
    public int getTimeHours()
    {
        return 8 + (int)(timeEl / SECONDS_PER_GAME_HOUR);
    }
}
