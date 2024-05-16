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

    /**
     * Initialises a new Energy object before each test is executed.
     * This ensures that each test starts with a fresh instance of Energy,
     * with the energy level set to its initial value (10).
     */
    @Before
    public void initialise() {
        energy = new Energy();
    }

    /**
     * Tests decreaseEnergy method to ensure it decreases the energy level correctly.
     * Checks the energy level decreases by the specified amount and doesn't fall below zero.
     */
    @Test
    public void decreaseEnergyTest() {
        assertEquals((Integer) 10, energy.getEnergy());
        energy.decreaseEnergy(4);
        assertEquals((Integer) 6, energy.getEnergy());
    }

    /**
     * Tests increaseEnergy method to ensure it correctly increases the energy level.
     * Checks the energy level increases by the specified amount and doesn't exceed the max cap of 10.
     */
    @Test
    public void increaseEnergyTest() {
        energy.decreaseEnergy(4); // Decreases energy to allow for an increase
        energy.increaseEnergy(2);
        assertEquals((Integer) 8, energy.getEnergy());
        energy.increaseEnergy(2);
        assertEquals((Integer) 10, energy.getEnergy());
    }

    /**
     * Tests getTextureName method to ensure it returns the correct texture path based on the current energy level.
     * Checks the texture name updates correctly as energy level changes.
     */
    @Test
    public void updateTextureNameTest() {
        assertEquals("energy/energy_10.png", energy.getTextureName());
        energy.decreaseEnergy(4);
        assertEquals("energy/energy_6.png", energy.getTextureName());
        energy.increaseEnergy(2);
        assertEquals("energy/energy_8.png", energy.getTextureName());
    }
}
