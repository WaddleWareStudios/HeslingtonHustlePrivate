package io.HeslingtonHustle.tests;

import com.main.utils.Energy;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests the Energy class
 */
public class EnergyTest {

    private Energy energy;

    @Before
    public void initialise() {
        energy = new Energy();
    }

    @Test
    public void decreaseEnergyTest() {
        assertEquals((Integer) 10, energy.getEnergy());
        energy.decreaseEnergy(4);
        assertEquals((Integer) 6, energy.getEnergy());
    }

    @Test
    public void updateTexture() {

    }
}
