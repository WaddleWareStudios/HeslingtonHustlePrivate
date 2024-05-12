package io.HeslingtonHustle.tests;

import com.main.utils.Leaderboards;
import org.junit.Before;

public class LeaderBoardTests
{
    private Leaderboards leaderboards;
    @Before
    public void initialise() {
        leaderboards = new Leaderboards("test.csv");
    }
}
